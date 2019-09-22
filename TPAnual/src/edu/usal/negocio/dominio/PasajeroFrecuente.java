package edu.usal.negocio.dominio;

public class PasajeroFrecuente {
	
	private double id;
	private String alianza;
	private String numero;
	private String categoria;
	private Aerolinea aerolinea;
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}	
	public String getAlianza() {
		return alianza;
	}
	public void setAlianza(String alianza) {
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
	
	
	
	public PasajeroFrecuente(double id, String alianza, Aerolinea aerolinea, String numero, String categoria) {
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
