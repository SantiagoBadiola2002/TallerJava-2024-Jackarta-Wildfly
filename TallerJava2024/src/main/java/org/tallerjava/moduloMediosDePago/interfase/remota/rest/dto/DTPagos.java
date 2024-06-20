package org.tallerjava.moduloMediosDePago.interfase.remota.rest.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DTPagos {
	String tipo;
	LocalDateTime fecha;
	double importe;
	int idCliente;
	int tag;
	
	public DTPagos() {}
	
	public DTPagos(String tipo, LocalDateTime fecha, double importe) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
	}

	public DTPagos(String tipo, LocalDateTime fecha, double importe, int idCliente) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
		this.idCliente = idCliente;
	}

	public DTPagos(String tipo, LocalDateTime fecha, double importe, int idCliente, int tag) {
		super();
		this.tipo = tipo;
		this.fecha = fecha;
		this.importe = importe;
		this.idCliente = idCliente;
		this.tag = tag;
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
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
	
	
}
