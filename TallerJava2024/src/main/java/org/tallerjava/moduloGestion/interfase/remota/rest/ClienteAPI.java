package org.tallerjava.moduloGestion.interfase.remota.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.PasadaPeaje;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.*;
@ApplicationScoped
@Path("/moduloGestion")
public class ClienteAPI {

	private static final Logger log = Logger.getLogger(ClienteAPI.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	 
	@Inject
	private ServicioPago servicioPago;

	// curl -X POST -v  http://localhost:8080/TallerJava2024/api/moduloGestion/realizarPrePago -H "Content-Type: application/json" -d '{"tag":555, "importe": 10.78}'
	@POST
	@Path("/realizarPrePago")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean realizarPrePago(DTPago dtPago) {
		log.infof("######### realizarPrePago: Tag: " + dtPago.getTag() + ", importe: " + dtPago.getImporte()
				+ " #########");
		return servicioPago.realizarPrePago(dtPago.getTag(), dtPago.getImporte());
		// return false;
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/realizarPostPago -H "Content-Type: application/json" -d '{"tag":555, "importe": 100.78}'
	@POST
	@Path("/realizarPostPago")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean realizarPostPago(DTPago dtPago) {
		log.infof(VIOLET+"#### realizarPostPago - Tag: " + dtPago.getTag() + ", importe: " + dtPago.getImporte()
				+ " #########");
		return servicioPago.realizarPostPago(dtPago.getTag(), dtPago.getImporte());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/esClienteTelepeaje -H "Content-Type: application/json" -d '{"tag":111}'
	@POST
	@Path("/esClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean esClienteTelepeaje(DTTag tag) {
		log.infof(VIOLET+"#### esClienteTelepeaje - Tag: " + tag.getTag() + " ###");
		return servicioPago.esClienteTelepeaje(tag.getTag());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/altaClienteTelepeaje -H "Content-Type: application/json" -d '{"nombre":"nomNacional","email":"nom1Nacional@gmail.com", "nacionalidad":0}'  
	@POST
	@Path("/altaClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean altaClienteTelepeaje(DTUsuario usr) {
		log.infof(VIOLET+"####  altaClienteTelepeaje ####");
		Usuario usu = usr.buildUsuario();
		return servicioPago.altaClienteTelepeaje(usu);
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/vincularVehiculo -H "Content-Type: application/json" -d '{"idCliente":1,"tag":555,"matricula":"BEC1234"}'
	@POST
	@Path("/vincularVehiculo")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean vincularVehiculo(DTVehiculo dtVehiculo) {
		log.infof(VIOLET+"####  vincularVehiculo - idCliente: " + dtVehiculo.getIdCliente() + " tag: " + dtVehiculo.getTag()
				+ "matricula: " + dtVehiculo.getMatricula() + " ###");
		return servicioPago.vincularVehiculo(dtVehiculo.getIdCliente(), dtVehiculo.getTag(), dtVehiculo.getMatricula());
	}

	// curl -X DELETE -v http://localhost:8080/TallerJava2024/api/moduloGestion/desvincularVehiculo -H "Content-Type: application/json" -d '{"idCliente":123,"tag":1,"matricula":1}'
	@DELETE
	@Path("/desvincularVehiculo")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean desvincularVehiculo(DTVehiculo dtVehiculo) {
		log.infof(VIOLET+"#### desvincularVehiculo - idCliente: " + dtVehiculo.getIdCliente() + " tag: " + dtVehiculo.getTag()
				+ "matricula: " + dtVehiculo.getMatricula() + " ###");
		return servicioPago.desvincularVehiculo(dtVehiculo.getIdCliente(), dtVehiculo.getTag(), dtVehiculo.getMatricula());
	}
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloGestion/mostrarVehiculoVinculados/1
	@GET
	@Path("/mostrarVehiculoVinculados/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<DTVehiculo> mostrarVehiculoVinculados(@PathParam("id") int id) {
		log.infof(VIOLET+"####  mostrarVehiculoVinculados: idCliente: " + id+ " ###");
		return servicioPago.mostrarVehiculoVinculados(id);
	}

	// curl -X GET -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/obtenerCuentasPorTag
	// -H
	// "Content-Type: application/json" -d '{"tag":123}'
	@GET
	@Path("/obtenerCuentasPorTag")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> obtenerCuentasPorTag(DTTag tag) {
		log.infof(VIOLET+"####  obtenerCuentasPorTag: Tag: " + tag.getTag() + " ###");
		return servicioPago.obtenerCuentasPorTag(tag.getTag());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/agregarTarjeta -H "Content-Type: application/json" -d '{"idCliente":1,"nroTarjeta":3333,"fechaVtoTarjeta":"2024-06-12T14:30:00","nombreCompletoUsuario":"Juan Lopez"}'    
	@POST
	@Path("/agregarTarjeta")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean agregarTarjeta(DTTarjeta dtTarjeta) {
		log.infof("fecha ANTES del parse: " + dtTarjeta.getFechaVtoTarjeta() + "\n");
		LocalDateTime date = LocalDateTime.parse(dtTarjeta.getFechaVtoTarjeta());
		
		log.infof("fecha DESPUES del parse: " + date + "\n");
		log.infof("fecha HOY: " + LocalDateTime.now()+ "\n");
		
		log.infof("######### agregarTarjeta: idClinte: " + dtTarjeta.getIdCliente() + " nroTarjeta: " + dtTarjeta.getNroTarjeta()
				+ "nombreUsuario: " + dtTarjeta.getNombreCompletoUsuario() + "#########");
		return servicioPago.agregarTarjeta(dtTarjeta.getIdCliente(), dtTarjeta.getNroTarjeta(), date,
				dtTarjeta.getNombreCompletoUsuario());
	}

	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloGestion/consultarPasadas -H "Content-Type: application/json" -d '{"idCliente":1,"tag":666,"matricula":"abc","fechaInicial":"2024-06-12T14:30:00","fechaFinal":"2024-07-12T14:30:00"}'
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloGestion/consultarPasadas -H "Content-Type: application/json" -d '{"idCliente":1,"fechaInicial":"2024-06-12T14:30:00","fechaFinal":"2024-07-12T14:30:00"}'
	@GET
	@Path("/consultarPasadas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DTPasadaPeaje> consultarPasadas(DTPasada dtPasadas) {
		LocalDateTime dateIni = LocalDateTime.parse(dtPasadas.getFechaInicial());
		LocalDateTime dateFin = LocalDateTime.parse(dtPasadas.getFechaFinal());

		if ((dtPasadas.getIdCliente() != 0) && (dtPasadas.getTag() ==0)) {
			log.infof(VIOLET+"#### consultarPasadas - idCliente: " + dtPasadas.getIdCliente() + "desde: " + dateIni
					+ " hasta: " + dateFin + " ###");
			
			return servicioPago.consultarPasadas(dtPasadas.getIdCliente(), dateIni, dateFin);
		} else {
			log.infof(VIOLET+"#### consultarPasadas con tag o matricula - ci: " + dtPasadas.getIdCliente() + "desde: " + dateIni
					+ " hasta: " + dateFin + ", TAG: " + dtPasadas.getTag() + " ###");
			return servicioPago.consultarPasadas(dtPasadas.getIdCliente(), dtPasadas.getTag(), dtPasadas.getMatricula(),
					dateIni, dateFin);
		}

	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/cargarSaldo -H "Content-Type: application/json" -d '{"idCliente":1,"importe":1000}'
	@POST
	@Path("/cargarSaldo")
	@Consumes(MediaType.APPLICATION_JSON)
	public double cargarSaldo(DTSaldo saldoDTO) {
		log.infof(VIOLET+"#### Carga saldo cliente - idCliente: ", saldoDTO.getIdCliente() + " $" + saldoDTO.getImporte() + " ###");
		double nuevoSaldo = servicioPago.cargarSaldo(saldoDTO.getIdCliente(), saldoDTO.getImporte());
		return nuevoSaldo;
	}

	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloGestion/consultarSaldo/1
	@GET
	@Path("/consultarSaldo/{idCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public double consultarSaldo(@PathParam("idCliente") int idCliente) {
		log.infof(VIOLET+"#### Consulta saldo - idCliente " + idCliente + " ###");

		
		return servicioPago.consultarSaldo(idCliente);
	}

}
