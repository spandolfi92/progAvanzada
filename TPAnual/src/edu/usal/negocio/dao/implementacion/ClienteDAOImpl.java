package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Alianza;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Direccion;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.PasajeroFrecuente;
import edu.usal.negocio.dominio.Pasaporte;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.negocio.dominio.Telefono;
import edu.usal.util.PropertiesUtil;

public class ClienteDAOImpl implements ClienteDAO{
	

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
	public List<Cliente> listarClientes() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsCliente = null;
		PreparedStatement psPasaporte = null;
		ResultSet rsPasaporte = null;
		PreparedStatement psTelefono = null;
		ResultSet rsTelefono = null;
		PreparedStatement psPasajeroFrecuente = null;
		ResultSet rsPasajeroFrecuente = null;
		PreparedStatement psDireccion = null;
		ResultSet rsDireccion = null;
		PreparedStatement psPais = null;
		ResultSet rsPais = null;
		PreparedStatement psAerolinea = null;
		ResultSet rsAerolinea = null;
		PreparedStatement psProvincia = null;
		ResultSet rsProvincia = null;


		try {
			con = getConnection();
			stm = con.createStatement();
			rsCliente = stm.executeQuery("SELECT * FROM CLIENTE");
			psPasaporte = con.prepareStatement("SELECT * FROM Pasaporte where id_cliente= ?");
			psTelefono = con.prepareStatement("SELECT * FROM Telefono where id_cliente= ?");
			psPasajeroFrecuente = con.prepareStatement("SELECT * FROM pasajero_frecuente where id_cliente= ?");
			psDireccion = con.prepareStatement("SELECT * FROM Direccion where id_cliente= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			psAerolinea = con.prepareStatement("SELECT * FROM Aerolineas where id_aerolinea= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			
			List<Cliente> clientes = new ArrayList<Cliente>();

			while (rsCliente.next()) {
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
				
				Alianza alianza = new Alianza();
				alianza.setNombre(rsPasajeroFrecuente.getString("alianza"));
				pasajeroFrecuente.setAlianza(alianza);
				
				psAerolinea.setInt(1, rsPasajeroFrecuente.getInt("id_aerolinea"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				Alianza alianza2 = new Alianza();
				alianza.setNombre(rsAerolinea.getString("alianza"));
				aerolinea.setAlianza(alianza);
				pasajeroFrecuente.setAerolinea(aerolinea);
				
				psDireccion.setInt(1, rsDireccion.getInt("id_direccion"));
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

	public Cliente obtenerCliente(String dni){
		Connection con = null;
		PreparedStatement psCliente = null;
		ResultSet rsCliente = null;
		PreparedStatement psPasaporte = null;
		ResultSet rsPasaporte = null;
		PreparedStatement psTelefono = null;
		ResultSet rsTelefono = null;
		PreparedStatement psPasajeroFrecuente = null;
		ResultSet rsPasajeroFrecuente = null;
		PreparedStatement psDireccion = null;
		ResultSet rsDireccion = null;
		PreparedStatement psPais = null;
		ResultSet rsPais = null;
		PreparedStatement psAerolinea = null;
		ResultSet rsAerolinea = null;
		PreparedStatement psProvincia = null;
		ResultSet rsProvincia = null;


		try {
			con = getConnection();
			psCliente = con.prepareStatement("SELECT * FROM CLIENTE where dni= ?");
			psPasaporte = con.prepareStatement("SELECT * FROM Pasaporte where id_cliente= ?");
			psTelefono = con.prepareStatement("SELECT * FROM Telefono where id_cliente= ?");
			psPasajeroFrecuente = con.prepareStatement("SELECT * FROM pasajero_frecuente where id_cliente= ?");
			psDireccion = con.prepareStatement("SELECT * FROM Direccion where id_cliente= ?");
			psPais = con.prepareStatement("SELECT * FROM PAIS where id_pais= ?");
			psAerolinea = con.prepareStatement("SELECT * FROM Aerolineas where id_aerolinea= ?");
			psProvincia = con.prepareStatement("SELECT * FROM PROVINCIA where id_provincia= ?");
			
			
				psCliente.setString(1, dni);
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
				
				Alianza alianza = new Alianza();
				alianza.setNombre(rsPasajeroFrecuente.getString("alianza"));
				pasajeroFrecuente.setAlianza(alianza);
				
				psAerolinea.setInt(1, rsPasajeroFrecuente.getInt("id_aerolinea"));
				rsAerolinea = psAerolinea.executeQuery();
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setId(rsAerolinea.getInt("id_aerolinea"));
				aerolinea.setNombre(rsAerolinea.getString("nombre_aerolinea"));
				Alianza alianza2 = new Alianza();
				alianza.setNombre(rsAerolinea.getString("alianza"));
				aerolinea.setAlianza(alianza);
				pasajeroFrecuente.setAerolinea(aerolinea);
				
				psDireccion.setInt(1, rsDireccion.getInt("id_direccion"));
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
	public void altaCliente(Cliente cliente) {
		Connection con = null;
		PreparedStatement psCliente = null;
		try{
			con = getConnection();
			psCliente=con.prepareStatement("INSERT INTO CLIENTE VALUES(NEXT VALUE FOR seq_cliente, ?, ?, ?, ?, ?, ?)");
			
			psCliente.setString(2, cliente.getNombre());
			psCliente.setString(3, cliente.getApellido());
			psCliente.setString(4, cliente.getDni());
			psCliente.setString(5, cliente.getCuitCuil());
			psCliente.setDate(6, cliente.getFechaNacimiento());
			psCliente.setString(7, cliente.getEmail());
			
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
	public void modificarCliente(Cliente cliente) {
		Connection con = null;
		PreparedStatement psCliente = null;
		try{
			con = getConnection();
			psCliente=con.prepareStatement("UPDATE CLIENTE SET nombre = ? , apellido = ? , dni = ? , cuit_cuil = ? , fecha_nacimiento = ? , email = ? WHERE dni = ?");
			
			psCliente.setString(2, cliente.getNombre());
			psCliente.setString(3, cliente.getApellido());
			psCliente.setString(4, cliente.getDni());
			psCliente.setString(5, cliente.getCuitCuil());
			psCliente.setDate(6, cliente.getFechaNacimiento());
			psCliente.setString(7, cliente.getEmail());
			psCliente.setString(8, cliente.getDni());
			
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
	public void eliminarCliente(String dni) {
		Connection con = null;
		PreparedStatement psCliente = null;
		try{
			con = getConnection();
			psCliente=con.prepareStatement("DELETE FROM CLIENTE WHERE dni = ?");
			
			psCliente.setString(1, dni);
			
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
