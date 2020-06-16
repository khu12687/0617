package com.dev;

import javax.servlet.http.HttpServlet;

/*
 * ex) 쓰레드의 생명주기 : 언제죽고 살아가는가..
 * 
 * 서블릿의 생명주기(서블릿은 언제 생성되며, 언제 소멸되고 , 언제까지 일하는가??)
 *	 
 * */

//서블릿의 인스턴스는 언제 생성되는가?
//최초에 브라우저로 접속할때 인스턴스 생성된다!!
//why? 실행부가 따로 없기 때문에.. 실행은 오직 클라이언트의 요청에 의해서만 수행된다!!


public class MyServlet extends HttpServlet{
	
}
