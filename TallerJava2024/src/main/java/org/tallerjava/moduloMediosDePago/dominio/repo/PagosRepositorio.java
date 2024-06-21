package org.tallerjava.moduloMediosDePago.dominio.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.tallerjava.moduloMediosDePago.dominio.*;


public interface PagosRepositorio {

	public void salvarCliente(Cliente cli);
	public void salvarVehiculo(Vehiculo v);
	public void salvarPago(Pago pago);
	
	public double traerImportesPorDia(LocalDateTime fecha);
	public List<Pago> traerPagosCliente(int idCliente);
	public List<Pago> traerPagosClienteYTag(int idCliente, int tag);
	
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
