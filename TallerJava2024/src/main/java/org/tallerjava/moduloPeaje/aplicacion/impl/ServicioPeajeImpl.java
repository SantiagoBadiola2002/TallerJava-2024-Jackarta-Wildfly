package org.tallerjava.moduloPeaje.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.*;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTVehiculo;
import org.tallerjava.moduloPeaje.infraestructura.messaging.EnviarMensajaQueueUtil;
import org.tallerjava.moduloPeaje.infraestructura.messaging.PagoRealizadoMessage;
//import ejemplo00.interfase.api.PagoDTO;

import jakarta.annotation.Resource;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import java.util.List;

@ApplicationScoped
public class ServicioPeajeImpl implements ServicioPeaje {
	private static final Logger log = Logger.getLogger(ServicioPeajeImpl.class);
    
	@Inject
    private EnviarMensajaQueueUtil mensajePago;
	
	@Inject
    private PeajeRepositorio repo;

    @Inject
    private PublicadorEvento evento;

    @Inject
    private ServicioPagoFacade servicioPagoFacade; //MODULO DE GESTION -> infra.api.local

    @Override
    public boolean estaHabilitadoSincronico(int tag, String matricula) {
    	log.infof("*** Verificando peaje vehiculo: tag %s, matricula: %s", tag, matricula);
    	boolean habilitado = false;
        Vehiculo vehiculo = existeVehiculo(tag, matricula);
        if (vehiculo != null) {
        	DTVehiculo dtVehiculo = new DTVehiculo(tag, vehiculo.getIdentificador().getMatricula(), vehiculo.getNacionalidad());
            if (vehiculo.nacional()) {
            	
                mandarAQueueDePagos(dtVehiculo);
                habilitado = true;

            } else {
                habilitado = procesarVehiculoExtranjero(dtVehiculo);
            }
        }

        log.infof("Resultado habilitacion tag %s, matricula %s es: %b", tag, matricula, habilitado);
        return habilitado;
    }

    private boolean  procesarVehiculoExtranjero(DTVehiculo dtVehiculo) {
    	log.infof("*** Procesando pago vehículo extranjero %s tag:", dtVehiculo.getTag());
    	boolean habilitado = false;
        //todos los vehiculos extranjeros son preferenciales
        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        log.infof("Tarifa obtenida %f ",tarifa.getValor());
        //según las reglas del negocio, lo primero es cobrar con PrePago
        habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());

        log.infof("Respuesta prePago: %b ",habilitado);
        if (!habilitado) {
            //fallo el cobro prepago, intento con la tarjeta (postPago)
            habilitado = servicioPagoFacade.realizarPostPago(dtVehiculo.getTag(), tarifa.getValor());
            log.infof("Respuesta postPago: %b ",habilitado);
            if (!habilitado) {
                //TODO mando evento al modulo de monitoreo
                //el auto no pasa
            	evento.publicarPagoNoRealizadoExtranjero("Pre y Post Pago a vehiculo Extranjero no realizado: " + dtVehiculo.getTag());
            	
            	
            }
        }
        return habilitado;
       
    }

    @Override
    public boolean  procesarVehiculoNacional(PagoRealizadoMessage pago) {
    	//convierto/desarmo json al dt objeto (podria ser una instancia de vehiculo)
    	DTVehiculo dtVehiculo = new DTVehiculo(
    								pago.tag(), 
    								pago.matricula(), 
    								pago.nacionalidad());
    	//para ver el proceso en el servidor
    	System.out.println("ESTOY POR ENTRAR A LA ESPERA....");
    	pausa();
    	
    	log.infof("*** Procesando pago vehículo nacional %s tag:", dtVehiculo.getTag());
        boolean habilitado = false;

        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        if (servicioPagoFacade.esClienteTelepeaje(dtVehiculo.getTag())) {
            //según las reglas del negocio, lo primero es cobrar con PrePago
            habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());
            //si es habilitado true
            //publico la pasada si lo anterior es true PREPAGO == 1
            log.infof("REGISTRAR PASADA PREPAGA: "+ dtVehiculo.getTag() );
            evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 1);
            
            if (!habilitado) {
                //fallo el cobro prepago, intento con la tarjeta (postPago)
                habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());
                //si es habilitado true
                //publico la pasada si lo anterior es true POSTPAGO == 2
                evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 2);
            }
        }
        if (!habilitado) {
            //significa que no es cliente preferencial o que fallaron los dos sistemas
            //de cobro previos
            //TODO invocar a modulo de pago Sucive
        	
        	
        	
        	if (habilitado) {
        		//si es habilitado Sucive true
        		//publico la pasada si lo anterior es true SUCIVE == 3
        		evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 3);
        	}else {
        		//TODO mando evento al modulo de monitoreo
                //el auto no pasa
            	evento.publicarPagoNoRealizadoNacional("Pre, Post y Sucive Pago a vehiculo Nacional no realizado: " + dtVehiculo.getTag());
        		
        	}
        }

        return habilitado;
    }

    private void mandarAQueueDePagos(DTVehiculo dtVehiculo) {
        //TODO por ahora lo procesamos sincrónicamente
    	
    	PagoRealizadoMessage pagoMessage = new PagoRealizadoMessage(
    			dtVehiculo.getTag(),
    			dtVehiculo.getMatricula(),
    			dtVehiculo.getNacionalidad()
    			);
    	String repreDTVehiculoJson = pagoMessage.toJson();
    	log.infof("Convierto DT a json: %s", repreDTVehiculoJson);
        
    	mensajePago.enviarMensaje(repreDTVehiculoJson);
    	//procesarVehiculoNacional(dtVehiculo);

        
    }
    
    private void pausa() {
        //simula algo de trabajo
        try {
            log.info("8 realizando pago...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Vehiculo existeVehiculo(int tag, String matricula) {
        Vehiculo vehiculo = repo.findByTag(tag);

        if (vehiculo != null) {
            log.infof("Vehiculo encontrado con tag: %s", tag);
        } else {
            vehiculo = repo.findByMatricula(matricula);
            if (vehiculo != null) {
                log.infof("Vehiculo encontrado com matricula: %s", tag);
            } else {
                //error grave el vehiculo no esta en el sistema
                evento.publicarVehiculoNoEncontrado(
                        "Vehiculo no encontrado: " + tag + " " + matricula);
            }
        }
        return vehiculo;
    }

    @Override
    public void actualizarTarifaComun(double importe) {
    	
    	 log.infof("######### IMPORTE #########" + importe);
    	 repo.actualizarTarifaComun(importe);
    }

    @Override
    public void actualizarTarifaPreferencial(double importe) {
    	repo.actualizarTarifaPreferencial(importe);
    }
    
    @Override
    @Transactional
    public void altaVehiculo(Vehiculo vehiculo) {
        log.infof("Alta de vehiculo %s", vehiculo.toString());
        repo.saveVehiculo(vehiculo);
    }
    
}
