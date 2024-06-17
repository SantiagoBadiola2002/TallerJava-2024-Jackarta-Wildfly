package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import org.jboss.logging.Logger;

import org.tallerjava.moduloMediosDePago.interfase.eventos.out.*;
import org.tallerjava.moduloMonitoreo.infraestructura.RegistradorDeMetricas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;


@ApplicationScoped
public class ObserverMediosPago{
	private static final Logger log = Logger.getLogger(ObserverGestion.class);
	
	@Inject
    private RegistradorDeMetricas register;
//	
//	public void accept(@Observes MediosPagoERRORAlProcesarPago event) {
//		log.infof("Evento procesado: MediosPago Error ProcesarPago: %s", event.getDescripcion());
//        register.incrementarCounter(RegistradorDeMetricas.MEDIOSPAGO_COUNTER_FALLO_PROCESAR_PAGO);
//	}
//
//	public void accept(@Observes MediosPagoERRORPagoTarjetaNoRealizado event) {
//		log.infof("Evento procesado: MediosPago Error PagoTarjetaNoRealizado: %s", event.getDescripcion());
//	    register.incrementarCounter(RegistradorDeMetricas.MEDIOSPAGO_COUNTER_COBRO_TARJETA_RECHAZADO);
//	}
//	
//	public void accept(@Observes MediosPagoOKPagoTarjetaRealizado event) {
//		log.infof("Evento procesado: MediosPago Ok PagoTarjetaRealizado: %s", event.getDescripcion());
//	    register.incrementarCounter(RegistradorDeMetricas.MEDIOSPAGO_COUNTER_COBRO_TARJETA);
//	}
//	

}
