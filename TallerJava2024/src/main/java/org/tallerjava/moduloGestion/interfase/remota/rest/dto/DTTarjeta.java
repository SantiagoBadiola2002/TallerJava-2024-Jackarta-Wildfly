package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTTarjeta {
	
	long ci;
	int nroTarjeta;
	String fechaVtoTarjeta;
	String nombreCompletoUsuario;
	
	public DTTarjeta() {
		
	}
	
	public DTTarjeta(long ci, int nroTarjeta, String fechaVtoTarjeta, String nombreCompletoUsuario) {
		this.ci = ci;
		this.nroTarjeta = nroTarjeta;
		this.fechaVtoTarjeta = fechaVtoTarjeta;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}

	public long getCi() {
		return ci;
	}

	public int getNroTarjeta() {
		return nroTarjeta;
	}

	public String getFechaVtoTarjeta() {
		return fechaVtoTarjeta;
	}

	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}

	public void setCi(long ci) {
		this.ci = ci;
	}	

}
