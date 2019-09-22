package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.PasaporteDAO;
import edu.usal.negocio.dominio.Pasaporte;

public class PasaporteDAOImpl implements PasaporteDAO{

	@Override
	public void altaPasaporte(Pasaporte pasaporte, double idCliente) {
		Connection con = null;
		PreparedStatement psPasaporte = null;
		try{
			con = Connect.getConnection();
			psPasaporte=con.prepareStatement("INSERT INTO PASAPORTE VALUES(NEXT VALUE FOR seq_pasaporte, ?, ?, ?, ?, ?, ?)");
			
			psPasaporte.setString(1, pasaporte.getNumero());
			psPasaporte.setString(2, pasaporte.getAutoridadEmision());
			psPasaporte.setDate(3, new java.sql.Date(pasaporte.getFechaEmision().getTime()));
			psPasaporte.setDate(4, new java.sql.Date(pasaporte.getFechaVencimiento().getTime()));
			psPasaporte.setDouble(5, pasaporte.getPais().getId());
			psPasaporte.setDouble(6, idCliente);
			
			psPasaporte.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasaporte.isClosed()){
					psPasaporte.close();
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
	public void modificarPasaporte(Pasaporte pasaporte, double idCliente) {
		Connection con = null;
		PreparedStatement psPasaporte = null;
		try{
			con = Connect.getConnection();
			psPasaporte=con.prepareStatement("UPDATE PASAPORTE \r\n" + 
					"SET nro_pasaporte = ? , autoridad_emision = ? , fecha_emision = ? , \r\n" + 
					"fecha_vencimiento= ? , id_pais = ? , id_cliente = ?\r\n" + 
					"WHERE id_cliente = ?");
			
			psPasaporte.setString(1, pasaporte.getNumero());
			psPasaporte.setString(2, pasaporte.getAutoridadEmision());
			psPasaporte.setDate(3, new java.sql.Date(pasaporte.getFechaEmision().getTime()));
			psPasaporte.setDate(4, new java.sql.Date(pasaporte.getFechaVencimiento().getTime()));
			psPasaporte.setDouble(5, pasaporte.getPais().getId());
			psPasaporte.setDouble(6, idCliente);
			psPasaporte.setDouble(7, idCliente);
			
			psPasaporte.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasaporte.isClosed()){
					psPasaporte.close();
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
	public void eliminarPasaporte(double idCliente) {
		Connection con = null;
		PreparedStatement psPasaporte = null;
		try{
			con = Connect.getConnection();
			psPasaporte=con.prepareStatement("DELETE FROM PASAPORTE WHERE id_cliente = ?");
			
			psPasaporte.setDouble(1, idCliente);
			
			psPasaporte.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasaporte.isClosed()) {
					psPasaporte.close();
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
