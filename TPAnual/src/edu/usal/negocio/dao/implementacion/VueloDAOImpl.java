package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.VueloDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Aeropuerto;
import edu.usal.negocio.dominio.Alianza;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.negocio.dominio.Vuelos;
import edu.usal.util.PropertiesUtil;

public class VueloDAOImpl implements VueloDAO{

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
	public List<Vuelos>listarVuelos() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsVuelos = null;
		
		PreparedStatement psVuelo = null;
		PreparedStatement psAeropuerto = null;
		PreparedStatement psAerolinea = null;
		PreparedStatement psProvincia = null;
		PreparedStatement psPais = null;
		
		ResultSet rsVuelo = null;
		ResultSet rsAeropuerto = null;
		ResultSet rsAerolinea = null;
		ResultSet rsProvincia = null;
		ResultSet rsPais = null;
		
		try {
			con = getConnection();
			stm = con.createStatement();
			
			rsVuelos = stm.executeQuery("SELECT * FROM VUELOS");
			psVuelo = con.prepareStatement("SELECT * FROM VUELOS where nro_vuelo = ?");
			psAeropuerto = con.prepareStatement("SELECT * FROM AEROPUERTO where nro_vuelo= ?");
			psAerolinea = con.prepareStatement("SELECT * FROM AEROLINEA where nro_vuelo= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			
			
			List<Vuelos> vuelos = new ArrayList<Vuelos>();
			
			while (rsVuelos.next()) {
				

				Vuelos vuelo = new Vuelos();
				vuelo.setId(rsVuelos.getInt("id_vuelo"));
				vuelo.setNumeroVuelo(rsVuelos.getString("nombre_vuelo"));
				vuelo.setCantidadAsientos(rsVuelos.getInt("cant_asientos"));
				vuelo.setFechaLlegada(rsVuelos.getDate("fecha_hs_llegada"));
				vuelo.setFechaSalida(rsVuelos.getDate("fecha_hs_salida"));
				vuelo.setTiempoVuelo(rsVuelos.getString("tiempo_vuelo"));
				
				//aeropuertollegada
				psAeropuerto.setInt(1, rsVuelos.getInt("id_aeropuerto_llegada"));
				rsAeropuerto = psAeropuerto.executeQuery(); 

				Aeropuerto aeropuerto = new Aeropuerto();
				aeropuerto.setId(rsAeropuerto.getInt("id_aeropuerto"));
				aeropuerto.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
				aeropuerto.setCiudad(rsAeropuerto.getString("ciudad"));
						
				psPais.setInt(1, rsAeropuerto.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais2 = new Pais();
				pais2.setId(rsPais.getDouble("id_pais"));
				pais2.setNombre(rsPais.getString("nombre_pais"));
				aeropuerto.setPais(pais2);
				
				psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia = new Provincia();
				provincia.setId(rsProvincia.getDouble("id_provincia"));
			
				provincia.setNombre(rsProvincia.getString("nombre_provincia"));
				aeropuerto.setProvincia(provincia); 
				vuelo.setAeropuertoLlegada(aeropuerto);
				
				//aeropuertosalida
				
				psAeropuerto.setInt(1, rsVuelos.getInt("id_aeropuerto_salida"));
				rsAeropuerto = psAeropuerto.executeQuery(); 

				Aeropuerto aeropuertosalida = new Aeropuerto();
				aeropuertosalida.setId(rsAeropuerto.getInt("id_aeropuerto"));
				aeropuertosalida.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
				aeropuertosalida.setCiudad(rsAeropuerto.getString("ciudad"));
						
				psPais.setInt(1, rsAeropuerto.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais paissalida = new Pais();
				paissalida.setId(rsPais.getDouble("id_pais"));
				paissalida.setNombre(rsPais.getString("nombre_pais"));
				aeropuertosalida.setPais(paissalida);
				
				psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provinciaSalida = new Provincia();
				provinciaSalida.setId(rsProvincia.getDouble("id_provincia"));

				provinciaSalida.setNombre(rsProvincia.getString("nombre_provincia"));
				aeropuertosalida.setProvincia(provinciaSalida); 
				vuelo.setAeropuertoSalida(aeropuertosalida);
				
	           // aerolinea + alianza
				psAerolinea.setInt(1, rsAeropuerto.getInt("id_aerolinea"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				Alianza alianza2 = new Alianza();
				alianza2.setNombre(rsAerolinea.getString("alianza"));
				aerolinea.setAlianza(alianza2);
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
				if (!psVuelo.isClosed()) {
					psVuelo.close();
				}
				if (!psAeropuerto.isClosed()) {
					psAeropuerto.close();
				}
				if (!psPais.isClosed()) {
					psPais.close();
				}
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
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
	
	public Vuelos obtenerVuelo(String codigovuelo){
		Connection con = null;
		
		PreparedStatement psVuelo = null;
		PreparedStatement psAeropuerto = null;
		PreparedStatement psAerolinea = null;
		PreparedStatement psProvincia = null;
		PreparedStatement psPais = null;
		
		ResultSet rsVuelo = null;
		ResultSet rsAeropuerto = null;
		ResultSet rsAerolinea = null;
		ResultSet rsProvincia = null;
		ResultSet rsPais = null;
		
		try {
			con = getConnection();
			
			psVuelo = con.prepareStatement("SELECT * FROM VUELOS where nro_vuelo = ?");
			psAeropuerto = con.prepareStatement("SELECT * FROM AEROPUERTO where nro_vuelo= ?");
			psAerolinea = con.prepareStatement("SELECT * FROM AEROLINEA where nro_vuelo= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			
			psVuelo.setString(1, codigovuelo);
			rsVuelo = psVuelo.executeQuery();
			
			Vuelos vuelo = new Vuelos();
			vuelo.setId(rsVuelo.getInt("id_vuelo"));
			vuelo.setNumeroVuelo(rsVuelo.getString("nombre_vuelo"));
			vuelo.setCantidadAsientos(rsVuelo.getInt("cant_asientos"));
			vuelo.setFechaLlegada(rsVuelo.getDate("fecha_hs_llegada"));
			vuelo.setFechaSalida(rsVuelo.getDate("fecha_hs_salida"));
			vuelo.setTiempoVuelo(rsVuelo.getString("tiempo_vuelo"));
			
			//aeropuertollegada
			psAeropuerto.setInt(1, rsVuelo.getInt("id_aeropuerto_llegada"));
			rsAeropuerto = psAeropuerto.executeQuery(); 

			Aeropuerto aeropuerto = new Aeropuerto();
			aeropuerto.setId(rsAeropuerto.getInt("id_aeropuerto"));
			aeropuerto.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
			aeropuerto.setCiudad(rsAeropuerto.getString("ciudad"));
					
			psPais.setInt(1, rsAeropuerto.getInt("id_pais"));
			rsPais = psPais.executeQuery(); 
			Pais pais2 = new Pais();
			pais2.setId(rsPais.getDouble("id_pais"));
			pais2.setNombre(rsPais.getString("nombre_pais"));
			aeropuerto.setPais(pais2);
			
			psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
			rsProvincia = psProvincia.executeQuery(); 
			Provincia provincia = new Provincia();
			provincia.setId(rsProvincia.getDouble("id_provincia"));
			
			provincia.setNombre(rsProvincia.getString("nombre_provincia"));
			aeropuerto.setProvincia(provincia); 
			vuelo.setAeropuertoLlegada(aeropuerto);
			
			//aeropuertosalida
			
			psAeropuerto.setInt(1, rsVuelo.getInt("id_aeropuerto_salida"));
			rsAeropuerto = psAeropuerto.executeQuery(); 

			Aeropuerto aeropuertosalida = new Aeropuerto();
			aeropuertosalida.setId(rsAeropuerto.getInt("id_aeropuerto"));
			aeropuertosalida.setIdentificacion(rsAeropuerto.getString("codigo_aeropuerto"));
			aeropuertosalida.setCiudad(rsAeropuerto.getString("ciudad"));
					
			psPais.setInt(1, rsAeropuerto.getInt("id_pais"));
			rsPais = psPais.executeQuery(); 
			Pais paissalida = new Pais();
			paissalida.setId(rsPais.getDouble("id_pais"));
			paissalida.setNombre(rsPais.getString("nombre_pais"));
			aeropuertosalida.setPais(paissalida);
			
			psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
			rsProvincia = psProvincia.executeQuery(); 
			Provincia provinciaSalida = new Provincia();
			provinciaSalida.setId(rsProvincia.getDouble("id_provincia"));

			provinciaSalida.setNombre(rsProvincia.getString("nombre_provincia"));
			aeropuertosalida.setProvincia(provinciaSalida); 
			vuelo.setAeropuertoSalida(aeropuertosalida);
			
           // aerolinea + alianza
			psAerolinea.setInt(1, rsAeropuerto.getInt("id_aerolinea"));
			rsAerolinea = psAerolinea.executeQuery();
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
			aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
			Alianza alianza2 = new Alianza();
			alianza2.setNombre(rsAerolinea.getString("alianza"));
			aerolinea.setAlianza(alianza2);
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
				if (!psAeropuerto.isClosed()) {
					psAeropuerto.close();
				}
				if (!psPais.isClosed()) {
					psPais.close();
				}
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
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
	public void altaVuelo(Vuelos vuelo) {
		Connection con = null;
		PreparedStatement psVuelo = null;
		try{
			con = getConnection();
			psVuelo=con.prepareStatement("INSERT INTO VUELOS VALUES(NEXT VALUE FOR seq_vuelo,?,?,?,?,?)");
			
			psVuelo.setString(2, vuelo.getNumeroVuelo());
			psVuelo.setInt(3, vuelo.getCantidadAsientos());
			psVuelo.setDate(4, (Date) vuelo.getFechaSalida());
			psVuelo.setDate(5, (Date) vuelo.getFechaLlegada());
			psVuelo.setString(6, vuelo.getTiempoVuelo());

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
			con = getConnection();
			psVuelo=con.prepareStatement("UPDATE VUELOS SET nro_vuelo = ? , cant_asientos = ?, fecha_hs_salida = ?, fecha_hs_llegada = ?, tiempo_vuelo = ? WHERE nro_vuelo = ?");
			
			psVuelo.setString(1, vuelo.getNumeroVuelo());
			psVuelo.setInt(2, vuelo.getCantidadAsientos());
			psVuelo.setDate(3, (Date) vuelo.getFechaSalida());
			psVuelo.setDate(4, (Date) vuelo.getFechaLlegada());
			psVuelo.setString(5, vuelo.getTiempoVuelo());
			psVuelo.setString(5, vuelo.getNumeroVuelo());

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
			con = getConnection();
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
