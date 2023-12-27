package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.itwillbs.util.JSMoveFunction;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateProAction_execute() 호출 ");
		
		// 로그인 세션제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달된 수정정보 저장(DTO)
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		
		// MemberDAO객체 - 정보수정메서드 호출
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
		// 결과에 따른 페이지 이동(JS)
		//JSMoveFunction.alertLocation(response,msg,url);
		if(result == 1) { // 수정완료 -> 메인페이지로 이동
			JSMoveFunction.alertLocation(response, "회원정보 수정완료!", "./Main.me");
			return null;
		}else if(result == 0) { // 수정실패 -> 비밀번호 오류 -> 뒤로가기 이동
			JSMoveFunction.alertBack(response, "수정실패X- 비밀번호 오류");
			return null;
		}else { //result == -1
			JSMoveFunction.alertBack(response, "회원정보가 없음!!!");
		}
		
		return null;
	}

}
