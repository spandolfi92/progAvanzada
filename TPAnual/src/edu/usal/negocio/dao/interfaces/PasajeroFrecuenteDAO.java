package edu.usal.negocio.dao.interfaces;

import edu.usal.negocio.dominio.PasajeroFrecuente;

public interface PasajeroFrecuenteDAO {
	
	public void altaPasajeroFrecuente (PasajeroFrecuente pasajeroFrecuente, double idCliente);
	public void modificarPasajeroFrecuente (PasajeroFrecuente pasajeroFrecuente, double idCliente);
	public void eliminarPasajeroFrecuente (double idCliente);

}
