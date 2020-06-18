package com.dev.model.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dev.model.notice.Notice;

/*CRUD
 * DAO란 오직 데이터베이스와 관련된 Create(insert)R(select),Update,Delete 즉 
 * DML을 수행하는 객체를 가리켜
 * 어플리케이션 설계 분야에서는 DAO 라고 부른다!!
 * 이 객체를 중립적으로 정의해놓으면, javaSE건 javaEE건 모두 사용이
 * 가능하게 되서, 재사용성이 높아지게 된다..(자바분야에서는 이 객체 모르면 간첩..)
 * */
public class NoticeDAO {
	//게시물 모두 가져오기
	public List selectAll() {
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##java";
		String pass = "1234";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//rs의 모든 데이터를 ArrayList로 옮겨심겠다!!
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		//오라클 연동하기 select 문 수행~~
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			String sql ="select * from notice order by notice_id desc";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt!=null) {	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
	//한건 등록(글 등록)
	//아래의 메서드는 웹이건, 응용이건 모두 사용해야 하므로,
	//특정 플랫폼에 국한되서는 안되며 기술에 중립적이어야 한다..
	public int insert(Notice notice) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##java";
		String pass = "1234";
		
		String sql="insert into notice(notice_id,title,writer,content)";
		sql+=" values(seq_notice.nextval,?,?,?)";
		int result = 0; //메서드 호출자로 하여금, 이 등록의 성패여부를 알 수 있도록 반환하기 위함!!
		
		//드라이버로드,접속,쿼리,해제
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			
			result = pstmt.executeUpdate(); //쿼리수행
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	
	//한건 가져오기(상세보기)
	public Notice select(int notice_id) {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//ResultSet을 반환해도 되지만, finally에서 ResultSet은 닫힐예정
		//이기 때문에 사용불가.. 또한 객체지향적인 객체로 레코드를 담는것이 더 직관적이다!!
		//(DTO,VO적극활용하자)
		Notice notice = null;
		
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##java";
		String pass = "1234";
		
		String sql = "select * from notice where notice_id=?";
		//드라이버 로드,접속, 쿼리,담기
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pass);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			rs=pstmt.executeQuery();
			//rs에 레코드가 한건이라도 있다면 그때 VO를 new 하자
			if(rs.next()) {
				notice = new Notice();
				//rs의 레코드르 vo로 옮겨심자.. rs가 죽기전에..
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}finally {
			if(rs!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt!=null) {	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return notice;
	}
	
	//한건 삭제
	public int delete(Notice notice) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##java";
		String pass = "1234";
		String sql="delete from notice where notice_id=?";
		int result = 0; //메서드 호출자로 하여금, 이 등록의 성패여부를 알 수 있도록 반환하기 위함!!
		
		//드라이버로드,접속,쿼리,해제
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,(notice.getNotice_id()));
			result = pstmt.executeUpdate(); //쿼리수행
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return result;
	}
	//한건 수정
	public int edit(Notice notice) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String url ="jdbc:oracle:thin:@localhost:1521:XE";
		String user = "c##java";
		String pass = "1234";
		String sql="update notice set title=?, writer=?, content=?";
		sql+=" where notice_id=?";
		int result = 0; //메서드 호출자로 하여금, 이 등록의 성패여부를 알 수 있도록 반환하기 위함!!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,notice.getTitle());
			pstmt.setString(2,notice.getWriter());
			pstmt.setString(3,notice.getContent());
			pstmt.setInt(4,notice.getNotice_id()); //숫자 바인드 변수

			result=pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {	try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(con!=null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		return result;
	}
	
}
