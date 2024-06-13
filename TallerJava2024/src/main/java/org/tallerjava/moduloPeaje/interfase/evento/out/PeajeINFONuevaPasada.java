package org.tallerjava.moduloPeaje.interfase.evento.out;

import java.time.LocalDateTime;

import org.tallerjava.moduloGestion.dominio.DTTipoCobro;

import lombok.Data;

@Data
public class PeajeINFONuevaPasada {

    private LocalDateTime fechaPasada;
	private double costo;
	private int tipoCobro;
	
	private int tag;
	private String matricula;
	
	public PeajeINFONuevaPasada(LocalDateTime fechaPasada, double costo, int tipoCobro, int tag, String matricula) {
		
		this.fechaPasada = fechaPasada;
		this.costo = costo;
		this.tipoCobro = tipoCobro;
		this.tag = tag;
		this.matricula = matricula;
	}


	public LocalDateTime getFechaPasada() {
		return fechaPasada;
	}

	public double getCosto() {
		return costo;
	}

	public int getTipoCobro() {
		return tipoCobro;
	}

	public int getTag() {
		return tag;
	}

	public String getMatricula() {
		return matricula;
	}

	
}
