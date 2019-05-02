package edu.usal.negocio.dao.interfaces;

import java.util.List;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.negocio.dominio.Ventas;

public interface VentaDAO {
	
	public List <Ventas> listarVentas() throws Exception;
	public void altaVenta (Ventas venta) throws Exception;
	public void modificarVenta (Ventas venta) throws Exception;
	public void eliminarVenta (Ventas venta) throws Exception;

}
