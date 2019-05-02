package edu.usal.negocio.dao.factory;

import edu.usal.negocio.dao.implementacion.AerolineaDAOImplSerializacion;
import edu.usal.negocio.dao.interfaces.AerolineaDAO;

public class AerolineaFactory {
	
	public static AerolineaDAO getImplementation(String source) {
		if (source.equals("Archivo")) 
			return new AerolineaDAOImplSerializacion();
		return null;
	}


}
