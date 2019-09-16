package edu.usal.negocio.dao.interfaces;

import java.util.List;
import edu.usal.negocio.dominio.Cliente;

public interface ClienteDAO {
	
	public List <Cliente> listarClientes();
	public Cliente obtenerCliente(String dni);
	public void altaCliente (Cliente cliente);
	public void modificarCliente (Cliente cliente);
	public void eliminarCliente (String dni);

}
