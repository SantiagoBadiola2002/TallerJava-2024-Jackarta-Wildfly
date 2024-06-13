package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GestionERRORTarjetaRechazada {
	
	private String descripcion;

	public GestionERRORTarjetaRechazada(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}