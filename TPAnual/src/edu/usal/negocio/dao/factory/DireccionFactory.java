package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.DireccionDAOImpl;
import edu.usal.negocio.dao.interfaces.DireccionDAO;

public class DireccionFactory {
	
	public static DireccionDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new DireccionDAOImpl();
		return null;
	}


}
