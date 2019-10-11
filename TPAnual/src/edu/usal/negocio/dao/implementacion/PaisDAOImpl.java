package edu.usal.negocio.dao.implementacion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.PaisDAO;
import edu.usal.negocio.dominio.Pais;

public class PaisDAOImpl implements PaisDAO{

	
	@Override
	public List<Pais> listarPaises() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsPais = null;

		try {
			con = Connect.getConnection();
			stm = con.createStatement();
			rsPais = stm.executeQuery("SELECT * FROM PAIS");
			List<Pais> paiss = new ArrayList<Pais>();

			while (rsPais.next()) {
				Pais pais = new Pais();
				pais.setId(rsPais.getInt("id_pais"));
				pais.setNombre(rsPais.getString("nombre_pais"));				
				paiss.add(pais);
			}
			return paiss;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (!stm.isClosed()) {
					stm.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public Pais obtenerPais(String nombre){
		Connection con = null;
		PreparedStatement psPais = null;
		ResultSet rsPais = null;
		
		try {
			con = Connect.getConnection();
			psPais = con.prepareStatement("SELECT * FROM Pais where nombre_pais= ?");
			
			
			psPais.setString(1, nombre);
			rsPais = psPais.executeQuery();
			rsPais.next();
			
			Pais pais = new Pais();
			pais.setId(rsPais.getInt("id_pais"));
			pais.setNombre(rsPais.getString("nombre_pais"));
			
			return pais;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psPais.isClosed()) {
					psPais.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
		
}
