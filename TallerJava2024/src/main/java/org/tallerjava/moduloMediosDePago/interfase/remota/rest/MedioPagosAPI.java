package org.tallerjava.moduloMediosDePago.interfase.remota.rest;

import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloMediosDePago.aplicacion.ServicioMediosDePago;
import org.tallerjava.moduloMediosDePago.interfase.remota.rest.dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/moduloMediosDePago")

public class MedioPagosAPI {

	private static final Logger log = Logger.getLogger(MedioPagosAPI.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	
	@Inject
	private ServicioMediosDePago servicioMedioPago;
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloMediosDePago/consultarDePagos -H "Content-Type: application/json" -d '{"fechaInicial":"2024-06-12","fechaFinal":"2024-07-01"}'    
	@GET
	@Path("/consultarDePagos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DTPagos>consultaDePago(DTFechas dtFecha){
		log.infof(VIOLET+"####  Medios Pago - consultarDePagos por fechas  ####");
		return servicioMedioPago.consultaDePagos(dtFecha.getFechaInicial(), dtFecha.getFechaFinal());
	}
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloMediosDePago/consultarDePagosCliente -H "Content-Type: application/json" -d '{"idCliente":1,"tag":555000}'   
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloMediosDePago/consultarDePagosCliente -H "Content-Type: application/json" -d '{"idCliente":1}'    
	@GET
	@Path("/consultarDePagosCliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DTPagos>consultaDePago(DTPagos dtPagos){
		if (dtPagos.getTag()!= 0) {
			log.infof(VIOLET+"####  Medios Pago - consultarDePagos por Cliente y Tag  ####");
			return servicioMedioPago.consultaDePagos(dtPagos.getIdCliente(), dtPagos.getTag());
		}else {
			log.infof(VIOLET+"####  Medios Pago - consultarDePagos por Cliente  ####");
			return servicioMedioPago.consultaDePagos(dtPagos.getIdCliente());
		}
	}
	
}
