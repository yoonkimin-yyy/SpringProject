package kr.co.green.contact.model.service;

import java.util.Map;


import kr.co.green.board.util.Pagination;
import kr.co.green.contact.model.dto.DataSearchDTO;
import kr.co.green.contact.model.dto.UserDTO;

public interface ContactService {

	
	
	public int getTotalCount(DataSearchDTO SearchDTO);
	Map<String,Object> contactlist(Pagination pagination,int currentPage,
									int listCount,int pageLimit,int boardLimit,DataSearchDTO searchDTO);
	
	public int enroll(UserDTO userDTO);
	public UserDTO detail(int userNo);
	public	UserDTO applyForm(int userNo);
	UserDTO apply(UserDTO userDTO);
}
