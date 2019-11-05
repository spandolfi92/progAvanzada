package edu.usal.negocio.dao.interfaces;
import java.util.List;

import edu.usal.negocio.dominio.Pais;

public interface PaisDAO {
	
	public List <Pais> listarPaises();
	public Pais obtenerPais(String nombre);
	public Pais altaPais(String nombre);
}
