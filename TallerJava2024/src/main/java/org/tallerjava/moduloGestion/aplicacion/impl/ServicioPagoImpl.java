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
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTPasadaPeaje;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTVehiculo;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloPeaje.aplicacion.impl.ServicioPeajeImpl;
import org.tallerjava.moduloSucive.aplicacion.ServicioSucive;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioPagoImpl implements ServicioPago {
	private static final Logger log = Logger.getLogger(ServicioPagoImpl.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	@Inject
	private PublicadorEvento evento;

	@Inject
	private UsuarioRepositorio repoUsuario;
	
	@Inject
	private ServicioMediosDePago servicioPagos;

	
	@Override
	@Transactional
	public boolean realizarPrePago(int tag, double importe) {
		log.infof(VIOLET + "*** Inicio realizar PrePago a: tag: " + tag + " importe: " + importe);
		boolean realizado = false;
        Vehiculo vehiculo = repoUsuario.findByTagVehiculo(tag);
        if (vehiculo != null) {

            if ( vehiculo.getCliente() != null) {
                ClienteTelepeaje cli = vehiculo.getCliente();
                PrePaga ctaPrepaga = cli.getCtaPrepaga();

                if (ctaPrepaga.getSaldo() >= importe) {
                    //TODO controllar que el salo sea suficente
                    ctaPrepaga.descontarSaldo(importe);
                    
                    log.infof(GREEN + "OK: Realizado pasaje Pre-pago al tag: ", tag);
                    evento.publicarNotificarPrePago("Se ha realizado el PrePago");
                  
                    realizado = true;

                } else {
                    log.infof(ORANGE + "¡ERROR!: Saldo insuficiente %s", tag);
                    evento.publicarNotificarSaldoInsuficiente("Saldo insuficiente en PrePago");
                    realizado = false;
                }

            } else {
                //estoy frente a otro problema de inconsistencia ya que para tener un tag
                //tengo que ser cliente del telepeaje
                //TODO logear y mandar evento al modulo de monitoreo
                log.infof(ORANGE + "¡ERROR!: Cliente Telepeaje para TAG no encontrado", tag);
                evento.publicarCliTelepeajeTagNoEncontrado("Cliente Telepeaje no encontrado en pre pago tag: " + tag);
                
                realizado = false;
            	
            	
            }

        } else {
            //estamos frente a un problema grave ya que dado un tag (vehiculo),
            // no podemos saber a que Cliente pertenece, recordar que los tags se
            //entregan cuando el Cliente se registra en el sistema
            //TODO logear y mandar evento al modulo de monitorio
            log.infof(ORANGE + "¡ERROR!: Vehiculo para TAG no encontrado", tag);
            evento.publicarVehiculoTagNoEncontrado("Vehiculo no encontrado en Pre pago tag: " + tag);
            
            realizado = false;
        }
        return realizado;
	}

	@Override
	@Transactional
	public boolean realizarPostPago(int tag, double importe) {
		// TODO muy parecido al anterior con la diferencia de que voy a tener que
		// interactuar con el modulo de Medios de pagos para cobrar con tarjeta

		log.infof(VIOLET + "*** Inicio realizar Post Pago a: tag: " + tag + " importe: " + importe);
		
		boolean realizado = false;
        Vehiculo vehiculo = repoUsuario.findByTagVehiculo(tag);
        if (vehiculo != null) {

            if ( vehiculo.getCliente() != null) {
                ClienteTelepeaje cli = vehiculo.getCliente();
                
                if (cli.getCtaPostpaga()!=null) {
                	PostPaga ctaPrepaga = cli.getCtaPostpaga();
                	
                	String codAutorizada = servicioPagos.notificarPago(cli.getIdCliente(), tag, importe, cli.getCtaPostpaga().getTarjeta().getNroTarjeta());
                	if (codAutorizada.equals("PAGO APROBADO")) {                		
                    
                		log.infof(GREEN + "OK: Realizado pago Post-pago al tag: "+ tag);
                		evento.publicarNotificarPostPago("Se ha realizado el pagos PostPago");
                		realizado = true;
                		

                	} else {
                		log.infof(ORANGE + "¡ERROR!: Tarjeta Rechaza en Post Pago al tag: "+ tag);
                		evento.publicarTarjetaRechazada("Tarjeta Rechazada en el PostPago");
                		
                		realizado = false;
                	}
                }else {
                	//no tiene cuenta post paga
                	//no puedo realizar el pago 
                	log.infof(ORANGE +"¡ERROR!: No tiene Tarjeta asociada para Post pago al tag: "+ tag);
                }
            } else {
                //estoy frente a otro problema de inconsistencia ya que para tener un tag
                //tengo que ser cliente del telepeaje
                //TODO logear y mandar evento al modulo de monitoreo
                log.infof(ORANGE + "¡ERROR!: Cliente Telepeaje para TAG no encontrado", tag);
                evento.publicarCliTelepeajeTagNoEncontrado("No se encontro Cliente Telepeaje por TAG");
                
                realizado = false;
            	
                
            }

        } else {
            //estamos frente a un problema grave ya que dado un tag (vehiculo),
            // no podemos saber a que Cliente pertenece, recordar que los tags se
            //entregan cuando el Cliente se registra en el sistema
            //TODO logear y mandar evento al modulo de monitorio
            log.infof(ORANGE + "¡ERROR!: Vehiculo para TAG no encontrado", tag);
            evento.publicarVehiculoTagNoEncontrado("No se encontro Vehiculo por TAG");
            
            realizado = false;
        }
        return realizado;
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
		log.infof(VIOLET + "*** Inicio altaClienteTelepeaje");
		// me fijo si es ya cliente telepeaje
		if (usr.getClienteTelepeaje() == null) {
			// diferencio Nacional o Extranjero en la BD
			ClienteTelepeaje cliTelepeaje = repoUsuario.crearClienteTelepeaje(usr);
			//esto seria alguna excepcion de la BD verrr
			if (cliTelepeaje !=null) {
				log.infof(GREEN + "OK: altaClienteTelepeaje OK. IdCliente: " + cliTelepeaje.getIdCliente() + " nombre: " + cliTelepeaje.getUsuario().getNombre() + " nacionalidad: " + cliTelepeaje.getUsuario().getNacionalidad());
				return true;
			}else {
				log.infof(ORANGE + "¡ERROR! no se pudo crear el cliente Telepeaje al usuario: " + usr.getNombre() + " nacionalidad: "+usr.getNacionalidad());
			}

		}
		return false;
	}

	@Transactional
	public boolean vincularVehiculo(int idCliente, int tag, String matricula) {
		boolean vinculado = true;
		log.infof(VIOLET + "*** Inicio vincularVehiculo ");
		Usuario usr = repoUsuario.findUsuarioCliTelepeaje(idCliente);
		Identificador i = new Identificador(matricula, tag);
		Vinculo vinculo = new Vinculo(LocalDateTime.now(), true);
		Vehiculo v = new Vehiculo(i,usr.getClienteTelepeaje(), usr ,vinculo);
		long idVehiculo = repoUsuario.salvarVehiculo(v);
		log.infof(GREEN + "OK: Vehiculo vinculado al Cliente con idVehiculo: " + idVehiculo);
		
		//le digo a modulo peaje y modulo medios pago del nuevo vehiculo
		evento.publicarNuevoVehiculo(v);
		
		repoUsuario.actualizarUsuario(usr);
		


		return vinculado;

	}
	//desvincula el vehiculo pero no borro sus pasadas por peaje 
	@Transactional
	 public boolean desvincularVehiculo(int idCliente, int tag, String matricula) {
		 boolean desvincular = false;
		 log.infof(VIOLET + "*** Inicio desvincularVehiculo ");
		 Usuario usr = repoUsuario.findUsuarioCliTelepeaje(idCliente);
		 if (usr != null) {
			 Vehiculo v = repoUsuario.findByTagVehiculo(tag);
			 if (v != null && v.getVinculo().isActivo()) {
				 //desvinculo cliente y el usuario
				 v.setUsuario(null);
				 v.setCliente(null);
				 //paso a falso el vinculo
				 v.getVinculo().setActivo(false);
				 repoUsuario.actualizarVehiculo(v);//no lo borro, lo desasocio del cliente
				 desvincular = true;
				 log.infof(GREEN + "OK: Vehiculo con tag: "+tag+" desvinculado al Cliente con idCliente: " + idCliente );
			 }else {
				 log.infof(ORANGE + "¡ERROR! No se encontro Vehiculo por tag: " + tag);
				 evento.publicarVehiculoTagNoEncontrado("No se encontro Vehiculo por TAG");
			 }
		 }else {
			 log.infof(ORANGE + "¡ERROR! No se encontro Cliente Telepeaje por idCliente: " + idCliente);
			 evento.publicarCliTelepeajeTagNoEncontrado("No se encontro Cliente Telepeaje por idCliente");
		 }
		 

		 
		 return desvincular;
	 }
	//muestra los vehiculos activos del cliente
	 @Override
	 public List<DTVehiculo> mostrarVehiculoVinculados(int id){
		 log.infof(VIOLET + "*** Inicio mostrarVehiculoVinculados ");
		 List<DTVehiculo> dtVehiculos = new ArrayList<>();
		 ///cambiar por find de usuario comun
		 
		 Usuario usr = repoUsuario.findUsuarioCliTelepeaje(id);
		 
		 //traigo los vehiculos
		 List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usr);
		 
		 for (Vehiculo v : vehiculos) {
			 //solo traigo los vehiculos activos del Cliente Telepeaje
			 if (v.getVinculo().isActivo()) {
				 log.infof(BLUE+" Vehiculos del idCliente: " + id + " Vehiculo activo con tag: " +v.getIdentificador().getTag() + "\n");
			 
				 DTVehiculo dtV = new DTVehiculo(v.getCliente().getIdCliente(), 
					 							v.getIdentificador().getTag(), 
					 							v.getIdentificador().getMatricula());
				 dtVehiculos.add(dtV);
			 }
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
		log.infof(VIOLET + "*** Inicio agregarTarjeta ");
		boolean agregado = false;
		
		ClienteTelepeaje clienteTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		if (clienteTelepeaje != null) {
			agregado = true;
			Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
			PostPaga postPaga = new PostPaga(LocalDateTime.now(), tarjeta, clienteTelepeaje.getCtaPrepaga().getNroCuenta());
			clienteTelepeaje.setCtaPostPaga(postPaga);
			long idCuenta = repoUsuario.salvarTarjetaPostPaga(postPaga, tarjeta);
			repoUsuario.actualizarCliTelepeaje(clienteTelepeaje);
			log.infof(GREEN + "OK: Tarjeta asociada al idCliente: "+idCliente+" nombre completo: " + nombreCompletoUsuario );
			evento.publicarNuevaTarjeta(clienteTelepeaje.getIdCliente() , tarjeta);
		}

		return agregado; 
	}

	@Override
	public List<DTPasadaPeaje> consultarPasadas(int idCliente, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
		log.infof(VIOLET + "*** Inicio consultarPasadas por fechas ");
		Usuario usr = repoUsuario.findUsuarioCliTelepeaje(idCliente);
		List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usr);
		List<DTPasadaPeaje> pasadas = new ArrayList<>();
		//System.out.println("PASAda fecha:" + fechaInicial + "///"+ fechaFinal +"\n");
		for (Vehiculo v : vehiculos) {
			//si esta activo el vehiculo para el cliente
			if (v.getVinculo().isActivo()) {
				//me traigo las pasadas del vehiculo
				List<PasadaPeaje> listaPasadas = repoUsuario.traerPasadasVehiculo(v);
				//chequeo las fechas
				for (PasadaPeaje p : listaPasadas) {
					
					log.infof(BLUE+ "Pasada tag:" + p.getVehiculo().getIdentificador().getTag() + " fecha: "+ p.getFecha() +"\n");
					if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
						//lo agrego al listado
						DTPasadaPeaje dtPasada = new DTPasadaPeaje(idCliente,
																	p.getVehiculo().getIdentificador().getTag(),
																	p.getVehiculo().getIdentificador().getMatricula(), 
																	p.getFecha().toString(),
																	p.getCosto());
						pasadas.add(dtPasada);
					}
				}
				
			}
			
		}

		return pasadas;
	}

	@Override
	public List<DTPasadaPeaje> consultarPasadas(int idCliente, int tag, String matricula, LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		log.infof(VIOLET + "*** Inicio consultarPasadas por idCliente ");
		Usuario usr = repoUsuario.findUsuarioCliTelepeaje(idCliente);
		List<Vehiculo> vehiculos = repoUsuario.traerVehiculosUsr(usr);
		List<DTPasadaPeaje> pasadas = new ArrayList<>();
		//System.out.println("PASAda fecha:" + fechaInicial + "///"+ fechaFinal +"\n");
		for (Vehiculo v : vehiculos) {
			//si esta activo el vehiculo para el cliente
			if (v.getVinculo().isActivo() && v.getIdentificador().getTag()==tag) {
				//me traigo las pasadas del vehiculo
				List<PasadaPeaje> listaPasadas = repoUsuario.traerPasadasVehiculo(v);
				//chequeo las fechas
				for (PasadaPeaje p : listaPasadas) {
					
					log.infof(BLUE+ "Pasada idCliente: "+idCliente+ " con tag: " + p.getVehiculo().getIdentificador().getTag() + " fecha: "+ p.getFecha() +"\n");
					if (p.getFecha().isAfter(fechaInicial) && p.getFecha().isBefore(fechaFinal)) {
						//lo agrego al listado
						DTPasadaPeaje dtPasada = new DTPasadaPeaje(idCliente,
																	p.getVehiculo().getIdentificador().getTag(),
																	p.getVehiculo().getIdentificador().getMatricula(), 
																	p.getFecha().toString(),
																	p.getCosto());
						pasadas.add(dtPasada);
					}
				}
				
			}
			
		}

		return pasadas;
	}

	@Override
	@Transactional
	public double cargarSaldo(int idCliente, double importe) {
		log.infof(VIOLET + "*** Inicio cargarSaldo ");
		ClienteTelepeaje cliTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		
		//log.infof("\n######### CARGAR SALDO aqui #########\n" + cliTelepeaje.getCtaPrepaga().getSaldo() + "#############");
		cliTelepeaje.getCtaPrepaga().incrementarSaldo(importe);
		log.infof(GREEN + "OK: se cargo saldo al idCliente: "+idCliente+" saldo actual: " + cliTelepeaje.getCtaPrepaga().getSaldo());
		
		repoUsuario.actualizarCuentaPrepaga(cliTelepeaje.getCtaPrepaga());
		repoUsuario.actualizarCliTelepeaje(cliTelepeaje);
		
		
		return cliTelepeaje.getCtaPrepaga().getSaldo();
	}



	@Override
	public double consultarSaldo(int idCliente) {
		log.infof(VIOLET + "*** Inicio cargarSaldo ");
		ClienteTelepeaje clienteTelepeaje = repoUsuario.findCliTelepeaje(idCliente);
		log.infof(BLUE + "Saldo del idCliente: "+idCliente+" saldo actual: " + clienteTelepeaje.getCtaPrepaga().getSaldo());
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
