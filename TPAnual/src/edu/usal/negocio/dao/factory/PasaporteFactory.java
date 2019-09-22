package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.PasaporteDAOImpl;
import edu.usal.negocio.dao.interfaces.PasaporteDAO;

public class PasaporteFactory {
	
	public static PasaporteDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new PasaporteDAOImpl();
		return null;
	}

}
