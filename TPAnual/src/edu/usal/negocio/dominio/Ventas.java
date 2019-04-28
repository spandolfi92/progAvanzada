package edu.usal.negocio.dominio;

import java.io.Serializable;
import java.util.Date;


public class Ventas implements Serializable{
	
	private Cliente cliente;
	private Vuelos vuelo;
	private Aerolinea aerolinea;
	private Date fechaVenta;
	private String formaPago;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vuelos getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelos vuelo) {
		this.vuelo = vuelo;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public Ventas(Cliente cliente, Vuelos vuelo, Aerolinea aerolinea, Date fechaVenta, String formaPago) {
		super();
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.aerolinea = aerolinea;
		this.fechaVenta = fechaVenta;
		this.formaPago = formaPago;
	}
	
	public Ventas() {
	}
	
	

}
