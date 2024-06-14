package org.tallerjava.moduloMediosDePago.interfase.eventos.out;

public class MediosPagoERRORAlProcesarPago {

	private String descripcion;

	public MediosPagoERRORAlProcesarPago(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
