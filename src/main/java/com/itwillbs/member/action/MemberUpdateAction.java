package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 호출 ");
		
		// 로그인 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DB에서 회원정보를 조회
		// MemberDAO 객체생성 - 회원정보 조회 메서드 호출
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMember(id);
		
		//dto.setAge(20-2);
		
		// request 영역에 저장
		request.setAttribute("dto", dto);
		//request.setAttribute("dto", dao.getMember(id));
		
		//session.setAttribute("dto", dto);
		
		
		// 페이지 이동(./member/updateForm.jsp)		
		forward.setPath("./member/updateForm.jsp"); 
		forward.setRedirect(false);
		
		return forward;
	}

}
