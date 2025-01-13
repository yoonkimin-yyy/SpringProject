package kr.co.green.board.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private int boardCount;
	private AuthorDTO authorDTO = new AuthorDTO();
	private FileDTO fileDTO = new FileDTO();
	
	
}
