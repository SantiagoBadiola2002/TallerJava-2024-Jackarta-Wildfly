package org.tallerjava.moduloPeaje.infraestructura.messaging;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.*;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(
    activationConfig = {
    @ActivationConfigProperty(
            propertyName = "destinationType",
            propertyValue = "jakarta.jms.Queue"),
    @ActivationConfigProperty(
            propertyName = "destinationLookup",
            propertyValue = "java:app/jms/ServicioPagoQueue"),
    @ActivationConfigProperty
            //Establece el número máximo de consumidores que estarán procesando
            //los mensajes
            //Por defecto este valor es 15 pero lo cambio a 1 para facilitar
            //la prueba que muestra su funcionamiento
            (propertyName = "maxSession", propertyValue = "1")
    }
    )
public class NuevoPagoConsumer implements MessageListener {
    private static final Logger log = Logger.getLogger(NuevoPagoConsumer.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
    
    
    
    @Inject
    private ServicioPeaje pagosServicios;

    public NuevoPagoConsumer() {};

    /**
     * Este método tiene la lógica que se procesa cuando el servidor recibe
     * un mensaje desde la queue.
     *
     * @param message the message passed to the listener
     */
    @Override
    public void onMessage(Message message) {
        try {

            String body = message.getBody(String.class);
            log.infof(VIOLET+"Inicio nuevo pago recibido de la Queue: \n"+message.toString()+ " body: "+
                    body);

            PagoRealizadoMessage pago = PagoRealizadoMessage.buildFromJson(body);
            
            //seria el procesar vehiculo nacional
            pagosServicios.procesarVehiculoNacional(pago);

        } catch (JMSException e) {
            log.errorf(ORANGE+"Se produjo un error al consumir el mensaej Pago %s",e.getMessage());
        }
    }
}