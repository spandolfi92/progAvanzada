package edu.usal.negocio.dominio;

import java.util.List;

public class Aerolinea {
	
	private String nombre;
	private String codigo;
	private Alianza alianza;
	private List<Vuelos> vuelos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	public Aerolinea(String nombre, String codigo, Alianza alianza, List<Vuelos> vuelos) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.alianza = alianza;
		this.vuelos = vuelos;
	}
	
	

}
