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

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

//이 서블릿은 게시물 삭제 요청을 처리하는 서블릿이다!
//이 서블릿의 요청 경로는 /notice/delete 이다!!
public class DeleteServlet extends HttpServlet{

	NoticeDAO noticeDAO = new NoticeDAO();
	//post or get?
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	//post or get 모든 요청이 아래의 메서드를 거쳐가게 하자!!
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩처리 한글안깨지게..
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//파라미터 받기!!
		String notice_id=request.getParameter("notice_id");
		
		//String sql="delete from notice where notice_id=?";
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
			
			int result=noticeDAO.delete(notice);
			if(result!=0) {
				out.print("<script>");
				out.print("alert('삭제성공');");
				out.print("location.href='/board/list.jsp';");
				out.print("</script>");
			}else {
				out.print("<script>");
				out.print("alert('삭제실패');");
				out.print("history.back();");
				out.print("</script>");
			}
	}
}
