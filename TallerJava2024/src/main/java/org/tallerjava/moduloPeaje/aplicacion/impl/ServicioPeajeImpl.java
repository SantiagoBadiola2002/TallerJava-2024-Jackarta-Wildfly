package org.tallerjava.moduloPeaje.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.*;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTVehiculo;
import org.tallerjava.moduloSucive.aplicacion.ServicioSucive;
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
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	
    
	@Inject
    private EnviarMensajaQueueUtil mensajePago;
	
	@Inject
    private PeajeRepositorio repo;

    @Inject
    private PublicadorEvento evento;

    @Inject
    private ServicioPagoFacade servicioPagoFacade; //MODULO DE GESTION -> infra.api.local

	@Inject
	private ServicioSucive servicioPagoSuvice;
	
    @Override
    public boolean estaHabilitado(int tag, String matricula) {
    	log.infof(BLUE +"*** Verificando peaje vehiculo: tag %s, matricula: %s", tag, matricula);
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

        log.infof(BLUE + "Resultado habilitacion tag %s, matricula %s es: %b", tag, matricula, habilitado);
        return habilitado;
    }

    private boolean  procesarVehiculoExtranjero(DTVehiculo dtVehiculo) {
    	log.infof(BLUE + "*** Procesando pago vehículo extranjero %s tag:", dtVehiculo.getTag());
    	boolean habilitado = false;
        //todos los vehiculos extranjeros son preferenciales
        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        log.infof(BLUE + "Tarifa obtenida %f ",tarifa.getValor());
        //según las reglas del negocio, lo primero es cobrar con PrePago
        habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());

        log.infof(BLUE + "Respuesta prePago: %b ",habilitado);
        if (!habilitado) {
            //fallo el cobro prepago, intento con la tarjeta (postPago)
            habilitado = servicioPagoFacade.realizarPostPago(dtVehiculo.getTag(), tarifa.getValor());
            log.infof(BLUE + "Respuesta postPago: %b ",habilitado);
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
    	System.out.println(VIOLET + "ESTOY POR ENTRAR A LA ESPERA....");
    	pausa();
    	
    	log.infof(BLUE + "*** Procesando pago vehículo nacional tag:", dtVehiculo.getTag());
        boolean habilitado = false;

        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        if (servicioPagoFacade.esClienteTelepeaje(dtVehiculo.getTag())) {
            //según las reglas del negocio, lo primero es cobrar con PrePago
            habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());
            if (habilitado) {
            	//publico la pasada si lo anterior es true PREPAGO == 1
            	log.infof(GREEN + "Registro pasada Nacional por peaje con pago prepago: "+ dtVehiculo.getTag() );
            	evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 1);
            }else {
                //fallo el cobro prepago, intento con la tarjeta (postPago)
                habilitado = servicioPagoFacade.realizarPrePago(dtVehiculo.getTag(), tarifa.getValor());
                //si es habilitado true
                if (habilitado) {
                	//publico la pasada si lo anterior es true POSTPAGO == 2
                	log.infof(GREEN + "Registro pasada Nacional por peaje con pago postpago: "+ dtVehiculo.getTag() );
                	evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 2);
                }else {
                    //significa que no es cliente preferencial o que fallaron los dos sistemas
                    //de cobro previos
                    //TODO invocar a modulo de pago Sucive
                	habilitado = servicioPagoSuvice.notificarPago(dtVehiculo.getMatricula(), tarifa.getValor());
                	if(habilitado) {
                		//publico la pasada si lo anterior es true SUCIVE == 3
                    	log.infof(GREEN + "Registro pasada Nacional por peaje con pago Sucive: "+ dtVehiculo.getTag() );
                    	evento.publicarNuevaPasada(dtVehiculo, tarifa.getValor(), 3);
                	}else {
                		//TODO mando evento al modulo de monitoreo
                        //el auto no pasa
                		log.infof(ORANGE + "Vehiculo Nacional por peaje NO PASA tag: "+ dtVehiculo.getTag() );
                    	evento.publicarPagoNoRealizadoNacional("Pre, Post y Sucive Pago a vehiculo Nacional NO realizado: " + dtVehiculo.getTag());
                	}
                	
                }
            }
        }
       

        return habilitado;
    }

    private void mandarAQueueDePagos(DTVehiculo dtVehiculo) {
    	
    	PagoRealizadoMessage pagoMessage = new PagoRealizadoMessage(
    			dtVehiculo.getTag(),
    			dtVehiculo.getMatricula(),
    			dtVehiculo.getNacionalidad()
    			);
    	String repreDTVehiculoJson = pagoMessage.toJson();
    	log.infof(BLUE + "Convierto DT a json: ", repreDTVehiculoJson);
        
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
