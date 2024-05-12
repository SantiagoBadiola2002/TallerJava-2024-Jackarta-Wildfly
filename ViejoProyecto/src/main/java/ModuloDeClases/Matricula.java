package ModuloDeClases;

import jakarta.inject.Named;

@Named
public class Matricula {
	
	String nroMatricula;
	
	public Matricula() {
		
	}
	
	public Matricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	public String getNroMatricula() {
		return nroMatricula;
	}

	public void setNroMatricula(String nroMatricula) {
		this.nroMatricula = nroMatricula;
	}
	
	

}
