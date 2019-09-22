package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.TelefonoDAOImpl;
import edu.usal.negocio.dao.interfaces.TelefonoDAO;

public class TelefonoFactory {
	
	public static TelefonoDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new TelefonoDAOImpl();
		return null;
	}

}
