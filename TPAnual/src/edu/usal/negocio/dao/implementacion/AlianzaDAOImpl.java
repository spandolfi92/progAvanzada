package edu.usal.negocio.dao.implementacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.AlianzaDAO;
import edu.usal.negocio.dominio.Alianza;
import edu.usal.util.PropertiesUtil;

public class AlianzaDAOImpl implements AlianzaDAO{
	
	private File archivo;
	private FileReader archivoReader;
	private BufferedReader archivoWFR;

	@Override
	public List<Alianza> listarAlianzas() throws IOException {
		archivo = new File(PropertiesUtil.getPropertyAlianza());
		archivoReader = new FileReader (archivo);
		archivoWFR = new BufferedReader (archivoReader);
		
		String linea;
		List<Alianza> listadoAlianzas = new ArrayList <Alianza>();
		while ((linea= archivoWFR.readLine())!=null) {
			listadoAlianzas.add(ParseAlianza(linea));
		}
	
		return listadoAlianzas;
	}
	
	private Alianza ParseAlianza(String linea) {
		String[] atributos = linea.split(";");
		Alianza alianza = new Alianza();
		alianza.setId(Integer.parseInt(atributos[0]));
		alianza.setNombre(atributos[1]);
		
		return alianza;
	}

}
