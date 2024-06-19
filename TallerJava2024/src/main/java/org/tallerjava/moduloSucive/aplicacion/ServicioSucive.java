package org.tallerjava.moduloSucive.aplicacion;

import java.time.LocalDateTime;
import java.util.List;

import org.tallerjava.moduloSucive.dominio.Pago;

public interface ServicioSucive {

	public boolean notificarPago(String matricula, double importe);
	
	public List<Pago> consultaDePagos(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
	
	public List<Pago> consultaDePagos(String matricula);
	
}
