package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.AerolineaDAOImpl;
import edu.usal.negocio.dao.interfaces.AerolineaDAO;

public class AerolineaFactory {
	
	public static AerolineaDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new AerolineaDAOImpl();
		return null;
	}


}
