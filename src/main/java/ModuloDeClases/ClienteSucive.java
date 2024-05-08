package ModuloDeClases;

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
