package kr.co.green.test.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.green.test.post.dto.PostDTO;
import kr.co.green.test.post.dto.TestPageInfoDTO;
import kr.co.green.test.post.dto.TestSearchDTO;

@Mapper
public interface TestMapper {

	List<PostDTO> list(@Param("pi")TestPageInfoDTO pi,
						@Param("testSearchDTO")TestSearchDTO testSearchDTO );
	
	public int getTotalCount(TestSearchDTO testSearchDTO);
}
