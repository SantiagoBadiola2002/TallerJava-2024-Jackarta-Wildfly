package org.tallerjava.moduloGestion.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
	@Inject
    private Event<ClienteTelepeajeNoEncontradoPorTag> cliTelepeajeNoEncontradoPorTag;

    public void publicarClienteTelepeajeNoEncontradoPorTag(String mensaje){
    	cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
    }

	public void publicarUsuarioNoEncontradoPorTag(String mensaje) {
		cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
		
	}
}



