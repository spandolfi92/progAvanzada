package edu.usal.negocio.dominio;

public class Direccion {
	
	private double id;
	private String calle;
	private String altura;
	private String ciudad;
	private String codigoPostal;
	private Provincia provincia;
	private Pais pais;
	
	
	
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
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
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public Direccion(double id, String calle, String altura, String ciudad, Provincia provincia, Pais pais,
			String codigoPostal) {
		super();
		this.id = id;
		this.calle = calle;
		this.altura = altura;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
	}
	public Direccion() {
		super();
	}
	
	

}
