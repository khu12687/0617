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
import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.Location;

public class DeleteServlet extends HttpServlet{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="c##java";
	String pass="1234";
	int result;
	Connection con;
	PreparedStatement pstmt;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int notice_id=0;
		notice_id = Integer.parseInt(request.getParameter("notice_id"));
		String sql="delete from notice where notice_id="+notice_id;
		out.print(sql);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			
			pstmt=con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.print("<script>");
		if(result==1) {
			out.print("alert('삭제완료')");
			out.print("location.href='/notice/list.jsp'");
		}else {
			out.print("alert('삭제실패')");
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
