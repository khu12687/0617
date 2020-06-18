<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	//선언부 == 서블릿의 맴버영역
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pass = "1234";
	
	Connection con;
	PreparedStatement pstmt;
%>
<%
	//수정 후, 다시 상세보기 페이지를 보여줘야 하므로, 현재 jsp는 디자인 코드가
	//필요없으므로 html 다 지워버렸다
	request.setCharacterEncoding("utf-8"); //인코딩처리 한글안깨지게..
	String title = request.getParameter("title");
	String writer= request.getParameter("writer");;
	String content = request.getParameter("content");;
	int notice_id = Integer.parseInt(request.getParameter("notice_id"));;
	
	out.print("title : "+title);
	out.print("writer: "+writer);
	out.print("content : "+content);
	out.print("notice_id : "+notice_id);
	
	String sql="update notice set title=?, writer=?, content=?";
	sql+=" where notice_id=?";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection(url,user,pass);
	pstmt=con.prepareStatement(sql);
	pstmt.setString(1,title);
	pstmt.setString(2,writer);
	pstmt.setString(3,content);
	pstmt.setInt(4,notice_id); //숫자 바인드 변수
	
	int result=pstmt.executeUpdate();
%>
<script>
<%if(result==1){%>
	alert("수정완료");
	location.href="/notice/content.jsp?notice_id=<%=notice_id%>"; 
<%}else{%>
alert("수정실패");
history.back;
<%}%>
</script>
<%
if(pstmt!=null) {pstmt.close();}
if(con!=null) {con.close();}
%>
