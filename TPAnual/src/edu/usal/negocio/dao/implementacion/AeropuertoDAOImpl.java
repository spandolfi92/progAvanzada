package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.AeropuertoDAO;
import edu.usal.negocio.dominio.Aeropuerto;

public class AeropuertoDAOImpl implements AeropuertoDAO{
	
	@Override
	public List<Aeropuerto> listarAeropuertos() {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rsAeropuerto = null;
	
		try {
			con = Connect.getConnection();
			stm = con.createStatement();
			rsAeropuerto = stm.executeQuery("SELECT * FROM AEROPUERTO");
			
			List<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

			while (rsAeropuerto.next()) {
				Aeropuerto aeropuerto = new Aeropuerto();
				aeropuerto.setId(rsAeropuerto.getInt("id_aeropuerto"));
				aeropuerto.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
				aeropuerto.setCiudad(rsAeropuerto.getString("ciudad"));
				
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
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
