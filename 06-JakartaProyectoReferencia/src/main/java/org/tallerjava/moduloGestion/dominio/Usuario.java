package org.tallerjava.moduloGestion.dominio;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Usuario {
	
    protected long id; //CI para nacional y Pasaporte para extranjero
    protected String nombre;
    protected String email;
    protected List<Vinculo> vehiculosVinculados;
    protected ClienteTelepeaje clienteTelepeaje;

    public abstract boolean soyNacional();

	public ClienteTelepeaje getClienteTelepeaje() {
		return this.clienteTelepeaje;
	}

	public void setClienteTelepeaje(ClienteTelepeaje cliTelepeaje) {
		this.clienteTelepeaje = cliTelepeaje;
		
	}

}
