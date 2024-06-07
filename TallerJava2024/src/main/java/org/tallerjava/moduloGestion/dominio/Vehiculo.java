package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Observar como este objeto de dominio es diferente al Vehiculo que tenemos
 * implementado en el modulo Peaje. Los objetos del dominio, dentro de cada
 * modulo, se adaptan al contexto del problema.
 *
 * No importa tener objetos repetidos, ya que estamos priorizando el bajo
 * acoplamiento.
 */
@Data
@Entity(name = "Vehiculo_Gestion") // no puedo tener dos entidades que se llamen igual, aunque las mismas
//esten en diferentes paquetes
//no permitimos dos autos con misma matrícula
@Table(name = "gestion_vehiculo", indexes = @Index(columnList = "matricula"))
public class Vehiculo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;

	@Embedded
	private Identificador identificador;

	@ManyToOne
	private ClienteTelepeaje cliente;

	@OneToMany
	private List<PasadasPorPeaje> pasadas;

	@Embedded
	private Vinculo vinculo;

	public Vehiculo() {

	}

	public Vehiculo(Identificador identificador, ClienteTelepeaje cliente, List<PasadasPorPeaje> pasadas) {
		this.identificador = identificador;
		this.cliente = cliente;
		this.pasadas = pasadas;
	}

	public Vehiculo(Identificador identificador, ClienteTelepeaje cliente, List<PasadasPorPeaje> pasadas,
			Vinculo vinculo) {
		this.identificador = identificador;
		this.cliente = cliente;
		this.pasadas = pasadas;
		this.vinculo = vinculo;
	}

	public Vehiculo( Identificador identificador, ClienteTelepeaje cliente, 
			Vinculo vinculo, List<PasadasPorPeaje> pasadas) {
		this.identificador = identificador;
		this.cliente = cliente;
		this.pasadas = pasadas;
		this.vinculo = vinculo;
	}


	public long getId() {
		return id;
	}

	public Identificador getIdentificador() {
		return identificador;
	}

	public ClienteTelepeaje getCliente() {
		return cliente;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdentificador(Identificador identificador) {
		this.identificador = identificador;
	}

	public void setCliente(ClienteTelepeaje cliente) {
		this.cliente = cliente;
	}

	public List<PasadasPorPeaje> getPasadas() {
		return pasadas;
	}

	public void setPasadas(List<PasadasPorPeaje> pasadas) {
		this.pasadas = pasadas;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

}
