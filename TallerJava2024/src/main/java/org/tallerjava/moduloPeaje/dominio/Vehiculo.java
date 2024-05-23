package org.tallerjava.moduloPeaje.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Vehiculo {
	
    private long id;
    private Identificador identificador;
    private String marca;
    private String modelo;
    private Nacionalidad nacionalidad;

  

	public Vehiculo(long id, Identificador identificador, String marca, String modelo, Nacionalidad nacionalidad) {
		this.id = id;
		this.identificador = identificador;
		this.marca = marca;
		this.modelo = modelo;
		this.nacionalidad = nacionalidad;
	}



	public boolean nacional() {
        return nacionalidad == Nacionalidad.NACIONAL?true:false;
    }



	public long getId() {
		return id;
	}



	public Identificador getIdentificador() {
		return identificador;
	}



	public String getMarca() {
		return marca;
	}



	public String getModelo() {
		return modelo;
	}



	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setIdentificador(Identificador identificador) {
		this.identificador = identificador;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
}
