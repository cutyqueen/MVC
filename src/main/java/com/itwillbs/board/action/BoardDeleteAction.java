package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSMoveFunction;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardDeleteAction_execute() 호출 ");
		
		// 전달정보 저장(bno,pass,pageNum)
		BoardDTO bdto = new BoardDTO();
		bdto.setBno(Integer.parseInt(request.getParameter("bno")));
		bdto.setPass(request.getParameter("pass"));
		
		String pageNum = request.getParameter("pageNum");
		
		// 디비에 가서 해당 글정보를 삭제 
		BoardDAO bdao = new BoardDAO();
		int result = bdao.deleteBoard(bdto);
		
		if(result == 0) {
			JSMoveFunction.alertBack(response, "비밀번호 오류!");
			return null;
		}
		
		if(result == -1) {
			JSMoveFunction.alertBack(response, "게시판 글없음!");
			return null;
		}
		
		JSMoveFunction.alertLocation(response, "삭제 완료!", "./BoardList.bo?pageNum="+pageNum);
		
		return null;
	}

}
