package edu.usal.negocio.dominio;

import java.util.Date;


public class Vuelos {
	
	private double id;
	private String numeroVuelo;
	private Integer cantidadAsientos;
	private Aeropuerto aeropuertoSalida;
	private Aeropuerto aeropuertoLlegada;
	private Date fechaSalida;
	private Date fechaLlegada;
	private String tiempoVuelo;
	private Aerolinea aerolinea;
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getNumeroVuelo() {
		return numeroVuelo;
	}
	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}
	public Integer getCantidadAsientos() {
		return cantidadAsientos;
	}
	public void setCantidadAsientos(Integer cantidadAsientos) {
		this.cantidadAsientos = cantidadAsientos;
	}
	public Aeropuerto getAeropuertoSalida() {
		return aeropuertoSalida;
	}
	public void setAeropuertoSalida(Aeropuerto aeropuertoSalida) {
		this.aeropuertoSalida = aeropuertoSalida;
	}
	public Aeropuerto getAeropuertoLlegada() {
		return aeropuertoLlegada;
	}
	public void setAeropuertoLlegada(Aeropuerto aeropuertoLlegada) {
		this.aeropuertoLlegada = aeropuertoLlegada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Date getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	public String getTiempoVuelo() {
		return tiempoVuelo;
	}
	public void setTiempoVuelo(String tiempoVuelo) {
		this.tiempoVuelo = tiempoVuelo;
	}
	public Aerolinea getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public Vuelos(double id, String numeroVuelo, Integer cantidadAsientos, Aeropuerto aeropuertoSalida,
			Aeropuerto aeropuertoLlegada, Date fechaSalida, Date fechaLlegada, String tiempoVuelo,
			Aerolinea aerolinea) {
		
		this.id = id;
		this.numeroVuelo = numeroVuelo;
		this.cantidadAsientos = cantidadAsientos;
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.tiempoVuelo = tiempoVuelo;
		this.aerolinea = aerolinea;
	}
	
	public Vuelos() {
		
	}
	
}