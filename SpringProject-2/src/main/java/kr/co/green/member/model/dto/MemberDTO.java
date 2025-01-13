package kr.co.green.member.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private int no;
	private String name;
	private String id;
	private String password;
	private String confirmPassword;
	private String status;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	
	
}
