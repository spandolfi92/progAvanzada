package edu.usal.negocio.dominio;

public class Telefono {

	private double id;
	private String numeroPersonal;
	private String numeroCelular;
	private String numeroLaboral;
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getNumeroPersonal() {
		return numeroPersonal;
	}
	public void setNumeroPersonal(String numeroPersonal) {
		this.numeroPersonal = numeroPersonal;
	}
	public String getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	public String getNumeroLaboral() {
		return numeroLaboral;
	}
	public void setNumeroLaboral(String numeroLaboral) {
		this.numeroLaboral = numeroLaboral;
	}
	
	
	
	public Telefono(double id, String numeroPersonal, String numeroCelular, String numeroLaboral) {
		super();
		this.id = id;
		this.numeroPersonal = numeroPersonal;
		this.numeroCelular = numeroCelular;
		this.numeroLaboral = numeroLaboral;
	}
	public Telefono() {
	}
	
	
	
	
}
