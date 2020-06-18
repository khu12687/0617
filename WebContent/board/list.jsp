<%@page import="com.dev.model.notice.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.dev.model.board.NoticeDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//데이터베이스 관련한 로직을 담당해주는 DAO를 이용하여
	//List 를 가져오자!!
	NoticeDAO noticeDAO = new NoticeDAO();
	List<Notice> list = noticeDAO.selectAll();
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
a{text-decoration: none} /*링크 밑줄 업애기*/
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script>
$(function(){ //문서가 로드될때
	//alert("load 성공");
	//누구를 어떻게 할지의 문법구조를 갖는다
	//누구를 표현하기 위한 선택자는 css의 선택자를 그래로 지원!
	$("button").click(function(){
		$(location).attr("href","/board/registForm.jsp");
	});
	
});
</script>
</head>
<body>
	<table width="100%" border="1px">
		<tr>
			<td width="5%">No</td>
			<td width="65%">제목</td>
			<td width="10%">작성자</td>
			<td width="10%">등록일</td>
			<td width="10%">조회수</td>
		</tr>
		<%int total = list.size(); %>
		<%for(int i=0; i<list.size(); i++){ %>
		<%Notice notice=list.get(i); %>
		<tr onmouseover="this.style.background='cyan'" onmouseout="this.style.background=''">
			<td><%=total-- %></td>
			<td><a href="/board/content.jsp?notice_id=<%=notice.getNotice_id() %>"><%=notice.getTitle() %></td>
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
