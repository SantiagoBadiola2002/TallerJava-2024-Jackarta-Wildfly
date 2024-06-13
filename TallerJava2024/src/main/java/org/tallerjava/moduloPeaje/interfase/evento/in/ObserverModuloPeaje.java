package org.tallerjava.moduloPeaje.interfase.evento.in;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.evento.out.GestionINFONuevoVehiculo;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.dominio.*;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObserverModuloPeaje {
    private static final Logger log = Logger.getLogger(ObserverModuloPeaje.class);

    @Inject
    private ServicioPeaje servicioPeaje;

    public void accept(@Observes GestionINFONuevoVehiculo event) {
        log.infof("Evento procesado: GestionNuevoVehiculo: %s", event.toString());
        Identificador identificador = new Identificador(event.getMatricula(), event.getTag());
        Vehiculo vehiculo = new Vehiculo(identificador, event.getNacionalidad());

        //servicioPeaje.altaVehiculo(vehiculo);
    }

}