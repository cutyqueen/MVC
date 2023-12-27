package com.itwillbs.member.action;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

/**
 *  Controller  
 *  Member 대한 정보 처리를 모두 수행하는 컨트롤러 
	
	http://localhost:8088/MVC7/member
 *  
 */
public class MemberFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController_doProcess() 호출 ");
		System.out.println(" C : GET/POST방식 모두 처리 ");
		
		System.out.println("\n -----------------1. 가상주소 계산 시작 --------------------");
		//System.out.println(request.getRequestURL());
		//System.out.println(request.getRequestURI()); // URI = URL - (프로토콜 - IP - 포트번호)
		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : "+ requestURI);
		String CTXPath = request.getContextPath();
		System.out.println(" C : CTXPath : "+CTXPath);
		String command = requestURI.substring(CTXPath.length());
		System.out.println(" C : command : "+command);		
		
		System.out.println(" -----------------1. 가상주소 계산 끝 --------------------");

		
		System.out.println("\n -----------------2. 가상주소 매핑 시작 --------------------");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberJoin.me")) {
			System.out.println(" C : /MemberJoin.me 매핑 ");
			System.out.println(" C : 패턴1 - DB처리X, 뷰페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
			
			System.out.println(" C : "+forward);			
		}
		else if(command.equals("/MemberJoinAction.me")) {
			System.out.println(" C : /MemberJoinAction.me 매핑" );
			System.out.println(" C : 패턴2 - DB처리O, 페이지 이동");
			
			// MemberJoinAction 객체생성
			//MemberJoinAction mja = new MemberJoinAction();
			action = new MemberJoinAction();
			try {
//				mja.execute(request, response);
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if(command.equals("/MemberLogin.me")) {
			System.out.println(" C : /MemberLogin.me 호출 ");
			System.out.println(" C : 패턴 1 - DB사용X, view페이지 출력");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/MemberLoginAction.me")) {
			System.out.println(" C : /MemberLoginAction.me 호출 ");
			System.out.println(" C : 패턴 2 - DB사용O, 페이지이동");
			
			// MemberLoginAction() 객체생성
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 처리결과를 가지고 이동
		}
		else if(command.equals("/Main.me")) {
			System.out.println(" C : /Main.me 호출");
			System.out.println(" C : 패턴 1 - DB사용X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberLogout.me")) {
			System.out.println(" C : /MemberLogout.me 호출 ");
			System.out.println(" C : 패턴 2 - 데이터처리O, 페이지로 이동");
			
			// MemberLogoutAction() 객체 생성
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.me")) {
			System.out.println(" C : /MemberInfo.me 호출 ");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력 ");
			
			// MemberInfoAction() 객체생성
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println(" C : /MemberUpdate.me 호출 ");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력");
			
			// MemberUpdateAction() 객체
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdateProAction.me")) {
			System.out.println(" C : /MemberUpdateProAction.me 호출 ");
			System.out.println(" C : 패턴 2- DB사용O,페이지 이동");
			
			// MemberUpdateProAction() 객체 
			action = new MemberUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberDelete.me")) {
			System.out.println(" C : /MemberDelete.me 호출 ");
			System.out.println(" C : 패턴 1 - DB사용X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberDeleteAction.me")) {
			System.out.println(" C : /MemberDeleteAction.me 호출 ");
			System.out.println(" C : 패턴 2 - DB사용O, 페이지 이동");
			
			// MemberDeleteAction() 객체생성
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberList.me")) {
			System.out.println(" C : /MemberList.me 호출 ");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력 ");
			
			// MemberListAction() 객체 생성
			action = new MemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" -----------------2. 가상주소 매핑 끝 --------------------");
		
		
		System.out.println("\n -----------------3. 가상주소 이동 시작 --------------------");
		if(forward != null) { //이동정보가 존재할때 
			
			if(forward.isRedirect()) { // true
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : sendRedirect() ");
				
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println(" C : "+forward.getPath()+"로, 이동방식 : forward() ");
				RequestDispatcher dis 
				           = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);				
			}
			
		}		
		System.out.println(" -----------------3. 가상주소 이동 끝 --------------------");
		
	}//doProcess
	
	// alt shift s + v
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("\n\n C : MemberFrontController_doGet() 호출 ");
		 doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController_doPost() 호출 ");
		doProcess(request, response);
	}

}// MemberFrontController
