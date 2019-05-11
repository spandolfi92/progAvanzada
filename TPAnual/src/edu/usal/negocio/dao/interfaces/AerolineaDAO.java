package edu.usal.negocio.dao.interfaces;

import java.util.List;

import edu.usal.negocio.dominio.Aerolinea;

public interface AerolineaDAO {
	
	public List <Aerolinea> listarAerolineas() throws Exception;
	public void altaAerolinea (Aerolinea aerolinea) throws Exception;
	public void modificarAerolinea (Aerolinea aerolinea) throws Exception;
	public void eliminarAerolinea (double id) throws Exception;

}
