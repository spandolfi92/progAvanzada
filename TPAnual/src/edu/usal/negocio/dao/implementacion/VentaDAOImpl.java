package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Ventas;
import edu.usal.negocio.dominio.Vuelos;

public class VentaDAOImpl implements VentaDAO{

	
	@Override
	public List<Ventas>listarVentas() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsVentas = null;

		try {
			con = Connect.getConnection();
			stm = con.createStatement();
			
			rsVentas = stm.executeQuery("select ventas.id_venta, ventas.fecha_hs_venta, ventas.forma_pago,\r\n" + 
					"cliente.nombre, cliente.id_cliente, cliente.apellido, cliente.dni,\r\n" + 
					"vuelos.nro_vuelo, vuelos.id_vuelo, vuelos.fecha_hs_salida, vuelos.fecha_hs_llegada,\r\n" + 
					"aerolinea.nombre_aerolinea, aerolinea.id_aerolinea \r\n" + 
					"from ventas\r\n" + 
					"join cliente on ventas.id_cliente = cliente.id_cliente\r\n" + 
					"join vuelos on ventas.id_vuelo = vuelos.id_vuelo\r\n" + 
					"join aerolinea on ventas.id_aerolinea = aerolinea.id_aerolinea");
			
			List<Ventas> ventas = new ArrayList<Ventas>();
			
			while (rsVentas.next()) {
			
				Ventas venta = new Ventas();
				venta.setId(rsVentas.getInt("id_venta"));
				venta.setFechaVenta(rsVentas.getDate("fecha_hs_venta"));
				venta.setFormaPago(rsVentas.getString("forma_pago"));
				
				Cliente cliente = new Cliente();
				cliente.setNombre(rsVentas.getString("nombre"));
				cliente.setApellido(rsVentas.getString("apellido"));
				cliente.setDni(rsVentas.getString("dni"));
				cliente.setId(rsVentas.getInt("id_cliente"));
				venta.setCliente(cliente);
				
				Vuelos vuelo = new Vuelos();
				vuelo.setNumeroVuelo(rsVentas.getString("nro_vuelo"));
				vuelo.setFechaLlegada(rsVentas.getDate("fecha_hs_llegada"));
				vuelo.setFechaSalida(rsVentas.getDate("fecha_hs_salida"));
				vuelo.setId(rsVentas.getInt("id_vuelo"));
				venta.setVuelo(vuelo);
			
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setNombre(rsVentas.getString("nombre_aerolinea"));
				aerolinea.setId(rsVentas.getInt("id_aerolinea"));
				venta.setAerolinea(aerolinea);
				
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
	
	public Ventas obtenerVenta(double id){
		Connection con = null;
		PreparedStatement psVenta = null;
		ResultSet rsVenta = null;

		try {
			con = Connect.getConnection();
			
			psVenta = con.prepareStatement("select ventas.id_venta, ventas.fecha_hs_venta, ventas.forma_pago,\r\n" + 
					"cliente.nombre, cliente.apellido, cliente.dni,\r\n" + 
					"vuelos.nro_vuelo, vuelos.fecha_hs_salida, vuelos.fecha_hs_llegada,\r\n" + 
					"aerolinea.nombre_aerolinea\r\n" + 
					"from ventas\r\n" + 
					"join cliente on ventas.id_cliente = cliente.id_cliente\r\n" + 
					"join vuelos on ventas.id_vuelo = vuelos.id_vuelo\r\n" + 
					"join aerolinea on ventas.id_aerolinea = aerolinea.id_aerolinea where ventas.id_venta= ?");
			
			psVenta.setDouble(1, id);
			rsVenta = psVenta.executeQuery();
			
			rsVenta.next();
			
				Ventas venta = new Ventas();
				venta.setId(rsVenta.getInt("id_venta"));
				venta.setFechaVenta(rsVenta.getDate("fecha_hs_venta"));
				venta.setFormaPago(rsVenta.getString("forma_pago"));
				
				Cliente cliente = new Cliente();
				cliente.setNombre(rsVenta.getString("nombre"));
				cliente.setApellido(rsVenta.getString("apellido"));
				cliente.setDni(rsVenta.getString("dni"));
				venta.setCliente(cliente);
				
				Vuelos vuelo = new Vuelos();
				vuelo.setNumeroVuelo(rsVenta.getString("nro_vuelo"));
				vuelo.setFechaLlegada(rsVenta.getDate("fecha_hs_llegada"));
				vuelo.setFechaSalida(rsVenta.getDate("fecha_hs_salida"));
				venta.setVuelo(vuelo);
			
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setNombre(rsVenta.getString("nombre_aerolinea"));
				venta.setAerolinea(aerolinea);
	
				return venta;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psVenta.isClosed()) {
					psVenta.close();
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
			con = Connect.getConnection();
			psVenta=con.prepareStatement("INSERT INTO VENTAS VALUES(NEXT VALUE FOR seq_ventas,?,?,?,?,?)");
			
			psVenta.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
			psVenta.setString(2, venta.getFormaPago());
			psVenta.setDouble(3, venta.getCliente().getId());
			psVenta.setDouble(4, venta.getVuelo().getId());
			psVenta.setDouble(5, venta.getAerolinea().getId());

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
			con = Connect.getConnection();
			psVenta=con.prepareStatement("update ventas set fecha_hs_venta=?, forma_pago=?,\r\n" + 
					"id_cliente=?, id_vuelo=?, id_aerolinea=?\r\n" + 
					"where id_venta=?");
			
			psVenta.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
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
	public void eliminarVenta(double id) {
		Connection con = null;
		PreparedStatement psVenta = null;
		try{
			con = Connect.getConnection();
			psVenta=con.prepareStatement("DELETE FROM VENTAS WHERE id_venta = ?");
			
			psVenta.setDouble(1, id);
			
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
