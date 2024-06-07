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
    
    public Usuario findUsuario(int idCliente);

	List<Cuenta> findCuentasByTag(int tag);
	
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr);

	public List<Vehiculo> findVehiculoByUser(Usuario usr);
	
	public void agregarTarjetaPostPaga(ClienteTelepeaje clienteTelepeaje, PostPaga postPaga);

	public ClienteTelepeaje findClienteTelepeajeByCi(long ci);

	public List<Vinculo> findVinculosByUser(Usuario usr);
	
	public long salvarVehiculo(Vehiculo vehiculo);
	
	public void actualizarUsuario(Usuario usr);
	
}
