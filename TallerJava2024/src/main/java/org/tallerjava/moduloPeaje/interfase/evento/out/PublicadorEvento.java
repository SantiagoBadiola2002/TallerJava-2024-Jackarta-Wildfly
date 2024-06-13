package org.tallerjava.moduloPeaje.interfase.evento.out;

import java.time.LocalDateTime;

import org.tallerjava.moduloPeaje.dominio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {

	
    @Inject
    private Event<PeajeERRORVehiculoNoEncontrado> vehiculoNoEncontrado;

    @Inject
    private Event<PeajeERRORPagoNoRealizadoExtranjero> pagoNoRealizadoExtranjero; 
    
    @Inject
    private Event<PeajeERRORPagoNoRealizadoNacional> pagoNoRealizadoNacional; 
    
    @Inject
    private Event<PeajeINFONuevaPasada> nuevaPasada; 
    
    public void publicarPagoNoRealizadoExtranjero(String mensaje){
    	pagoNoRealizadoExtranjero.fire(new PeajeERRORPagoNoRealizadoExtranjero(mensaje));
    }
    
    public void publicarPagoNoRealizadoNacional(String mensaje){
    	pagoNoRealizadoNacional.fire(new PeajeERRORPagoNoRealizadoNacional(mensaje));
    }
    
    public void publicarVehiculoNoEncontrado(String mensaje){
    	vehiculoNoEncontrado.fire(new PeajeERRORVehiculoNoEncontrado(mensaje));
    }
    
    public void publicarNuevaPasada(Vehiculo vehiculo, double costo, int tipoCobro){
    	System.out.println("PUBLICADOR EVENTO PEAJE cobro: " + tipoCobro);
    	nuevaPasada.fire(new PeajeINFONuevaPasada(LocalDateTime.now(), costo, tipoCobro, 
    												vehiculo.getIdentificador().getTag(), 
    												vehiculo.getIdentificador().getMatricula()));
    }
}
