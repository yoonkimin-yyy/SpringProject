package kr.co.green.board.model.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.PageInfoDTO;
import kr.co.green.board.model.dto.SearchDTO;
import kr.co.green.board.model.mapper.BoardMapper;
import kr.co.green.board.util.FileUpload;
import kr.co.green.board.util.Pagination;

@Service
public class BoardServiceImpl implements BoardService {
private final BoardMapper boardMapper;
private final FileUpload fu;


	public  BoardServiceImpl(BoardMapper boardMapper,FileUpload fu) {
		this.boardMapper= boardMapper;
		this.fu= fu;
		
	}
	@Override
	public Map<String,Object>list(Pagination pagination, int currentPage,int postCount,
			int pageLimit,int boardLimit,SearchDTO searchDTO){
		// 페이징 처리
		PageInfoDTO pi = pagination.getPageInfo(postCount,currentPage,pageLimit,boardLimit);
													
		// 페이지 따라서 필요한 게시글들만 SELECT
		List<BoardDTO> posts = boardMapper.list(pi, searchDTO);
		Map<String, Object> result = new HashMap<>();
		result.put("pi",pi);
		result.put("posts", posts);
		
		return result;
	}
	
	@Override
	public int getTotalCount(SearchDTO searchDTO) {
		return boardMapper.getTotalCount(searchDTO);
	}
	@Override
	public int enroll(BoardDTO boardDTO, MultipartFile file) {
		// 1. id가지고 no조회하기
		int result = 0;
		
		result = boardMapper.enroll(boardDTO);
		if(result == 1 && file != null && !file.isEmpty()) {
			try {
				fu.uploadFile(file,boardDTO.getFileDTO(),"free");
				boardMapper.enrollFile(boardDTO);
			} catch (IOException e) {
				e.printStackTrace();
			} // -- 예외처리 필수
		}
		
		return result;
	}
	@Override
	public BoardDTO detail(int boardNo) {
		int addViewCount = boardMapper.addViewCount(boardNo);
		if(addViewCount == 1) {
			// no가지고 file테이블에 데이터가 있는지
			// 데이터가 있으면 boardDTO.fileDTO에 넣고
			BoardDTO fileCheck = boardMapper.getFileInfo(boardNo);
			BoardDTO result;
			
			if(fileCheck != null) {
				fileCheck.setBoardNo(boardNo);
				result = boardMapper.detailFile(fileCheck);
				result.setFileDTO(fileCheck.getFileDTO());
			}else {
				result = boardMapper.detail(boardNo);
			}
			
			// free_board 테이블 다시 select해서 제목,내용 가져오기
			return result;
		}else {
			return null;
		}
	}
	@Override
	public BoardDTO updateForm(int boardNo) {
		return boardMapper.updateForm(boardNo);
	}
	@Override
	public int editForm(BoardDTO boardDTO,int memberNo,MultipartFile file) {
		// 게시글 no로 조회(SELECT)해서 글 작성자 no(author_no) 가져오기
		BoardDTO checkCount = boardMapper.editForm(boardDTO);
		
		// 글 작성자 no(checkCount)랑 로그인한 사용자의 no(memberNo)가 같으면
		if(checkCount.getAuthorDTO().getAuthorNo() == memberNo) {
			if(file != null && !file.isEmpty()) {
				BoardDTO fileCheck = boardMapper.getFileInfo(boardDTO.getBoardNo());
				String fileName = fileCheck.getFileDTO().getChangeName();
				String localPath = fileCheck.getFileDTO().getLOCAL_PATH();
				
				boardMapper.deleteFile(fileName);
				
				try {
					fu.deleteFile(localPath, "free", fileName);
					
					// 2 새로운 파일 업로드 및 insert
					fu.uploadFile(file, boardDTO.getFileDTO(), "free");
					boardMapper.enrollFile(boardDTO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			int result = boardMapper.update(boardDTO);
			return result;
		}else {
			return 0;
		}
		
	}
	
	@Override
	public int delete(BoardDTO boardDTO,int memberNo, String fileName) {
		
		BoardDTO deleteData = boardMapper.editForm(boardDTO);
		
		
		//1. 서버에 저장된 파일 삭제s
		//2. db 파일 테이블에서 삭제
		
		if(deleteData.getAuthorDTO().getAuthorNo() == memberNo) {
			
			try {
				fu.deleteFile(boardDTO.getFileDTO().getLOCAL_PATH(), "free", fileName);
				boardMapper.deleteFile(fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int result= boardMapper.delete(boardDTO);
			return result;
		}else {
			return 0;
		}
	}
}
