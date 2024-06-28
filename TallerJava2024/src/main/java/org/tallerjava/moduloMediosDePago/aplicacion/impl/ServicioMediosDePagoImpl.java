package org.tallerjava.moduloMediosDePago.aplicacion.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;
import org.tallerjava.moduloMediosDePago.interfase.eventos.out.PublicadorEvento;
import org.tallerjava.moduloMediosDePago.interfase.remota.rest.dto.DTPagos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicioMediosDePagoImpl implements ServicioMediosDePago {

	private static final Logger log = Logger.getLogger(ServicioMediosDePagoImpl.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	 
	@Inject
	private PublicadorEvento evento;

	@Inject
	private PagosRepositorio repoPagos;

	@Inject
	private ServicioPagoFacade servicioPagoFacade;

	@Override
	@Transactional
	public void altaCliente(int idCliente, int nroTarjeta, LocalDateTime fechaVtoTarjeta,
			String nombreCompletoUsuario) {
		log.infof(BLUE+ "Medios Pago - Agregando Ciente: " +idCliente + "numero Tarjeta: " +nroTarjeta);

		Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
		Cliente cli = new Cliente(idCliente, tarjeta, null);
		log.infof(GREEN + "OK: Tarjeta agregada nro: "+ tarjeta.getNro());
		repoPagos.salvarCliente(cli);
	}

	@Override
	@Transactional
	public void altaVehiculo(int idCliente, String matricula, int tag) {
		log.infof(BLUE+ "Medios Pago - Agregando Ciente y Vehiculo idCliente: "+ idCliente + " tag: "+tag);
		Identificador idVehiculo = new Identificador(matricula, tag);
		Cliente cli = repoPagos.findByIdCliente(idCliente);

		Vehiculo v = new Vehiculo(idVehiculo, cli);

		repoPagos.salvarVehiculo(v);

	}

	@Override
	public String notificarPago(int idCliente, int tag, double importe, int nroTarjeta) {
		log.infof(VIOLET +"*** Inicio de Pago con sistema Externo Cliente: "+idCliente+ " Vehiculo tag: "+ tag);
		Cliente cli = repoPagos.findByIdCliente(idCliente);
		
//		String responseBody = "PAGO RECHAZADO";
//		Cliente cli = repoPagos.findByIdCliente(idCliente);
//		//guardar pago
//		Pago pago = new Pago(LocalDateTime.now(), cli, cli.getTarjeta(), tag, importe);
//		repoPagos.salvarPago(pago);
//		evento.publicarPagoTarjetaNoRealizado(responseBody);
//		//evento.publicarPagoTarjetaRealizado(responseBody);
		
		//DESCOMENTAR LUEGO DE LEVANTAR EL SERVIDOR PAGOS
		
		Client client = ClientBuilder.newClient();
		String responseBody = "";
		try {
            // Realizar la solicitud GET al endpoint autorizarPago con el número de tarjeta en la URL
            Response response = client.target("http://localhost:8080/ServicioMockMedioPago/api/servicioPagosMock")
                    .path("/autorizarPago/" + nroTarjeta)
                    .request(MediaType.TEXT_PLAIN)
                    .get();

            // Verificar la respuesta
            if (response.getStatus() == 200) {
                responseBody = response.readEntity(String.class);
                if (responseBody.equals("PAGO APROBADO")) {
                	evento.publicarPagoTarjetaRealizado(responseBody);
					 //guardar pago
		Pago pago = new Pago(LocalDateTime.now(), cli, cli.getTarjeta(), tag, importe);
		repoPagos.salvarPago(pago);
                }else {
                	evento.publicarPagoTarjetaNoRealizado(responseBody);
                }
                log.infof(BLUE + "Respuesta de la API medio pagos externos: " + responseBody);
            } else {
            	evento.publicarPagoTarjetaNoRealizado("La solicitud al SERVICIO Mock API falló, error: " + response.getStatus());
                log.infof(ORANGE +"La solicitud de la API medio pagos externos falló con código de respuesta: " + response.getStatus());
            }
        } finally {
            // Cerrar el cliente
            client.close();
        }
    
	return responseBody;

	}

	@Override
	public List<DTPagos> consultaDePagos(LocalDate fechaInicial, LocalDate fechaFinal) {
		log.infof(VIOLET +"*** Inicio consultaDePagos por fechas");
		List<DTPagos> pagosDia = new ArrayList<>();	
		long dias = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
		
		double importe = 0;
		LocalDateTime fechaIni = fechaInicial.atTime(0,0);
		
		for (int i=0; i<=dias; i++) {			
			importe = repoPagos.traerImportesPorDia(fechaIni.plusDays(i));
			log.infof(BLUE+  "Medios Pago - Consulta por fechas DIA: "+ fechaInicial.plusDays(i) + " importe "+importe+"\n");
		
			DTPagos dtPago = new DTPagos("Dia", fechaIni.plusDays(i), importe);
			pagosDia.add(dtPago);
		}
		
		return pagosDia;
	}

	@Override
	public List<DTPagos> consultaDePagos(int idCliente) {
		log.infof(VIOLET +"*** Inicio consultaDePagos por idCliente: " + idCliente);
		List<DTPagos> pagosCli = new ArrayList<>();	
		
		List<Pago> pagos = repoPagos.traerPagosCliente(idCliente);
		
		for (Pago p : pagos) {
			log.infof(BLUE+  "Medios Pago - Consulta por Cliente al tag: " + p.getTag()+" fecha: "+ p.getFecha() + " importe: "+ p.getImporte()+"\n");
			DTPagos dtPago = new DTPagos("Cliente", p.getFecha() , p.getImporte(), idCliente);
			pagosCli.add(dtPago);
		}
		return pagosCli;
	}

	@Override
	public List<DTPagos> consultaDePagos(int idCliente, int tag) {
		log.infof(VIOLET +"*** Inicio consultaDePagos por idCliente: " + idCliente + " y tag: "+tag);
		List<DTPagos> pagosCliTag = new ArrayList<>();	
		
		List<Pago> pagos = repoPagos.traerPagosClienteYTag(idCliente, tag);
		
		for (Pago p : pagos) {
			log.infof(BLUE+  "Medios Pago - Consulta por Cliente y TAG: "+ p.getFecha() + " importe: "+ p.getImporte()+"\n");
			DTPagos dtPago = new DTPagos("Vehiculo", p.getFecha() , p.getImporte(), idCliente, tag);
			pagosCliTag.add(dtPago);
		}
		return pagosCliTag;
	}

}
