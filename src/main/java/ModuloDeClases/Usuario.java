package ModuloDeClases;

public class Usuario {
    private String ci;
    private String nombreUsuario;
    private String email;
    private ClienteTelepeaje clienteTelepeaje;
    
    public Usuario() {
    	
    }
    
    public Usuario(String ci, String nombreUsuario, String email) {
        this.ci = ci;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }
    
    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ClienteTelepeaje getClienteTelepeaje() {
        return clienteTelepeaje;
    }

    public void setClienteTelepeaje(ClienteTelepeaje clienteTelepeaje) {
        this.clienteTelepeaje = clienteTelepeaje;
    }
}