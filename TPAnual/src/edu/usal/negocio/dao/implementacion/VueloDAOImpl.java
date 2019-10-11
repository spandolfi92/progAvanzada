package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.VueloDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Aeropuerto;
import edu.usal.negocio.dominio.Vuelos;

public class VueloDAOImpl implements VueloDAO{

	@Override
	public List<Vuelos>listarVuelos() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsVuelos = null;
		
		try {
		
			con = Connect.getConnection();
			stm = con.createStatement();
			rsVuelos = stm.executeQuery("select vuelos.id_vuelo, vuelos.nro_vuelo, vuelos.cant_asientos, vuelos.fecha_hs_salida, vuelos.fecha_hs_llegada, vuelos.tiempo_vuelo,\r\n" + 
					"ae1.codigo_aeropuerto, ae2.codigo_aeropuerto, aerolinea.nombre_aerolinea\r\n" + 
					"from vuelos\r\n" + 
					"join AEROPUERTO as ae1 on vuelos.id_aeropuerto_salida = ae1.id_aeropuerto \r\n" + 
					"join AEROPUERTO as ae2 on vuelos.id_aeropuerto_llegada = ae2.id_aeropuerto \r\n" + 
					"join AEROLINEA on AEROLINEA.id_aerolinea = vuelos.id_aerolinea");
			
			
			List<Vuelos> vuelos = new ArrayList<Vuelos>();
			
			while (rsVuelos.next()) {
				
				Vuelos vuelo = new Vuelos();
				vuelo.setId(rsVuelos.getInt("id_vuelo"));
				vuelo.setNumeroVuelo(rsVuelos.getString("nro_vuelo"));
				vuelo.setCantidadAsientos(rsVuelos.getInt("cant_asientos"));
				vuelo.setFechaSalida(rsVuelos.getDate("fecha_hs_salida"));
				vuelo.setFechaLlegada(rsVuelos.getDate("fecha_hs_llegada"));				
				vuelo.setTiempoVuelo(rsVuelos.getString("tiempo_vuelo"));
				
				Aeropuerto aeropuertoSalida = new Aeropuerto();
				aeropuertoSalida.setIdentificacion(rsVuelos.getString("codigo_aeropuerto"));
				vuelo.setAeropuertoSalida(aeropuertoSalida);
				
				Aeropuerto aeropuertoLlegada = new Aeropuerto();
				aeropuertoSalida.setIdentificacion(rsVuelos.getString("codigo_aeropuerto"));
				vuelo.setAeropuertoLlegada(aeropuertoLlegada);
		
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setNombre(rsVuelos.getString("nombre_aerolinea"));
				vuelo.setAerolinea(aerolinea);
				
				vuelos.add(vuelo);
			}
				
	return vuelos;
			
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
	
	public Vuelos obtenerVuelo(String codigovuelo){
		Connection con = null;
		PreparedStatement psVuelo = null;
		ResultSet rsVuelo = null;
		
		try {
			con = Connect.getConnection();
			
			psVuelo = con.prepareStatement("select vuelos.id_vuelo, vuelos.nro_vuelo, vuelos.cant_asientos, vuelos.fecha_hs_salida, vuelos.fecha_hs_llegada, vuelos.tiempo_vuelo,\r\n" + 
					"ae1.codigo_aeropuerto, ae2.codigo_aeropuerto, aerolinea.nombre_aerolinea\r\n" + 
					"from vuelos\r\n" + 
					"join AEROPUERTO as ae1 on vuelos.id_aeropuerto_salida = ae1.id_aeropuerto \r\n" + 
					"join AEROPUERTO as ae2 on vuelos.id_aeropuerto_llegada = ae2.id_aeropuerto \r\n" + 
					"join AEROLINEA on AEROLINEA.id_aerolinea = vuelos.id_aerolinea\r\n" + 
					"where nro_vuelo = ? ");
			
			psVuelo.setString(1, codigovuelo);
			rsVuelo = psVuelo.executeQuery();
			
			rsVuelo.next();
			
			Vuelos vuelo = new Vuelos();
			vuelo.setId(rsVuelo.getInt("id_vuelo"));
			vuelo.setNumeroVuelo(rsVuelo.getString("nro_vuelo"));
			vuelo.setCantidadAsientos(rsVuelo.getInt("cant_asientos"));
			vuelo.setFechaSalida(rsVuelo.getDate("fecha_hs_salida"));
			vuelo.setFechaLlegada(rsVuelo.getDate("fecha_hs_llegada"));				
			vuelo.setTiempoVuelo(rsVuelo.getString("tiempo_vuelo"));
			
			Aeropuerto aeropuertoSalida = new Aeropuerto();
			aeropuertoSalida.setIdentificacion(rsVuelo.getString("codigo_aeropuerto"));
			vuelo.setAeropuertoSalida(aeropuertoSalida);
			
			Aeropuerto aeropuertoLlegada = new Aeropuerto();
			aeropuertoSalida.setIdentificacion(rsVuelo.getString("codigo_aeropuerto"));
			vuelo.setAeropuertoLlegada(aeropuertoLlegada);
	
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setNombre(rsVuelo.getString("nombre_aerolinea"));
			vuelo.setAerolinea(aerolinea);
			
			return vuelo;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psVuelo.isClosed()) {
					psVuelo.close();
				}
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	
	
	@Override
	public void altaVuelo(Vuelos vuelo) {
		Connection con = null;
		PreparedStatement psVuelo = null;
		try{
			con = Connect.getConnection();
			psVuelo=con.prepareStatement("INSERT INTO VUELOS VALUES(NEXT VALUE FOR seq_vuelos,?,?,?,?,?,?,?,?)");
			
			psVuelo.setString(1, vuelo.getNumeroVuelo());
			psVuelo.setInt(2, vuelo.getCantidadAsientos());
			psVuelo.setDate(3, new java.sql.Date(vuelo.getFechaSalida().getTime()));
			psVuelo.setDate(4, new java.sql.Date(vuelo.getFechaLlegada().getTime()));
			psVuelo.setString(5, vuelo.getTiempoVuelo());
			psVuelo.setDouble(6, vuelo.getAeropuertoSalida().getId());
			psVuelo.setDouble(7, vuelo.getAeropuertoLlegada().getId());
			psVuelo.setDouble(8, vuelo.getAerolinea().getId());

			psVuelo.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVuelo.isClosed()){
					psVuelo.close();
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
	public void modificarVuelo(Vuelos vuelo) {
		Connection con = null;
		PreparedStatement psVuelo = null;
		try{
			con = Connect.getConnection();
			psVuelo=con.prepareStatement("update vuelos set nro_vuelo=?, cant_asientos=?, fecha_hs_salida=?,\r\n" + 
					"fecha_hs_llegada=?, tiempo_vuelo=?, id_aeropuerto_salida=?,\r\n" + 
					"id_aeropuerto_llegada=?, id_aerolinea\r\n" + 
					"where id_vuelo=?");
			
			psVuelo.setString(1, vuelo.getNumeroVuelo());
			psVuelo.setInt(2, vuelo.getCantidadAsientos());
			psVuelo.setDate(3, new java.sql.Date(vuelo.getFechaSalida().getTime()));
			psVuelo.setDate(4, new java.sql.Date(vuelo.getFechaLlegada().getTime()));
			psVuelo.setString(5, vuelo.getTiempoVuelo());
			psVuelo.setDouble(6, vuelo.getAeropuertoSalida().getId());
			psVuelo.setDouble(7, vuelo.getAeropuertoLlegada().getId());
			psVuelo.setDouble(8, vuelo.getAerolinea().getId());

			psVuelo.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVuelo.isClosed()){
					psVuelo.close();
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
	public void eliminarVuelo(String cdoVuelo) {
		Connection con = null;
		PreparedStatement psVuelo = null;
		try{
			con = Connect.getConnection();
			psVuelo=con.prepareStatement("DELETE FROM VUELOS WHERE nro_vuelo = ?");
			
			psVuelo.setString(1, cdoVuelo);
			
			psVuelo.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVuelo.isClosed()) {
					psVuelo.close();
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
