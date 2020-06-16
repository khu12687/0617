<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
//클라이언트 스크립트 언어 : 실행 시점이 서버가 아닌 클라이언트의 브라우저에서
//실행되므로.. front-end
for(var i=1;i<=9;i++){
	console.log("5x"+i+" = "+5*i+"<br>")
}

</script>
</head>
<body>
<%
	//서버사이드 언어 : server-side 언어
	//실행시점은 서버에서 수행됨.. back-end
	for(int i=1;i<=9;i++){
		out.print("2x"+i+"="+2*i+"<br>");
	}
%>
난 jsp     옆에있는건  a 테크
<a href="http://naver.com">네이버</a> 
</body>
</html>