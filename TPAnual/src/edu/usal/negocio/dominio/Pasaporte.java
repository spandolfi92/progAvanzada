package edu.usal.negocio.dominio;

import java.io.Serializable;
import java.util.Date;


public class Pasaporte implements Serializable{

	private String numero;
	private Pais pais;
	private String autoridadEmision;
	private Date fechaEmision;
	private Date fechaVencimiento;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getAutoridadEmision() {
		return autoridadEmision;
	}
	public void setAutoridadEmision(String autoridadEmision) {
		this.autoridadEmision = autoridadEmision;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public Pasaporte(String numero, Pais pais, String autoridadEmision, Date fechaEmision, Date fechaVencimiento) {
		super();
		this.numero = numero;
		this.pais = pais;
		this.autoridadEmision = autoridadEmision;
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public Pasaporte() {
	}
	
	
}
