package org.tallerjava.moduloSucive.interfase.api.local.impl;

import java.time.Instant;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicioMockSuciveImpl implements ServicioMockSucive {
	
	private static final Logger log = Logger.getLogger(ServicioMockSuciveImpl.class);

	public static final String GREEN = "\u001B[32m";

	 public static final String ORANGE = "\u001B[38;5;208m";
	
	private static final String[] MATRICULAS_ERROR = { "XXX9999", "YYYY9999", "ZZZ9999" };
	
	public int realizarPagoSucive(String matricula, double importe) {

		if (!isMatriculaError(matricula)) {
			int idPago = Long.valueOf(Instant.now().toEpochMilli()).intValue() /1000;
			log.info(GREEN +"OK: Pago Sucive con id: " + idPago);
			return idPago;
		}else {
			log.info(ORANGE +"ERROR: No se pudo realizar Pago Sucive matricula: " + matricula);
			return 0;
		}
	
	}
	
	private boolean isMatriculaError(String matricula) {
		for (String matri : MATRICULAS_ERROR) {
			if (matri.equals(matricula)) {
				return true;
			}
		}
		return false;
	}

}
