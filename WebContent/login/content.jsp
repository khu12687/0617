<%@page import="java.sql.DriverManager"%>
<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	//선언부 == 서블릿의 맴버영역
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "c##java";
	String pass = "1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
%>
<%
	int notice_id=0;
	//게시물의 pk를 클라이언트로부터 넘겨받자!!
	notice_id=Integer.parseInt(request.getParameter("notice_id"));	
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection(url,user,pass);
	
	String sql="update notice set hit = hit+1 where notice_id ="+notice_id;
	
	pstmt = con.prepareStatement(sql);
	pstmt.executeUpdate();
	
	sql="select * from notice where notice_id="+notice_id;
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery();
	
	Notice notice = new Notice();
	if(rs.next()){ //레코드가 있다면..
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setContent(rs.getString("content"));
		notice.setRegdate(rs.getString("regdate"));
		notice.setHit(rs.getInt("hit"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록 폼</title>
<style >
div{
margin:auto;
}
div{
	width:500px;
	height:500px;
	border:2px solid blue;
	text-align:center;
}
div input, textarea{
	width:98%;
}
textarea{
	height:350px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	//등록버튼 누르면..
	$("#bt_list").click(function(){
		//history.back(); //이전 페이지로가기 
		//케시를 보여주는 거라서, 누군가가 글을 썻을때 갱신된 내용을 볼수 없다
		
		//새롭게 서버에게 요청을 시도하는 것임
		$(location).attr("href","/notice/list.jsp");
	});
	
	$("#bt_del").click(function() {
		if(confirm("삭제하시겠습니가?")){
			del();	
		}
	});
	
	$("#bt_edit").click(function() {
			if(confirm("수정하시겠습니가?")){
				edit();	
			}
		});
});

function del(){
	//alert("삭제요청 시도!!");
	location.href="/notice/delete.jsp?notice_id=<%=notice_id%>";
}

function edit(){
	//수정을 담당하는 서블릿에게 요청!!
	$("form").attr("method","get"); //양이 많아서
	$("form").attr("action","/notice/edit.jsp"); //양이 많아서
	$("form").submit();
}
</script>

</head>
<body bgcolor="yellow">
	<div>
		<form>
			<input type ="hidden" name="notice_id" value="<%=notice.getNotice_id() %>"/>
			<input type ="text" name="title" value="<%=notice.getTitle() %>"/>
			<input type ="text" name="writer" value="<%=notice.getWriter() %>"/>
			<textarea name ="content" ><%=notice.getContent() %> </textarea>
		</form>
		<button id="bt_list">리스트</button>
		<button id="bt_edit">수정</button>
		<button id="bt_del">삭제</button>
	</div>
</body>
</html>
<%
	if(rs!=null) {rs.close();}
	if(pstmt!=null) {pstmt.close();}
	if(con!=null) {con.close();}
%>