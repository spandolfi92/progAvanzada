package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.ClienteDAOImpl;
import edu.usal.negocio.dao.interfaces.ClienteDAO;

public class ClienteFactory {
	
	public static ClienteDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new ClienteDAOImpl();
		return null;
	}

}
