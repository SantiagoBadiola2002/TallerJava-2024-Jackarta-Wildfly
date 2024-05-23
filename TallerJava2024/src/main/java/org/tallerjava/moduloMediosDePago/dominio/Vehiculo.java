package org.tallerjava.moduloMediosDePago.dominio;

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
    
	public Vehiculo(long id, Identificador identificador, ClienteTelepeaje cliente) {
		super();
		this.id = id;
		this.identificador = identificador;
		this.cliente = cliente;
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
	
	
    
    
    
}
