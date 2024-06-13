package org.tallerjava.moduloMediosDePago.dominio.repo;

import org.tallerjava.moduloMediosDePago.dominio.*;

public interface PagosRepositorio {

	public void salvarCliente(Cliente cli);
	public void salvarVehiculo(Vehiculo v);

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
