package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTTarjeta {
	
	int idCliente;
	int nroTarjeta;
	String fechaVtoTarjeta;
	String nombreCompletoUsuario;
	
	public DTTarjeta() {
		
	}
	
	public DTTarjeta(int idCliente, int nroTarjeta, String fechaVtoTarjeta, String nombreCompletoUsuario) {
		this.idCliente = idCliente;
		this.nroTarjeta = nroTarjeta;
		this.fechaVtoTarjeta = fechaVtoTarjeta;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}

	public int getIdCliente() {
		return idCliente;
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

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}	

}
