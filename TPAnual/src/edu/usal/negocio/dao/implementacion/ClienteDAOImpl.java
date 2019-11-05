package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Direccion;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.PasajeroFrecuente;
import edu.usal.negocio.dominio.Pasaporte;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.negocio.dominio.Telefono;

public class ClienteDAOImpl implements ClienteDAO{
	

	@Override
	public List<Cliente> listarClientes() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsCliente = null;
		
		try {
			con = Connect.getConnection();
			stm = con.createStatement();
			rsCliente = stm.executeQuery("SELECT cliente.id_cliente, cliente.nombre, cliente.apellido, cliente.dni, cliente.cuit_cuil, cliente.fecha_nacimiento, cliente.email,\r\n" + 
					"pasaporte.id_pasaporte, pasaporte.nro_pasaporte, pasaporte.autoridad_emision, pasaporte.fecha_emision, pasaporte.fecha_vencimiento,\r\n" + 
					"pais.id_pais as pais1, pais.nombre_pais,\r\n" + 
					"telefono.id_telefono, telefono.personal, telefono.celular, telefono.laboral,\r\n" + 
					"pasajero_frecuente.id_pasajero_frecuente, pasajero_frecuente.alianza, pasajero_frecuente.numero, pasajero_frecuente.categoria,\r\n" + 
					"aerolinea.id_aerolinea, aerolinea.nombre_aerolinea, aerolinea.alianza,\r\n" + 
					"direccion.id_direccion, direccion.calle, direccion.altura, direccion.ciudad, direccion.codigo_postal,\r\n" + 
					"provincia.id_provincia, provincia.nombre_provincia,\r\n" + 
					"a.id_pais as pais2, a.nombre_pais\r\n" + 
					"FROM cliente\r\n" + 
					"JOIN pasaporte on cliente.id_cliente = pasaporte.id_cliente\r\n" + 
					"JOIN pais on pasaporte.id_pais = pais.id_pais\r\n" + 
					"JOIN telefono on cliente.id_cliente = telefono.id_cliente\r\n" + 
					"JOIN pasajero_frecuente on cliente.id_cliente = pasajero_frecuente.id_cliente\r\n" + 
					"JOIN aerolinea on pasajero_frecuente.id_aerolinea = aerolinea.id_aerolinea\r\n" + 
					"JOIN direccion on cliente.id_cliente = direccion.id_cliente\r\n" + 
					"JOIN provincia on direccion.id_provincia = provincia.id_provincia\r\n" + 
					"JOIN pais a on direccion.id_pais = a.id_pais");
			
			List<Cliente> clientes = new ArrayList<Cliente>();

			while (rsCliente.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rsCliente.getInt("id_cliente"));
				cliente.setNombre(rsCliente.getString("nombre"));
				cliente.setApellido(rsCliente.getString("apellido"));
				cliente.setDni(rsCliente.getString("dni"));
				cliente.setCuitCuil(rsCliente.getString("cuit_cuil"));
				cliente.setFechaNacimiento(new Date(rsCliente.getDate("fecha_nacimiento").getTime()));
				cliente.setEmail(rsCliente.getString("email"));

				Pasaporte pasaporte = new Pasaporte();
				pasaporte.setId(rsCliente.getInt("id_pasaporte"));
				pasaporte.setNumero(rsCliente.getString("nro_pasaporte"));
				pasaporte.setAutoridadEmision(rsCliente.getString("autoridad_emision"));
				pasaporte.setFechaEmision(new Date(rsCliente.getDate("fecha_emision").getTime()));
				pasaporte.setFechaVencimiento(new Date(rsCliente.getDate("fecha_vencimiento").getTime()));
				Pais paisPasaporte = new Pais();
				paisPasaporte.setId(rsCliente.getInt("pais1"));
				paisPasaporte.setNombre(rsCliente.getString("nombre_pais"));
				pasaporte.setPais(paisPasaporte);
				cliente.setPasaporte(pasaporte);
				
				Telefono telefono = new Telefono();
				telefono.setId(rsCliente.getInt("id_telefono"));
				telefono.setNumeroPersonal(rsCliente.getString("personal"));
				telefono.setNumeroCelular(rsCliente.getString("celular"));
				telefono.setNumeroLaboral(rsCliente.getString("laboral"));
				cliente.setTelefono(telefono);
				
				PasajeroFrecuente pasajeroFrecuente = new PasajeroFrecuente();
				pasajeroFrecuente.setId(rsCliente.getInt("id_pasajero_frecuente"));
				pasajeroFrecuente.setAlianza(rsCliente.getString("alianza"));
				pasajeroFrecuente.setNumero(rsCliente.getString("numero"));
				pasajeroFrecuente.setCategoria(rsCliente.getString("categoria"));
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsCliente.getInt("id_aerolinea"));
				aerolinea.setNombre(rsCliente.getString("nombre_aerolinea"));
				aerolinea.setAlianza(rsCliente.getString("alianza"));
				pasajeroFrecuente.setAerolinea(aerolinea);
				cliente.setPasajeroFrecuente(pasajeroFrecuente);
				
