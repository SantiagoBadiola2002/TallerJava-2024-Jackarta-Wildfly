package org.tallerjava.moduloGestion.interfase.remota.rest.dto;


import lombok.Data;

@Data
public class DTPasadas {
	
	int idCliente; 
	int tag; 
	String matricula;
	String fechaInicial;
	String fechaFinal;
	
	public DTPasadas() {
		
	}
	
	public DTPasadas(int idCliente, int tag, String matricula, String fechaInicial, String fechaFinal) {
		this.idCliente = idCliente;
		this.tag = tag;
		this.matricula = matricula;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public DTPasadas(int idCliente, String fechaInicial, String fechaFinal) {
		this.idCliente = idCliente;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
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

	public String getFechaInicial() {
		return fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}


}
