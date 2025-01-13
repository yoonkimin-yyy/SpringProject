package kr.co.green.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.green.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	 public int signup(MemberDTO memberDTO);
	 public MemberDTO signin(MemberDTO memberDTO);
	 int checkId(String memberId);
}
