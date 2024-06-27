package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.Getter;

@Getter
public class PeajeOKPagoRealizadoNacional {
	
    private String descripcion;
    

	public PeajeOKPagoRealizadoNacional(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}
	

