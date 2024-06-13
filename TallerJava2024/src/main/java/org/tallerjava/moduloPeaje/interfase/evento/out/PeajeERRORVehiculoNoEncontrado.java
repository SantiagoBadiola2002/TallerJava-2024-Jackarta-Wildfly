package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PeajeERRORVehiculoNoEncontrado {
	
    private String descripcion;
    

	public PeajeERRORVehiculoNoEncontrado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
