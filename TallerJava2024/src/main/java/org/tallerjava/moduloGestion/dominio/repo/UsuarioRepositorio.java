package org.tallerjava.moduloGestion.dominio.repo;

import org.tallerjava.moduloGestion.dominio.*;

import java.util.List;

public interface UsuarioRepositorio {
    /**
     * Devuelve el usuario asignado al vehiculo
     * @param tag
     * @return
     */
    public Usuario findByTag(int tag);
    
    public Vehiculo findByTagVehiculo(int tag);
    
    public Usuario findUsuarioCliTelepeaje(int idCliente);

	List<Cuenta> findCuentasByTag(int tag);
	
	public ClienteTelepeaje findCliTelepeaje(int idCliente);
	
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr);

	public List<Vinculo> findVinculosByUser(Usuario usr);
	
	public long salvarVehiculo(Vehiculo vehiculo);
	public long salvarTarjetaPostPaga(PostPaga postPaga, Tarjeta tarjeta);
	public int salvarPasadaPeaje(PasadaPeaje pasada);
	
	public void actualizarUsuario(Usuario usr);
	public void actualizarCliTelepeaje(ClienteTelepeaje clienteTelepeaje);
	public void actualizarCuentaPrepaga(PrePaga ctaPrepaga);

	public List<Vehiculo> traerVehiculosUsr(Usuario id);
	
	public Usuario findUsuario(int id);

	public void actualizarVehiculo(Vehiculo v);

	public List<PasadaPeaje> traerPasadasVehiculo(Vehiculo v);

	
	
}
