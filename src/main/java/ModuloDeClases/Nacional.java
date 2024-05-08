package ModuloDeClases;

public class Nacional extends Vehiculo {
	
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