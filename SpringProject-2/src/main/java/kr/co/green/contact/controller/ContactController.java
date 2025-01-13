package kr.co.green.contact.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.PageInfoDTO;
import kr.co.green.board.util.Pagination;
import kr.co.green.contact.model.dto.DataSearchDTO;
import kr.co.green.contact.model.dto.UserDTO;
import kr.co.green.contact.model.service.ContactServiceImpl;

@Controller
@RequestMapping("/contact")


public class ContactController {
	private final ContactServiceImpl contactService;
	private final Pagination pagination;
	
	public ContactController(ContactServiceImpl contactService, Pagination pagination) {
		this.contactService = contactService;
		this.pagination = pagination;
	}
	
	

	@GetMapping("/contactList")
	public String contactList(@RequestParam(value="currentPage",defaultValue="1")int currentPage,
							Model model,
							@ModelAttribute(value="searchDTO") DataSearchDTO searchDTO) {
		
		// 1.전체 게시글 수
		int postCount = contactService.getTotalCount(searchDTO);
		// 2. 보여질 페이지 수
		int pageLimit = 5;
		
		// 3. 한페이지에 들어갈 게시글 수
		int boardLimit = 7;
		
		Map<String,Object> result = contactService.contactlist(pagination, currentPage,
															postCount, pageLimit, boardLimit, searchDTO);
		
		PageInfoDTO piResult = (PageInfoDTO) result.get("pi");
		List<UserDTO> dataResult = (List<UserDTO>) result.get("data");
	
		model.addAttribute("data", dataResult);
		model.addAttribute("pi", piResult);
		
		
		
		
		return "contact/list";
	}
	@GetMapping("/contactEnroll")
	public String contactEnroll() {
		
		return "contact/enroll";
	}
	
	@PostMapping("/enroll")
	public String enroll(UserDTO userDTO)
						 {
		// 만약에 안되면 여기서 필요한 값들 잘 가져오는지 sysout으로 찍어보기
		// 안가져오는거 있으면 타임리프(html)
		
		int result = contactService.enroll(userDTO);
		
		System.out.println(result); // 0
		
		return "redirect:/";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(value="userNo")int userNo,
								Model model) {
		
		UserDTO result = contactService.detail(userNo);
		model.addAttribute("text", result);
		
		return "/contact/detail";
	}
	
	@GetMapping("/apply/form")
	public String applyForm(@RequestParam(value="userNo")int userNo,
									Model model) {
		
		UserDTO result = contactService.detail(userNo);
		model.addAttribute("post", result);
		return "contact/apply";
		
	}
	
	
	
	@PostMapping("/apply")
	public String apply(UserDTO userDTO) {
		
		
		
		
		
		return "contact/list";
		
	}
	
	
	
	
}
