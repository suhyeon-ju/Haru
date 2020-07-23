package common;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * JDBC 연결을 위한 JDBCTemplate 클래스
 */
public class JDBCTemplate {
/**
 * Connection 객체를 가져오는 메소드
 * @return Connection 객체를 리턴
 */
	public static Connection getConnection() {
		boolean server = true;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. 드라이버 연결");
		}catch(ClassNotFoundException e) {
			System.out.println("01. 드라이버 연결 실패");
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "semi";
		String pw = "semi22"; //비밀번호는 대소문자 구분 있음
		if(server) { //서버 변수값이 true면 서버 DB 접속
			url="jdbc:oracle:thin:@sclass.iptime.org:1521:xe";
			id=pw="semi0103pm04";
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,id,pw);
			System.out.println("02. 계정 연결");
			con.setAutoCommit(false);
		}catch (SQLException e) {
			System.out.println("02. 계정 연결 실패");
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * Connection 이 연결중인지 확인합니다.
	 * @param con Connection
	 * @return 연결중이라면 true 리턴
	 */
	public static boolean isConnection(Connection con) {
		boolean valid = true;
		try {
			if (con==null || con.isClosed()) {
				valid = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}
	/**
	 * 전달받은 Connection 을 close 합니다.
	 * @param con Connection
	 */
	public static void close(Connection con) {
		if(isConnection(con)) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 전달받은 ResultSet 을 close 합니다.
	 * @param rs ResultSet
	 */
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 전달받은 Statement , PreparedStatment 를 close 합니다.
	 * @param stmt Statement, ReparedStatment
	 */
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * close에 rs, stmt(pstm), con 을 한번에 전달합니다
	 * @param rs ResultSet
	 * @param stmt Statement , PreparedStatement
	 * @param con Connection
	 */
	public static void close(ResultSet rs, Statement stmt,Connection con) {
		close(rs);
		close(stmt);
		close(con);
	}
	/**
	 * close 에 stmt(pstm), con을 한번에 전달합니다.
	 * @param stmt Statement, PreparedStatement
	 * @param con Connection
	 */
	public static void close(Statement stmt,Connection con) {
		close(stmt);
		close(con);
	}
	/**
	 * commit 합니다.
	 * @param con Connection
	 */
	public static void commit(Connection con) {
		if(isConnection(con)) {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * rollback 합니다.
	 * @param con Connection
	 */
	public static void rollback(Connection con) {
		if(isConnection(con)) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
