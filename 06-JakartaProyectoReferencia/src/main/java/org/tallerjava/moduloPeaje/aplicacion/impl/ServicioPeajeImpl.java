package org.tallerjava.moduloPeaje.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.dominio.Cuenta;
import org.tallerjava.moduloGestion.dominio.PostPaga;
import org.tallerjava.moduloGestion.dominio.PrePaga;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.dominio.Preferencial;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloPeaje.interfase.evento.out.PublicadorEvento;

import java.util.List;

@ApplicationScoped
public class ServicioPeajeImpl implements ServicioPeaje {
	private static final Logger log = Logger.getLogger(ServicioPeajeImpl.class);
	
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
            if (vehiculo.nacional()) {
                mandarAQueueDePagos(vehiculo);
                habilitado = true;

            } else {
                habilitado = procesarVehiculoExtranjero(tag, vehiculo);
            }
        }

        log.infof("Resultado habilitacion tag %s, matricula %s es: %b", tag, matricula, habilitado);
        return habilitado;
    }

    private boolean  procesarVehiculoExtranjero(int tag, Vehiculo vehiculo) {
    	log.infof("*** Procesando pago vehículo extranjero %s tag:", tag);
    	boolean habilitado = false;
        //todos los vehiculos extranjeros son preferenciales
        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        log.infof("Tarifa obtenida %f ",tarifa.getValor());
    	
    	//Obtengo cuentas por tag del MGestion (primera pre, segunda post)
    	List<Integer> cuentasExtranjero = servicioPagoFacade.obtenerCuentasPorTag(tag);

    	System.out.println("que tiene cuentas en 0" +cuentasExtranjero.get(0));
    	
    	//según las reglas del negocio, lo primero es cobrar con PrePago
    	if (cuentasExtranjero.get(0)>= tarifa.getValor()) {
            habilitado = servicioPagoFacade.realizarPrePago(tag, tarifa.getValor());
            log.infof("Respuesta prePago habilitado?: %b ",habilitado);
    	}else {
    		log.infof("Respuesta prePago no realizado por sin saldo o sin cuenta prepaga asociada. ");
    	}

        if (!habilitado && cuentasExtranjero.get(1)>=0 ) {
            //fallo el cobro prepago, intento con la tarjeta (postPago)
            habilitado = servicioPagoFacade.realizarPostPago(tag, tarifa.getValor());
            log.infof("Respuesta postPago habilitado?: %b ",habilitado);
            
        }else {
        	log.infof("Respuesta postPago no realizado por postPaga asociada. ");
        	
        }
        
        if (!habilitado) {
            //TODO mando evento al modulo de monitoreo
            //el auto no pasa
        	evento.publicarPagoNoRealizado(
        			"Pago no realizado a extranjero: " + tag + " ");
        }
        return habilitado;
    }


    private boolean  procesarVehiculoNacional(int tag, Vehiculo vehiculo) {
        boolean habilitado = false;

        Preferencial tarifa = repo.obtenerTarifaPreferencial();
        if (servicioPagoFacade.esClienteTelepeaje(tag)) {
            //según las reglas del negocio, lo primero es cobrar con PrePago
            habilitado = servicioPagoFacade.realizarPrePago(tag, tarifa.getValor());
            if (!habilitado) {
                //fallo el cobro prepago, intento con la tarjeta (postPago)
                habilitado = servicioPagoFacade.realizarPrePago(tag, tarifa.getValor());
            }
        }
        if (!habilitado) {
            //significa que no es cliente preferencial o que fallaron los dos sistemas
            //de cobro previos
            //TODO invocar a modulo de pago Sucive
        }

        return habilitado;
    }

    private void mandarAQueueDePagos(Vehiculo vehiculo) {
        //TODO esto lo vamos a hacer más adelante.
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
    	repo.actualizarTarifaComun(importe); 
    }

    @Override
    public void actualizarTarifaPreferencial(double importe) {
    	repo.actualizarTarifaPreferencial(importe);
    }
}
