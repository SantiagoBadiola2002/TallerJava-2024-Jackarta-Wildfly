package org.tallerjava.moduloGestion.infraestructura.persistencia;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.dominio.repo.UsuarioRepositorio;
import org.tallerjava.moduloGestion.interfase.remota.rest.ClienteAPI;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class UsuarioRepositorioImpl implements UsuarioRepositorio {
	
	@PersistenceContext
    private EntityManager em;
	
	private static final Logger log = Logger.getLogger(ClienteAPI.class);
	
	
	public void inicializar() {
		
	}
	
	//ESTO NO FUNCA
	public Vehiculo findVehiculoByTag(int tag) {
        TypedQuery<Vehiculo> query = em.createQuery(
                "SELECT v FROM Vehiculo_Gestion v WHERE v.identificador.tag = :tag", Vehiculo.class);
        query.setParameter("tag", tag);
        return query.getSingleResult();
    }
	
	public int findIdClienteByTag(int tag) {
		 try {	        
		        int idCli = (int) em.createNativeQuery(
		            "SELECT cliente_idClienteTelepeaje FROM gestion_vehiculo WHERE tag = :tag")
		            .setParameter("tag", tag)
		            .getSingleResult();
		        return idCli;
		 }  catch (NoResultException e) {
		        return 0;
	    }
	}
	
	
	public Usuario findUsuarioByTag(int tag) {
	    try {
	        int idCli = findIdClienteByTag(tag);
	        log.info("###repoImpl### idCli: " + idCli);
	        
	        Usuario usu = (Usuario) em.createNativeQuery(
	                "SELECT id, nombre, email, clienteTelepeaje_idClienteTelepeaje FROM gestion_usuario WHERE id = :id")
	                .setParameter("id", idCli)
	                .getSingleResult();
	        
	        return usu;
	    } catch (NoResultException e) {
	        return null;
	    }
	}

	@Override
	public Usuario findByTag(int tag) {
		log.info("### findByTag###");
		//int CI = findIdClienteByTag(tag);
		Usuario usu = findUsuarioByTag(tag);
		//Vehiculo v = findVehiculoByTag(tag);
		//log.info("### despues de findVeh###" + v.getId());
		
		
	return null;
}

    @Override
    public List<Cuenta> findCuentasByTag(int tag) {
    	Usuario usu = findByTag(tag);
    	List<Cuenta> cuentas = new ArrayList<>();
    	Cuenta cuenta = usu.getClienteTelepeaje().getCtaPostpaga();
    	Cuenta cuenta2 = usu.getClienteTelepeaje().getCtaPrepaga();
    	if(cuenta != null) {
    		cuentas.add(cuenta);
    	}
    	if(cuenta2 != null) {
    		cuentas.add(cuenta2);
    	}
    	return cuentas;
    }
    
    
    @Override
    public ClienteTelepeaje crearClienteTelepeaje(Usuario usr) {
//    	//incio saldo prePaga en 0 sin tarjeta
//    	LocalDateTime ahora = LocalDateTime.now();
//
//    	if (usr.soyNacional()) {
////    		PrePaga prePaga = new PrePaga(1, 0, ahora, 0);
////    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(null, prePaga, null);
////    		crearClienteSucive((Nacional) usr);
////    		//ACTUALIZAE USR EN BD
////    		usr.setClienteTelepeaje(cliTelepeaje);
//    		return cliTelepeaje; 
//        
//    	}else {
//    		//se actualiza al asosiar(cli, tarjeta)
//    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(usr, null, null);
//    		//ACTUALIZAE USR EN BD
//    		usr.setClienteTelepeaje(cliTelepeaje);
//    		return cliTelepeaje; 
//    	}
    	return null;
    }
    
    @Override
    public void crearClienteSucive(Nacional usr) {
    	ClienteSucive cliSucive = new ClienteSucive(usr);
    	//ACTUALIZAE USR EN BD
    	usr.setClienteSucive(cliSucive);
    }

    public List<Vehiculo> findVehiculoByUser(Usuario usr) {
//    	List<Vehiculo> vehiculos = new ArrayList<>();
//    	for(Vinculo vinculos: usr.getVehiculosVinculados()) {
//    		if(vinculos.isActivo() == true) {
//    			vehiculos.add(vinculos.getVehiculo());
//    		}
//    	}
    	
    	return null;
    }

	@Override
	public void agregarTarjetaPostPaga(ClienteTelepeaje clienteTelepeaje, PostPaga postPaga) {
		clienteTelepeaje.setCtaPostPaga(postPaga);
	}

	@Override
	public ClienteTelepeaje findClienteTelepeajeByCi(long ci) {
		
		return null;
	}

	@Override
	public Usuario findUsuarioByCi(long ci) {
		return null;
	}

	@Override
	public List<Vinculo> findVinculosByUser(Usuario usr) {
		
		return null;
	}
}
