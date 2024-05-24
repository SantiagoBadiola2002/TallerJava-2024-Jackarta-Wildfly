package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTSaldo {
	
	long ci; 
	double importe;
	
	public DTSaldo() {
		
	}

	public DTSaldo(long ci, double importe) {
		this.ci = ci;
		this.importe = importe;
	}

	public long getCi() {
		return ci;
	}

	public double getImporte() {
		return importe;
	}

 
}
