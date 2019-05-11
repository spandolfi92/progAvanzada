package edu.usal.negocio.dominio;

import java.io.Serializable;

public class Pais implements Serializable{
	
	private double id;
	private String nombre;
	
	
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Pais(double id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Pais() {
	}
	
	

}
