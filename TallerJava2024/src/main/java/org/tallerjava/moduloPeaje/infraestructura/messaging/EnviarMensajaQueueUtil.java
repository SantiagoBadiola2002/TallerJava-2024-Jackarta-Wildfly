package org.tallerjava.moduloPeaje.infraestructura.messaging;

import org.jboss.logging.Logger;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import jakarta.jms.ObjectMessage;




@ApplicationScoped
public class EnviarMensajaQueueUtil {
    Logger log = Logger.getLogger(EnviarMensajaQueueUtil.class);

    //injecto objeto que me permite interactuar con una queue
    @Inject
    private JMSContext jmsContext;

    @Resource (lookup = "java:jboss/exported/jms/queue/servicioPago") //direccion jndi
    //otro tipo de injección de depenencia
    //en este caso injecto un recurso (queue) disponible
    private Queue queuePagosRealizados ;

    public void enviarMensaje(String mensaje) {
        log.infof("3 Inicio envio mensaje: %s", mensaje);

        jmsContext.createProducer().send(queuePagosRealizados, mensaje);
        log.infof("4 Fin envio mensaje: %s", mensaje);
    }
}
