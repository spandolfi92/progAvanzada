package edu.usal.negocio.dao.interfaces;
import java.util.List;

import edu.usal.negocio.dominio.Provincia;

public interface ProvinciaDAO {
	
	public List <Provincia> listarProvincias();
	public Provincia obtenerProvincia(String prov);
}
