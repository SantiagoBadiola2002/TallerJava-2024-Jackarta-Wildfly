package org.tallerjava.moduloPeaje.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTVehiculo {
	int tag; 
	String matricula;
	int nacionalidad; //0 nacional 1 extranjero
	
	public DTVehiculo() {}
	
	public DTVehiculo(int tag, String matricula, int nacionalidad) {
		this.tag = tag;
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
	}
	public DTVehiculo(int tag, String matricula) {
		this.tag = tag;
		this.matricula = matricula;
	
	}
	public int getTag() {
		return tag;
	}
	public String getMatricula() {
		return matricula;
	}
	public int getNacionalidad() {
		return nacionalidad;
	} 
	
}
