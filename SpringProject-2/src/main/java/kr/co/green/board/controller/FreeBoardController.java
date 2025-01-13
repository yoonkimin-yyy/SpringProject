package kr.co.green.board.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.green.board.model.dto.BoardDTO;
import kr.co.green.board.model.dto.FileDTO;
import kr.co.green.board.model.dto.PageInfoDTO;
import kr.co.green.board.model.dto.SearchDTO;
import kr.co.green.board.model.service.BoardServiceImpl;
import kr.co.green.board.util.Pagination;

@Controller
@RequestMapping("/board/free")
public class FreeBoardController {
private final BoardServiceImpl boardService;
private final Pagination pagination;
	public FreeBoardController(BoardServiceImpl boardService,Pagination pagination) {
		this.boardService=boardService;
		this.pagination = pagination;
	}
	
	
	// 요청 url : /board/free/list?currentPage=3
	// 요청 url : /board/free/list -- 첫번쨰 페이지의 값을 가져올거다.
	@GetMapping("/list")
	public String list(	@RequestParam(value="currentPage",defaultValue="1")int currentPage
						,Model model,
						@ModelAttribute SearchDTO searchDTO) {
		
		//1. 전체 게시글 수
		int postCount = boardService.getTotalCount(searchDTO);
	
		//2. 보여질 페이지 수
		int pageLimit = 10;
		
		//3. 한 페이지에 들어갈 게시글 수
		int boardLimit = 5;
		
		
		// 1. pageInfo 대신 currentpage,postcount, pagelimit,boardlimit 다 인자로 작성
		// 2. 배열로 만들어서 넣어주는 방법  -- int[] paginationParams ={urrentpage,postcount, pagelimit,boardlimit}
		Map<String,Object> result = boardService.list( pagination,currentPage,postCount,pageLimit,
				boardLimit,searchDTO);
		PageInfoDTO piResult = (PageInfoDTO) result.get("pi");
		List<BoardDTO> postsResult = (List<BoardDTO>) result.get("posts");
		
		
		for(BoardDTO item : postsResult) {
			System.out.println(item.getBoardTitle());
			System.out.println(item.getAuthorDTO().getAuthorId());
		}
		
		model.addAttribute("posts", postsResult);
		model.addAttribute("pi", piResult);
		
		return "board/free/list";
		
		
	}
	@GetMapping("/enrollForm")
	public String enrollForm(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		return "/board/free/enroll";
	}
	@PostMapping("enroll")
	public String enroll(BoardDTO boardDTO,
						@RequestParam("file") MultipartFile file,
						@SessionAttribute("memberNo") int memberNo) {
		
		boardDTO.setFileDTO(new FileDTO());
		boardDTO.getAuthorDTO().setAuthorNo(memberNo);
		
		
		int result = boardService.enroll(boardDTO,file);
		return "redirect:/board/free/list";
		
		
		
	}
	@GetMapping("/detail")
	public String detail(@RequestParam(value = "boardNo") int boardNo,
				Model model) {
		// 선택한 게시글에 대한 정보를 불러와야함
		// 제목,내용,작성자, 작성일, 조회수
		
		BoardDTO result= boardService.detail(boardNo);
		model.addAttribute("text", result);
		
		
		
		return "/board/free/detail";
	}
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam(value="boardNo")int boardNo,
								Model model) {
		BoardDTO result = boardService.updateForm(boardNo);
		model.addAttribute("test2", result);
		return "board/free/update";
	}
	@PostMapping("/update")
	public String update(BoardDTO boardDTO) {
		System.out.println(boardDTO.getBoardTitle());
		System.out.println(boardDTO.getBoardNo());
		System.out.println(boardDTO.getBoardContent());
		
		return "";
	}
	
	
	
	
	@PostMapping("/editForm")
	public String editForm(BoardDTO boardDTO,@SessionAttribute("memberNo") int memberNo,
							@RequestParam("file") MultipartFile file,
							Model model, RedirectAttributes redirectAttributes) {
		
		int result = boardService.editForm( boardDTO,memberNo,file);
		
		
		// 컨트롤러에서 컨트롤러로 데이터 넘기는 방법
		// 1. redirectAttributes  - 일회용 데이터 전달할때 사용(데이터가 한번 사용후 사라짐) , redirect로 데이터 전달시 주로 사용
		// 2. model-forward 사용
		// 3. GET 요청일 경우 쿼리스트링 사용
		// 4. 세션 사용
		 redirectAttributes.addAttribute("boardNo", boardDTO.getBoardNo());
		//redirectAttributes.addFlashAttribute("no", boardDTO.getBoardNo());
		return "redirect:/board/free/detail";
	}
	@PostMapping("/delete")
	public String delete(BoardDTO boardDTO,
						@RequestParam(value="fileName", defaultValue="none")String fileName,
							@SessionAttribute("memberNo")int memberNo,
							Model model	) {
		int result = boardService.delete(boardDTO,memberNo,fileName);
		
		return "redirect:/board/free/list";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