				Direccion direccion = new Direccion();
				direccion.setId(rsCliente.getInt("id_direccion"));
				direccion.setCalle(rsCliente.getString("calle"));
				direccion.setAltura(rsCliente.getString("altura"));
				direccion.setCiudad(rsCliente.getString("ciudad"));
				direccion.setCodigoPostal(rsCliente.getString("codigo_postal"));
				Provincia provincia = new Provincia();
				provincia.setId(rsCliente.getInt("id_provincia"));
				provincia.setNombre(rsCliente.getString("nombre_provincia"));
				direccion.setProvincia(provincia);
				Pais PaisDireccion = new Pais();
				PaisDireccion.setId(rsCliente.getInt("pais2"));
				PaisDireccion.setNombre(rsCliente.getString("nombre_pais"));
				direccion.setPais(PaisDireccion);
				cliente.setDireccion(direccion);
				
				clientes.add(cliente);
			}
			return clientes;
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

	public Cliente obtenerCliente(String dni){
		Connection con = null;
		PreparedStatement psCliente = null;
		ResultSet rsCliente = null;
		
		try {
			con = Connect.getConnection();
			psCliente = con.prepareStatement("SELECT cliente.id_cliente, cliente.nombre, cliente.apellido, cliente.dni, cliente.cuit_cuil, cliente.fecha_nacimiento, cliente.email,\r\n" + 
					"pasaporte.id_pasaporte, pasaporte.nro_pasaporte, pasaporte.autoridad_emision, pasaporte.fecha_emision, pasaporte.fecha_vencimiento,\r\n" + 
					"pais.id_pais, pais.nombre_pais,\r\n" + 
					"telefono.id_telefono, telefono.personal, telefono.celular, telefono.laboral,\r\n" + 
					"pasajero_frecuente.id_pasajero_frecuente, pasajero_frecuente.alianza, pasajero_frecuente.numero, pasajero_frecuente.categoria,\r\n" + 
					"aerolinea.id_aerolinea, aerolinea.nombre_aerolinea, aerolinea.alianza,\r\n" + 
					"direccion.id_direccion, direccion.calle, direccion.altura, direccion.ciudad, direccion.codigo_postal,\r\n" + 
					"provincia.id_provincia, provincia.nombre_provincia,\r\n" + 
					"a.id_pais, a.nombre_pais\r\n" + 
					"FROM cliente\r\n" + 
					"JOIN pasaporte on cliente.id_cliente = pasaporte.id_cliente\r\n" + 
					"JOIN pais on pasaporte.id_pais = pais.id_pais\r\n" + 
					"JOIN telefono on cliente.id_cliente = telefono.id_cliente\r\n" + 
					"JOIN pasajero_frecuente on cliente.id_cliente = pasajero_frecuente.id_cliente\r\n" + 
					"JOIN aerolinea on pasajero_frecuente.id_aerolinea = aerolinea.id_aerolinea\r\n" + 
					"JOIN direccion on cliente.id_cliente = direccion.id_cliente\r\n" + 
					"JOIN provincia on direccion.id_provincia = provincia.id_provincia\r\n" + 
					"JOIN pais a on direccion.id_pais = a.id_pais\r\n" + 
					"WHERE cliente.dni = ?");
			
			psCliente.setString(1, dni);
			rsCliente = psCliente.executeQuery();
			
			rsCliente.next();
			
			Cliente cliente = new Cliente();
			cliente.setId(rsCliente.getInt("id_cliente"));
			cliente.setNombre(rsCliente.getString("nombre"));
			cliente.setApellido(rsCliente.getString("apellido"));
			cliente.setDni(rsCliente.getString("dni"));
			cliente.setCuitCuil(rsCliente.getString("cuit_cuil"));
			cliente.setFechaNacimiento(new Date(rsCliente.getDate("fecha_nacimiento").getTime()));
			cliente.setEmail(rsCliente.getString("email"));

			Pasaporte pasaporte = new Pasaporte();
			pasaporte.setId(rsCliente.getInt("id_pasaporte"));
			pasaporte.setNumero(rsCliente.getString("nro_pasaporte"));
			pasaporte.setAutoridadEmision(rsCliente.getString("autoridad_emision"));
			pasaporte.setFechaEmision(new Date(rsCliente.getDate("fecha_emision").getTime()));
			pasaporte.setFechaVencimiento(new Date(rsCliente.getDate("fecha_vencimiento").getTime()));
			Pais paisPasaporte = new Pais();
			paisPasaporte.setId(rsCliente.getInt("id_pais"));
			paisPasaporte.setNombre(rsCliente.getString("nombre_pais"));
			pasaporte.setPais(paisPasaporte);
			cliente.setPasaporte(pasaporte);
			
			Telefono telefono = new Telefono();
			telefono.setId(rsCliente.getInt("id_telefono"));
			telefono.setNumeroPersonal(rsCliente.getString("personal"));
			telefono.setNumeroCelular(rsCliente.getString("celular"));
			telefono.setNumeroLaboral(rsCliente.getString("laboral"));
			cliente.setTelefono(telefono);
			
			PasajeroFrecuente pasajeroFrecuente = new PasajeroFrecuente();
			pasajeroFrecuente.setId(rsCliente.getInt("id_pasajero_frecuente"));
			pasajeroFrecuente.setAlianza(rsCliente.getString("alianza"));
			pasajeroFrecuente.setNumero(rsCliente.getString("numero"));
			pasajeroFrecuente.setCategoria(rsCliente.getString("categoria"));
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setId(rsCliente.getInt("id_aerolinea"));
			aerolinea.setNombre(rsCliente.getString("nombre_aerolinea"));
			aerolinea.setAlianza(rsCliente.getString("alianza"));
			pasajeroFrecuente.setAerolinea(aerolinea);
			cliente.setPasajeroFrecuente(pasajeroFrecuente);
			
			Direccion direccion = new Direccion();
			direccion.setId(rsCliente.getInt("id_direccion"));
			direccion.setCalle(rsCliente.getString("calle"));
			direccion.setAltura(rsCliente.getString("altura"));
			direccion.setCiudad(rsCliente.getString("ciudad"));
			direccion.setCodigoPostal(rsCliente.getString("codigo_postal"));
			Provincia provincia = new Provincia();
			provincia.setId(rsCliente.getInt("id_provincia"));
			provincia.setNombre(rsCliente.getString("nombre_provincia"));
			direccion.setProvincia(provincia);
			Pais PaisDireccion = new Pais();
			PaisDireccion.setId(rsCliente.getInt("id_pais"));
			PaisDireccion.setNombre(rsCliente.getString("nombre_pais"));
			direccion.setPais(PaisDireccion);
			cliente.setDireccion(direccion);
		
			return cliente;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (!psCliente.isClosed()) {
					psCliente.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	@Override
	public double altaCliente(Cliente cliente) {
		Connection con = null;
		PreparedStatement psCliente = null;
		PreparedStatement psCliente2 = null;
		ResultSet rsCliente = null;
		double idCliente = 0;
		try{
			con = Connect.getConnection();
			psCliente=con.prepareStatement("INSERT INTO CLIENTE VALUES(NEXT VALUE FOR seq_cliente, ?, ?, ?, ?, ?, ?)");
			
			psCliente.setString(1, cliente.getNombre());
			psCliente.setString(2, cliente.getApellido());
			psCliente.setString(3, cliente.getDni());
			psCliente.setString(4, cliente.getCuitCuil());
			psCliente.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
			psCliente.setString(6, cliente.getEmail());
			
			psCliente.execute();
			
			psCliente2=con.prepareStatement("SELECT * FROM CLIENTE WHERE DNI = " + cliente.getDni());
			rsCliente = psCliente2.executeQuery();
			rsCliente.next();
		
			idCliente = rsCliente.getInt("id_cliente");
			
			
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psCliente.isClosed()){
					psCliente.close();
				}
				if(!psCliente2.isClosed()){
					psCliente2.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idCliente;
		
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		Connection con = null;
		PreparedStatement psCliente = null;
		try{
			con = Connect.getConnection();
			psCliente=con.prepareStatement("UPDATE CLIENTE SET nombre = ? , apellido = ? , dni = ? , cuit_cuil = ? , fecha_nacimiento = ? , email = ? WHERE id_cliente = ?");
			
			psCliente.setString(1, cliente.getNombre());
			psCliente.setString(2, cliente.getApellido());
			psCliente.setString(3, cliente.getDni());
			psCliente.setString(4, cliente.getCuitCuil());
			psCliente.setDate(5, new java.sql.Date(cliente.getFechaNacimiento().getTime()));
			psCliente.setString(6, cliente.getEmail());
			psCliente.setDouble(7, cliente.getId());
			
			psCliente.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psCliente.isClosed()){
					psCliente.close();
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
	public void eliminarCliente(double idCliente) {
		Connection con = null;
		PreparedStatement psCliente = null;
		try{
			con = Connect.getConnection();
			psCliente=con.prepareStatement("DELETE FROM CLIENTE WHERE id_cliente = ?");
			
			psCliente.setDouble(1, idCliente);
			
			psCliente.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psCliente.isClosed()) {
					psCliente.close();
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
