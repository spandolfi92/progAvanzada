package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.VueloDAOImpl;
import edu.usal.negocio.dao.interfaces.VueloDAO;

public class VueloFactory {
	
	public static VueloDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new VueloDAOImpl();
		return null;
	}

}
