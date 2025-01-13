package kr.co.green.member.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.green.member.model.dto.MemberDTO;
import kr.co.green.member.model.service.MemberServiceImpl;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberServiceImpl memberService;
	
	
	public MemberController(MemberServiceImpl memberService) {
		this.memberService = memberService;
		//this.passwordEncoder = new BCryptPasswordEncoder();
		
	}
	
	@GetMapping("/signup/form")
	public String signupForm() {
		
		return "/member/sign_up";
		
	}
	@PostMapping("/signup")
	public String signup(MemberDTO memberDTO) {
		int result = memberService.signup(memberDTO);
		
		return "/member/sign_in";
	}
	@GetMapping("/signin/form")
	public String signinForm() {
		return "/member/sign_in";
	}
	@PostMapping("/signin")
	public String signin(MemberDTO memberDTO, HttpSession session) {
		
		MemberDTO loginUser = memberService.signin(memberDTO);
		
		if(loginUser != null) {
			session.setAttribute("memberNo", loginUser.getNo());
			session.setAttribute("memberId", loginUser.getId());
			session.setAttribute("memberName", loginUser.getName());
		}
		
		return "home";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@PostMapping("/checkId")
	@ResponseBody // view를 지나치지 않음 비동기 통신할떄 주로 사용된다.
	public String checkId(@RequestParam("memberId")String memberId) {
		boolean isDuplication = memberService.checkId(memberId);
		
		if(isDuplication) {
			return "true";
		}else {
			return "false";
		}
		
	}

}
