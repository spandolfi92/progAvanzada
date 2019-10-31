package Principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.usal.negocio.dao.factory.AerolineaFactory;
import edu.usal.negocio.dao.factory.AlianzaFactory;
import edu.usal.negocio.dao.factory.ClienteFactory;
import edu.usal.negocio.dao.factory.DireccionFactory;
import edu.usal.negocio.dao.factory.PasajeroFrecuenteFactory;
import edu.usal.negocio.dao.factory.PasaporteFactory;
import edu.usal.negocio.dao.factory.TelefonoFactory;
import edu.usal.negocio.dao.implementacion.Connect;
import edu.usal.negocio.dao.interfaces.AerolineaDAO;
import edu.usal.negocio.dao.interfaces.AlianzaDAO;
import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dao.interfaces.DireccionDAO;
import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;
import edu.usal.negocio.dao.interfaces.PasaporteDAO;
import edu.usal.negocio.dao.interfaces.TelefonoDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Direccion;
import edu.usal.negocio.dominio.Pais;
import edu.usal.negocio.dominio.PasajeroFrecuente;
import edu.usal.negocio.dominio.Pasaporte;
import edu.usal.negocio.dominio.Provincia;
import edu.usal.negocio.dominio.Telefono;

public class Main {

	public static void main(String[] args) throws SQLException{

		AerolineaDAO aerolineaDAO = AerolineaFactory.getImplementation("BD");

			
			
		
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setAlianza("Oneworld");
			aerolinea.setNombre("Prueba");
			
			aerolineaDAO.altaAerolinea(aerolinea);
			
		
		
		/*ClienteDAO clienteDAO = ClienteFactory.getImplementation("BD");
		PasaporteDAO pasaporteDAO = PasaporteFactory.getImplementation("BD");
		TelefonoDAO telefonoDAO = TelefonoFactory.getImplementation("BD");
		PasajeroFrecuenteDAO pasajeroFrecuenteDAO = PasajeroFrecuenteFactory.getImplementation("BD");
		DireccionDAO direccionDAO = DireccionFactory.getImplementation("BD");
		
		
		Connection con = null;
		con = Connect.getConnection();
		double idCliente = 0;	
		
		try {
			con.setAutoCommit(false);
			
			//Cargo cliente
			Cliente cliente = new Cliente();
			cliente.setNombre("Sofia");
			cliente.setApellido("Pandolfi");
			cliente.setDni("36873765");
			cliente.setCuitCuil("27368737653");
			String fechaNacimiento = "1992-05-14 10:30:00";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date fecha;
			try {
				fecha = format.parse(fechaNacimiento);
				cliente.setFechaNacimiento(fecha);
			} catch (ParseException e) {
					e.printStackTrace();
			}
			cliente.setEmail("sofi_pandolfi@hotmail.com");
			idCliente= clienteDAO.altaCliente(cliente);
			
			//Cargo pasaporte
			Pasaporte pasaporte = new Pasaporte();
			pasaporte.setNumero("123456");
			pasaporte.setAutoridadEmision("Autoridad emision");
			String fechaEmision = "2018-05-14";
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha2;
			try {
				fecha2 = format2.parse(fechaEmision);
				pasaporte.setFechaEmision(fecha2);
			} catch (ParseException e) {
					e.printStackTrace();
			}
			String fechaVencimiento = "2023-05-14";
			SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha3;
			try {
				fecha3 = format3.parse(fechaVencimiento);
				pasaporte.setFechaVencimiento(fecha3);
			} catch (ParseException e) {
					e.printStackTrace();
			}
			Pais pais = new Pais();
			pais.setId(9);
			pais.setNombre("Argentina");
			pasaporte.setPais(pais);
			pasaporteDAO.altaPasaporte(pasaporte, idCliente);
		
			//Cargo telefono
			Telefono telefono = new Telefono();
			telefono.setNumeroCelular("153333333");
			telefono.setNumeroPersonal("153333333");
			telefono.setNumeroLaboral("153333333");
			telefonoDAO.altaTelefono(telefono, idCliente);
			
			//Cargo pasajero frecuente
			PasajeroFrecuente pasajeroFrecuente = new PasajeroFrecuente();
			pasajeroFrecuente.setAlianza("Alianza");
			pasajeroFrecuente.setNumero("1234");
			pasajeroFrecuente.setCategoria("1234");
			Aerolinea aerolinea = new Aerolinea();
			aerolinea.setId(1);
			pasajeroFrecuente.setAerolinea(aerolinea);
			pasajeroFrecuenteDAO.altaPasajeroFrecuente(pasajeroFrecuente, idCliente);
			
			//Cargo direccion
			Direccion direccion = new Direccion();
			direccion.setCalle("calle");
			direccion.setAltura("Altura");
			direccion.setCiudad("Ciudad");
			direccion.setCodigoPostal("123");
			Provincia provincia = new Provincia();
			provincia.setId(1);
			direccion.setProvincia(provincia);
			direccion.setPais(pais);
			direccionDAO.altaDireccion(direccion, idCliente);
			
			
			
			Connect.commit();
		} catch(SQLException e) {
			Connect.rollback();
		}*/

	}

}
