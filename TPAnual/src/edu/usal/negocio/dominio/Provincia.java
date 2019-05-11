package edu.usal.negocio.dominio;

import java.io.Serializable;

public class Provincia implements Serializable{
	
	private String nombre;

	private double id;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia() {

	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Provincia(String nombre, double id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	

}
