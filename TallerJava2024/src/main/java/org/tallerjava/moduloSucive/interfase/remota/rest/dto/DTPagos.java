package org.tallerjava.moduloSucive.interfase.remota.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTPagos {
	String tipo;
	LocalDateTime fecha;
	double importe;
	String matricula;
	
	public DTPagos() {}
	

	public DTPagos(String tipo, LocalDateTime fecha, double importe) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
	}

	public DTPagos(String tipo, LocalDateTime fecha, double importe, String matricula) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
		this.matricula = matricula;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFechaInicial(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setTag(String matricula) {
		this.matricula = matricula;
	}
	
	
	
	
}
