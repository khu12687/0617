<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
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
	//클라이언트가 전송한 파라미터들을 받아서 오라클에 넣을 것임..
	//따라서 디자인 코드는 필요 없음..
	//out.print("나 regist.jsp에요");
	//클라이언트가 전송한 파라미터는 요청객체에 들어있고, 이 요청객체는
	//이미 jsp에서는 내장객체라는 이름으로 지원된다..
	//참고로 request 객체의 자료형은 HttpServletRequest이다
	
	//파라미터를 받기전에, 인코딩 처리를 먼저해놓자!
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	out.print("제목은 "+title+"<br>");
	out.print("작성자는 "+writer+"<br>");
	out.print("내용은  "+content+"<br>");
	
	/*오라클에 넣기!!
		1)드라이버 로드
		2)접속
		3)쿼리문 수행
		4)연결 끊기
	*/
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection(url,user,pass);
	if(con!=null){
		out.print("성공");
	}else{
		out.print("실패");
	}
	
	//여기에 insert 수행
	String sql = "insert into Notice(notice_id, title, writer, content)";
			  sql+=" values(seq_notice.nextval,?,?,?)";

	pstmt = con.prepareStatement(sql);
	pstmt.setString(1,title);
	pstmt.setString(2,writer);
	pstmt.setString(3,content);
	int result = pstmt.executeUpdate();
	if(result!=0){
		out.print("등록성공");
	}else{
		out.print("등록실패");
	}
	
	if(pstmt!=null){
		pstmt.close();
	}
	if(con!=null){
		con.close();
	}

%>
<script>
alert("등록 성공");
location.href="/notice/list.jsp";
</script>