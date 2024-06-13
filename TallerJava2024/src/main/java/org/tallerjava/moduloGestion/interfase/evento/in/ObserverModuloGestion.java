package org.tallerjava.moduloGestion.interfase.evento.in;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.DTTipoCobro;
import org.tallerjava.moduloGestion.dominio.PasadaPeaje;
import org.tallerjava.moduloPeaje.interfase.evento.out.PeajeINFONuevaPasada;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverModuloGestion {
	private static final Logger log = Logger.getLogger(ObserverModuloGestion.class);

    @Inject
    private ServicioPago servicioPago;
    
    public void accept(@Observes PeajeINFONuevaPasada event) {
        log.infof("Evento procesado: PeajeNuevaPasada: %s", event.toString());
        
        DTTipoCobro tipoCobro = (event.getTipoCobro() == 1) ? DTTipoCobro.PREPAGO :
            					(event.getTipoCobro() == 2) ? DTTipoCobro.POSTPAGO :
            												  DTTipoCobro.SUCIVE;
       
        PasadaPeaje pasada = new PasadaPeaje(event.getFechaPasada(), 
        								event.getCosto(), 
        								tipoCobro, 
        								servicioPago.traerVehiculo(event.getTag()));
        servicioPago.altaPasadaPeaje(pasada);
        
    }
    
}
