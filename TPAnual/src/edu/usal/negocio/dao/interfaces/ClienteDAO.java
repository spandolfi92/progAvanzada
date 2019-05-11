package edu.usal.negocio.dao.interfaces;

import java.util.List;
import edu.usal.negocio.dominio.Cliente;

public interface ClienteDAO {
	
	public List <Cliente> listarClientes() throws Exception;
	public void altaCliente (Cliente cliente) throws Exception;
	public void modificarCliente (Cliente cliente) throws Exception;
	public void eliminarCliente (double id) throws Exception;

}
