package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteSucive {
    private Nacional usuarioNacional;

    public ClienteSucive(Nacional usrNacional) {
    	this.usuarioNacional = usrNacional;
    	
    }

}


