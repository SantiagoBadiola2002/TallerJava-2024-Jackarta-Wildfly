package org.tallerjava.moduloSucive.dominio.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.tallerjava.moduloSucive.dominio.Pago;

public interface SuciveRepositorio {

	public void salvar(Pago pago);

	public double traerImportesPorDia(LocalDateTime fecha);

	public List<Pago> traerPagosMatricula(String matricula);

}
