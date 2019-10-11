package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.usal.util.PropertiesUtil;

public class Connect {
	
	Connection con = null;
	
	public static Connection getConnection()  {
		Connection con = null;
		try {
			Class.forName(PropertiesUtil.getPropertyDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(PropertiesUtil.getPropertyUrl(), PropertiesUtil.getPropertyUser(),
					PropertiesUtil.getPropertyPass());
			con.setAutoCommit(false);
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public static void commit() {
		try {
			Connect.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback() {
		try {
			Connect.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
