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
@Table(name = "peaje_vehiculo")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Embedded
	private Identificador identificador;
	private int nacionalidad;
	
	public Vehiculo() {}

	public Vehiculo( Identificador identificador, int nacionalidad) {
		this.identificador = identificador;
		this.nacionalidad = nacionalidad;
	}

	public Vehiculo( long id, Identificador identificador, int nacionalidad) {
		this.id = id;
		this.identificador = identificador;
		this.nacionalidad = nacionalidad;
	}

	public boolean nacional() {
		return (nacionalidad == 0);
	}

	public long getId() {
		return id;
	}

	public Identificador getIdentificador() {
		return identificador;
	}


	public int getNacionalidad() {
		return nacionalidad;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdentificador(Identificador identificador) {
		this.identificador = identificador;
	}


	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}
