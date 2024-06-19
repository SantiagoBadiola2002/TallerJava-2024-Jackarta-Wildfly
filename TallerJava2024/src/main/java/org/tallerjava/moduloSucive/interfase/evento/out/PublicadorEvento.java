package org.tallerjava.moduloSucive.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {

	@Inject
    private Event<SuciveOKPago> pagoSuciveRealizado;
	
	@Inject
    private Event<SuciveERRORPago> pagoSuciveNoRealizado;
	
	public void publicarPagoSuciveRealizado(String mensaje){
		pagoSuciveRealizado.fire(new SuciveOKPago(mensaje));
    }
	public void publicarPagoSuciveNORealizado(String mensaje){
		pagoSuciveNoRealizado.fire(new SuciveERRORPago(mensaje));
    }
}
