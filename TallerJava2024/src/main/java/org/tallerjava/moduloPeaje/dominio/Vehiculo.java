package org.tallerjava.moduloPeaje.dominio;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "peaje_Vehiculo")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Embedded
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
		return nacionalidad == Nacionalidad.NACIONAL ? true : false;
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
