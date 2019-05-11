package edu.usal.negocio.dominio;

import java.io.Serializable;

public class PasajeroFrecuente implements Serializable{
	private double id;
	private Alianza alianza; //usar como ENUM o String porque son 3
	private Aerolinea aerolinea;
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}

	private String numero;
	private String categoria;
	
	public Alianza getAlianza() {
		return alianza;
	}
	public void setAlianza(Alianza alianza) {
		this.alianza = alianza;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	public PasajeroFrecuente(double id, Alianza alianza, Aerolinea aerolinea, String numero, String categoria) {
		super();
		this.id = id;
		this.alianza = alianza;
		this.aerolinea = aerolinea;
		this.numero = numero;
		this.categoria = categoria;
	}
	public PasajeroFrecuente() {
	
	}
	
	

}
