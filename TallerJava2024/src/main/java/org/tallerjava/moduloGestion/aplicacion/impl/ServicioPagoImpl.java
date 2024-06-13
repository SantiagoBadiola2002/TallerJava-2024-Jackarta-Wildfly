package org.tallerjava.moduloGestion.aplicacion.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;
import org.tallerjava.moduloGestion.interfase.evento.out.PublicadorEvento;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTIdCliente;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTVehiculo;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioPagoImpl implements ServicioPago {
	private static final Logger log = Logger.getLogger(ServicioPagoImpl.class);

	@Inject
	private PublicadorEvento evento;

	@Inject
	private UsuarioRepositorio repoUsuario;
	
	@Inject
	private ServicioMediosDePago servicioPagos;

	
	@Override
	@Transactional
	public boolean realizarPrePago(int tag, double importe) {
		log.infof("*** Respuesta Pre Pago realizado: tag , importe , estado Pago ", tag, importe);
		boolean realizado = false;
        Vehiculo vehiculo = repoUsuario.findByTagVehiculo(tag);
        if (vehiculo != null) {

            if ( vehiculo.getCliente() != null) {
                ClienteTelepeaje cli = vehiculo.getCliente();
                PrePaga ctaPrepaga = cli.getCtaPrepaga();

                if (ctaPrepaga.getSaldo() >= importe) {
                    //TODO controllar que el salo sea suficente
                    ctaPrepaga.descontarSaldo(importe);
                    
                    log.infof("OK: Realizado pasaje Pre-pago %s", tag);
                    notificarPrePago(cli.getUsuario(), tag, importe);
                    realizado = true;

                } else {
                    log.infof("¡ERROR!: Saldo insuficiente %s", tag);
                    notificarSaldoInsuficiente(cli.getUsuario(), tag, importe, cli.getCtaPrepaga().getSaldo() );
                    realizado = false;
                }

            } else {
                //estoy frente a otro problema de inconsistencia ya que para tener un tag
                //tengo que ser cliente del telepeaje
                //TODO logear y mandar evento al modulo de monitoreo
                log.infof("¡ERROR!: Cliente Telepeaje para TAG no encontrado", tag);
                notificarCliTelepeajeTagNoEncontrado( tag, importe);
                
                realizado = false;
            	
            	
            }

        } else {
            //estamos frente a un problema grave ya que dado un tag (vehiculo),
            // no podemos saber a que Cliente pertenece, recordar que los tags se
            //entregan cuando el Cliente se registra en el sistema
            //TODO logear y mandar evento al modulo de monitorio
            log.infof("¡ERROR!: Vehiculo para TAG no encontrado", tag);
            notificarVehiculoTagNoEncontrado( tag, importe);
            
            realizado = false;
        }
        return realizado;
	}

	@Override
	@Transactional
	public boolean realizarPostPago(int tag, double importe) {
		// TODO muy parecido al anterior con la diferencia de que voy a tener que
		// interactuar con el modulo de Medios de pagos para cobrar con tarjeta

		log.infof("*** Respuesta Pre Pago realizado: tag , importe , estado Pago ", tag, importe);
		boolean realizado = false;
        Vehiculo vehiculo = repoUsuario.findByTagVehiculo(tag);
        if (vehiculo != null) {

            if ( vehiculo.getCliente() != null) {
                ClienteTelepeaje cli = vehiculo.getCliente();
                
                if (cli.getCtaPostpaga()!=null) {
                	PostPaga ctaPrepaga = cli.getCtaPostpaga();
                	
                	String codAutorizada = servicioPagos.notificarPago(cli.getIdCliente(), tag, importe, cli.getCtaPostpaga().getTarjeta().getNroTarjeta());
                	if (codAutorizada.equals("PAGO APROBADO")) {
                		//TODO controllar que el salo sea suficente
                		
                    
                		log.infof("OK: Realizado pasaje Post-pago %s", tag);
                		notificarPostPago(cli.getIdCliente(), tag, importe, cli.getCtaPostpaga().getTarjeta().getNroTarjeta());
                		realizado = true;

                	} else {
                		log.infof("¡ERROR!: Tarjeta Rechaza en Post Pago", tag);
                		notificarTarjetaRechazada(cli.getIdCliente(), tag, importe, cli.getCtaPostpaga().getTarjeta().getNroTarjeta() );
                		realizado = false;
                	}
                }else {
                	//no tiene cuenta post paga
                	//no puedo realizar el pago 
                	log.infof("¡ERROR!: No tiene Tarjeta asociada para Post pago %s", tag);
                }
            } else {
                //estoy frente a otro problema de inconsistencia ya que para tener un tag
                //tengo que ser cliente del telepeaje
                //TODO logear y mandar evento al modulo de monitoreo
                log.infof("¡ERROR!: Cliente Telepeaje para TAG no encontrado", tag);
                notificarCliTelepeajeTagNoEncontrado( tag, importe);
                
                realizado = false;
            	
            	
            }

        } else {
            //estamos frente a un problema grave ya que dado un tag (vehiculo),
            // no podemos saber a que Cliente pertenece, recordar que los tags se
            //entregan cuando el Cliente se registra en el sistema
            //TODO logear y mandar evento al modulo de monitorio
            log.infof("¡ERROR!: Vehiculo para TAG no encontrado", tag);
            notificarVehiculoTagNoEncontrado( tag, importe);
            
            realizado = false;
        }
        return realizado;
	}

	private void notificarPrePago(Usuario usr, int tag, double importe) {
		evento.publicarNotificarPrePago("Se ha realizado el PrePago");
	}
	
	

	private void notificarPostPago(int idCliente, int tag, double importe, int idTarjeta) {
		evento.publicarNotificarPostPago("Se ha realizado el PostPago");
	}
	
	private void notificarTarjetaRechazada(int idCliente, int tag, double importe, int idTarjeta) {
		evento.publicarTarjetaRechazada("Tarjeta Rechazada en el PostPago");
	}

	private void notificarSaldoInsuficiente(Usuario usr, int tag, double importe, double saldo) {
		evento.publicarNotificarSaldoInsuficiente("Saldo insuficiente en PrePago");
	}

	private void notificarVehiculoTagNoEncontrado(int tag, double importe) {
		evento.publicarVehiculoTagNoEncontrado("No se encontro Vehiculo por TAG");
		
	}

	private void notificarCliTelepeajeTagNoEncontrado(int tag, double importe) {
		evento.publicarCliTelepeajeTagNoEncontrado("No se encontro Cliente Telepeaje por TAG");
		
	}
	
	@Override
	public boolean esClienteTelepeaje(int tag) {
		Usuario usuario = repoUsuario.findByTag(tag);
		if ((usuario != null) && (usuario.getClienteTelepeaje() != null)) {
			return true;
		} else
			return false;
	}

	@Override
	@Transactional
	public boolean altaClienteTelepeaje(Usuario usr) {
		log.infof("\n######### altaClienteTelepeaje 1 #########\n");
		// me fijo si es ya cliente telepeaje
		if (usr.getClienteTelepeaje() == null) {
			// diferencio Nacional o Extranjero
			ClienteTelepeaje cliTelepeaje = repoUsuario.crearClienteTelepeaje(usr);
			//esto seria alguna excepcion de la BD verrr
			if (cliTelepeaje !=null) {
				log.infof("\n######### altaClienteTelepeaje OK. IdCliente: #########\n" + cliTelepeaje.getIdCliente());
				return true;
			}
//			if (usr.getNacionalidad()==0) {
//				repoUsuario.crearClienteSucive((Nacional) usr);
//			}
		}
		return false;
	}

	@Transactional
	public boolean vincularVehiculo(int idCliente, int tag, String matricula) {
		boolean vinculado = true;

		Usuario usr = repoUsuario.findUsuarioCliTelepeaje(idCliente);
		Identificador i = new Identificador(matricula, tag);
		Vinculo vinculo = new Vinculo(LocalDateTime.now(), true);
		Vehiculo v = new Vehiculo(i,usr.getClienteTelepeaje(), usr ,vinculo);
		long idVehiculo = repoUsuario.salvarVehiculo(v);
		log.infof("\n######### Salvar Vehiculo OK. IdVehiculo: #########\n" + idVehiculo);
		
		//le digo a modulo peaje del nuevo vehiculo
		evento.publicarNuevoVehiculo(v);
		
		repoUsuario.actualizarUsuario(usr);
		


		return vinculado;

	}
	
	 public boolean desvincularVehiculo(int idCliente, int tag, String matricula) {
		 boolean desvincular = false;
//		 Usuario usr = repoUsuario.findUsuarioByCi(ci);
//		List<Vinculo> vinculos = repoUsuario.findVinculosByUser(usr);
//		 for(Vinculo v : vinculos) {
//			 if(((tag != 0) && (v.getVehiculo().getIdentificador().getTag() == tag)) || (
//			 (matricula != null) && (v.getVehiculo().getIdentificador().getMatricula().equals(matricula)))) {
//				 v.setActivo(false);
//				 v.getVehiculo().setCliente(null);
//				 return desvincular = true;
//			 }
//		 }
//		 
		 return desvincular;
	 }
	 @Override
	 public List<DTVehiculo> mostrarVehiculoVinculados(int id){
		 List<DTVehiculo> dtVehiculos = new ArrayList<>();
		 ///cambiar por find de usuario comun
		 
		 Usuario usr = repoUsuario.findUsuarioCliTelepeaje(id);
		 
		 //traigo los vehiculos
		 List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usr);
		 
		 for (Vehiculo v : vehiculos) {
			 
			 log.infof("\n######### SERVICIO PAGO TAG #########\n" + v.getIdentificador().getTag() + "#############");
			 
			 DTVehiculo dtV = new DTVehiculo(v.getCliente().getIdCliente(), 
					 							v.getIdentificador().getTag(), 
					 							v.getIdentificador().getMatricula());
			 dtVehiculos.add(dtV);
		 }
		 
		 return dtVehiculos;
	 }; 


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

				//cuentas.add(usr.getClienteTelepeaje().getCtaPostpaga());
			} else {
				cuentas.add(-1);
			}

		} else {
			cuentas.add(-1);
			cuentas.add(-1);
		}
		return cuentas;
	}

	@Override
	@Transactional
	public boolean agregarTarjeta(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta, String nombreCompletoUsuario) {
		
		boolean agregado = false;
		
		ClienteTelepeaje clienteTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		if (clienteTelepeaje != null) {
			agregado = true;
			Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
			PostPaga postPaga = new PostPaga(LocalDateTime.now(), tarjeta, clienteTelepeaje.getCtaPrepaga().getNroCuenta());
			clienteTelepeaje.setCtaPostPaga(postPaga);
			long idCuenta = repoUsuario.salvarTarjetaPostPaga(postPaga, tarjeta);
			repoUsuario.actualizarCliTelepeaje(clienteTelepeaje);
			
			evento.publicarNuevaTarjeta(clienteTelepeaje.getIdCliente() , tarjeta);
		}

		return agregado; 
	}

	@Override
	public List<PasadaPeaje> consultarPasadas(int idCliente, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		Usuario usu = repoUsuario.findUsuario(idCliente);
		List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usu);
		List<PasadaPeaje> pasadas = new ArrayList<>();

