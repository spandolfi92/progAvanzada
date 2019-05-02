package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.VueloDAOImplSerializacion;
import edu.usal.negocio.dao.interfaces.VueloDAO;

public class VueloFactory {
	
	public static VueloDAO getImplementation(String source) {
		if (source.equals("Archivo")) 
			return new VueloDAOImplSerializacion();
		return null;
	}

}
