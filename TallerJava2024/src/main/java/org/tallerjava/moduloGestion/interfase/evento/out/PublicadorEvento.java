package org.tallerjava.moduloGestion.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
	
	@Inject
    private Event<ClienteTelepeajeNoEncontradoPorTag> cliTelepeajeNoEncontradoPorTag;
	
	@Inject
	private Event<GestionOKPagoPrePago> OKPagoPrePago;
	
	@Inject
	private Event<GestionOKPagoPostPago> OKPagoPostPago;
	
	@Inject
	private Event<GestionERRORSaldoInsuficiente> ERRORSaldoInsuficiente;
	
	

    public void publicarClienteTelepeajeNoEncontradoPorTag(String mensaje){
    	cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
    }

	public void publicarUsuarioNoEncontradoPorTag(String mensaje) {
		cliTelepeajeNoEncontradoPorTag.fire(new ClienteTelepeajeNoEncontradoPorTag(mensaje));
		
	}
	
	public void publicarNotificarPrePago(String mensaje) {
		OKPagoPrePago.fire(new GestionOKPagoPrePago(mensaje));
	}
	
	public void publicarNotificarPostPago(String mensaje) {
		OKPagoPostPago.fire(new GestionOKPagoPostPago(mensaje));
	}
	
	public void publicarNotificarSaldoInsuficiente(String mensaje) {
		ERRORSaldoInsuficiente.fire(new GestionERRORSaldoInsuficiente(mensaje));
	}
}



