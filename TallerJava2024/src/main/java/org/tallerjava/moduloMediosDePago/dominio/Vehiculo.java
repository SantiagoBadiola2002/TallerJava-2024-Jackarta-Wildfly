package org.tallerjava.moduloMediosDePago.dominio;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Observar como este objeto de dominio es diferente al Vehiculo que tenemos implementado
 * en el modulo Peaje.
 * Los objetos del dominio, dentro de cada modulo, se adaptan al contexto del problema.
 *
 * No importa tener objetos repetidos, ya que estamos priorizando el bajo acoplamiento.
 */
@Data
@Entity(name = "Vehiculo_MedioPagos") // no puedo tener dos entidades que se llamen igual, aunque las mismas
//esten en diferentes paquetes
//no permitimos dos autos con misma matrícula
@Table(name = "mediosPago_vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Embedded
    private Identificador identificador;
	
	@ManyToOne
    private Cliente cliente;
	
	public Vehiculo() {}
    
	public Vehiculo(Identificador identificador, Cliente cliente) {

		this.identificador = identificador;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public Identificador getIdentificador() {
		return identificador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdentificador(Identificador identificador) {
		this.identificador = identificador;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
    
    
    
}
