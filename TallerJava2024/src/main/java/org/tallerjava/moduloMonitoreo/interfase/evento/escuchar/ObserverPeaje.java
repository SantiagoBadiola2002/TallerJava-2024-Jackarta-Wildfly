package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;
import org.tallerjava.moduloMonitoreo.infraestructura.RegistradorDeMetricas;
import org.tallerjava.moduloPeaje.interfase.evento.out.*;

/**
 * Observese que este Modulo si esta acoplado con el módulo de Peaje.
 * De todas maneras, no perder de vista que el modulo Peaje no conoce a modulo de Monitoreo
 * Un nivel mayor de desacoplamiento lo podemos lograr con JMS
 */
@ApplicationScoped
public class ObserverPeaje {
	private static final Logger log = Logger.getLogger(ObserverGestion.class);
	
	@Inject
    private RegistradorDeMetricas register;
	
    public void accept(@Observes PeajeERRORVehiculoNoEncontrado event) {
		log.infof("Evento procesado: Vehiculo no encontrado por Tag: %s", event.getDescripcion());
	    register.incrementarCounter(RegistradorDeMetricas.PEAJE_COUNTER_VEHICULO_NO_ENCONTRADO);
    	
    }
    
    public void accept(@Observes PeajeOKPagoRealizadoExtranjero event) {
		log.infof("Evento procesado: Pago Extranjero no realizado: %s", event.getDescripcion());
	    register.incrementarCounter(RegistradorDeMetricas.PEAJE_COUNTER_PAGO_EXTRANJERO);
    	
    }
    
    public void accept(@Observes PeajeOKPagoRealizadoNacional event) {
		log.infof("Evento procesado: Pago Nacional no realizado: %s", event.getDescripcion());
	    register.incrementarCounter(RegistradorDeMetricas.PEAJE_COUNTER_PAGO_NACIONAL);
    	
    }
}
