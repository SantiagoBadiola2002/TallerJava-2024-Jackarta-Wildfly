package org.tallerjava.moduloMediosDePago.aplicacion.impl;

import java.time.LocalDateTime;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.api.local.ServicioPagoFacade;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloMediosDePago.dominio.*;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;
import org.tallerjava.moduloMediosDePago.interfase.eventos.out.PublicadorEvento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicioMediosDePagoImpl implements ServicioMediosDePago {

	private static final Logger log = Logger.getLogger(ServicioMediosDePagoImpl.class);

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
		log.infof("*** Agregando Ciente y Tarjeta: ", idCliente, nroTarjeta);

		Tarjeta tarjeta = new Tarjeta(nroTarjeta, fechaVtoTarjeta, nombreCompletoUsuario);
		Cliente cli = new Cliente(idCliente, tarjeta, null);

		repoPagos.salvarCliente(cli);
	}

	@Override
	@Transactional
	public void altaVehiculo(int idCliente, String matricula, int tag) {
		log.infof("*** Agregando Ciente y Vehiculo: ", idCliente, tag);
		Identificador idVehiculo = new Identificador(matricula, tag);
		Cliente cli = repoPagos.findByIdCliente(idCliente);

		Vehiculo v = new Vehiculo(idVehiculo, cli);

		repoPagos.salvarVehiculo(v);

	}

	@Override
	public String notificarPago(int idCliente, int tag, double importe, int nroTarjeta) {
		log.infof("*** Verificando Pago sistema Externo Ciente y Vehiculo: ", idCliente, tag);

		String responseBody = "PAGO RECHAZADO";
		evento.publicarPagoTarjetaNoRealizado(responseBody);
		
//		Client client = ClientBuilder.newClient();
//		String responseBody = "";
//		try {
//            // Realizar la solicitud GET al endpoint autorizarPago con el número de tarjeta en la URL
//            Response response = client.target("http://localhost:8080/ServicioMockMedioPago/api/servicioPagosMock")
//                    .path("/autorizarPago/" + nroTarjeta)
//                    .request(MediaType.TEXT_PLAIN)
//                    .get();
//
//            // Verificar la respuesta
//            if (response.getStatus() == 200) {
//                responseBody = response.readEntity(String.class);
//                if (responseBody.equals("PAGO APROBADO")) {
//                	evento.publicarPagoTarjetaRealizado(responseBody);
//                }else {
//                	evento.publicarPagoTarjetaNoRealizado(responseBody);
//                }
//                System.out.println("Respuesta de la API: " + responseBody);
//            } else {
//            	evento.publicarAlProcesarPago("La solicitud al SERVICIO Mock API falló, error: " + response.getStatus())
//                System.out.println("La solicitud falló con código de respuesta: " + response.getStatus());
//            }
//        } finally {
//            // Cerrar el cliente
//            client.close();
//        }
    
	return responseBody;

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
