package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateAction_execute() 호출 ");
		
		// 전달정보 bno/pageNum 받기
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// 기존에 저장된 글정보를 가져오기
		BoardDAO bdao = new BoardDAO();
		
		BoardDTO bdto = bdao.getBoard(bno);
		
		// request 영역에 정보저장후 전달
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동준비
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
