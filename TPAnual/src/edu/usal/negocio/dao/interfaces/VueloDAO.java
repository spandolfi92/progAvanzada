package edu.usal.negocio.dao.interfaces;

import java.util.List;

import edu.usal.negocio.dominio.Vuelos;

public interface VueloDAO {
	
	public List<Vuelos> listarVuelos();
	public Vuelos obtenerVuelo(String codigovuelo);
	public void altaVuelo (Vuelos codigovuelo);
	public void modificarVuelo (Vuelos codigovuelo);
	public void eliminarVuelo (String codigovuelo);

}
