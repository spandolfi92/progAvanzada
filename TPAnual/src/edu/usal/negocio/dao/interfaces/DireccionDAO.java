package edu.usal.negocio.dao.interfaces;

import edu.usal.negocio.dominio.Direccion;

public interface DireccionDAO {
	
	public void altaDireccion (Direccion direccion, double idCliente);
	public void modificarDireccion (Direccion direccion, double idCliente);
	public void eliminarDireccion (double idCliente);

}
