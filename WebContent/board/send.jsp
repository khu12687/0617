<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단 전송</title>
<script>
function send(){
	//get방식으로 데이터 전송!!
	if(isNaN(form1.dan.value)){
		alert("숫자만 입력하세요!!");
		return;
	}
	form1.action="receive.jsp";
	form1.method="get";
	form1.submit(); //전송이 일어나는 시점!!
}
</script>
</head>
<body>
	<form name ="form1">
	<input type ="text" name ="dan" placeholder="원하는 단입력"/>
	</form>
	<button onclick="send()">전송</button>
	
</body>
</html>