package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.Getter;

@Getter
public class PeajeERRORPagoNoRealizadoNacional {
	
    private String descripcion;
    

	public PeajeERRORPagoNoRealizadoNacional(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
	

