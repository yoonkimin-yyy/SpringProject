package kr.co.green.board.util;

import org.springframework.stereotype.Component;

import kr.co.green.board.model.dto.PageInfoDTO;

@Component
public class Pagination {

	public PageInfoDTO getPageInfo(int listCount,int currentPage,
										int pageLimit, int boardLimit
										) {
		// <전체 페이지 수>
		// - listCount:300, boardList:5
		// - math.ceil은 소수점 올림처리를 해준다
		int maxPage = (int)(Math.ceil((double)listCount/boardLimit));
		// <현재 페이지가 속한 범위의 시작 페이지>
		// - currentPage = 16, pageLimit:10
		// 1. (currenPage-1) = 15
		// 2. (currentPage-1) / pageLimit = 1(int로 계산했기때문)
		// 3. (currentPage-1) / pageLimit * pageLimit = 1 x 10 = 10
		// 4. (currentPage-1) / pageLimit * pageLimit + 1 = 10+1 = 11
		int startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		//< 현재 페이지가 속한 범위의 끝 페이지 수>
		// 1. startPage + pageLimit = 11+10 =21
		// 2. startPage + pageLimit - 1 = 21-1=  20;
		int endPage = startPage+pageLimit-1;
		//
		// 1. (currentPage-1) = 15-1 = 14
		// 2. (currentPage-1) * boardLimit = 14 x 5 = 60
		// 3. listCount - (currentPage-1) * boardLimit  = 300-60=240;
		int row = listCount-(currentPage-1)*boardLimit;
		
		// 사용자가 1페이지를 보고있다
		// offset = 1, limit = 10
		// offset = 11, limit = 20
		int offset = (currentPage-1)*boardLimit+1;
		int limit = offset+boardLimit-1;
		
		if(endPage>maxPage) {
			endPage=maxPage;
		}
		return new PageInfoDTO(listCount, currentPage,pageLimit,boardLimit,
								maxPage,startPage,endPage,row,offset,limit);	
		
	}
	
	
}
