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

import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.util.PropertiesUtil;

public class ClienteDAOImplSerializacion implements ClienteDAO{

	
	private List<Cliente> clientes = null; 
	
	@Override
	public List<Cliente> listarClientes() throws IOException, ClassNotFoundException {
		if(clientes == null){
			clientes = new ArrayList<Cliente>();
			File file = new File(PropertiesUtil.getPropertyCliente());
			file.createNewFile();
			FileInputStream fiStream = new FileInputStream(file);
			ObjectInputStream oiStream = new ObjectInputStream(fiStream);
			try{
				clientes = (List<Cliente>)oiStream.readObject();
				oiStream.close();
				fiStream.close();
			} 
			catch (EOFException e) { 
				return new ArrayList<Cliente>();
			}
			finally{
				oiStream.close();
				fiStream.close();
			}
		}
		return clientes;
	}

	@Override
	public void altaCliente(Cliente cliente) throws Exception {
		listarClientes();
		clientes.add(cliente);
		File file = new File(PropertiesUtil.getPropertyCliente());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(clientes);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void modificarCliente(Cliente cliente) throws Exception {
		listarClientes();
		for(int i = 0; i < clientes.size(); i ++){
			if(clientes.get(i).getId() == cliente.getId())
			{
				clientes.set(i, cliente);
			}
		}
		File file = new File(PropertiesUtil.getPropertyCliente());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(clientes);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

	@Override
	public void eliminarCliente(double id) throws Exception {
		listarClientes();
		for(int i = 0; i < clientes.size(); i ++){
			if(clientes.get(i).getId() == id){
				clientes.remove(clientes.get(i));
			}
		}
		File file = new File(PropertiesUtil.getPropertyCliente());
		FileOutputStream foStream = new FileOutputStream(file);
		ObjectOutputStream ooStream = new ObjectOutputStream(foStream);
		try{
			file.createNewFile();
			ooStream.writeObject(clientes);
		}
		finally{
			ooStream.close();
			foStream.close();
		}
	}

}
