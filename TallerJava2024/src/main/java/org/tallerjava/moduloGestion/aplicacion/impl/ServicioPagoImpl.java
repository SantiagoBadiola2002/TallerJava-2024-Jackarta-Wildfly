package org.tallerjava.moduloGestion.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;
import org.tallerjava.moduloGestion.interfase.evento.out.PublicadorEvento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class ServicioPagoImpl implements ServicioPago {
	private static final Logger log = Logger.getLogger(ServicioPagoImpl.class);

	@Inject
	private PublicadorEvento evento;

	@Inject
	private UsuarioRepositorio repoUsuario;

	@Override
	public boolean realizarPrePago(int tag, double importe) {
		log.infof("*** Respuesta Pre Pago realizado: tag %s, importe %s, estado Pago %s", tag, importe);
		return true;
//		boolean realizado = false;
//		Usuario usr = repoUsuario.findByTag(tag);
//		if (usr != null) {
//			if (usr.getClienteTelepeaje() != null) {
//				PrePaga ctaPrepaga = usr.getClienteTelepeaje().getCtaPrepaga();
//
//				if (ctaPrepaga.getSaldo() >= importe) {
//					ctaPrepaga.descontarSaldo(importe);
//					notificarPrePago(usr, tag, importe);
//					log.infof("*** Respuesta Pre Pago realizado: tag %s, importe %s, estado Pago %s", tag, importe,
//							realizado);
//					realizado = true;
//				} else {
//					log.infof("*** Respuesta Pre Pago NO realizado: tag %s, importe %s, saldo %s", tag, importe,
//							ctaPrepaga.getSaldo());
//					notificarSaldoInsuficiente(usr, tag, importe, ctaPrepaga.getSaldo());
//				}
//
//			} else {
//				// estoy frente a otro problema de inconsistencia ya que para tener un tag
//				// tengo que ser cliente del telepeaje
//				// TODO logear y mandar evento al modulo de monitoreo
//				evento.publicarClienteTelepeajeNoEncontradoPorTag(
//						"Cliente Telepeaje no encontrado por el tag %s: " + tag + " ");
//
//			}
//			realizado = true;
//		} else {
//			// estamos frente a un problema grave ya que dado un tag (vehiculo),
//			// no podemos saber a que Cliente pertenece, recordar que los tags se
//			// entregan cuando el Cliente se registra en el sistema
//			// TODO logear y mandar evento al modulo de monitorio
//			evento.publicarUsuarioNoEncontradoPorTag("Usuario no encontrado por el tag %s: " + tag + " ");
//
//		}
//		return realizado;
	}

	@Override
	public boolean realizarPostPago(int tag, double importe) {
		// TODO muy parecido al anterior con la diferencia de que voy a tener que
		// interactuar con el modulo de Medios de pagos para cobrar con tarjeta

		boolean realizado = false;
		Usuario usr = repoUsuario.findByTag(tag);
		if (usr != null) {
			if (usr.getClienteTelepeaje() != null) {

				PostPaga ctaPostpaga = usr.getClienteTelepeaje().getCtaPostpaga();

				if (ctaPostpaga != null) {
					List<Vehiculo> vehiculos = repoUsuario.findVehiculoByUser(usr);
					log.infof("*** Respuesta Post Pago: tag %s, importe %s, estado Pago %s", tag, importe, realizado);
					notificarPostPago(usr, tag, importe, ctaPostpaga.getTarjeta().getIdTarjeta());
					realizado = true;
				} else {
					log.infof(
							"*** Respuesta Post Pago sin cuenta PostPaga asociada: tag %s, importe %s, estado Pago %s",
							tag, importe, realizado);
				}

			} else {
				evento.publicarClienteTelepeajeNoEncontradoPorTag(
						"Cliente Telepeaje no encontrado por el tag %s: " + tag + " ");

			}
			realizado = true;
		} else {
			evento.publicarUsuarioNoEncontradoPorTag("Usuario no encontrado por el tag %s: " + tag + " ");

		}
		return realizado;
	}

	private void notificarPrePago(Usuario usr, int tag, double importe) {
		evento.publicarNotificarPrePago("Se ha realizado el PrePago");

	}

	private void notificarPostPago(Usuario usr, int tag, double importe, int idTarjeta) {
		evento.publicarNotificarPostPago("Se ha realizado el PostPago");
	}

	private void notificarSaldoInsuficiente(Usuario usr, int tag, double importe, double saldo) {
		evento.publicarNotificarSaldoInsuficiente("Saldo insuficiente en PrePago");
	}

	@Override
	public boolean esClienteTelepeaje(int tag) {
		Usuario usuario = repoUsuario.findByTag(tag);
		if (usuario.getClienteTelepeaje() != null) {
			return true;
		} else
			return false;
	}

	@Override
	public void altaClienteTelepeaje(Usuario usr) {

		// me fijo si es ya cliente telepeaje
		if (usr.getClienteTelepeaje() == null) {
			// diferencio Nacional o Extranjero
			repoUsuario.crearClienteTelepeaje(usr);
			if (usr.soyNacional()) {
				repoUsuario.crearClienteSucive((Nacional) usr);
			}
		}
	}

	
	public boolean vincularVehiculo(long ci, int tag, String matricula) {
		boolean vinculado = false;

		Usuario usr = repoUsuario.findUsuarioByCi(ci);
		Identificador i = new Identificador(matricula, tag);
		Vehiculo v = new Vehiculo(i,usr.getClienteTelepeaje(), null);
		Vinculo vinculo = new Vinculo(LocalDateTime.now(), true, v);
		
		if((usr.getVehiculosVinculados()) == null) {
			List<Vinculo> vinculos = new ArrayList<>();	
			vinculos.add(vinculo);
			vinculado = true;
		} else {
			usr.getVehiculosVinculados().add(vinculo);
			vinculado = true;
		}

		//ACTUALIZAR BD


		return vinculado;

	}
	
	 public boolean desvincularVehiculo(long ci, int tag, String matricula) {
		 boolean desvincular = false;
		 Usuario usr = repoUsuario.findUsuarioByCi(ci);
		List<Vinculo> vinculos = repoUsuario.findVinculosByUser(usr);
		 for(Vinculo v : vinculos) {
			 if(((tag != 0) && (v.getVehiculo().getIdentificador().getTag() == tag)) || (
			 (matricula != null) && (v.getVehiculo().getIdentificador().getMatricula().equals(matricula)))) {
				 v.setActivo(false);
				 v.getVehiculo().setCliente(null);
				 return desvincular = true;
			 }
		 }
		 
		 return desvincular;
	 }

	private Usuario findUserByCliente(ClienteTelepeaje cliTelepeaje) {

		return cliTelepeaje.getUsuario();
	}

	public List<Integer> obtenerCuentasPorTag(int tag) {
		Usuario usr = repoUsuario.findByTag(tag);

		List<Integer> cuentas = new ArrayList<>();

		if (usr.getClienteTelepeaje() != null) {
			if (usr.getClienteTelepeaje().getCtaPrepaga() != null) {
				cuentas.add(usr.getClienteTelepeaje().getCtaPrepaga().getSaldo());
			} else {
				cuentas.add(-1);
			}
			;

			if (usr.getClienteTelepeaje().getCtaPostpaga() != null) {

				cuentas.add(usr.getClienteTelepeaje().getCtaPostpaga().getIdTarjeta());
			} else {
				cuentas.add(-1);
			}

		} else {
			cuentas.add(-1);
			cuentas.add(-1);
		}
		return cuentas;
	}

	public ClienteTelepeaje obtenerClienteTelepeajeByCi(long ci) {
		return repoUsuario.findClienteTelepeajeByCi(ci);
	}

	@Override
	public void agregarTarjeta(long ci, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		ClienteTelepeaje clienteTelepeaje = obtenerClienteTelepeajeByCi(ci);
		Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
		PostPaga postPaga = new PostPaga(LocalDateTime.now(), tarjeta);
		repoUsuario.agregarTarjetaPostPaga(clienteTelepeaje, postPaga);
	}

	@Override
	public List<PasadasPorPeaje> consultarPasadas(long ci, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		Usuario usu = repoUsuario.findUsuarioByCi(ci);
		List<Vehiculo> vehiculos = repoUsuario.findVehiculoByUser(usu);
		List<PasadasPorPeaje> pasadas = new ArrayList<>();

		for (Vehiculo v : vehiculos) {
			for (PasadasPorPeaje p : v.getPasadas()) {
				if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
					pasadas.add(p);
				}
			}
		}

		return pasadas;
	}

	@Override
	public List<PasadasPorPeaje> consultarPasadas(long ci, int tag, String matricula, LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		Usuario usu = repoUsuario.findUsuarioByCi(ci);
		List<Vehiculo> vehiculos = repoUsuario.findVehiculoByUser(usu);
		List<PasadasPorPeaje> pasadas = new ArrayList<>();

		for (Vehiculo v : vehiculos) {
			if ((matricula != null && v.getIdentificador().getMatricula().equals(matricula))
					|| (tag != 0 && (v.getIdentificador().getTag() == tag))) {
				for (PasadasPorPeaje p : v.getPasadas()) {
					if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
						pasadas.add(p);
					}
				}
			}
		}

		return pasadas;
	}

	@Override
	public void cargarSaldo(long ci, double importe) {
		ClienteTelepeaje clienteTelepeaje = obtenerClienteTelepeajeByCi(ci);
		clienteTelepeaje.getCtaPrepaga().incrementarSaldo(importe);
	}

	@Override
	public double consultarSaldo(long ci) {
		ClienteTelepeaje clienteTelepeaje = obtenerClienteTelepeajeByCi(ci);
		return clienteTelepeaje.getCtaPrepaga().getSaldo();
	}

}
