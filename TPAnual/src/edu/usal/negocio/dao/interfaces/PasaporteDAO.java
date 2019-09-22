package edu.usal.negocio.dao.interfaces;

import edu.usal.negocio.dominio.Pasaporte;

public interface PasaporteDAO{
	
	public void altaPasaporte (Pasaporte pasaporte, double idCliente);
	public void modificarPasaporte (Pasaporte pasaporte, double idCliente);
	public void eliminarPasaporte (double idCliente);


}
