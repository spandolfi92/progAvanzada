package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.TelefonoDAO;
import edu.usal.negocio.dominio.Telefono;

public class TelefonoDAOImpl implements TelefonoDAO{

	@Override
	public void altaTelefono(Telefono telefono, double idCliente) {
		Connection con = null;
		PreparedStatement psTelefono = null;
		try{
			con = Connect.getConnection();
			psTelefono=con.prepareStatement("INSERT INTO TELEFONO VALUES(NEXT VALUE FOR seq_telefono, ?, ?, ?, ?)");
			
			psTelefono.setString(1, telefono.getNumeroPersonal());
			psTelefono.setString(2, telefono.getNumeroCelular());
			psTelefono.setString(3, telefono.getNumeroLaboral());
			psTelefono.setDouble(4, idCliente);
			
			psTelefono.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psTelefono.isClosed()){
					psTelefono.close();
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
	public void modificarTelefono(Telefono telefono, double idCliente) {
		Connection con = null;
		PreparedStatement psTelefono = null;
		try{
			con = Connect.getConnection();
			psTelefono=con.prepareStatement("UPDATE TELEFONO \r\n" + 
					"SET personal = ? , celular = ? , laboral = ? \r\n" + 
					"WHERE id_cliente = ?");
			
			psTelefono.setString(1, telefono.getNumeroPersonal());
			psTelefono.setString(2, telefono.getNumeroCelular());
			psTelefono.setString(3, telefono.getNumeroLaboral());
			psTelefono.setDouble(4, idCliente);
			
			psTelefono.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psTelefono.isClosed()){
					psTelefono.close();
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
	public void eliminarTelefono(double idCliente) {
		Connection con = null;
		PreparedStatement psTelefono = null;
		try{
			con = Connect.getConnection();
			psTelefono=con.prepareStatement("DELETE FROM TELEFONO WHERE id_cliente = ?");
			
			psTelefono.setDouble(1, idCliente);
			
			psTelefono.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psTelefono.isClosed()) {
					psTelefono.close();
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
