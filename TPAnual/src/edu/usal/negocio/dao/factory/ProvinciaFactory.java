package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.ProvinciaDAOImpl;
import edu.usal.negocio.dao.interfaces.ProvinciaDAO;

public class ProvinciaFactory {
	
	public static ProvinciaDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new ProvinciaDAOImpl();
		return null;
	}


}
