<%@ page contentType="text/html; charset=UTF-8"%>
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
	$("#bt_regist").click(function(){
		regist();
	});
});

function regist(){
	//웹서버의 서블릿(jsp)에게 오라클에 넣을 것을 요청하자!
	$("form").attr("method","post"); //양이 많아서
	$("form").attr("action","regist.jsp"); //양이 많아서
	$("form").submit(); //양이 많아서
}
</script>

</head>
<body>
	<div>
		<form>
			<input type ="text" name="title" placeholder="제목 입력"/>
			<input type ="text" name="writer" placeholder="작성자 입력"/>
			<textarea name ="content" placeholder="내용작성..."></textarea>
		</form>
		<button id="bt_list">리스트</button>
		<button id="bt_regist">등록</button>
	</div>
</body>
</html>