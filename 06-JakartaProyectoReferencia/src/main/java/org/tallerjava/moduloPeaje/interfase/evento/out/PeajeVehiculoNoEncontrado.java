package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PeajeVehiculoNoEncontrado {
	
    private String descripcion;
    

	public PeajeVehiculoNoEncontrado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
