package edu.usal.negocio.dominio;

import java.util.Date;


public class Ventas {
	
	private double id;
	private Cliente cliente;
	private Vuelos vuelo;
	private Aerolinea aerolinea;
	private Date fechaVenta;
	private String formaPago;
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
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
	
	
	public Ventas(double id, Cliente cliente, Vuelos vuelo, Aerolinea aerolinea, Date fechaVenta, String formaPago) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.aerolinea = aerolinea;
		this.fechaVenta = fechaVenta;
		this.formaPago = formaPago;
	}
	public Ventas() {
	}
	
	

}
