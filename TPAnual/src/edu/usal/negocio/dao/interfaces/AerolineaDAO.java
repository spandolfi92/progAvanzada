package edu.usal.negocio.dao.interfaces;

import java.util.List;

import edu.usal.negocio.dominio.Aerolinea;

public interface AerolineaDAO {
	
	public List <Aerolinea> listarAerolineas();
	public void altaAerolinea (Aerolinea aerolinea);
	public void modificarAerolinea (Aerolinea aerolinea);
	public void eliminarAerolinea (double id);

}
