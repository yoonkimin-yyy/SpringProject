package kr.co.green.contact.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private int userNo;
	private String userEmail;
	private String userName;
	private String answer;
	private String writeDate;
	private int contactCount;
	private ApplyAnswerDTO applyAnswer = new ApplyAnswerDTO();
	private AskAnswerDTO askAnswer = new AskAnswerDTO();
	
	
	
}
