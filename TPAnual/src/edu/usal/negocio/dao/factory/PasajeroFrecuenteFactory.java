package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.PasajeroFrecuenteDAOImpl;
import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;

public class PasajeroFrecuenteFactory {
	
	public static PasajeroFrecuenteDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new PasajeroFrecuenteDAOImpl();
		return null;
	}

}
