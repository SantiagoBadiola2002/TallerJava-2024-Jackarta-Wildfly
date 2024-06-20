package org.tallerjava.moduloMediosDePago.dominio.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.tallerjava.moduloMediosDePago.dominio.*;


public interface PagosRepositorio {

	public void salvarCliente(Cliente cli);
	public void salvarVehiculo(Vehiculo v);
	public void salvarPago(Pago pago);
	
	public double traerImportesPorDia(LocalDateTime fecha);
	
	public Cliente findByIdCliente(int idCliente);

	
	/*
	 *  public Usuario findByTag(int tag);

	List<Cuenta> findCuentasByTag(int tag);
	
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr);

	public void crearClienteSucive(Nacional usr);

	public void vicularUsuarioVehiculo(Usuario usr, Vehiculo vehiculo);

	public List<Vehiculo> findVehiculoByUser(Usuario usr);
	 */

}
