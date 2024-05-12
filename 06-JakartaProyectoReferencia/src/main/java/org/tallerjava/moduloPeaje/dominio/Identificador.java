package org.tallerjava.moduloPeaje.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Identificador {
	
	private long id;
    private String matricula;
    private int tag;
    
	public Identificador(long id, String matricula, int tag) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.tag = tag;
	}
	
 
	
}
