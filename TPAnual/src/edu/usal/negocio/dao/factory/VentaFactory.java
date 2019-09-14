package edu.usal.negocio.dao.factory;


import edu.usal.negocio.dao.implementacion.VentaDAOImpl;
import edu.usal.negocio.dao.interfaces.VentaDAO;

public class VentaFactory {
	
	public static VentaDAO getImplementation(String source) {
		if (source.equals("BD")) 
			return new VentaDAOImpl();
		return null;
	}

}
