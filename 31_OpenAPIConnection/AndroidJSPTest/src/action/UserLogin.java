package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserLogin {
	
	private static UserLogin login = new UserLogin();
	
	private UserLogin() {
		
	}
	
	public static UserLogin getWriter() {
		return login;
	}
	
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs = null;
	String returns = "";
		
	//DB에 데이터 추가
	public String write( String id, String pwd ) {
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle_test");
			conn = ds.getConnection();
			
			String sql = "select * from TEST_USER where id=? and pwd=?";
			
			//쿼리문 수행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while( rs.next() ) {
				rs.getString("id");
				rs.getString("pwd");
				cnt++;
			}
			
			if( cnt == 1 ) { //정보검색 성공한 경우
				returns = String.format("{res:[{'result':'%s'}]}", "success" );
			}else {
				returns = String.format("{res:[{'result':'%s'}]}", "fail" );
			}			
						
		} catch (Exception e) {
			
		}finally {
			
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return returns;
		
	} //write
	
	
}
