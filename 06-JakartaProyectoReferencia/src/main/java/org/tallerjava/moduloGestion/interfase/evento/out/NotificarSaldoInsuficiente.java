package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotificarSaldoInsuficiente {
	
	private String descripcion;

	public NotificarSaldoInsuficiente(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

