package org.tallerjava.moduloGestion.dominio;

import java.util.List;

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
public class Vehiculo {
  
	private long id;
    private Identificador identificador;
    private ClienteTelepeaje cliente;
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
