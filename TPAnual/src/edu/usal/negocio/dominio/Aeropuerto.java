package edu.usal.negocio.dominio;

public class Aeropuerto {
	
	private String identificacion;
	private String ciudad;
	private Provincia provincia;
	private Pais pais;
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	public Aeropuerto(String identificacion, String ciudad, Provincia provincia, Pais pais) {
		super();
		this.identificacion = identificacion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
	}
	
	public Aeropuerto() {
	}
	
	

}