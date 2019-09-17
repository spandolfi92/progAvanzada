package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.PaisDAOImpl;
import edu.usal.negocio.dao.interfaces.PaisDAO;

public class PaisFactory {
	
	public static PaisDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new PaisDAOImpl();
		return null;
	}


}
