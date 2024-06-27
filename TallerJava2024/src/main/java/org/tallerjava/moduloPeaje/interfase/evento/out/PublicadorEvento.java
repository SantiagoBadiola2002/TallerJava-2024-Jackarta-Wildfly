package org.tallerjava.moduloPeaje.interfase.evento.out;

import java.time.LocalDateTime;

import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTVehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {

	
    @Inject
    private Event<PeajeERRORVehiculoNoEncontrado> vehiculoNoEncontrado;

    @Inject
    private Event<PeajeOKPagoRealizadoExtranjero> pagoRealizadoExtranjero; 
    
    @Inject
    private Event<PeajeOKPagoRealizadoNacional> pagoRealizadoNacional; 
    
    @Inject
    private Event<PeajeINFONuevaPasada> nuevaPasada; 
    
    public void publicarPagoRealizadoExtranjero(String mensaje){
    	pagoRealizadoExtranjero.fire(new PeajeOKPagoRealizadoExtranjero(mensaje));
    }
    
    public void publicarPagoRealizadoNacional(String mensaje){
    	pagoRealizadoNacional.fire(new PeajeOKPagoRealizadoNacional(mensaje));
    }
    
    public void publicarVehiculoNoEncontrado(String mensaje){
    	vehiculoNoEncontrado.fire(new PeajeERRORVehiculoNoEncontrado(mensaje));
    }
    
    public void publicarNuevaPasada(DTVehiculo vehiculo, double costo, int tipoCobro){
    	System.out.println("PUBLICADOR EVENTO PEAJE cobro: " + tipoCobro);
    	nuevaPasada.fire(new PeajeINFONuevaPasada(LocalDateTime.now(), costo, tipoCobro, 
    												vehiculo.getTag(), 
    												vehiculo.getMatricula()));
    }
}
