package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Identificador {
	
	private long id;
    private String matricula;
    private int tag;
    
	public Identificador(long id, String matricula, int tag) {
		this.id = id;
		this.matricula = matricula;
		this.tag = tag;
	}
	
	public Identificador(String matricula, int tag) {
		this.matricula = matricula;
		this.tag = tag;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
	
 
	
}
