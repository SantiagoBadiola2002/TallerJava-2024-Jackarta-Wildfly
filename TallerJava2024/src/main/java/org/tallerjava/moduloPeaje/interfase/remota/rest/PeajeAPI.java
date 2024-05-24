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
import org.tallerjava.moduloGestion.aplicacion.ServicioPago;
import org.tallerjava.moduloGestion.dominio.PasadasPorPeaje;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTCi;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTPago;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTPasadas;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTSaldo;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTTag;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTTarjeta;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTUsuario;
import org.tallerjava.moduloGestion.interfase.remota.rest.dto.DTVehiculo;
import org.tallerjava.moduloPeaje.aplicacion.ServicioPeaje;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTIdentificador;
import org.tallerjava.moduloPeaje.interfase.remota.rest.dto.DTImporte;

@ApplicationScoped
@Path("/moduloPeaje")
public class PeajeAPI {

	private static final Logger log = Logger.getLogger(PeajeAPI.class);

	@Inject
	private ServicioPeaje servicioPeaje;

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloPeaje/estaHabilitadoSincronico -H Content-Type: application/json" -d '{"tag":123, "matricula":"456.78"}'
	@POST
	@Path("/estaHabilitadoSincronico")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean estaHabilitadoSincronico(DTIdentificador dtIdentificador) {
		log.infof("######### estaHabilitadoSincronico: Tag: " + dtIdentificador.getTag() + ", matricula: "
				+ dtIdentificador.getMatricula() + " #########");
		return servicioPeaje.estaHabilitadoSincronico(dtIdentificador.getTag(), dtIdentificador.getMatricula());
	}

	// curl -X POST -v http://localhost:8080/TallerJava2024/api/moduloPeaje/actualizarTarifaComun -H "Content-Type: application/json" -d '{"importe":"456.78"}'
	@POST
	@Path("/actualizarTarifaComun")
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarTarifaComun(DTImporte dtImporte) {
		log.infof("######### actualizarTarifaComun: importe: " + dtImporte.getImporte() + " #########");
		servicioPeaje.actualizarTarifaComun(dtImporte.getImporte());
	}

	// curl -X POST -v
	// http://localhost:8080/TallerJava2024/api/moduloPeaje/actualizarTarifaPreferencial
	// -H
	// "Content-Type: application/json" -d '{"importe":"456.78"}'
	@POST
	@Path("/actualizarTarifaPreferencial")
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizarTarifaPreferencial(DTImporte dtImporte) {
		log.infof("######### actualizarTarifaPreferencial: importe: " + dtImporte.getImporte() + " #########");
		servicioPeaje.actualizarTarifaPreferencial(dtImporte.getImporte());
	}

}
