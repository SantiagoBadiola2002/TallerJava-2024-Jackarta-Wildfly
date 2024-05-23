package org.tallerjava.moduloPeaje.interfase.evento.out;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {
    @Inject
    private Event<PeajeVehiculoNoEncontrado> vehiculoNoEncontrado;

    @Inject
    private Event<PeajePagoNoRealizado> pagoNoRealizado; 
    
    public void publicarPagoNoRealizado(String mensaje){
    	pagoNoRealizado.fire(new PeajePagoNoRealizado(mensaje));
    }
    
    public void publicarVehiculoNoEncontrado(String mensaje){
    	vehiculoNoEncontrado.fire(new PeajeVehiculoNoEncontrado(mensaje));
    }
    
    
}
