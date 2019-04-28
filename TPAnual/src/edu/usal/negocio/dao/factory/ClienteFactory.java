package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.ClienteDAOImplSerializacion;
import edu.usal.negocio.dao.interfaces.ClienteDAO;

public class ClienteFactory {
	
	public static ClienteDAO getImplementation(String source) {
		if (source.equals("Archivo")) 
			return new ClienteDAOImplSerializacion();
		return null;
	}

}
