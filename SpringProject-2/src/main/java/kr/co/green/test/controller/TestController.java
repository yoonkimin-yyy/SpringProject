package kr.co.green.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.green.test.model.service.TestServiceImpl;
import kr.co.green.test.post.dto.PostDTO;
import kr.co.green.test.post.dto.TestPageInfoDTO;
import kr.co.green.test.post.dto.TestSearchDTO;
import kr.co.green.test.util.TestPagination;

@Controller
@RequestMapping("/test")

public class TestController {

	private final TestServiceImpl testService;
	private final TestPagination testPagination;
	
	
	
	public TestController(TestServiceImpl testService,TestPagination testPagination) {
		
		this.testService = testService;
		this.testPagination = testPagination;
	}
	
	
	@GetMapping("/list")
	public String list(@RequestParam(value="currentPage",defaultValue="1") int currentPage,
						Model model,
						@ModelAttribute(value="testSearchDTO") TestSearchDTO testSearchDTO) {
		
		
		int postCount = testService.getTotalCount(testSearchDTO);
				
		int pageLimit = 5;
		
		int boardLimit = 10;
		
		
		Map<String,Object> result = testService.list(testPagination, currentPage,
													postCount, pageLimit, boardLimit, testSearchDTO);
		
		TestPageInfoDTO piResult = (TestPageInfoDTO)result.get("pi");
		List<PostDTO> testResult= (List<PostDTO>) result.get("post");
		
		model.addAttribute("post", testResult);
		model.addAttribute("pi", piResult );
		
		
		
		return "test/list";
	}
	
	
	
}
