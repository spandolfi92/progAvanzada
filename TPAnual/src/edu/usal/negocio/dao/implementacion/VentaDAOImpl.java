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

import edu.usal.negocio.dao.interfaces.VentaDAO;
import edu.usal.negocio.dominio.Ventas;
import edu.usal.util.PropertiesUtil;

public class VentaDAOImpl implements VentaDAO{

	
	private List<Ventas> ventas = null;

	@Override
	public List<Ventas> listarVentas() throws IOException, ClassNotFoundException {
		if(ventas == null){
			ventas = new ArrayList<Ventas>();
			File file = new File(PropertiesUtil.getPropertyVenta());
			file.createNewFile();
			FileInputStream fiStream = new FileInputStream(file);
			ObjectInputStream oiStream = new ObjectInputStream(fiStream);
			try{
				ventas = (List<Ventas>)oiStream.readObject();
				oiStream.close();
				fiStream.close();
			} 
			catch (EOFException e) { 
				return new ArrayList<Ventas>();
			}
			finally{
				oiStream.close();
				fiStream.close();
			}
		}
		return ventas;
	}

	@Override
	public void altaVenta(Ventas venta) throws Exception {
		listarVentas();
		ventas.add(venta);
		File file = new File(PropertiesUtil.getPropertyVenta());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(ventas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void modificarVenta(Ventas ventaModificada) throws Exception {
		listarVentas();
		for(int i = 0; i < ventas.size(); i ++){
			if(ventas.get(i).getId() == ventaModificada.getId())
			{
				ventas.set(i, ventaModificada);
			}
		}
		File file = new File(PropertiesUtil.getPropertyVenta());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(ventas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void eliminarVenta(Ventas ventaElegida) throws Exception {
		listarVentas();
		for(int i = 0; i < ventas.size(); i ++){
			if(ventas.get(i).getId() == ventaElegida.getId()){
				ventas.remove(ventas.get(i));
			}
		}
		File file = new File(PropertiesUtil.getPropertyVenta());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(ventas);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}
	


}
