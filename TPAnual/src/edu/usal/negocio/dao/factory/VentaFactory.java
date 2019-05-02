package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.VentaDAOImplSerializacion;
import edu.usal.negocio.dao.interfaces.VentaDAO;

public class VentaFactory {
	
	public static VentaDAO getImplementation(String source) {
		if (source.equals("Archivo")) 
			return new VentaDAOImplSerializacion();
		return null;
	}

}
