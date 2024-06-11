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
    
    public Usuario findUsuarioCliTelepeaje(int idCliente);

	List<Cuenta> findCuentasByTag(int tag);
	
	public ClienteTelepeaje findCliTelepeaje(int idCliente);
	
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr);
	
	public void agregarTarjetaPostPaga(ClienteTelepeaje clienteTelepeaje, PostPaga postPaga);

	public List<Vinculo> findVinculosByUser(Usuario usr);
	
	public long salvarVehiculo(Vehiculo vehiculo);
	
	public void actualizarUsuario(Usuario usr);
	public void actualizarCliTelepeaje(ClienteTelepeaje clienteTelepeaje);
	public void actualizarCuentaPrepaga(PrePaga ctaPrepaga);

	public List<Vehiculo> traerVehiculosUsr(Usuario id);
	
	public Usuario findUsuario(int id);
	
}
