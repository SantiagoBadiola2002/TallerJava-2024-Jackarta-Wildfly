package org.tallerjava.moduloPeaje.interfase.remota.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTImporte;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTVehiculo;

@ApplicationScoped
@Path("/moduloPeaje")
public class PeajeAPI {

	private static final Logger log = Logger.getLogger(PeajeAPI.class);
	public static final String BLUE = "\u001B[34m";
	public static final String GREEN = "\u001B[32m";
	 public static final String RED = "\u001B[31m";
	 public static final String ORANGE = "\u001B[38;5;208m";
	 public static final String VIOLET = "\u001B[35m";
	

	@Inject
	private ServicioPeaje servicioPeaje;

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloPeaje/estaHabilitado -H "Content-Type: application/json" -d '{"tag":555, "matricula":"BEC1234", "nacionalidad": 0}'
	@POST
	@Path("/estaHabilitado")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean estaHabilitado(DTVehiculo dtVehiculo) {
		System.out.println(BLUE + "estaHabilitado");
		log.infof(BLUE + "######### estaHabilitado: Tag: %s %s" + dtVehiculo.getTag() + ", matricula: "
				+ dtVehiculo.getMatricula() + " #########");
		return servicioPeaje.estaHabilitado(dtVehiculo.getTag(), dtVehiculo.getMatricula());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloPeaje/actualizarTarifaComun -H "Content-Type: application/json" -d '{"importe":"456.78"}'
	@POST
	@Path("/actualizarTarifaComun")
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarTarifaComun(DTImporte dtImporte) {
		log.infof("######### actualizarTarifaComun: importe: " + dtImporte.getImporte() + " #########");
		servicioPeaje.actualizarTarifaComun(dtImporte.getImporte());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloPeaje/actualizarTarifaPreferencial -H "Content-Type: application/json" -d '{"importe":"456.78"}'
	@POST
	@Path("/actualizarTarifaPreferencial")
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarTarifaPreferencial(DTImporte dtImporte) {
		log.infof("######### actualizarTarifaPreferencial: importe: " + dtImporte.getImporte() + " #########");
		servicioPeaje.actualizarTarifaPreferencial(dtImporte.getImporte());
	}

}
