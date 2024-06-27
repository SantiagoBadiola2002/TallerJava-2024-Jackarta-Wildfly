package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import org.jboss.logging.Logger;
import org.tallerjava.moduloMonitoreo.infraestructura.RegistradorDeMetricas;
import org.tallerjava.moduloSucive.interfase.evento.out.SuciveOKPago;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverSucive {

	private static final Logger log = Logger.getLogger(ObserverSucive.class);
	
	@Inject
    private RegistradorDeMetricas register;
	
	public void accept(@Observes SuciveOKPago event) {
		log.infof("Evento procesado: Sucive Pago OK Procesado: %s", event.getDescripcion());
        register.incrementarCounter(RegistradorDeMetricas.SUCIVE_COUNTER_COBRO_SUCIVE);
	}
}
