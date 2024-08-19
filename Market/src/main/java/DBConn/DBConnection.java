package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	Connection con = null;
	PreparedStatement stmt =null;
	ResultSet rs = null;
	
	//String url = "jdbc:oracle:thin:@//localhost:1521/xe";
	//String userId = "system";
	//String pass = "1234";
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/market";
	String dbId = "root";
	String dbPass = "autoset";

	
	private  static DBConnection db=new DBConnection();
	
	DBConnection(){}
	
	 public static DBConnection getInstance() {  
		  return  db;
	 }
	 
	 public  Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Class.forName("oracle.jdbc.OracleDriver");
		    	con = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		return con; 
	 }
	 
	public void close(PreparedStatement stmt, Connection con ) {
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void close(ResultSet rs, PreparedStatement stmt, Connection con ) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt !=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}


}
