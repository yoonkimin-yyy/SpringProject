package kr.co.green.contact.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import kr.co.green.board.model.dto.PageInfoDTO;

import kr.co.green.contact.model.dto.DataSearchDTO;
import kr.co.green.contact.model.dto.UserDTO;



@Mapper
public interface ContactMapper {

	 public int getTotalCount(DataSearchDTO SearchDTO);
	 List<UserDTO> contactlist(@Param("pi")PageInfoDTO pi,
				@Param("searchDTO") DataSearchDTO searchDTO);
	
	 public int enroll(UserDTO userDTO);
	 public int askEnroll(UserDTO userDTO);
	 UserDTO detail(int userNo);
	 UserDTO applyForm(int userNo);
	public UserDTO apply(UserDTO userDTO);
	 
}
