package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTVehiculo {
	private long ci; 
	int tag; 
	String matricula;
	
	public DTVehiculo() {
		
	}
	
	public DTVehiculo(long ci, int tag, String matricula) {
		super();
		this.ci = ci;
		this.tag = tag;
		this.matricula = matricula;
	}

	public long getCi() {
		return ci;
	}

	public int getTag() {
		return tag;
	}

	public String getMatricula() {
		return matricula;
	}
	

}
