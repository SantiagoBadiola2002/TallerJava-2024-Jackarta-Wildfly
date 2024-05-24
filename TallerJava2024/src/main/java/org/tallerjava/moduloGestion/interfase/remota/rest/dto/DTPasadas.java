package org.tallerjava.moduloGestion.interfase.remota.rest.dto;


import lombok.Data;

@Data
public class DTPasadas {
	
	long ci; 
	int tag; 
	String matricula;
	String fechaInicial;
	String fechaFinal;
	
	public DTPasadas() {
		
	}
	
	public DTPasadas(long ci, int tag, String matricula, String fechaInicial, String fechaFinal) {
		this.ci = ci;
		this.tag = tag;
		this.matricula = matricula;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public DTPasadas(long ci, String fechaInicial, String fechaFinal) {
		this.ci = ci;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public long getCi() {
		return ci;
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
