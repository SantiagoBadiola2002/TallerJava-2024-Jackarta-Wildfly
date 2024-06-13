package org.tallerjava.moduloMediosDePago.interfase.eventos.in;

import java.time.LocalDateTime;
import org.jboss.logging.Logger;

import org.tallerjava.moduloGestion.interfase.evento.out.GestionINFONuevaTarjeta;
import org.tallerjava.moduloGestion.interfase.evento.out.GestionINFONuevoVehiculo;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverModuloMediosPago {
	private static final Logger log = Logger.getLogger(ObserverModuloMediosPago.class);

    @Inject
    private ServicioMediosDePago servicioMediosPago;

    public void accept(@Observes GestionINFONuevaTarjeta event) {
        log.infof("Evento procesado: MediosPagoNuevaTarjeta: %s", event.toString());
       
        servicioMediosPago.altaCliente(event.getIdCliente(), event.getNroTarjeta(), event.getFechaVto(), event.getNombreCompletoUsuario());
    }
    
    public void accept(@Observes GestionINFONuevoVehiculo event) {
        log.infof("Evento procesado: MediosPagoNuevoVehiculo: %s", event.toString());
       
        servicioMediosPago.altaVehiculo(event.getIdCliente(), event.getMatricula(), event.getTag());
    }
    
}


