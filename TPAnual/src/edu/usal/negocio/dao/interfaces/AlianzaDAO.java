package edu.usal.negocio.dao.interfaces;
import java.io.IOException;
import java.util.List;

import edu.usal.negocio.dominio.Alianza;

public interface AlianzaDAO {
	
	public List <Alianza> listarAlianzas() throws IOException;

}
