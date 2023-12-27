package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

//@WebServlet("*.bo") : 컨트롤러에 특정 주소 매핑 (서블릿 3.1~ 이후 적용가능)

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : BoardFrontController_doProcess()");

		/***********************1. 가상주소 계산 시작 **************************/
		System.out.println("\n\n C : 1. 가상주소 계산 시작---------------");
		String requestURI = request.getRequestURI();
		System.out.println("\t requestURI : "+requestURI );
		String CTXPath = request.getContextPath();
		System.out.println("\t CTXPath : "+CTXPath );
		String command = requestURI.substring(CTXPath.length());
		System.out.println("\t command : "+command );
		System.out.println(" C : 1. 가상주소 계산 끝------------------");
		/***********************1. 가상주소 계산 끝 **************************/
		
		
		/***********************2. 가상주소 매핑 시작**************************/
		System.out.println("\n\n C : 2. 가상주소 매핑 시작------------------");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")) {
			System.out.println("\t C : /BoardWrite.bo 호출 ");
			System.out.println("\t C : 패턴 1 - DB사용X, 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/BoardWriteAction.bo")) {
			System.out.println("\t C : /BoardWriteAction.bo 호출 ");
			System.out.println("\t C : 패턴 2 - DB사용O, 페이지 이동 ");
			
			// BoardWriteAction() 객체 생성
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList.bo")) {
			System.out.println(" C : /BoardList.bo 호출 ");
			System.out.println(" C : 패턴 3 - DB사용O, 페이지 출력");
			
			//  BoardListAction() 객체 생성
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardContent.bo")) {
			System.out.println(" C : /BoardContent.bo 호출 ");
			System.out.println(" C : 패턴 3 - DB정보사용 O, 화면 출력 ");
			// BoardContentAction() 객체
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdate.bo")) {
			System.out.println(" C : /BoardUpdate.bo 호출 ");
			System.out.println(" C : 패턴 3 - DB사용 O,페이지 출력");
			
			// BoardUpdateAction() 객체 
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdateProAction.bo")) {
				System.out.println(" C : /BoardUpdateProAction.bo 호출 ");
				System.out.println(" C : 패턴 2 - DB사용O, 페이지이동");
				
				// BoardUpdateProAction()객체 생성
				action = new BoardUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		else if(command.equals("/BoardDelete.bo")) {
			System.out.println(" C : /BoardDelete.bo  호출 ");
			System.out.println(" C : 패턴 1 -DB사용X, 페이지이동 ");
			
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/BoardDeleteAction.bo")) {
			System.out.println(" C : /BoardDeleteAction.bo 호출 ");
			System.out.println(" C : 패턴 2 - DB사용O, 페이지 이동");
			
			// BoardDeleteAction() 객체
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardReWrite.bo")) {
			System.out.println(" C : /BoardReWrite.bo 호출 ");
			System.out.println(" C : 패턴 1 - DB사용X,페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/BoardReWriteAction.bo")) {
			System.out.println(" C : /BoardReWriteAction.bo 호출 ");
			System.out.println(" C :  패턴 2 - DB사용O, 페이지 이동 ");
			
			// BoardReWriteAction() 객체 생성
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardFileWrite.bo")) {
			System.out.println(" C : /BoardFileWrite.bo 호출 ");
			System.out.println(" C : 패턴 1 - DB사용X, 페이지이동");
			
			forward = new ActionForward();
			forward.setPath("./board/fWriteForm.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/BoardFileWriteAction.bo")) {
			System.out.println(" C : /BoardFileWriteAction.bo 호출 ");
			System.out.println(" C : 패턴 2 - DB사용O,페이지 이동 ");
			
			// BoardFileWriteAction() 객체
			action = new BoardFileWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(command.equals("/Ajax.bo")) {
			System.out.println(" C : /Ajax.bo 호출 ");
			System.out.println(" C : 패턴 1 - DB처리X,페이지이동 ");
			
			forward = new ActionForward();
			forward.setPath("./ajax/ajaxMain.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/AjaxAction.bo")) {
			System.out.println(" C : /AjaxAction.bo 호출");
			System.out.println(" C : 패턴 3 - DB처리O,페이지 출력");
			
			// AjaxAction 객체
			action = new AjaxAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" C : 2. 가상주소 매핑 끝------------------");
		/***********************2. 가상주소 매핑 끝**************************/
		
		/***********************3. 가상주소 이동 시작**************************/
		System.out.println("\n\n C : 3. 가상주소 이동 시작------------------");
		if(forward != null) {
			if(forward.isRedirect()) { // true
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : sendRedirect() 방식 ");
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println("\t C : 이동주소 : "+forward.getPath());
				System.out.println("\t C : 이동방법 : forward() 방식 ");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}		
		System.out.println(" C : 3. 가상주소 이동 끝------------------");
		/***********************3. 가상주소 이동 끝**************************/
		
	
	}//doProcess
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : BoardFrontController_doGET()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n C : BoardFrontController_doPOST()");
		doProcess(request, response);
	}	

}
