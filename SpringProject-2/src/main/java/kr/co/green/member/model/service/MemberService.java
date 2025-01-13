package kr.co.green.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.green.member.model.dto.MemberDTO;

public interface MemberService {
	
	
	
	
	
	
	
	public int signup(MemberDTO memberDTO);
	public MemberDTO signin(MemberDTO memberDTO);
	boolean checkId(String memberId);
	
}
