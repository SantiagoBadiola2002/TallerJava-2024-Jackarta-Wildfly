package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTIdCliente {
	
	int idCliente;
	
	public DTIdCliente() {
		
	}

	public DTIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

}
