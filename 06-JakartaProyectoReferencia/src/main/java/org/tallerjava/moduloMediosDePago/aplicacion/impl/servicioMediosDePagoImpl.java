package org.tallerjava.moduloMediosDePago.aplicacion.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloMediosDePago.aplicacion.servicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;
import org.tallerjava.moduloMediosDePago.interfase.eventos.out.PublicadorDeEventos;

import jakarta.inject.Inject;

public class servicioMediosDePagoImpl implements servicioMediosDePago {

	private static final Logger log = Logger.getLogger(servicioMediosDePagoImpl.class);

	@Inject
	private PublicadorDeEventos evento;

	@Inject
	private PagosRepositorio pagosRepositorio;

	 @Inject
	 private ServicioPagoFacade servicioPagoFacade;

	@Override
	public void altaCliente(long ci, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		//LLAMA AL MODULO GESTION CLIENTE TELEPEAJE CON CI DEBE EXISTIR PREVIAMENTE
		//HAY QUE GUARDARLO AQUI?
		servicioPagoFacade.agregarTarjeta(ci, nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);

	}

	@Override
	public int notificarPago(Cliente cliente, Vehiculo vehiculo, double importe, Tarjeta tarjeta) {
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
