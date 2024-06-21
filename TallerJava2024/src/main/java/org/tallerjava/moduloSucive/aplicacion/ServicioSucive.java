package org.tallerjava.moduloSucive.aplicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.tallerjava.moduloSucive.dominio.Pago;
import org.tallerjava.moduloSucive.interfase.remota.rest.dto.DTPagos;

public interface ServicioSucive {

	public boolean notificarPago(String matricula, double importe);
	
	public List<DTPagos> consultaDePagos(LocalDate fechaInicial, LocalDate fechaFinal);
	
	public List<DTPagos> consultaDePagos(String matricula);
	
}
