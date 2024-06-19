package org.tallerjava.moduloSucive.aplicacion.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloSucive.aplicacion.*;
import org.tallerjava.moduloSucive.dominio.*;
import org.tallerjava.moduloSucive.dominio.repo.*;
import org.tallerjava.moduloSucive.interfase.api.local.impl.ServicioMockSucive;
import org.tallerjava.moduloSucive.interfase.evento.out.PublicadorEvento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicioSuciveImpl implements ServicioSucive {
	private static final Logger log = Logger.getLogger(ServicioSuciveImpl.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	
	@Inject
	private PublicadorEvento evento;
	
	@Inject
	private SuciveRepositorio repoPago;
	
	@Inject
	private ServicioMockSucive servicioMockPago;
	
	
	@Override
	@Transactional
	public boolean notificarPago(String matricula, double importe) {
		
		log.infof(BLUE + "*** Sucive realizando pago a matricula: " + matricula + " importe: "+ importe +".");
		
		int idPago = servicioMockPago.realizarPagoSucive(matricula, importe);
		
		if (idPago != 0 ) {
			log.infof(GREEN + "*** OK: Pago Sucive realizando idPago: " + idPago +".");
			//guardo el pago
			Pago pago = new Pago(matricula, importe, LocalDateTime.now());
			repoPago.salvar(pago);	
			//para Grafana
			evento.publicarPagoSuciveRealizado("OK: Pago Sucive realizando idPago: " + idPago + ".");
			
			return true;
		}else {
			log.infof(ORANGE + "*** ERROR: Pago Sucive NO realizando idPago: " + idPago + " matricula: "+ matricula +".");
			//para Grafana
			evento.publicarPagoSuciveNORealizado("ERROR: Pago Sucive NO realizando idPago: " + idPago + ".");
		}
		return false;
	}
	
	@Override
	public List<Pago> consultaDePagos(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
		return null;
	}
	
	@Override
	public List<Pago> consultaDePagos(String matricula){
		return null;
		
	}
	
}
