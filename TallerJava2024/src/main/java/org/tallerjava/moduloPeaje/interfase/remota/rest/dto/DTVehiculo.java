package org.tallerjava.moduloPeaje.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTVehiculo {
	int tag; 
	String matricula;
	int nacionalidad;
	
	public DTVehiculo() {}
	
	public DTVehiculo(int tag, String matricula, int nacionalidad) {
		this.tag = tag;
		this.matricula = matricula;
		this.nacionalidad = nacionalidad;
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
