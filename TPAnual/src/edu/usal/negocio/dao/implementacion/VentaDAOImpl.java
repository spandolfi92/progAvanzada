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

import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Aeropuerto;
import edu.usal.negocio.dominio.Alianza;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Direccion;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.PasajeroFrecuente;
import edu.usal.negocio.dominio.Pasaporte;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.negocio.dominio.Telefono;
import edu.usal.negocio.dominio.Ventas;
import edu.usal.negocio.dominio.Vuelos;
import edu.usal.util.PropertiesUtil;

public class VentaDAOImpl implements VentaDAO{

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
	public List<Ventas>listarVentas() {
		Connection con = null;
		Statement stm = null;

		PreparedStatement psVuelos = null;
		PreparedStatement psAeropuerto = null;
		PreparedStatement psAerolinea = null;
		PreparedStatement psProvincia = null;
		PreparedStatement psPais = null;
		PreparedStatement psCliente = null;
		PreparedStatement psPasaporte = null;
		PreparedStatement psTelefono = null;
		PreparedStatement psPasajeroFrecuente = null;
		PreparedStatement psDireccion = null;
		ResultSet rsVentas = null;
		ResultSet rsAeropuerto = null;
		ResultSet rsAerolinea = null;
		ResultSet rsProvincia = null;
		ResultSet rsPais = null;
		ResultSet rsVuelos = null;
		ResultSet rsCliente = null;
		ResultSet rsPasaporte = null;
		ResultSet rsTelefono = null;
		ResultSet rsPasajeroFrecuente = null;
		ResultSet rsDireccion = null;


		
		try {
			con = getConnection();
			stm = con.createStatement();
			
			
			rsVentas = stm.executeQuery("SELECT * FROM VENTAS");
			
			psCliente = con.prepareStatement("SELECT * FROM CLIENTE where id_venta = ?");
			psVuelos = con.prepareStatement("SELECT * FROM VUELOS where id_venta = ?");
			psAerolinea = con.prepareStatement("SELECT * FROM AEROLINEA where id_venta= ?");

			psAeropuerto = con.prepareStatement("SELECT * FROM AEROPUERTO where id_vuelo= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			psPasaporte = con.prepareStatement("SELECT * FROM Pasaporte where id_cliente= ?");
			psTelefono = con.prepareStatement("SELECT * FROM Telefono where id_cliente= ?");
			psPasajeroFrecuente = con.prepareStatement("SELECT * FROM pasajero_frecuente where id_cliente= ?");
			psDireccion = con.prepareStatement("SELECT * FROM Direccion where id_cliente= ?");

			List<Ventas> ventas = new ArrayList<Ventas>();
			
			while (rsVentas.next()) {
				
            //VENTA
				
				Ventas venta = new Ventas();
				venta.setId(rsVentas.getInt("id_venta"));
				venta.setFechaVenta(rsVentas.getDate("fecha_hs_venta"));
				venta.setFormaPago(rsVentas.getString("forma_pago"));
				
			// ID CLIENTE
				psCliente.setInt(1, rsVentas.getInt("id_cliente"));
				rsCliente = psCliente.executeQuery(); 
				Cliente cliente = new Cliente();
				cliente.setId(rsCliente.getInt("id_cliente"));
				cliente.setNombre(rsCliente.getString("nombre"));
				cliente.setApellido(rsCliente.getString("apellido"));
				cliente.setDni(rsCliente.getString("dni"));
				cliente.setCuitCuil(rsCliente.getString("cuit_cuil"));
				cliente.setFechaNacimiento(rsCliente.getDate("fecha_nacimiento"));
				cliente.setEmail(rsCliente.getString("email"));

				
				psPasaporte.setInt(1, rsCliente.getInt("id_cliente"));
				rsPasaporte = psPasaporte.executeQuery(); 
				Pasaporte pasaporte = new Pasaporte();
				pasaporte.setId(rsPasaporte.getDouble("id_pasaporte"));
				pasaporte.setNumero(rsPasaporte.getString("nro_pasaporte"));
				pasaporte.setAutoridadEmision(rsPasaporte.getString("autoridad_emision"));
				pasaporte.setFechaEmision(rsPasaporte.getDate("fecha_emision"));
				pasaporte.setFechaVencimiento(rsPasaporte.getDate("fecha_vencimiento"));
				
				psPais.setInt(1, rsCliente.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais = new Pais();
				pais.setId(rsPais.getDouble("id_pais"));
				pais.setNombre(rsPais.getString("nombre_pais"));
				pasaporte.setPais(pais); 
				
				cliente.setPasaporte(pasaporte); 
				
				psTelefono.setInt(1, rsCliente.getInt("id_cliente"));
				rsTelefono = psTelefono.executeQuery(); 
				Telefono telefono = new Telefono();
				telefono.setId(rsTelefono.getDouble("id_telefono"));
				telefono.setNumeroPersonal(rsTelefono.getString("personal"));
				telefono.setNumeroCelular(rsTelefono.getString("celular"));
				telefono.setNumeroLaboral(rsTelefono.getString("laboral"));
				
				psPasajeroFrecuente.setInt(1, rsCliente.getInt("id_cliente"));
				rsPasajeroFrecuente = psPasajeroFrecuente.executeQuery(); 
				PasajeroFrecuente pasajeroFrecuente = new PasajeroFrecuente();
				pasajeroFrecuente.setId(rsPasajeroFrecuente.getDouble("id_pasajero_frecuente"));
				pasajeroFrecuente.setNumero(rsPasajeroFrecuente.getString("numero"));
				pasajeroFrecuente.setCategoria(rsPasajeroFrecuente.getString("categoria"));
				
				
				psAerolinea.setInt(1, rsPasajeroFrecuente.getInt("id_aerolinea"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				
				pasajeroFrecuente.setAerolinea(aerolinea);
				
				psDireccion.setInt(1, rsCliente.getInt("id_cliente"));
				rsDireccion = psDireccion.executeQuery(); 
				Direccion direccion = new Direccion();
				direccion.setId(rsDireccion.getDouble("id_direccion"));
				direccion.setCalle(rsDireccion.getString("calle"));
				direccion.setAltura(rsDireccion.getString("altura"));
				direccion.setCiudad(rsDireccion.getString("ciudad"));
				direccion.setCodigoPostal(rsDireccion.getString("codigo_Postal"));
				
				psPais.setInt(1, rsDireccion.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais2 = new Pais();
				pais2.setId(rsPais.getDouble("id_pais"));
				pais2.setNombre(rsPais.getString("nombre_pais"));
				direccion.setPais(pais2);
				
				psProvincia.setInt(1, rsDireccion.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia = new Provincia();
				provincia.setId(rsProvincia.getDouble("id_provincia"));
				provincia.setNombre(rsProvincia.getString("nombre_provincia"));
				direccion.setProvincia(provincia); 
				
				cliente.setDireccion(direccion);
				
				venta.setCliente(cliente);
			//ID VUELO
				psVuelos.setInt(1, rsVentas.getInt("id_venta"));
				rsVuelos = psVuelos.executeQuery(); 
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
				Pais pais21 = new Pais();
				pais21.setId(rsPais.getDouble("id_pais"));
				pais21.setNombre(rsPais.getString("nombre_pais"));
				aeropuerto.setPais(pais21);
				
				psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia1 = new Provincia();
				provincia1.setId(rsProvincia.getDouble("id_provincia"));
			
				provincia1.setNombre(rsProvincia.getString("nombre_provincia"));
				aeropuerto.setProvincia(provincia1); 
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
				Aerolinea aerolinea1 = new Aerolinea();
				aerolinea1.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea1.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				
				vuelo.setAerolinea(aerolinea1);
				venta.setVuelo(vuelo);
				
			//ID AEROLINEA 
				
				
	           // aerolinea + alianza
				psAerolinea.setInt(1, rsAeropuerto.getInt("id_venta"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea11 = new Aerolinea();
				aerolinea11.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea11.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				Alianza alianza211 = new Alianza();
				
				venta.setAerolinea(aerolinea11);
				
				ventas.add(venta);
			}
				
	return ventas;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psVuelos.isClosed()) {
					psVuelos.close();
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
				if (!psPasaporte.isClosed()) {
					psPasaporte.close();
				}
				
				if (!psTelefono.isClosed()) {
					psTelefono.close();
				}
				
				if (!psPasajeroFrecuente.isClosed()) {
					psPasajeroFrecuente.close();
				}
				
				if (!psDireccion.isClosed()) {
					psDireccion.close();
				}
				
				if (!psPais.isClosed()) {
					psPais.close();
				}
				
				if (!psAerolinea.isClosed()) {
					psAerolinea.close();
				}
				
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public Ventas obtenerVenta(String cdoVenta){
		Connection con = null;

		PreparedStatement psVentas = null;
		PreparedStatement psVuelos = null;
		PreparedStatement psAeropuerto = null;
		PreparedStatement psAerolinea = null;
		PreparedStatement psProvincia = null;
		PreparedStatement psPais = null;
		PreparedStatement psCliente = null;
		PreparedStatement psPasaporte = null;
		PreparedStatement psTelefono = null;
		PreparedStatement psPasajeroFrecuente = null;
		PreparedStatement psDireccion = null;
		ResultSet rsVentas = null;
		ResultSet rsAeropuerto = null;
		ResultSet rsAerolinea = null;
		ResultSet rsProvincia = null;
		ResultSet rsPais = null;
		ResultSet rsVuelos = null;
		ResultSet rsCliente = null;
		ResultSet rsPasaporte = null;
		ResultSet rsTelefono = null;
		ResultSet rsPasajeroFrecuente = null;
		ResultSet rsDireccion = null;


		
		try {
			con = getConnection();
			
			
			psVentas = con.prepareStatement("SELECT * FROM VENTAS where id_venta = ?");
			
			psCliente = con.prepareStatement("SELECT * FROM CLIENTE where id_venta = ?");
			psVuelos = con.prepareStatement("SELECT * FROM VUELOS where id_venta = ?");
			psAerolinea = con.prepareStatement("SELECT * FROM AEROLINEA where id_venta= ?");

			psAeropuerto = con.prepareStatement("SELECT * FROM AEROPUERTO where id_vuelo= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			psPasaporte = con.prepareStatement("SELECT * FROM Pasaporte where id_cliente= ?");
			psTelefono = con.prepareStatement("SELECT * FROM Telefono where id_cliente= ?");
			psPasajeroFrecuente = con.prepareStatement("SELECT * FROM pasajero_frecuente where id_cliente= ?");
			psDireccion = con.prepareStatement("SELECT * FROM Direccion where id_cliente= ?");

			
			psVentas.setString(1, cdoVenta);
			rsVentas = psVentas.executeQuery();
				
            //VENTA
				
				Ventas venta = new Ventas();
				venta.setId(rsVentas.getInt("id_venta"));
				venta.setFechaVenta(rsVentas.getDate("fecha_hs_venta"));
				venta.setFormaPago(rsVentas.getString("forma_pago"));
				
			// ID CLIENTE
				psCliente.setInt(1, rsVentas.getInt("id_venta"));
				rsCliente = psCliente.executeQuery(); 
				Cliente cliente = new Cliente();
				cliente.setId(rsCliente.getInt("id_cliente"));
				cliente.setNombre(rsCliente.getString("nombre"));
				cliente.setApellido(rsCliente.getString("apellido"));
				cliente.setDni(rsCliente.getString("dni"));
				cliente.setCuitCuil(rsCliente.getString("cuit_cuil"));
				cliente.setFechaNacimiento(rsCliente.getDate("fecha_nacimiento"));
				cliente.setEmail(rsCliente.getString("email"));

				
				psPasaporte.setInt(1, rsCliente.getInt("id_cliente"));
				rsPasaporte = psPasaporte.executeQuery(); 
				Pasaporte pasaporte = new Pasaporte();
				pasaporte.setId(rsPasaporte.getDouble("id_pasaporte"));
				pasaporte.setNumero(rsPasaporte.getString("nro_pasaporte"));
				pasaporte.setAutoridadEmision(rsPasaporte.getString("autoridad_emision"));
				pasaporte.setFechaEmision(rsPasaporte.getDate("fecha_emision"));
				pasaporte.setFechaVencimiento(rsPasaporte.getDate("fecha_vencimiento"));
				
				psPais.setInt(1, rsCliente.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais = new Pais();
				pais.setId(rsPais.getDouble("id_pais"));
				pais.setNombre(rsPais.getString("nombre_pais"));
				pasaporte.setPais(pais); 
				
				cliente.setPasaporte(pasaporte); 
				
				psTelefono.setInt(1, rsCliente.getInt("id_cliente"));
				rsTelefono = psTelefono.executeQuery(); 
				Telefono telefono = new Telefono();
				telefono.setId(rsTelefono.getDouble("id_telefono"));
				telefono.setNumeroPersonal(rsTelefono.getString("personal"));
				telefono.setNumeroCelular(rsTelefono.getString("celular"));
				telefono.setNumeroLaboral(rsTelefono.getString("laboral"));
				
				psPasajeroFrecuente.setInt(1, rsCliente.getInt("id_cliente"));
				rsPasajeroFrecuente = psPasajeroFrecuente.executeQuery(); 
				PasajeroFrecuente pasajeroFrecuente = new PasajeroFrecuente();
				pasajeroFrecuente.setId(rsPasajeroFrecuente.getDouble("id_pasajero_frecuente"));
				pasajeroFrecuente.setNumero(rsPasajeroFrecuente.getString("numero"));
				pasajeroFrecuente.setCategoria(rsPasajeroFrecuente.getString("categoria"));
				


				psAerolinea.setInt(1, rsPasajeroFrecuente.getInt("id_aerolinea"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				
				pasajeroFrecuente.setAerolinea(aerolinea);
				
				psDireccion.setInt(1, rsCliente.getInt("id_cliente"));
				rsDireccion = psDireccion.executeQuery(); 
				Direccion direccion = new Direccion();
				direccion.setId(rsDireccion.getDouble("id_direccion"));
				direccion.setCalle(rsDireccion.getString("calle"));
				direccion.setAltura(rsDireccion.getString("altura"));
				direccion.setCiudad(rsDireccion.getString("ciudad"));
				direccion.setCodigoPostal(rsDireccion.getString("codigo_Postal"));
				
				psPais.setInt(1, rsDireccion.getInt("id_pais"));
				rsPais = psPais.executeQuery(); 
				Pais pais2 = new Pais();
				pais2.setId(rsPais.getDouble("id_pais"));
				pais2.setNombre(rsPais.getString("nombre_pais"));
				direccion.setPais(pais2);
				
				psProvincia.setInt(1, rsDireccion.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia = new Provincia();
				provincia.setId(rsProvincia.getDouble("id_provincia"));
				provincia.setNombre(rsProvincia.getString("nombre_provincia"));
				direccion.setProvincia(provincia); 
				
				cliente.setDireccion(direccion);
				
				venta.setCliente(cliente);
			//ID VUELO
				psVuelos.setInt(1, rsVentas.getInt("id_venta"));
				rsVuelos = psVuelos.executeQuery(); 
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
				Pais pais21 = new Pais();
				pais21.setId(rsPais.getDouble("id_pais"));
				pais21.setNombre(rsPais.getString("nombre_pais"));
				aeropuerto.setPais(pais21);
				
				psProvincia.setInt(1, rsAeropuerto.getInt("id_provincia"));
				rsProvincia = psProvincia.executeQuery(); 
				Provincia provincia1 = new Provincia();
				provincia1.setId(rsProvincia.getDouble("id_provincia"));
			
				provincia1.setNombre(rsProvincia.getString("nombre_provincia"));
				aeropuerto.setProvincia(provincia1); 
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
				Aerolinea aerolinea1 = new Aerolinea();
				aerolinea1.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea1.setNombre(rsAerolinea.getString("nombre_aerolinea"));


				vuelo.setAerolinea(aerolinea1);
				venta.setVuelo(vuelo);
				
			//ID AEROLINEA 
				
				
	           // aerolinea + alianza
				psAerolinea.setInt(1, rsAeropuerto.getInt("id_venta"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea11 = new Aerolinea();
				aerolinea11.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea11.setNombre(rsAerolinea.getString("nombre_aerolinea"));


				venta.setAerolinea(aerolinea11);
				
						
				
	return venta;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psVuelos.isClosed()) {
					psVuelos.close();
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
				if (!psPasaporte.isClosed()) {
					psPasaporte.close();
				}
				
				if (!psTelefono.isClosed()) {
					psTelefono.close();
				}
				
				if (!psPasajeroFrecuente.isClosed()) {
					psPasajeroFrecuente.close();
				}
				
				if (!psDireccion.isClosed()) {
					psDireccion.close();
				}
				
				if (!psPais.isClosed()) {
					psPais.close();
				}
				
				if (!psAerolinea.isClosed()) {
					psAerolinea.close();
				}
				
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	
	
	@Override
	public void altaVenta(Ventas venta) {
		Connection con = null;
		PreparedStatement psVenta = null;
		try{
			con = getConnection();
			psVenta=con.prepareStatement("INSERT INTO VENTAS VALUES(NEXT VALUE FOR seq_venta,?,?,?,?,?)");
			
			psVenta.setDate(2, (Date) venta.getFechaVenta());
			psVenta.setString(3, venta.getFormaPago());
			psVenta.setDouble(4, venta.getCliente().getId());
			psVenta.setDouble(5, venta.getVuelo().getId());
			psVenta.setDouble(6, venta.getAerolinea().getId());

			psVenta.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVenta.isClosed()){
					psVenta.close();
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
	public void modificarVenta(Ventas venta) {
		Connection con = null;
		PreparedStatement psVenta = null;
		try{
			con = getConnection();
			psVenta=con.prepareStatement("UPDATE VENTAS SET fecha_hs_venta = ? , forma_pago = ?, id_cliente = ?, id_vuelo = ?, id_aerolinea = ? WHERE id_venta = ?");
			
			psVenta.setDate(1, (Date) venta.getFechaVenta());
			psVenta.setString(2, venta.getFormaPago());
			psVenta.setDouble(3, venta.getCliente().getId());
			psVenta.setDouble(4, venta.getVuelo().getId());
			psVenta.setDouble(5, venta.getAerolinea().getId());
			psVenta.setDouble(6, venta.getId());

			psVenta.execute();
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVenta.isClosed()){
					psVenta.close();
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
	public void eliminarVenta(String cdoVenta) {
		Connection con = null;
		PreparedStatement psVenta = null;
		try{
			con = getConnection();
			psVenta=con.prepareStatement("DELETE FROM VENTAS WHERE nro_venta = ?");
			
			psVenta.setString(1, cdoVenta);
			
			psVenta.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psVenta.isClosed()) {
					psVenta.close();
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