//		for (Vehiculo v : vehiculos) {
//			for (PasadaPeaje p : v.getPasadaPeaje()) {
//				if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
//					pasadas.add(p);
//				}
//			}
//		}

		return pasadas;
	}

	@Override
	public List<PasadaPeaje> consultarPasadas(int idCliente, int tag, String matricula, LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		Usuario usu = repoUsuario.findUsuario(idCliente);
		List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usu);
		List<PasadaPeaje> pasadas = new ArrayList<>();

//		for (Vehiculo v : vehiculos) {
//			if ((matricula != null && v.getIdentificador().getMatricula().equals(matricula))
//					|| (tag != 0 && (v.getIdentificador().getTag() == tag))) {
//				for (PasadaPeaje p : v.getPasadaPeaje()) {
//					if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
//						pasadas.add(p);
//					}
//				}
//			}
//		}

		return pasadas;
	}

	@Override
	@Transactional
	public double cargarSaldo(int idCliente, double importe) {
		ClienteTelepeaje cliTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		
		log.infof("\n######### CARGAR SALDO aqui #########\n" + cliTelepeaje.getCtaPrepaga().getSaldo() + "#############");
		cliTelepeaje.getCtaPrepaga().incrementarSaldo(importe);
		log.infof("\n######### CARGAR SALDO despues #########\n" + cliTelepeaje.getCtaPrepaga().getSaldo() + "#############");
		
		repoUsuario.actualizarCuentaPrepaga(cliTelepeaje.getCtaPrepaga());
		repoUsuario.actualizarCliTelepeaje(cliTelepeaje);
		return cliTelepeaje.getCtaPrepaga().getSaldo();
	}



	@Override
	public double consultarSaldo(int idCliente) {
		ClienteTelepeaje clienteTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		return clienteTelepeaje.getCtaPrepaga().getSaldo();
	}

	
	@Override
	@Transactional
	public Vehiculo traerVehiculo(int tag) {
		return repoUsuario.findByTagVehiculo(tag);
	}
	
	@Override
	@Transactional
	public void altaPasadaPeaje(PasadaPeaje pasada) {
		repoUsuario.salvarPasadaPeaje(pasada);
		
	}
}
