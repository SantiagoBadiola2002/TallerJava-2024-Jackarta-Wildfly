package org.tallerjava.moduloGestion.dominio;

import java.util.Date;

public class Vinculo {
	
	private Date fechaini;
	private boolean activo;
	private Vehiculo vehiculo;
	
	public Vinculo(Date fechaini, boolean activo, Vehiculo vehiculo) {
		this.fechaini = fechaini;
		this.activo = activo;
		this.vehiculo = vehiculo;
	}

	public Date getFechaini() {
		return fechaini;
	}

	public boolean isActivo() {
		return activo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
    
	
	
}
