package kr.co.green.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.PageInfoDTO;
import kr.co.green.board.model.dto.SearchDTO;
import kr.co.green.board.util.Pagination;

@Mapper
public interface BoardMapper {

	List<BoardDTO> list(@Param("pi")PageInfoDTO pi,
							@Param("searchDTO") SearchDTO searchDTO);
	 
	 public int getTotalCount(SearchDTO searchDTO);
	 public int enroll(BoardDTO boardDTO);
	 BoardDTO detail (int boardNo);

	int addViewCount(int boardNo);
	BoardDTO updateForm(int boardNo);
	
	
	BoardDTO editForm(BoardDTO boardDTO);
	int update(BoardDTO boardDTO);
	

	int delete(BoardDTO boardDTO);

	int enrollFile(BoardDTO boardDTO);

	BoardDTO getFileInfo(int boardNo);

	BoardDTO detailFile(BoardDTO fileCheck );

	void deleteFile(String fileName);
	
	
	
}
