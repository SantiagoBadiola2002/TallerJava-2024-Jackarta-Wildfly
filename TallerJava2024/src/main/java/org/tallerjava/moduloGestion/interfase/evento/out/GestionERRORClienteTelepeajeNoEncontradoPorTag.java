package org.tallerjava.moduloGestion.interfase.evento.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GestionERRORClienteTelepeajeNoEncontradoPorTag {

private String descripcion;
    

	public GestionERRORClienteTelepeajeNoEncontradoPorTag(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}

