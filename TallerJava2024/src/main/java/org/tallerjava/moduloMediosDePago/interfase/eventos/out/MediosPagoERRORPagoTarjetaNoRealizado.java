package org.tallerjava.moduloMediosDePago.interfase.eventos.out;

public class MediosPagoERRORPagoTarjetaNoRealizado {
	private String descripcion;

	public MediosPagoERRORPagoTarjetaNoRealizado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
