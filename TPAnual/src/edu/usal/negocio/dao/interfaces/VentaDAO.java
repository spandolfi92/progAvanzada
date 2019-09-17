package edu.usal.negocio.dao.interfaces;

import java.util.List;

import edu.usal.negocio.dominio.Ventas;

public interface VentaDAO {
	
	public List <Ventas> listarVentas();
	public Ventas obtenerVenta(String codigoVenta);
	public void altaVenta (Ventas venta);
	public void modificarVenta (Ventas venta);
	public void eliminarVenta (String codigoVenta);

}
