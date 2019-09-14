package edu.usal.negocio.dao.implementacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.VueloDAO;
import edu.usal.negocio.dominio.Ventas;
import edu.usal.negocio.dominio.Vuelos;
import edu.usal.util.PropertiesUtil;

public class VueloDAOImpl implements VueloDAO{

	private List<Vuelos> vuelos = null;

	@Override
	public List<Vuelos> listarVuelos() throws IOException, ClassNotFoundException {
		if(vuelos == null){
			vuelos = new ArrayList<Vuelos>();
			File file = new File(PropertiesUtil.getPropertyVuelo());
			file.createNewFile();
			FileInputStream fiStream = new FileInputStream(file);
			ObjectInputStream oiStream = new ObjectInputStream(fiStream);
			try{
				vuelos = (List<Vuelos>)oiStream.readObject();
				oiStream.close();
				fiStream.close();
			} 
			catch (EOFException e) { 
				return new ArrayList<Vuelos>();
			}
			finally{
				oiStream.close();
				fiStream.close();
			}
		}
		return vuelos;
	}

	@Override
	public void altaVuelo(Vuelos vuelo) throws Exception {
		listarVuelos();
		vuelos.add(vuelo);
		File file = new File(PropertiesUtil.getPropertyVuelo());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(vuelos);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void modificarVuelo(Vuelos vueloModificado) throws Exception {
		listarVuelos();
		for(int i = 0; i < vuelos.size(); i ++){
			if(vuelos.get(i).getId()==vueloModificado.getId())
			{
				vuelos.set(i, vueloModificado);
			}
		}
		File file = new File(PropertiesUtil.getPropertyVuelo());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(vuelos);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void eliminarVuelo(Vuelos vueloElegido) throws Exception {
		listarVuelos();
		for(int i = 0; i < vuelos.size(); i ++){
			if(vuelos.get(i).getId()==vueloElegido.getId()){
				vuelos.remove(vuelos.get(i));
			}
		}
		File file = new File(PropertiesUtil.getPropertyVuelo());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(vuelos);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}
	
	

}
