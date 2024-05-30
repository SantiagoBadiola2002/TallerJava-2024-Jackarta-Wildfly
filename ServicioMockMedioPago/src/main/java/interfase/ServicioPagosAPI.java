package interfase;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import ejemplo00.aplicacion.impl.ServicioMedioDePagoImpl;
import interfase.dto.DTNroTarjeta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
//import org.jboss.logging.Logger;

/**
 * Ejemplo de API RESTful
 * 
 * http://<hostname>:<port>/<context-root>/<REST-config>/<resource-config>
 * 
 * context-root --> (nombre del war por defecto, se puede cambiar agregando
 * archivo conf. ) REST-config --> se establece en web.xml (tambien existe una
 * anotación para poder establecerlo) resource-config --> se establece con la
 * anotación @Path
 */

@SuppressWarnings("unused")
@ApplicationScoped
@Path("/servicioPagosMock")
public class ServicioPagosAPI {

	@Inject
	private ServicioMedioDePagoImpl servicioPago;

	// private static final Logger log = Logger.getLogger(ServicioPagosAPI.class);

	private static final String[] TARJETAS_OK = { "111", "222", "333" };
	private static final String[] TARJETAS_ERROR = { "444", "555", "666" };
	private static final Random RANDOM = new Random();

	private boolean isTarjetaOK(String numeroTarjeta) {
		for (String tarjeta : TARJETAS_OK) {
			if (tarjeta.equals(numeroTarjeta)) {
				return true;
			}
		}
		return false;
	}

	private boolean isTarjetaError(String numeroTarjeta) {
		for (String tarjeta : TARJETAS_ERROR) {
			if (tarjeta.equals(numeroTarjeta)) {
				return true;
			}
		}
		return false;
	}

	private boolean esPagoAceptado() {
		// Generar un número entre 0 y 5. Si es diferente de 0, el pago es aceptado.
		return RANDOM.nextInt(6) != 0;
	}
	
	// curl -X POST -v
		// http://localhost:8080/ServicioMockMedioPago/api/servicioPagosMock/autorizarPago -H
		// "Content-Type: application/json" -d '{"nroTarjeta":111}'
	@POST
    @Path("/autorizarPago")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String autorizarPago(DTNroTarjeta  nroTarjeta) {
		String numeroTarjeta = nroTarjeta.getNroTarjeta();

		if (isTarjetaOK(numeroTarjeta)) {
			return "PAGO APROBADO";
		} else if (isTarjetaError(numeroTarjeta)) {
			return "PAGO RECHAZADO";
		}

		// Decidir aleatoriamente si el pago es aceptado o rechazado, con una relación
		// 5:1
		if (esPagoAceptado()) {
			return "PAGO APROBADO";
		} else {
			return "PAGO RECHAZADO";
		}
	}

}
