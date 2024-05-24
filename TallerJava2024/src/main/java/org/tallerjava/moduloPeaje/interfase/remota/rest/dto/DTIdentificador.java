package org.tallerjava.moduloPeaje.interfase.remota.rest.dto;

import lombok.Data;

@Data
public class DTIdentificador {
	
	int tag; 
	String matricula;
	
	public DTIdentificador() {
		
	}

	public int getTag() {
		return tag;
	}

	public String getMatricula() {
		return matricula;
	}

}
