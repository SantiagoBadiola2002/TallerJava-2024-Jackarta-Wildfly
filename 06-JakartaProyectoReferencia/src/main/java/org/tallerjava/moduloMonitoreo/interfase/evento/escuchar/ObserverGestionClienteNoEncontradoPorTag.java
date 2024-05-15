package org.tallerjava.moduloMonitoreo.interfase.evento.escuchar;

import org.tallerjava.moduloGestion.interfase.evento.out.ClienteTelepeajeNoEncontradoPorTag;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

/**
 * Observese que este Modulo si esta acoplado con el módulo de Peaje.
 * De todas maneras, no perder de vista que el modulo Peaje no conoce a modulo de Monitoreo
 * Un nivel mayor de desacoplamiento lo podemos lograr con JMS
 */
@ApplicationScoped
public class ObserverGestionClienteNoEncontradoPorTag {
	public void accept(@Observes ClienteTelepeajeNoEncontradoPorTag event) {
        //en un futuro acá voy a tener que mostrar en una gráfica de error lo ocurrido
        //System.out.println(event.getDescripcion());
    	System.out.println("Evento ClienteTelepeajeNoEncontradoPorTag");
    }
}
