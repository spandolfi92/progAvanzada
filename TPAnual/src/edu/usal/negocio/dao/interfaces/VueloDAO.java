package edu.usal.negocio.dao.interfaces;

import java.util.List;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Ventas;
import edu.usal.negocio.dominio.Vuelos;

public interface VueloDAO {
	
	public List <Vuelos> listarVuelos() throws Exception;
	public void altaVuelo (Vuelos vuelo) throws Exception;
	public void modificarVuelo (Vuelos vuelo) throws Exception;
	public void eliminarVuelo (Vuelos vuelo) throws Exception;

}
