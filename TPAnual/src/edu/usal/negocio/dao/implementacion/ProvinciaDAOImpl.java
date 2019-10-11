package edu.usal.negocio.dao.implementacion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.ProvinciaDAO;
import edu.usal.negocio.dominio.Provincia;

public class ProvinciaDAOImpl implements ProvinciaDAO{

	
	@Override
	public List<Provincia> listarProvincias() {
		Connection con = null;
		Statement stm = null;
		ResultSet rsProvincia = null;

		try {
			con = Connect.getConnection();
			stm = con.createStatement();
			rsProvincia = stm.executeQuery("SELECT * FROM PROVINCIA");
			List<Provincia> provincias = new ArrayList<Provincia>();

			while (rsProvincia.next()) {
				Provincia provincia = new Provincia();
				provincia.setId(rsProvincia.getInt("id_provincia"));
				provincia.setNombre(rsProvincia.getString("nombre_provincia"));
											
				provincias.add(provincia);
			}
			return provincias;
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

	
	public Provincia obtenerProvincia(String nombre){
		Connection con = null;
		PreparedStatement psProvincia = null;
		ResultSet rsProvincia = null;
		
		try {
			con = Connect.getConnection();
			psProvincia = con.prepareStatement("SELECT * FROM Provincias where nombre_provincia= ?");
			
			
			psProvincia.setString(1, nombre);
			rsProvincia = psProvincia.executeQuery();
			rsProvincia.next();
			
			Provincia provincia = new Provincia();
			provincia.setId(rsProvincia.getInt("id_provincia"));
			provincia.setNombre(rsProvincia.getString("nombre_provincia"));
							
			return provincia;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		 }finally {
			try {
				if (!psProvincia.isClosed()) {
					psProvincia.close();
				}
				
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
		
	
	
}
