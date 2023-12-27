package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

/**
 *	회원가입을 처리하는 객체 (insertPro.jsp페이지의 동작 수행) 
 *
 */
public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			  HttpServletResponse response) throws Exception {
	
		System.out.println(" M : MemberJoinAction_execute() 실행 ");
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달정보 (파라메터) 저장 + 회원가입일
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+dto);
		
		// DB연결 - SQL 실행
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		// 회원가입 메서드를 호출
		dao.insertMember(dto);
		
		// 페이지 이동(로그인페이지) => 컨트롤러에서만 가능
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);	
		
		System.out.println(" M : "+forward);
		
		return forward;
	}//exeucte()
}
