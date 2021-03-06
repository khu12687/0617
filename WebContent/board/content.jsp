<%@page import="com.dev.model.board.NoticeDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	NoticeDAO noticeDAO = new NoticeDAO();
%>
<%
	int notice_id=0;
	//게시물의 pk를 클라이언트로부터 넘겨받자!!
	notice_id=Integer.parseInt(request.getParameter("notice_id"));	
	
	out.print(noticeDAO);
	Notice notice = noticeDAO.select(notice_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록 폼</title>
<style >
body{
	font-size:9pt;
}
div{
margin:auto;
}
div{
	width:600px;
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
		$(location).attr("href","/board/list.jsp");
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
	location.href="/board/delete?notice_id=<%=notice_id%>";
}

function edit(){
	//수정을 담당하는 서블릿에게 요청!!
	$("form").attr("method","post"); //양이 많아서
	$("form").attr("action","/board/edit"); //양이 많아서
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
		<div>
			<table border="1px" width="100%">
				<tr>
					<td width="65%"><input type="text" placeholder="댓글"/></td>
					<td width="25%"><input type="text" placeholder="작성자"/></td>
					<td width="10%"><input type="button" value="댓글등록"/></td>
				</tr>
				<tr>
					<td>댓글</td>
					<td>작성자</td>
					<td>등록일</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>
