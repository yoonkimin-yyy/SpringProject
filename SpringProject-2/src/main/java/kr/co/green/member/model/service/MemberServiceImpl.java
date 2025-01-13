package kr.co.green.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	public  MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public int signup(MemberDTO memberDTO) {
		
		//1. 중복 검사
		int checkId = memberMapper.checkId(memberDTO.getId());
		if(checkId != 1) {
			//2. 패스워드 암호화
			String encodePassword = passwordEncoder.encode(memberDTO.getPassword());
			// 암호화를 한 패스워드 다시 DTO에 주입
			memberDTO.setPassword(encodePassword);
		}else {
			return 0;
		}
		
		
		//3. insert
		return memberMapper.signup(memberDTO);
	}
	@Override
	public MemberDTO signin(MemberDTO memberDTO) {
		MemberDTO loginUser = memberMapper.signin(memberDTO);
		
		// 첫번쨰 매개변수에 사용자가 입력한 패스워드
		// 두번쨰 매개변수에 DB에서 가져온(암호화된) 패스워드
		if(passwordEncoder.matches(memberDTO.getPassword(),loginUser.getPassword())) {
			return loginUser;
		}else {
			return null;
		}
		
	}
	@Override
	public boolean checkId(String memberId) {
		
		System.out.println(memberId);
		int result = memberMapper.checkId(memberId);
		System.out.println(result);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	
}
