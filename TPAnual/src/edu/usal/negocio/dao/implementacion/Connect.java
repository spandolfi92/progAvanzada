package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.usal.util.PropertiesUtil;

public class Connect {
	
	
	static Connection getConnection()  {
		Connection con = null;
		try {
			Class.forName(PropertiesUtil.getPropertyDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(PropertiesUtil.getPropertyUrl(), PropertiesUtil.getPropertyUser(),
					PropertiesUtil.getPropertyPass());
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return con;
	}

}
