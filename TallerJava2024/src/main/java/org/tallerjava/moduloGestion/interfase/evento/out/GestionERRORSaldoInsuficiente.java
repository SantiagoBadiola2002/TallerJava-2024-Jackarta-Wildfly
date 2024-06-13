package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GestionERRORSaldoInsuficiente {
	
	private String descripcion;

	public GestionERRORSaldoInsuficiente(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

