package edu.usal.negocio.dao.implementacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.usal.negocio.dao.interfaces.ClienteDAO;
import edu.usal.negocio.dominio.Cliente;
import edu.usal.util.PropertiesUtil;

public class ClienteDAOImpl implements ClienteDAO{
	
	@SuppressWarnings("unused")
	private static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName(PropertiesUtil.getPropertyDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		con = DriverManager.getConnection(PropertiesUtil.getPropertyUrl(), PropertiesUtil.getPropertyUser(), PropertiesUtil.getPropertyPass());
		return con;
	}

	@Override
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(double id) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
