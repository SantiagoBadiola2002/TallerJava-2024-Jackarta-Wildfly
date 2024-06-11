package org.tallerjava.moduloGestion.interfase.remota.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.PasadasPorPeaje;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.*;
@ApplicationScoped
@Path("/moduloGestion")
public class ClienteAPI {

	private static final Logger log = Logger.getLogger(ClienteAPI.class);

	@Inject
	private ServicioPago servicioPago;

	// curl -X POST -v  http://localhost:8080/TallerJava2024/api/moduloGestion/realizarPrePago -H "Content-Type: application/json" -d '{"tag":123, "importe": 456.78}'
	@POST
	@Path("/realizarPrePago")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean realizarPrePago(DTPago dtPago) {
		log.infof("######### realizarPrePago: Tag: " + dtPago.getTag() + ", importe: " + dtPago.getImporte()
				+ " #########");
		return servicioPago.realizarPrePago(dtPago.getTag(), dtPago.getImporte());
		// return false;
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/realizarPostPago -H
	// "Content-Type: application/json" -d '{"tag":123, "importe": 456.78}'
	@POST
	@Path("/realizarPostPago")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean realizarPostPago(DTPago dtPago) {
		log.infof("######### realizarPostPago: Tag: " + dtPago.getTag() + ", importe: " + dtPago.getImporte()
				+ " #########");
		return servicioPago.realizarPostPago(dtPago.getTag(), dtPago.getImporte());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/esClienteTelepeaje -H "Content-Type: application/json" -d '{"tag":111}'
	@POST
	@Path("/esClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean esClienteTelepeaje(DTTag tag) {
		log.infof("######### esClienteTelepeaje: Tag: " + tag.getTag() + " #########");
		return servicioPago.esClienteTelepeaje(tag.getTag());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/altaClienteTelepeaje -H "Content-Type: application/json" -d '{"nombre":"nomNacional","email":"nom1Nacional@gmail.com", "nacionalidad":0}'
	@POST
	@Path("/altaClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean altaClienteTelepeaje(DTUsuario usr) {
		log.infof("######### altaClienteTelepeaje #########");
		Usuario usu = usr.buildUsuario();
		return servicioPago.altaClienteTelepeaje(usu);
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/vincularVehiculo -H "Content-Type: application/json" -d '{"idCliente":1,"tag":555,"matricula":"BEC1234"}'
	@POST
	@Path("/vincularVehiculo")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean vincularVehiculo(DTVehiculo dtVehiculo) {
		log.infof("######### vincularVehiculo: idCliente: " + dtVehiculo.getIdCliente() + " tag: " + dtVehiculo.getTag()
				+ "matricula: " + dtVehiculo.getMatricula() + "#########");
		return servicioPago.vincularVehiculo(dtVehiculo.getIdCliente(), dtVehiculo.getTag(), dtVehiculo.getMatricula());
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/desvincularVehiculo -H
	// "Content-Type: application/json" -d '{"idCliente":123,"tag":1,"matricula":1}'
	@POST
	@Path("/desvincularVehiculo")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean desvincularVehiculo(DTVehiculo dtVehiculo) {
		log.infof("######### desvincularVehiculo: idCliente: " + dtVehiculo.getIdCliente() + " tag: " + dtVehiculo.getTag()
				+ "matricula: " + dtVehiculo.getMatricula() + "#########");
		return servicioPago.desvincularVehiculo(dtVehiculo.getIdCliente(), dtVehiculo.getTag(), dtVehiculo.getMatricula());
	}
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloGestion/mostrarVehiculoVinculados/10000
	@GET
	@Path("/mostrarVehiculoVinculados/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<DTVehiculo> mostrarVehiculoVinculados(@PathParam("id") int id) {
		log.infof("######### mostrarVehiculoVinculados: idCliente: " + id+ "#########");
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
		log.infof("######### obtenerCuentasPorTag: Tag: " + tag.getTag() + " #########");
		return servicioPago.obtenerCuentasPorTag(tag.getTag());
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/agregarTarjeta -H
	// "Content-Type: application/json" -d
	// '{"ci":123,"nroTarjeta":1,"fechaVtoTarjeta":"23/05/2024","nombreCompletoUsuario":"Juan
	// Lopez"}'
	@POST
	@Path("/agregarTarjeta")
	@Consumes(MediaType.APPLICATION_JSON)
	public void agregarTarjeta(DTTarjeta dtTarjeta) {
		LocalDateTime date = LocalDateTime.parse(dtTarjeta.getFechaVtoTarjeta());
		log.infof("######### agregarTarjeta: ci: " + dtTarjeta.getCi() + " nroTarjeta: " + dtTarjeta.getNroTarjeta()
				+ "nombreUsuario: " + dtTarjeta.getNombreCompletoUsuario() + "#########");
		servicioPago.agregarTarjeta(dtTarjeta.getCi(), dtTarjeta.getNroTarjeta(), date,
				dtTarjeta.getNombreCompletoUsuario());
	}

	// curl -X GET -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/consultarPasadas
	// -H
	// "Content-Type: application/json" -d
	// '{"idCliente":123,"tag":1,"matricula":"abc","fechaInicial":"23/05/2024","fechaFinal":"23/06/2024"}'
	@GET
	@Path("/consultarPasadas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PasadasPorPeaje> consultarPasadas(DTPasadas dtPasadas) {
		LocalDateTime dateIni = LocalDateTime.parse(dtPasadas.getFechaInicial());
		LocalDateTime dateFin = LocalDateTime.parse(dtPasadas.getFechaFinal());

		if ((dtPasadas.getTag() == 0) && (dtPasadas.getMatricula() == null)) {
			log.infof("######### consultarPasadas: idCliente: " + dtPasadas.getIdCliente() + "desde: " + dtPasadas.getFechaInicial()
					+ " hasta: " + dtPasadas.getFechaFinal() + " #########");
			
			return servicioPago.consultarPasadas(dtPasadas.getIdCliente(), dateIni, dateFin);
		} else {
			log.infof("######### consultarPasadas con tag o matricula: ci: " + dtPasadas.getIdCliente() + "desde: "
					+ dtPasadas.getFechaInicial() + " hasta: " + dtPasadas.getFechaFinal() + " #########");
			return servicioPago.consultarPasadas(dtPasadas.getIdCliente(), dtPasadas.getTag(), dtPasadas.getMatricula(),
					dateIni, dateFin);
		}

	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloGestion/cargarSaldo -H "Content-Type: application/json" -d '{"idCliente":10000,"importe":1000}'
	@POST
	@Path("/cargarSaldo")
	@Consumes(MediaType.APPLICATION_JSON)
	public double cargarSaldo(DTSaldo saldoDTO) {
		log.infof("Carga saldo cliente %s : ", saldoDTO.getIdCliente() + " $" + saldoDTO.getImporte());
		double nuevoSaldo = servicioPago.cargarSaldo(saldoDTO.getIdCliente(), saldoDTO.getImporte());
		return nuevoSaldo;
	}

	// curl -X GET -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/consultarSaldo
	// -H "Content-Type: application/json" -d
	// '{"ci":123}'
	@GET
	@Path("/consultarSaldo")
	@Produces(MediaType.APPLICATION_JSON)
	public double consultarSaldo(DTIdCliente dtIdCliente) {
		log.infof("Consulta saldo: " + dtIdCliente.getIdCliente());
		return servicioPago.consultarSaldo(dtIdCliente.getIdCliente());
	}

}
