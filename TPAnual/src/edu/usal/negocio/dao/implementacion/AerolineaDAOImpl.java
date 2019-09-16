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
import edu.usal.util.PropertiesUtil;

public class AerolineaDAOImpl implements AerolineaDAO{

	
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

		try {
			con = getConnection();
			stm = con.createStatement();
			rsAerolinea = stm.executeQuery("SELECT * FROM AEROLINEA");
			List<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

			while (rsAerolinea.next()) {
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				
				Alianza alianza = new Alianza();
				alianza.setNombre(rsAerolinea.getString("alianza"));
				
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

	
	public Aerolinea obtenerAerolinea(String nombre){
		Connection con = null;
		PreparedStatement psAerolinea = null;
		ResultSet rsAerolinea = null;
		
		try {
			con = getConnection();
			psAerolinea = con.prepareStatement("SELECT * FROM Aerolineas where nombre_aerolinea= ?");
			
			
			psAerolinea.setString(1, nombre);
			rsAerolinea = psAerolinea.executeQuery();
			
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
			aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
			
			Alianza alianza = new Alianza();
			alianza.setNombre(rsAerolinea.getString("alianza"));

			aerolinea.setAlianza(alianza);
				
			return aerolinea;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psAerolinea.isClosed()) {
					psAerolinea.close();
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
		Connection con = null;
		PreparedStatement psAerolinea = null;
		try{
			con = getConnection();
			psAerolinea=con.prepareStatement("INSERT INTO Aerolinea VALUES(NEXT VALUE FOR seq_aerolinea, ?, ?)");
			
			psAerolinea.setString(2, aerolinea.getNombre());
			psAerolinea.setString(3, aerolinea.getAlianza().getNombre());
			
			psAerolinea.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psAerolinea.isClosed()){
					psAerolinea.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void modificarAerolinea(Aerolinea aerolinea) {
		Connection con = null;
		PreparedStatement psAerolinea = null;
		try{
			con = getConnection();
			psAerolinea=con.prepareStatement("UPDATE Aerolinea SET nombre = ? , alianza = ? WHERE nombre = ?");
			
			psAerolinea.setString(1, aerolinea.getNombre());
			psAerolinea.setString(2, aerolinea.getAlianza().getNombre());
			psAerolinea.setString(3, aerolinea.getNombre());
			
			psAerolinea.execute();
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psAerolinea.isClosed()){
					psAerolinea.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void eliminarAerolinea(String nombre) {
		Connection con = null;
		PreparedStatement psAerolinea = null;
		try{
			con = getConnection();
			psAerolinea=con.prepareStatement("DELETE FROM Aerolinea WHERE nombre = ?");
			
			psAerolinea.setString(1, nombre);
			
			psAerolinea.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psAerolinea.isClosed()) {
					psAerolinea.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	
	
	
}
