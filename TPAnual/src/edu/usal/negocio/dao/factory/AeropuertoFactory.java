package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.AeropuertoDAOImpl;
import edu.usal.negocio.dao.interfaces.AeropuertoDAO;

public class AeropuertoFactory {
	
	public static AeropuertoDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new AeropuertoDAOImpl();
		return null;
	}

}
