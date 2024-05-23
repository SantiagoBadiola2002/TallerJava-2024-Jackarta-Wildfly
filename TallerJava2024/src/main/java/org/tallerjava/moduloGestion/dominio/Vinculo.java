package org.tallerjava.moduloGestion.dominio;

import java.time.LocalDateTime;


public class Vinculo {
	
	private LocalDateTime fechaini;
	private boolean activo;
	private Vehiculo vehiculo;
	
	public Vinculo(LocalDateTime fechaini, boolean activo, Vehiculo vehiculo) {
		this.fechaini = fechaini;
		this.activo = activo;
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaini() {
		return fechaini;
	}

	public boolean isActivo() {
		return activo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setFechaini(LocalDateTime fechaini) {
		this.fechaini = fechaini;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
    
	
	
}
