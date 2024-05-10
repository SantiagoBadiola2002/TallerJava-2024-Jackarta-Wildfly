package ModuloDeClases;

import jakarta.inject.Inject;

public class UsrNacional extends Usuario {
	
	@Inject
	private ClienteSucive clienteSucive;
    
	
    public UsrNacional() {
		super();
	}

	// Constructor vacío
    public UsrNacional(ClienteSucive clienteSucive) {
        super(); 
        this.clienteSucive = clienteSucive;
    }
    
    // Constructor con parámetros
    public UsrNacional(String ci, String nombreUsuario, String email) {
        super(ci, nombreUsuario, email); 
    }
    
    
    
}