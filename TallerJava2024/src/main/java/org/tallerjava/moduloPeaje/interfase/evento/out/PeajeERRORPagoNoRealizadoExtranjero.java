package org.tallerjava.moduloPeaje.interfase.evento.out;


import lombok.Getter;

@Getter
public class PeajeERRORPagoNoRealizadoExtranjero {
	
    private String descripcion;
    

	public PeajeERRORPagoNoRealizadoExtranjero(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}