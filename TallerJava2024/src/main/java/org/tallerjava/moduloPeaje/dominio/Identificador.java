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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
    private String matricula;
	@Column(nullable = false)
    private int tag;
    
	public Identificador(long id, String matricula, int tag) {
		super();
		this.id = id;
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
