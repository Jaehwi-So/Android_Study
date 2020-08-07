package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserJoin {
	
	private static UserJoin join = new UserJoin();
	
	private UserJoin() {
		
	}
	
	public static UserJoin getWriter() {
		return join;
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
			
			String sql = "insert into TEST_USER values( seq_tuser_idx.nextVal, ?, ? )";
			
			//쿼리문 수행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			
			returns = String.format("{res:[{'result':'%s'}]}", "success" );
						
		} catch (Exception e) {
			returns = String.format("{res:[{'result':'%s'}]}", "fail" );
		}finally {
			
			try {
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
