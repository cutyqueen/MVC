package com.itwillbs.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;
import com.itwillbs.util.Action;
import com.itwillbs.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFileWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardFileWriteAction_execute() 호출 ");
		
		// upload 가상의 폴더 생성
		String realPath = request.getRealPath("/upload");
		System.out.println(" M : realPath :"+realPath);
		// 첨부파일의 크기 설정 (5MB)
		int maxSize = 5 * 1024 * 1024; 
		
		// 첨부파일업로드 => 객체 생성
		MultipartRequest multi =
				new MultipartRequest(
						request,
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		System.out.println(" M : 파일업로드 성공! ");
		
//		File file = new File(realPath+"/"+multi.getFilesystemName("file"));
//		file.delete();
		
		// 나머지 정보저장
		BoardDTO dto = new BoardDTO();
		dto.setName(multi.getParameter("name"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setContent(multi.getParameter("content"));
		dto.setPass(multi.getParameter("pass"));
		dto.setFile(multi.getFilesystemName("file"));
		dto.setIp(request.getRemoteAddr());
		
		// BoardDAO 객체 -> 첨부파일 저장
		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}
