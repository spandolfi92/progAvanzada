package edu.usal.negocio.dao.interfaces;
import java.io.IOException;
import java.util.List;

import edu.usal.negocio.dominio.Provincia;

public interface ProvinciaDAO {
	
	public List <Provincia> listarProvincias() throws IOException;
	public Provincia obtenerProvincia(String prov);
}
