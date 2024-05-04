package org.tallerjava.moduloA.dominio;

public class Nacional extends Vehiculo {
	
	private Matricula matricula;
	
	private Tag tag;
	
	public Nacional() {
		
	}

	public Nacional(Matricula matricula, Tag tag) {
		super();
		this.matricula = matricula;
		this.tag = tag;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
    
}