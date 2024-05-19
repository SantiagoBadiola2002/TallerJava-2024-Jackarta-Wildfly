package org.tallerjava.moduloGestion.dominio.repo;

import org.tallerjava.moduloGestion.dominio.ClienteTelepeaje;
import org.tallerjava.moduloGestion.dominio.Cuenta;
import org.tallerjava.moduloGestion.dominio.Nacional;
import org.tallerjava.moduloGestion.dominio.PostPaga;
import org.tallerjava.moduloGestion.dominio.Usuario;
import org.tallerjava.moduloGestion.dominio.Vehiculo;

import java.util.List;

public interface UsuarioRepositorio {
    /**
     * Devuelve el usuario asignado al vehiculo
     * @param tag
     * @return
     */
    public Usuario findByTag(int tag);

	List<Cuenta> findCuentasByTag(int tag);
	
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr);

	public void crearClienteSucive(Nacional usr);

	public void vicularUsuarioVehiculo(Usuario usr, Vehiculo vehiculo);

	public List<Vehiculo> findVehiculoByUser(Usuario usr);
	
	public void agregarTarjetaPostPaga(ClienteTelepeaje clienteTelepeaje, PostPaga postPaga);

	public ClienteTelepeaje findClienteTelepeajeByCi(long ci);
	
	public Usuario findUsuarioByCi(long ci);
}
