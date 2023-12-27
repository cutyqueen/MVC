package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSMoveFunction;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateProAction_execute() 호출 ");
		
		// 한글처리 => 필터사용(생략)
		
		// 전달정보 저장(bno,name,subject,content,pass, pageNum)
		// => BoardDTO 객체에 저장
		BoardDTO bdto = new BoardDTO();
		bdto.setBno(Integer.parseInt(request.getParameter("bno")));
		bdto.setName(request.getParameter("name"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		bdto.setPass(request.getParameter("pass"));
		
		String pageNum = request.getParameter("pageNum");
		
		// 전달받은 정보를 사용해서 기존의 정보 수정
		BoardDAO bdao = new BoardDAO();
		int result = bdao.updateBoard(bdto);
		
		if(result == 0) {
			//JS사용 페이지 이동
			JSMoveFunction.alertBack(response, " 비밀번호 오류! ");
			return null;
		}
		
		if(result == -1) {
			JSMoveFunction.alertBack(response, " 게시판 글 없음! ");
			 return null;
		}
		
		//result == 1
		JSMoveFunction.alertLocation(response, "수정 성공!", "./BoardList.bo?pageNum="+pageNum);
		
		return null;
	}

}
