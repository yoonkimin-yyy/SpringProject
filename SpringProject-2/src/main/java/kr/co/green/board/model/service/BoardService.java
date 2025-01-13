package kr.co.green.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.PageInfoDTO;
import kr.co.green.board.model.dto.SearchDTO;
import kr.co.green.board.util.Pagination;

public interface BoardService {

	Map<String,Object>list(Pagination pagination,int currentPage,
			int listCount,int pageLimit,int boardLimit,SearchDTO searchDTO);
	public int getTotalCount(SearchDTO searchDTO);
	public int enroll(BoardDTO boardDTO,MultipartFile file);
	public BoardDTO detail(int boardNo);
	BoardDTO updateForm(int boardNo);
	
	int editForm(BoardDTO boardDTO,int memberNo,MultipartFile file);
	int delete(BoardDTO boardDTO,int memberNo,String fileName);
}
