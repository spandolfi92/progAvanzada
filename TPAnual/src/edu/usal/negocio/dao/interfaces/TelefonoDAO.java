package edu.usal.negocio.dao.interfaces;

import edu.usal.negocio.dominio.Telefono;

public interface TelefonoDAO {
	
	public void altaTelefono (Telefono telefono, double idCliente);
	public void modificarTelefono (Telefono telefono, double idCliente);
	public void eliminarTelefono (double idCliente);

}
