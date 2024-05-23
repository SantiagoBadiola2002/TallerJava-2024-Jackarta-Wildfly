package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Nacional extends Usuario{
	
    private ClienteSucive clienteSucive;

    public Nacional (long id, String nombre, String email,
                     List<Vinculo> vehiculosVinculados,
                     ClienteTelepeaje cliTelepeaje) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.vehiculosVinculados = vehiculosVinculados;
        this.clienteTelepeaje = cliTelepeaje;
    }

    @Override
    public boolean soyNacional() {
        return true;
    }
    
    public void setClienteSucive(ClienteSucive cliSucive) {
    	this.clienteSucive = cliSucive;
    	
    }
}
