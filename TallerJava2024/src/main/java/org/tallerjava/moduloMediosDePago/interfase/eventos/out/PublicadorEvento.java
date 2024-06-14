package org.tallerjava.moduloMediosDePago.interfase.eventos.out;

import org.tallerjava.moduloGestion.interfase.evento.out.GestionERRORClienteTelepeajeNoEncontradoPorTag;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PublicadorEvento {

	@Inject
    private Event<MediosPagoOKPagoTarjetaRealizado> pagoTarjetaRealizado;
	
	@Inject
    private Event<MediosPagoERRORAlProcesarPago> alProcesarPago;
	
	@Inject
    private Event<MediosPagoERRORPagoTarjetaNoRealizado> pagoTarjetaNoRealizado;

	public void publicarPagoTarjetaRealizado(String mensaje){
		pagoTarjetaRealizado.fire(new MediosPagoOKPagoTarjetaRealizado(mensaje));
    }
	public void publicarAlProcesarPago(String mensaje){
		alProcesarPago.fire(new MediosPagoERRORAlProcesarPago(mensaje));
    }
	
	public void publicarPagoTarjetaNoRealizado(String mensaje){
		pagoTarjetaNoRealizado.fire(new MediosPagoERRORPagoTarjetaNoRealizado(mensaje));
    }
	
	

}
