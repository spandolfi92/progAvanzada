package edu.usal.negocio.dominio;

import java.sql.Date;

public class Vuelos {
	
	private String numeroVuelo;
	private Integer cantidadAsientos;
	private Aeropuerto aeropuertoSalida;
	private Aeropuerto aeropuertoLlegada;
	private Date fechaSalida;
	private Date fechaLlegada;
	private String tiempoVuelo;
	
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
	
	public Vuelos(String numeroVuelo, Integer cantidadAsientos, Aeropuerto aeropuertoSalida,
			Aeropuerto aeropuertoLlegada, Date fechaSalida, Date fechaLlegada, String tiempoVuelo) {
		super();
		this.numeroVuelo = numeroVuelo;
		this.cantidadAsientos = cantidadAsientos;
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.tiempoVuelo = tiempoVuelo;
	}
	
	public Vuelos() {
	}
	
	

}
