package com.dev.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

public class RegistServlet extends HttpServlet{
	//글 등록을 처리하는 서블릿!!
	//클라이언트가 post 방시으로 요청을 할 것임!!
	//글 등록 파라미터 중 상세보기의 경우 데이터량이 많기 때문..
	
	//service()에 의해 doXXX형 메서드 호출된다!!
	//따라서 개발자는 service()메서드가 아닌, doXXX형에 집중해야함
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 추출하기
		request.setCharacterEncoding("utf-8");
		
		//출력스트림을 얻기전에 response객체에 인코딩처리를 해야한다!!
		//아래의 두 코드는 jsp 에서의 Page 지시영역에 해당함!
		response.setContentType("text/html"); //mime type
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		//파라미터 추출하기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice(); //empty 상태의 VO
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		if(result!=0) {
			out.print("<script>");
			out.print("alert('등록성공');");
			out.print("location.href='/board/list.jsp';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		}
		
	}
}
