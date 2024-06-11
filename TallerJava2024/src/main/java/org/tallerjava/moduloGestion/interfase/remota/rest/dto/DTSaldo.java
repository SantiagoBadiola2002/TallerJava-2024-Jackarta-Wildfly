package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTSaldo {
	
	int idCliente; 
	double importe;
	
	public DTSaldo() {
		
	}

	public DTSaldo(int idCliente, double importe) {
		this.idCliente = idCliente;
		this.importe = importe;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public double getImporte() {
		return importe;
	}

 
}
