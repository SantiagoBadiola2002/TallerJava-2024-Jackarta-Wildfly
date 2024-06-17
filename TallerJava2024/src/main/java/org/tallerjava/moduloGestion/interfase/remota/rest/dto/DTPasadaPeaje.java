package org.tallerjava.moduloGestion.interfase.remota.rest.dto;


import lombok.Data;

@Data
public class DTPasadaPeaje {
	
	int idCliente; 
	int tag; 
	String matricula;
	String fechaPasada;
	double costo;
	
	public DTPasadaPeaje() {
		
	}
	
	public DTPasadaPeaje(int idCliente, int tag, String matricula, String fechaPasada, double costo) {
		this.idCliente = idCliente;
		this.tag = tag;
		this.matricula = matricula;
		this.fechaPasada = fechaPasada;
		this.costo = costo;
	}

	public DTPasadaPeaje(int idCliente, String fechaPasada, double costo) {
		this.idCliente = idCliente;
		this.fechaPasada = fechaPasada;
		this.costo = costo;
	}

	public double getCosto() {
		return costo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getTag() {
		return tag;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getFechaPasada() {
		return this.fechaPasada;
	}



}
