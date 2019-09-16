package edu.usal.negocio.dominio;

import java.util.List;

public class Aerolinea {
	 
	private double id;
	private String nombre;
	private Alianza alianza;
	private List<Vuelos> vuelos;
	
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
	public Alianza getAlianza() {
		return alianza;
	}
	public void setAlianza(Alianza alianza) {
		this.alianza = alianza;
	}
	public List<Vuelos> getVuelos() {
		return vuelos;
	}
	public void setVuelos(List<Vuelos> vuelos) {
		this.vuelos = vuelos;
	}
	
	public Aerolinea(double id, String nombre, Alianza alianza, List<Vuelos> vuelos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alianza = alianza;
		this.vuelos = vuelos;
	}
	public Aerolinea() {
	
	}
	

	
	

}
