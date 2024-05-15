package org.tallerjava.moduloGestion.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.ClienteTelepeaje;
import org.tallerjava.moduloGestion.dominio.Cuenta;
import org.tallerjava.moduloGestion.dominio.Nacional;
import org.tallerjava.moduloGestion.dominio.PostPaga;
import org.tallerjava.moduloGestion.dominio.PrePaga;
import org.tallerjava.moduloGestion.dominio.Tarjeta;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.dominio.Vehiculo;
import org.tallerjava.moduloGestion.dominio.Vinculo;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;
import org.tallerjava.moduloGestion.interfase.evento.out.PublicadorEvento;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioPagoImpl implements ServicioPago {
	private static final Logger log = Logger.getLogger(ServicioPagoImpl.class);
	
    @Inject
    private PublicadorEvento evento;
    
    @Inject
    private UsuarioRepositorio repoUsuario;

    @Override
    public boolean realizarPrePago(int tag, double importe) {
        boolean realizado = false;
        Usuario usr = repoUsuario.findByTag(tag);
        if (usr != null) {
            if (usr.getClienteTelepeaje() != null) {
                PrePaga ctaPrepaga = usr.getClienteTelepeaje().getCtaPrepaga();
                ctaPrepaga.descontarSaldo(importe);

                
                notificarPrePago(usr, tag, importe);
                log.infof("*** Respuesta Pre Pago: tag %s, importe %s, estado Pago %s", tag, importe, realizado);
                realizado = true;
            } else {
                //estoy frente a otro problema de inconsistencia ya que para tener un tag
                //tengo que ser cliente del telepeaje
                //TODO logear y mandar evento al modulo de monitoreo
                evento.publicarClienteTelepeajeNoEncontradoPorTag(
                        "Cliente Telepeaje no encontrado por el tag %s: " + tag + " ");
            	
            }
            realizado = true;
        } else {
            //estamos frente a un problema grave ya que dado un tag (vehiculo),
            // no podemos saber a que Cliente pertenece, recordar que los tags se
            //entregan cuando el Cliente se registra en el sistema
            //TODO logear y mandar evento al modulo de monitorio
            evento.publicarUsuarioNoEncontradoPorTag(
                    "Usuario no encontrado por el tag %s: " + tag + " ");
        	
        }
        return realizado;
    }
    
    
    @Override
    public boolean realizarPostPago(int tag, double importe) {
        //TODO muy parecido al anterior con la diferencia de que voy a tener que
        //interactuar con el modulo de Medios de pagos para cobrar con tarjeta
    	
    	 boolean realizado = false;
         Usuario usr = repoUsuario.findByTag(tag);
         if (usr != null) {
             if (usr.getClienteTelepeaje() != null) {
            	 
            	 PostPaga ctaPostpaga = usr.getClienteTelepeaje().getCtaPostpaga();
            	 //notificarPago(Cliente, Vehículo, importe, Tarjeta)
            	 Vehiculo vehiculo = repoUsuario.findVehiculoByUser(usr);
            	 notificarPostPago(usr, tag, 100, ctaPostpaga.getTarjeta());
                 log.infof("*** Respuesta Post Pago: tag %s, importe %s, estado Pago %s", tag, importe, realizado);
                 realizado = true;
             } else {
                 evento.publicarClienteTelepeajeNoEncontradoPorTag(
                         "Cliente Telepeaje no encontrado por el tag %s: " + tag + " ");
             	
             }
             realizado = true;
         } else {
             evento.publicarUsuarioNoEncontradoPorTag(
                     "Usuario no encontrado por el tag %s: " + tag + " ");
         	
         }
         return realizado;
    }
    
    

    private void notificarPrePago(Usuario usr, int tag, double importe) {
//        List<Vinculo> vinculos = new ArrayList<>();
//        Vehiculo vehiculo = repoUsuario.findVehiculoByTag(tag);
//        ClienteTelepeaje cliTelepeaje = usr.getClienteTelepeaje();
        evento.publicarNotificarPrePago("Se ha realizado el PrePago, Usuario: ");
        
    }
    
    private void notificarPostPago(Usuario usr, int tag, double importe, Tarjeta tarjeta) {
       
    }

    

    @Override
    public boolean esClienteTelepeaje(int tag) {
        Usuario usuario = repoUsuario.findByTag(tag);
        if (usuario.getClienteTelepeaje() != null) {
            return true;
        } else return false;
    }
    
    @Override
    public void altaClienteTelepeaje(Usuario usr) {
    	
    	//me fijo si es ya cliente telepeaje
    	if (usr.getClienteTelepeaje() == null) {
    		//diferencio Nacional o Extranjero
    		repoUsuario.crearClienteTelepeaje(usr);
    		if (usr.soyNacional()) {
    			repoUsuario.crearClienteSucive((Nacional) usr);
    		}
    	}	
    }
    
    public boolean vincularVehiculo(ClienteTelepeaje cliTelepeaje, Vehiculo vehiculo) {
    	boolean vinculado = false;
    	
    	Usuario usr = findUserByCliente(cliTelepeaje);
    	
    	if (usr == null) {//no encontro usuario
    		System.out.println("No se encontró usuario");

    	}else {
    			repoUsuario.vicularUsuarioVehiculo(usr,  vehiculo);
    			System.out.println("Usuario y Vehiculo vinculado");
    			vinculado = true; 
    	}
    	
    	return vinculado;
    	
    }

	private Usuario findUserByCliente(ClienteTelepeaje cliTelepeaje) {
		
		return cliTelepeaje.getUsuario();
	}
    
}
