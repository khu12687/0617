package com.dev.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

public class EditServlet extends HttpServlet{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩처리 한글안깨지게..
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String title = request.getParameter("title");
		String writer= request.getParameter("writer");;
		String content = request.getParameter("content");;
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));;
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(notice_id);
		int result = noticeDAO.edit(notice);
		
		if(result==1) {
			out.print("<script>");
			out.print("alert('수정완료');");
			out.print("location.href='/board/content.jsp?notice_id="+notice_id+"';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('수정실패');");
			out.print("history.back();");
			out.print("</script>");
		}
	}
}
