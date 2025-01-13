package kr.co.green.test.model.service;

import java.util.Map;

import kr.co.green.test.post.dto.TestSearchDTO;
import kr.co.green.test.util.TestPagination;

public interface TestService {

	
	Map<String,Object> list(TestPagination testPagination,int currentPage,
						int listCount,int pageLimit,int boardLimit,TestSearchDTO testSearchDTO);
	
	public int getTotalCount(TestSearchDTO testSearchDTO);
}
