package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute 호출 ");
		
		// 전달정보 저장 (bno,pageNum,search(생략))
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO(); 
		// BoardDAO 객체  - 특정 글 조회수 1증가()
		bdao.updateReadcount(bno);
		//      "         - 특정 글의 정보를 가져오는()
		BoardDTO dto = bdao.getBoard(bno);
		
		// 글정보를 request 영역에 저장
		request.setAttribute("dto", dto);
		// pageNum값도 request 영역에 저장 (view 페이지에서 리스트갈때 사용)
		request.setAttribute("pageNum",pageNum);
		
		// 페이지 이동준비 (./board/content.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
