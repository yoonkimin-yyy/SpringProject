package kr.co.green.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	
	@GetMapping("/book/form")
	public String bookForm() {
		return "book/book_manage";
	}
	
	
	

	@RequestMapping("/")
//	public String home(Model model,HttpSession session) {
	public String home(Model model,
			@SessionAttribute(name="memberNo",required=false)Integer memberNo,
			@SessionAttribute(name="memberName",required=false)String memberName,
			@SessionAttribute(name="memberId",required=false)String memberId)	{
									
		// 세션에서 값을 가져오기
//		Object memberNo = session.getAttribute("memberNo");
//		Object memberName = session.getAttribute("memberName");
//		Object memberId = session.getAttribute("memberId");
		
		model.addAttribute("memberNo", memberNo);
		model.addAttribute("memberName", memberName);
		model.addAttribute("memberId", memberId);
		
		
		return "home";
	}
}




