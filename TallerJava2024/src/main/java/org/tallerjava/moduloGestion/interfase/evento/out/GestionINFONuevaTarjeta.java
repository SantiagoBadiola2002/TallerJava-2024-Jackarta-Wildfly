package org.tallerjava.moduloGestion.interfase.evento.out;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GestionINFONuevaTarjeta {
    private int idCliente;

	private int nroTarjeta;
    private LocalDateTime fechaVto;
    private String nombreCompletoUsuario;
	
    
    
	public GestionINFONuevaTarjeta(int idCliente, int nroTarjeta, LocalDateTime fechaVto,
			String nombreCompletoUsuario) {

		this.idCliente = idCliente;
		this.nroTarjeta = nroTarjeta;
		this.fechaVto = fechaVto;
		this.nombreCompletoUsuario = nombreCompletoUsuario;
	}
	public int getIdCliente() {
		return idCliente;
	}

    public int getNroTarjeta() {
		return nroTarjeta;
	}

	public LocalDateTime getFechaVto() {
		return fechaVto;
	}

	public String getNombreCompletoUsuario() {
		return nombreCompletoUsuario;
	}

    
    
}
