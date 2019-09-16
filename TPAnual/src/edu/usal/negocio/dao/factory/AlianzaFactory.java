package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.AlianzaDAOImpl;
import edu.usal.negocio.dao.interfaces.AlianzaDAO;

public class AlianzaFactory {
	
	public static AlianzaDAO getImplementation(String source) {
		if (source.equals("Archivo")) 
			return new AlianzaDAOImpl();
		return null;
	}

	
}
