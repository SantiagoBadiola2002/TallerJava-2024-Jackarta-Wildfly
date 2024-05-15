package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import org.tallerjava.moduloGestion.interfase.evento.out.NotificarPostPago;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class ObserverGestionNotificarPostPago {

	public void accept(@Observes NotificarPostPago event) {
		System.out.println("Evento NotificarPostPago");
	}
}
