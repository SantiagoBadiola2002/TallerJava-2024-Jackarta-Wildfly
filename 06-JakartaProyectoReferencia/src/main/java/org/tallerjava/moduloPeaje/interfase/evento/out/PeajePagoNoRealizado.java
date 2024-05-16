package org.tallerjava.moduloPeaje.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PeajePagoNoRealizado {
	
    private String descripcion;
    

	public PeajePagoNoRealizado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
    

}