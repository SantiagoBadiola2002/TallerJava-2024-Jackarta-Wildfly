package org.tallerjava.moduloGestion.dominio;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity (name = "Vehiculo_Gestion") //no puedo tener dos entidades que se llamen igual, aunque las mismas
//esten en diferentes paquetes
//no permitimos dos autos con misma matrícula
@Table (name = "gestion_vehiculo", indexes = @Index (columnList = "matricula"))
public class Vehiculo {
  
	@Id
	private long id;
	
	@Embedded
    private Identificador identificador;
    
    @ManyToOne
    private ClienteTelepeaje cliente;
    
    @OneToMany
    private List<PasadasPorPeaje> pasadas;
    
   

	public Vehiculo(Identificador identificador, ClienteTelepeaje cliente, List<PasadasPorPeaje> pasadas) {
		this.identificador = identificador;
		this.cliente = cliente;
		this.pasadas = pasadas;
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
	
	
    
    
    
}
