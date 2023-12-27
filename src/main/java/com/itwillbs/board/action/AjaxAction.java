package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;

public class AjaxAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AjaxAction_execute 호출 ");
		
		 BoardDAO dao = new BoardDAO(); 
		//1.데이터 전달
		/*
		 * String msg = "ITWILL"; int num = 100;
		 * 
		 *dao.getBoardCount();
		 * response.setContentType("text/html; charset=UTF-8");
		 * //response.getWriter().print(msg); //response.getWriter().print(num);
		 * response.getWriter().print(dao.getBoardCount());
		 */
		 
		// http://localhost:8088/MVC7/AjaxAction.bo
		// 2. 일반 객체 전달
//		BoardDTO dto = dao.getBoard(1);
//		
//		// 정보 출력
////		response.setContentType("text/html; charset=UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().print(dto);
//		// => ajax 호출X (js-BoardDTO 객체를 인식불가)
		 
		// http://localhost:8088/MVC7/AjaxAction.bo
		// 3. JSON 객체 전달
//		JSONObject obj = new JSONObject();
//		obj.put("bno", 100);
//		obj.put("subject","testSubject");
//		
//		BoardDTO dto = dao.getBoard(1);
//		obj.put("name", dto.getName());
		
		// 게시판정보
		JSONObject obj1 = new JSONObject();
		obj1.put("bno", 100);
		obj1.put("subject","testSubject");
		// 회원정보
		JSONObject obj2 = new JSONObject();
		obj2.put("ID", "ADMIN");
		obj2.put("PW","1234");
		
		// => MAP 형태로 2가지 객체 전달
		JSONObject obj = new JSONObject();
		obj.put("obj1", obj1);
		obj.put("obj2", obj2);
		
		// => ArrayList 형태로 여러개의 객체 전달
		JSONArray arrlist = new JSONArray();
		arrlist.add(obj1);
		arrlist.add(obj2);
		
//		//response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		//response.getWriter().print(obj); 
		response.getWriter().print(arrlist); 
		 
		 
		 
		 
		
		
		return null;
	}

}
