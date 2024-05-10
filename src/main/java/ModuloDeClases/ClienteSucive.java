package ModuloDeClases;

import jakarta.inject.Named;

@Named // Si deseas que esta clase sea reconocida por CDI y se pueda inyectar
class ClienteSucive {
	
    private UsrNacional usuario;
    

    public ClienteSucive() {
    	
    }
    
    public ClienteSucive(UsrNacional usuario) {
        this.usuario = usuario;
    }

    public void pagarComun(Comun comun) {
        // Realizar acciones para pagar el servicio Comun
    }

	public UsrNacional getUsuario() {
		return usuario;
	}

	public void setUsuario(UsrNacional usuario) {
		this.usuario = usuario;
	}

    
    
}
