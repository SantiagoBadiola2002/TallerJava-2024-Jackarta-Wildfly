package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTVehiculo {
	private int idCliente; 
	int tag; 
	String matricula;
	
	public DTVehiculo() {
		
	}
	
	public DTVehiculo(int idCliente, int tag, String matricula) {
		super();
		this.idCliente = idCliente;
		this.tag = tag;
		this.matricula = matricula;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getTag() {
		return tag;
	}

	public String getMatricula() {
		return matricula;
	}
	

}
