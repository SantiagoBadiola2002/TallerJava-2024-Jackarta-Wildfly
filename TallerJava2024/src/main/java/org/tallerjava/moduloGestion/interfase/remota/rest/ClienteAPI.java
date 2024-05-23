package org.tallerjava.moduloGestion.interfase.remota.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTPago;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTTag;

@ApplicationScoped
@Path("/moduloGestion")
public class ClienteAPI {

	private static final Logger log = Logger.getLogger(ClienteAPI.class);

	@Inject
	private ServicioPago servicioPago;

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/realizarPrePago -H
	// "Content-Type: application/json" -d '{"tag": 123, "importe": 456.78}'
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
	// "Content-Type: application/json" -d '{"tag": 123, "importe": 456.78}'
	@POST
	@Path("/realizarPostPago")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean realizarPostPago(DTPago dtPago) {
		log.infof("######### realizarPostPago: Tag: " + dtPago.getTag() + ", importe: " + dtPago.getImporte()
				+ " #########");
		return servicioPago.realizarPostPago(dtPago.getTag(), dtPago.getImporte());
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/esClienteTelepeaje -H
	// "Content-Type: application/json" -d '{"tag": 123}'
	@POST
	@Path("/esClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean esClienteTelepeaje(DTTag tag) {
		log.infof("######### esClienteTelepeaje: Tag: " + tag + " #########");
		return servicioPago.esClienteTelepeaje(tag.getTag());
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloGestion/altaClienteTelepeaje
	// -H "Content-Type: application/json" -d
	// '{"nombre":"nom1","email":"nom1@gmail.com", "nacionalidad":1}'
	@POST
	@Path("/altaClienteTelepeaje")
	@Consumes(MediaType.APPLICATION_JSON)
	public void altaClienteTelepeaje(Usuario usr) {
		log.infof("######### altaClienteTelepeaje #########");
		servicioPago.altaClienteTelepeaje(usr);
	}
	
	public boolean vincularVehiculo(long ci, int tag, String matricula) {
		
		return true;
	}
	
	

}
