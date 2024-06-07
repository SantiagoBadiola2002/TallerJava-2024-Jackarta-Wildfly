package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Embeddable
public class Vinculo {
	
	
	private LocalDateTime fechaini;
	private boolean activo;
	
	public Vinculo() {}
	
	public Vinculo(LocalDateTime fechaini, boolean activo) {
		this.fechaini = fechaini;
		this.activo = activo;
	}

	public LocalDateTime getFechaini() {
		return fechaini;
	}

	public boolean isActivo() {
		return activo;
	}


	public void setFechaini(LocalDateTime fechaini) {
		this.fechaini = fechaini;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
