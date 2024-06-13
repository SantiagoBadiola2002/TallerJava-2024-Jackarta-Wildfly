package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;
import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.evento.out.*;
import org.tallerjava.moduloMonitoreo.infraestructura.RegistradorDeMetricas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;


@ApplicationScoped
public class ObserverGestion{
	private static final Logger log = Logger.getLogger(ObserverGestion.class);
	
	@Inject
    private RegistradorDeMetricas register;
	
	public void accept(@Observes GestionOKPagoPrePago event) {
		log.infof("Evento procesado: GestionPagoCuentaPrePaga: %s", event.getDescripcion());
        register.incrementarCounter(RegistradorDeMetricas.GESTION_COUNTER_PRE_PAGO);
	}

	public void accept(@Observes GestionOKPagoPostPago event) {
		log.infof("Evento procesado: GestionPagoCuentaPostPaga: %s", event.getDescripcion());
	    register.incrementarCounter(RegistradorDeMetricas.GESTION_COUNTER_POST_PAGO);
	}
	
	public void accept(@Observes GestionERRORSaldoInsuficiente event) {
		log.infof("Evento procesado: ErrorGestion Saldo Insuficiente: %s", event.getDescripcion());
	    register.incrementarCounter(RegistradorDeMetricas.GESTION_COUNTER_SALDO_INSUFICIENTE);
	}
	
	public void accept(@Observes GestionERRORClienteTelepeajeNoEncontradoPorTag event) {
        //en un futuro acá voy a tener que mostrar en una gráfica de error lo ocurrido
		log.infof("Evento procesado: Cliente Telepeaje no encontrado: %s", event.getDescripcion());
	    //register.incrementarCounter(RegistradorDeMetricas);
    }
	
	public void accept(@Observes GestionERRORVehiculoTagNoEncontradoPorTag event) {
        //en un futuro acá voy a tener que mostrar en una gráfica de error lo ocurrido
		log.infof("Evento procesado: Vehiculo no encontrado por Tag: %s", event.getDescripcion());
	    //register.incrementarCounter(RegistradorDeMetricas);
    }
	

}
