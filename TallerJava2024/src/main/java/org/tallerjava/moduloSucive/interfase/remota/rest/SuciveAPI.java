package org.tallerjava.moduloSucive.interfase.remota.rest;

import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloSucive.aplicacion.ServicioSucive;
import org.tallerjava.moduloSucive.interfase.remota.rest.SuciveAPI;
import org.tallerjava.moduloSucive.interfase.remota.rest.dto.DTFechas;
import org.tallerjava.moduloSucive.interfase.remota.rest.dto.DTPagos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@ApplicationScoped
@Path("/moduloSucive")

public class SuciveAPI {

	private static final Logger log = Logger.getLogger(SuciveAPI.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	
	@Inject
	private ServicioSucive servicioSucive;
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloSucive/consultarDePagos -H "Content-Type: application/json" -d '{"fechaInicial":"2024-06-19","fechaFinal":"2024-06-22"}'    
	@GET
	@Path("/consultarDePagos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DTPagos>consultaDePago(DTFechas dtFecha){
		log.infof(VIOLET+"####  Sucive - consultarDePagos por fechas  ####");
		return servicioSucive.consultaDePagos(dtFecha.getFechaInicial(), dtFecha.getFechaFinal());
	}
	
	// curl -X GET -v http://localhost:8080/TallerJava2024/api/moduloSucive/consultarDePagosMatricula -H "Content-Type: application/json" -d '{"matricula": "BEC1234"}'   
	
	@GET
	@Path("/consultarDePagosMatricula")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DTPagos>consultaDePago(DTPagos dtPagos){

			log.infof(VIOLET+"####  Sucive - consultarDePagos por matricula ####");
			return servicioSucive.consultaDePagos(dtPagos.getMatricula());
		
	
	}
}	
