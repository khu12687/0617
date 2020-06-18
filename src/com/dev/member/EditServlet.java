package com.dev.member;

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

public class EditServlet extends HttpServlet{
	//선언부 == 서블릿의 맴버영역
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pass = "1234";
	
	Connection con;
	PreparedStatement pstmt;
	int result;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩처리 한글안깨지게..
		String title = request.getParameter("title");
		String writer= request.getParameter("writer");;
		String content = request.getParameter("content");;
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));;
		PrintWriter out = response.getWriter();

		out.print("title : "+title);
		out.print("writer: "+writer);
		out.print("content : "+content);
		out.print("notice_id : "+notice_id);
		
		String sql="update notice set title=?, writer=?, content=?";
		sql+=" where notice_id=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,writer);
			pstmt.setString(3,content);
			pstmt.setInt(4,notice_id); //숫자 바인드 변수
			
			result=pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.print("<script>");
		if(result==1) {
			out.print("alert('수정완료')");
			out.print("location.href='/notice/content.jsp?notice_id='+notice_id");
		}else {
			out.print("alert('수정실패')");
			out.print("history.back");
		}
		out.print("</script>");
		
		try {
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
