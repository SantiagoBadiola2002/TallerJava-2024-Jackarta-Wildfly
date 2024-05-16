package org.tallerjava.moduloGestion.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
	
	@Inject
    private Event<ClienteTelepeajeNoEncontradoPorTag> cliTelepeajeNoEncontradoPorTag;
	
	@Inject
	private Event<NotificarPrePago> notificarPrePago;
	
	@Inject
	private Event<NotificarPostPago> notificarPostPago;
	
	@Inject
	private Event<NotificarSaldoInsuficiente> notificarSaldoInsuficiente;
	
	

    public void publicarClienteTelepeajeNoEncontradoPorTag(String mensaje){
    	cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
    }

	public void publicarUsuarioNoEncontradoPorTag(String mensaje) {
		cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
		
	}
	
	public void publicarNotificarPrePago(String mensaje) {
		notificarPrePago.fire(new NotificarPrePago(mensaje));
	}
	
	public void publicarNotificarPostPago(String mensaje) {
		notificarPostPago.fire(new NotificarPostPago(mensaje));
	}
	
	public void publicarNotificarSaldoInsuficiente(String mensaje) {
		notificarSaldoInsuficiente.fire(new NotificarSaldoInsuficiente(mensaje));
	}
}



