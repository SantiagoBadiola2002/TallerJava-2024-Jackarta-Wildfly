package org.tallerjava.moduloGestion.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTPago {
	
	 private int tag;
	 private double importe;
	 
	 
	 public DTPago() {
		 
	 }
	 
	public DTPago(int tag, double importe) {
		super();
		this.tag = tag;
		this.importe = importe;
	}


	public int getTag() {
		return tag;
	}


	public double getImporte() {
		return importe;
	}
	 

}
