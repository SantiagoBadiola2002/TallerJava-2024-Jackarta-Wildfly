package ModuloDeClases;

import jakarta.inject.Inject;

public class Nacional extends Vehiculo {
	
	@Inject
    private Matricula matricula; 

    public Nacional(Tag tag, Matricula matricula) {
        super(); 
        this.matricula = matricula;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}