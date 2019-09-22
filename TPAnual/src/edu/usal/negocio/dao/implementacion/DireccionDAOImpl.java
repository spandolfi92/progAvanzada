package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.DireccionDAO;
import edu.usal.negocio.dominio.Direccion;

public class DireccionDAOImpl implements DireccionDAO{

	@Override
	public void altaDireccion(Direccion direccion, double idCliente) {
		Connection con = null;
		PreparedStatement psDireccion = null;
		try{
			con = Connect.getConnection();
			psDireccion=con.prepareStatement("INSERT INTO DIRECCION VALUES(NEXT VALUE FOR seq_direccion, ?, ?, ?, ?, ?, ?, ?)");
			
			psDireccion.setString(1, direccion.getCalle());
			psDireccion.setString(2, direccion.getAltura());
			psDireccion.setString(3, direccion.getCiudad());
			psDireccion.setString(4, direccion.getCodigoPostal());
			psDireccion.setDouble(5, direccion.getPais().getId());
			psDireccion.setDouble(6, direccion.getProvincia().getId());
			psDireccion.setDouble(7, idCliente);

			psDireccion.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psDireccion.isClosed()){
					psDireccion.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void modificarDireccion(Direccion direccion, double idCliente) {
		Connection con = null;
		PreparedStatement psDireccion = null;
		try{
			con = Connect.getConnection();
			psDireccion=con.prepareStatement("UPDATE DIRECCION \r\n" + 
					"SET calle = ? , altura = ? , ciudad = ? , codigo_postal = ? ,\r\n" + 
					"id_pais = ? , id_provincia = ? , id_cliente = ?\r\n" + 
					"WHERE id_cliente = ?");
			
			psDireccion.setString(1, direccion.getCalle());
			psDireccion.setString(2, direccion.getAltura());
			psDireccion.setString(3, direccion.getCiudad());
			psDireccion.setString(4, direccion.getCodigoPostal());
			psDireccion.setDouble(5, direccion.getPais().getId());
			psDireccion.setDouble(6, direccion.getProvincia().getId());
			psDireccion.setDouble(7, idCliente);
			psDireccion.setDouble(8, idCliente);

			psDireccion.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psDireccion.isClosed()){
					psDireccion.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void eliminarDireccion(double idCliente) {
		Connection con = null;
		PreparedStatement psDireccion = null;
		try{
			con = Connect.getConnection();
			psDireccion=con.prepareStatement("DELETE FROM DIRECCION WHERE id_cliente = ?");
			
			psDireccion.setDouble(1, idCliente);
			
			psDireccion.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psDireccion.isClosed()) {
					psDireccion.close();
				}
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
