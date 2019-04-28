package edu.usal.negocio.dominio;

import java.io.Serializable;

public class Alianza implements Serializable{

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Alianza(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Alianza() {

	}
	
	
	
}
