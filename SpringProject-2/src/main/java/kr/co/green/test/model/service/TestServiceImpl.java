package kr.co.green.test.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.green.test.model.mapper.TestMapper;
import kr.co.green.test.post.dto.PostDTO;
import kr.co.green.test.post.dto.TestPageInfoDTO;
import kr.co.green.test.post.dto.TestSearchDTO;
import kr.co.green.test.util.TestPagination;

@Service


public class TestServiceImpl implements TestService{

	private  final TestMapper testMapper;
	
	public TestServiceImpl(TestMapper testMapper) {
		this.testMapper = testMapper;
	}
	
	@Override
	public Map<String,Object> list(TestPagination testPagination,int currentPage,
			int listCount,int pageLimit,int boardLimit,TestSearchDTO testSearchDTO){
		
		TestPageInfoDTO pi= testPagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		List<PostDTO> posts = testMapper.list(pi, testSearchDTO);
		Map<String, Object> result = new HashMap<>();
		result.put("pi",pi);
		result.put("post", posts);
		
		return result;
		
	}
	
	
	
	@Override
	public int getTotalCount(TestSearchDTO testSearchDTO) {
		
		return testMapper.getTotalCount(testSearchDTO);
	}
}
