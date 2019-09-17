package edu.usal.negocio.dao.interfaces;
import java.io.IOException;
import java.util.List;

import edu.usal.negocio.dominio.Pais;

public interface PaisDAO {
	
	public List <Pais> listarPaises() throws IOException;
	public Pais obtenerPais(String nombre);
}
