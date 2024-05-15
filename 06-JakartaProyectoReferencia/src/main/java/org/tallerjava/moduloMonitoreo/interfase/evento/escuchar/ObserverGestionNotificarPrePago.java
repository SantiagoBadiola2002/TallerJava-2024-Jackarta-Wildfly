package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import org.tallerjava.moduloGestion.interfase.evento.out.NotificarPrePago;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;


@ApplicationScoped
public class ObserverGestionNotificarPrePago {
	
	public void accept(@Observes NotificarPrePago event) {
		System.out.println("Evento NotificarPrePago");
	}

}
