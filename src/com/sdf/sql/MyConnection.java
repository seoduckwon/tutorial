package com.sdf.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;*/

public class MyConnection {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@192.168.0.165:1521:xe";
		String user = "sdf";
		String password = "sdf";
		return DriverManager.getConnection(url, user, password);		

/*		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();
			return conn;
		} catch (NamingException e) {
			throw new SQLException(e.getMessage());
		}*/
		
		
	}

	public static void close(ResultSet rs, Statement stmt, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
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
				e.printStackTrace();
			}
		}
		
	}
}
