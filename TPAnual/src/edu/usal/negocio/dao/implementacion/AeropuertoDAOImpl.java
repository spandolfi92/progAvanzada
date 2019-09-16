package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.AeropuertoDAO;
import edu.usal.negocio.dominio.Aeropuerto;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.util.PropertiesUtil;

public class AeropuertoDAOImpl implements AeropuertoDAO{
	
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
	public List<Aeropuerto> listarAeropuertos() {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rsAeropuerto = null;
		PreparedStatement psPais = null;
		ResultSet rsPais = null;
		PreparedStatement psProvincia = null;
		ResultSet rsProvincia = null;

		try {
			con = getConnection();
			stm = con.createStatement();
			rsAeropuerto = stm.executeQuery("SELECT * FROM AEROPUERTO");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			
			List<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

			while (rsAeropuerto.next()) {
				Aeropuerto aeropuerto = new Aeropuerto();
				aeropuerto.setId(rsAeropuerto.getInt("id_aeropuerto"));
				aeropuerto.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
				aeropuerto.setCiudad(rsAeropuerto.getString("ciudad"));
				
				psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia = new Provincia();
				provincia.setId(rsProvincia.getDouble("id_provincia"));
				provincia.setNombre(rsProvincia.getString("nombre_provincia"));
				aeropuerto.setProvincia(provincia); 
				
				psPais.setInt(1, rsAeropuerto.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais = new Pais();
				pais.setId(rsPais.getDouble("id_pais"));
				pais.setNombre(rsPais.getString("nombre_pais"));
				aeropuerto.setPais(pais); 
				
				aeropuertos.add(aeropuerto);
			}
			return aeropuertos;
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
				
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
				
				if (!psPais.isClosed()) {
					psPais.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
