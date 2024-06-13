package org.tallerjava.moduloPeaje.interfase.evento.out;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.impl.ServicioPeajeImpl;
import org.tallerjava.moduloPeaje.dominio.DTTipoCobro;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {

	
    @Inject
    private Event<PeajeVehiculoNoEncontrado> vehiculoNoEncontrado;

    @Inject
    private Event<PeajePagoNoRealizado> pagoNoRealizado; 
    
    @Inject
    private Event<PeajeINFONuevaPasada> nuevaPasada; 
    
    public void publicarPagoNoRealizado(String mensaje){
    	pagoNoRealizado.fire(new PeajePagoNoRealizado(mensaje));
    }
    
    public void publicarVehiculoNoEncontrado(String mensaje){
    	vehiculoNoEncontrado.fire(new PeajeVehiculoNoEncontrado(mensaje));
    }
    
    public void publicarNuevaPasada(Vehiculo vehiculo, double costo, int tipoCobro){
    	System.out.println("PUBLICADOR EVENTO PEAJE cobro: " + tipoCobro);
    	nuevaPasada.fire(new PeajeINFONuevaPasada(LocalDateTime.now(), costo, tipoCobro, 
    												vehiculo.getIdentificador().getTag(), 
    												vehiculo.getIdentificador().getMatricula()));
    }
}
