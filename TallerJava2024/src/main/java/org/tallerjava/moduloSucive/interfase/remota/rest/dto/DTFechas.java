package org.tallerjava.moduloSucive.interfase.remota.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTFechas {

	LocalDate fechaInicial;
	LocalDate fechaFinal;

	public DTFechas(){}

	public DTFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public LocalDate getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(LocalDate fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}	
	
	
}
