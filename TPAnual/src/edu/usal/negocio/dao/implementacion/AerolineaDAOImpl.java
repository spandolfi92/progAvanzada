package edu.usal.negocio.dao.implementacion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.AerolineaDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Alianza;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.util.PropertiesUtil;

public class AerolineaDAOImpl implements AerolineaDAO{

	
	@SuppressWarnings("unused")
	private static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName(PropertiesUtil.getPropertyDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(PropertiesUtil.getPropertyUrl(), PropertiesUtil.getPropertyUser(), PropertiesUtil.getPropertyPass());
		return con;
	}
	
	
	@Override
	public List<Aerolinea> listarAerolineas() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsAerolinea = null;
		PreparedStatement psAlianza = null;
		ResultSet rsAlianza = null;
		try {
			con = getConnection();
			stm = con.createStatement();
			rsAerolinea = stm.executeQuery("SELECT * FROM AEROLINEA");
			psAlianza = con.prepareStatement("SELECT * FROM Alianzas WHERE nombre_alianza = ?");
			List<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

			while (rsAerolinea.next()) {
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setNombreAerolinea(rsAerolinea.getString("nombre"));
				aerolinea.setCodigo(rsAerolinea.getString("codigo"));
				
				
				psAlianza.setString(1, rsAerolinea.getString("alianza_id"));
				rsAlianza = psAlianza.executeQuery();
				rsAlianza.next();
				Alianza alianza = new Alianza();
				alianza.setNombre(rsAlianza.getString("nombre_alianza"));
				aerolinea.setAlianza(alianza);
				
				aerolineas.add(aerolinea);
			}
			return aerolineas;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (!stm.isClosed()) {
					stm.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void altaAerolinea(Aerolinea aerolinea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarAerolinea(Aerolinea aerolinea) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarAerolinea(double id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
}
