package org.tallerjava.moduloMediosDePago.dominio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
public class Identificador {
	
    private String matricula;
    private int tag;
    
    public Identificador() {}
	public Identificador( String matricula, int tag) {

		this.matricula = matricula;
		this.tag = tag;
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
