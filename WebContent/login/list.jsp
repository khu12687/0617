<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%!
	String url ="jdbc.oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pass = "1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
%>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con=DriverManager.getConnection(url,user,pass);
	String sql="select * from notice order by notice_id desc";
	pstmt = con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	
	ArrayList<Notice> list = new ArrayList<Notice>();
	
	while(rs.next()){
		Notice notice = new Notice();
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setContent(rs.getString("content"));
		notice.setRegdate(rs.getString("regdate"));
		notice.setHit(rs.getInt("hit"));
		
		list.add(notice);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<style>
button{
	background : yellow;
	color:red;
}
a{text-decoration: none}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>
$(function(){
	$("button").click(funcion(){
		$(location).attr("href","/notice/registForm.jsp");
	});
});
</script>
</head>
<body>
	<table width="100% border="1px">
		<tr>
			<td width ="5%">no</td>
			<td width ="65%">제목</td>
			<td width ="10%">작성자</td>
			<td width ="10%">등록일</td>
			<td width ="10%">조회수</td>
		</tr>
		<%int total = list.size(); %>
		<%for(int i=0; i<list.size(); i++){ %>
		<%Notice notice = list.get(i); %>
	<tr onmouseover="this.style.background='cyan'" onmouseout="this.style.background=''">
			<td><%=total-- %></td>
			<td><a href="/notice/content.jsp?notice_id=<%=notice.getNotice_id() %>"><%=notice.getTitle() %></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate().substring(0,10) %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
				<button>등록</button>
			</td>
		</tr>
	</table>
</body>
</html>