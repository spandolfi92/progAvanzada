package edu.usal.negocio.dominio;

import java.io.Serializable;

public class Pais implements Serializable{
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Pais() {
	}
	
	

}
