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

import edu.usal.negocio.dao.interfaces.AerolineaDAO;
import edu.usal.negocio.dominio.Aerolinea;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.util.PropertiesUtil;

public class AerolineaDAOImplSerializacion implements AerolineaDAO{

	private List<Aerolinea> aerolineas = null; 
	
	@Override
	public List<Aerolinea> listarAerolineas() throws IOException, ClassNotFoundException {
		if(aerolineas == null){
			aerolineas = new ArrayList<Aerolinea>();
			File file = new File(PropertiesUtil.getPropertyAerolinea());
			file.createNewFile();
			FileInputStream fiStream = new FileInputStream(file);
			ObjectInputStream oiStream = new ObjectInputStream(fiStream);
			try{
				aerolineas = (List<Aerolinea>)oiStream.readObject();
				oiStream.close();
				fiStream.close();
			} 
			catch (EOFException e) { 
				return new ArrayList<Aerolinea>();
			}
			finally{
				oiStream.close();
				fiStream.close();
			}
		}
		return aerolineas;
	}

	@Override
	public void altaAerolinea(Aerolinea aerolinea) throws Exception {
		listarAerolineas();
		aerolineas.add(aerolinea);
		File file = new File(PropertiesUtil.getPropertyAerolinea());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(aerolineas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
		
	}

	@Override
	public void modificarAerolinea(Aerolinea aerolinea) throws Exception {
		listarAerolineas();
		for(int i = 0; i < aerolineas.size(); i ++){
			if(aerolineas.get(i).getId() == aerolinea.getId())
			{
				aerolineas.set(i, aerolinea);
			}
		}
		File file = new File(PropertiesUtil.getPropertyCliente());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(aerolineas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
		
	}

	@Override
	public void eliminarAerolinea(double id) throws Exception {
		listarAerolineas();
		for(int i = 0; i < aerolineas.size(); i ++){
			if(aerolineas.get(i).getId() == id){
				aerolineas.remove(aerolineas.get(i));
			}
		}
		File file = new File(PropertiesUtil.getPropertyCliente());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(aerolineas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
		
	}
	
}
