package kr.co.green.contact.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.PageInfoDTO;

import kr.co.green.board.util.Pagination;

import kr.co.green.contact.model.dto.DataSearchDTO;
import kr.co.green.contact.model.dto.UserDTO;
import kr.co.green.contact.model.mapper.ContactMapper;
@Service

public class ContactServiceImpl implements ContactService {

private final ContactMapper contactMapper;


public ContactServiceImpl(ContactMapper contactMapper) {
	
	this.contactMapper= contactMapper;
	
}
	@Override
	public Map<String,Object> contactlist(Pagination pagination,int currentPage,
					int listCount,int pageLimit,int boardLimit,DataSearchDTO searchDTO){
		
		PageInfoDTO pi = pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		List<UserDTO> posts = contactMapper.contactlist(pi, searchDTO);
		Map<String, Object> result = new HashMap<>();
		result.put("pi",pi);
		result.put("data", posts);
		
		return result;
	}


	@Override
	public int getTotalCount(DataSearchDTO SearchDTO) {
		
		return contactMapper.getTotalCount(SearchDTO);
	}
	
	
	@Override
	public int enroll(UserDTO userDTO) {
		
		int result = 0;
		result = contactMapper.enroll(userDTO); // contact_user 테이블에 insert
		// userNo에 시퀀스가 실행된 값이 들어있음
		
		if(result == 1) {
			result = contactMapper.askEnroll(userDTO); // contact_data 테이블에 insert
		}
	
		return result;
	}
	@Override
	public UserDTO detail(int userNo) {
		
		return  contactMapper.detail(userNo);
		
		
	}
	@Override
	public UserDTO applyForm(int userNo) {
		
		return contactMapper.applyForm(userNo);
		
	}
	@Override
	public UserDTO apply(UserDTO userDTO) {
		return contactMapper.apply(userDTO);
		
	}
	
	
	
	
	
	
}
