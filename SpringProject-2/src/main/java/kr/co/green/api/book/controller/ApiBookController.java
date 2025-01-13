package kr.co.green.api.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.green.api.book.dto.BookDTO;

@RestController
@RequestMapping("/api/books")
public class ApiBookController {
	
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		
		  BookDTO book = new BookDTO();
	      book.setId(1);
	      book.setTitle("자바의 정석");
	      book.setAuthor("남궁성");
	      
	      return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	
   
   @GetMapping("/{title}")
   public ResponseEntity<?> selectBook(@PathVariable("title") String title) {
      System.out.println("사용자가 입력한 책 제목 : " + title);
      
      // 이 뒤로 책 제목으로 SELECT해오는 로직 작성
      // Service -> Mapper -> MyBatis -> Oracle
      BookDTO book = new BookDTO();
      book.setId(1);
      book.setTitle("자바의 정석");
      book.setAuthor("남궁성");
      
      if(book.getTitle().equals(title)) {
         return ResponseEntity.ok(book);
      } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(title+"을 찾을 수 없습니다");
      }
      
//      return new ResponseEntity<>(book, HttpStatus.OK);
   }
}





