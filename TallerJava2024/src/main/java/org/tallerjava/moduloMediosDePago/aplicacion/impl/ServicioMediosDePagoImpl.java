package org.tallerjava.moduloMediosDePago.aplicacion.impl;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;
import org.tallerjava.moduloMediosDePago.interfase.eventos.out.PublicadorDeEventos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicioMediosDePagoImpl implements ServicioMediosDePago {

	private static final Logger log = Logger.getLogger(ServicioMediosDePagoImpl.class);

	@Inject
	private PublicadorDeEventos evento;

	@Inject
	private PagosRepositorio repoPagos;

	 @Inject
	 private ServicioPagoFacade servicioPagoFacade;

	@Override
	@Transactional
	public void altaCliente(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		log.infof("*** Agregando Ciente y Tarjeta: ", idCliente, nroTarjeta);
		
		Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
		Cliente cli = new Cliente(idCliente, tarjeta, null);
		
			
		repoPagos.salvarCliente(cli);
	}
	@Override
	public int notificarPago(int idCliente, Vehiculo vehiculo, double importe, Tarjeta tarjeta) {
		return 0;
	}
	
	
	@Override
	public List<Pago> consultaDePagos(Date fechaInicial, Date fechaFinal) {
		//return servicioPagoFacade.consultarPagosPorFechas(fechaInicial, fechaFinal);
		//FALTA A SUCIVE
		return null;
	}

	@Override
	public List<Pago> consultaDePagos(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pago> consultaDePagos(Cliente cliente, Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

}
