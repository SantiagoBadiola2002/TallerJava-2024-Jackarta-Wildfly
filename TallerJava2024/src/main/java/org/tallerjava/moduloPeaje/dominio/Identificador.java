package org.tallerjava.moduloPeaje.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
public class Identificador {
	
	@Column(nullable = false)
    private String matricula;
	@Column(nullable = false)
    private int tag;
    
	public Identificador(String matricula, int tag) {
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
