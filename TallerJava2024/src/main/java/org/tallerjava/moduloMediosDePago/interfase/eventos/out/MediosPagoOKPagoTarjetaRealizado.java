package org.tallerjava.moduloMediosDePago.interfase.eventos.out;


public class MediosPagoOKPagoTarjetaRealizado {

private String descripcion;
    

	public MediosPagoOKPagoTarjetaRealizado(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    
}


