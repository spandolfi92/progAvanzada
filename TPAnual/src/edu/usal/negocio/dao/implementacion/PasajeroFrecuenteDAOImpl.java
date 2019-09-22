package edu.usal.negocio.dao.implementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.usal.negocio.dao.interfaces.PasajeroFrecuenteDAO;
import edu.usal.negocio.dominio.PasajeroFrecuente;

public class PasajeroFrecuenteDAOImpl implements PasajeroFrecuenteDAO{

	@Override
	public void altaPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, double idCliente) {
		Connection con = null;
		PreparedStatement psPasajeroFrecuente = null;
		try{
			con = Connect.getConnection();
			psPasajeroFrecuente=con.prepareStatement("INSERT INTO PASAJERO_FRECUENTE \r\n" + 
					"VALUES(NEXT VALUE FOR seq_pasajero_frecuente, ?, ?, ?, ?, ?)");
			
			psPasajeroFrecuente.setString(1, pasajeroFrecuente.getAlianza());
			psPasajeroFrecuente.setString(2, pasajeroFrecuente.getNumero());
			psPasajeroFrecuente.setString(3, pasajeroFrecuente.getCategoria());
			psPasajeroFrecuente.setDouble(4, idCliente);
			psPasajeroFrecuente.setDouble(5, pasajeroFrecuente.getAerolinea().getId());

			
			psPasajeroFrecuente.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasajeroFrecuente.isClosed()){
					psPasajeroFrecuente.close();
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
	public void modificarPasajeroFrecuente(PasajeroFrecuente pasajeroFrecuente, double idCliente) {
		Connection con = null;
		PreparedStatement psPasajeroFrecuente = null;
		try{
			con = Connect.getConnection();
			psPasajeroFrecuente=con.prepareStatement("UPDATE PASAJERO_FRECUENTE \r\n" + 
					"SET alianza = ? , numero = ? , categoria = ? , \r\n" + 
					"id_cliente = ? , id_aerolinea = ? \r\n" + 
					"WHERE id_cliente = ?");
			
			psPasajeroFrecuente.setString(1, pasajeroFrecuente.getAlianza());
			psPasajeroFrecuente.setString(2, pasajeroFrecuente.getNumero());
			psPasajeroFrecuente.setString(3, pasajeroFrecuente.getCategoria());
			psPasajeroFrecuente.setDouble(4, idCliente);
			psPasajeroFrecuente.setDouble(5, pasajeroFrecuente.getAerolinea().getId());
			psPasajeroFrecuente.setDouble(6, idCliente);
			
			psPasajeroFrecuente.execute();
					
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasajeroFrecuente.isClosed()){
					psPasajeroFrecuente.close();
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
	public void eliminarPasajeroFrecuente(double idCliente) {
		Connection con = null;
		PreparedStatement psPasajeroFrecuente = null;
		try{
			con = Connect.getConnection();
			psPasajeroFrecuente=con.prepareStatement("DELETE FROM PASAJERO_FRECUENTE WHERE id_cliente = ?");
			
			psPasajeroFrecuente.setDouble(1, idCliente);
			
			psPasajeroFrecuente.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!psPasajeroFrecuente.isClosed()) {
					psPasajeroFrecuente.close();
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
