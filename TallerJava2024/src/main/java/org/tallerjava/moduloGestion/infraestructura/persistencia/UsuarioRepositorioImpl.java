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
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class UsuarioRepositorioImpl implements UsuarioRepositorio {

	@PersistenceContext
	private EntityManager em;

	private static final Logger log = Logger.getLogger(UsuarioRepositorioImpl.class);


	public int findIdClienteByTag(int tag) {
		try {
			int idCli = (int) em
					.createNativeQuery("SELECT cliente_idClienteTelepeaje FROM gestion_vehiculo v WHERE v.tag =:tag")
					.setParameter("tag", tag).getSingleResult();
			return idCli;
		} catch (NoResultException e) {
			return 0;
		}
	}
	
	
	
	@Override
	public Usuario findUsuarioCliTelepeaje(int idCliente) {
		try {

			Nacional nacional = (Nacional) em.createQuery("SELECT n FROM nacional n WHERE n.clienteTelepeaje.idClienteTelepeaje = :id")
					.setParameter("id", idCliente).getSingleResult();

			if (nacional != null) {
				return nacional;
			}
				
				
		} catch (NoResultException e) {
			try {
			Extranjero extranjero = (Extranjero) em.createQuery("SELECT n FROM extranjero n WHERE n.clienteTelepeaje.idClienteTelepeaje = :id")
					.setParameter("id", idCliente).getSingleResult();

				return extranjero;

			}catch (NoResultException e2){
				return null;
			}
		}
		return null;
	}

	@Override
	public Usuario findUsuario(int id) {
		
		return em.find(Usuario.class, id);
	}
	
	@Override
	public ClienteTelepeaje findCliTelepeaje(int idCliente) {
		return em.find(ClienteTelepeaje.class, idCliente);
		
	}
	
	@Override
	public Usuario findByTag(int tag) {
		int idCliente = findIdClienteByTag(tag);
		Usuario usu = findUsuarioCliTelepeaje(idCliente);

		return usu;
	}
	
    @Override
    public Vehiculo findByTagVehiculo(int tag) {
        String sql = "select v from Vehiculo_Gestion v where v.identificador.tag= :tag";

        TypedQuery<Vehiculo> findByTag = em.createQuery(sql, Vehiculo.class).setParameter("tag", tag);
        try {
            return findByTag.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void actualizarUsuario(Usuario usr) {
        em.merge(usr);
    };
    
    @Override
    @Transactional
    public void actualizarCliTelepeaje (ClienteTelepeaje cliTelepeaje) {
    	//em.merge(cliTelepeaje.getCtaPrepaga());
    	//em.merge(cliTelepeaje.getCtaPostpaga());
        em.merge(cliTelepeaje);
    };
    
    @Override
    @Transactional
    public void actualizarCuentaPrepaga(PrePaga ctaPrepaga) {
    	em.merge(ctaPrepaga);
    	
    };
	
	@Override
	public List<Cuenta> findCuentasByTag(int tag) {
		Usuario usu = findByTag(tag);
		List<Cuenta> cuentas = new ArrayList<>();
		Cuenta cuenta = usu.getClienteTelepeaje().getCtaPostpaga();
		Cuenta cuenta2 = usu.getClienteTelepeaje().getCtaPrepaga();
		if (cuenta != null) {
			cuentas.add(cuenta);
		}
		if (cuenta2 != null) {
			cuentas.add(cuenta2);
		}
		return cuentas;
	}

	@Override
	@Transactional
	public ClienteTelepeaje crearClienteTelepeaje(Usuario usr) {
		log.infof("\n######### altaClienteTelepeaje 2 #########\n");
    	//incio saldo prePaga en 0 sin tarjeta
    	LocalDateTime ahora = LocalDateTime.now();
    	
    	if (usr.getNacionalidad()==0) {
    		//soy nacional
    		Nacional nacional = (Nacional) usr;
    		//creo cuenta prepaga con saldo 0
    		PrePaga prePaga = new PrePaga( ahora, 0); //nroCuenta verrr
    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(nacional, prePaga, null);
    		ClienteSucive cliSucive = new ClienteSucive(nacional);
    		nacional.setClienteSucive(cliSucive);
    		nacional.setClienteTelepeaje(cliTelepeaje);
    		log.infof("\n######### altaClienteTelepeaje 3 #########\n");
            em.persist(nacional.getClienteTelepeaje().getCtaPrepaga());
            log.infof("\n######### altaClienteTelepeaje 4 #########\n");
            em.persist(nacional.getClienteTelepeaje());
            em.persist(nacional.getClienteSucive());
            em.persist(nacional);
    		
    		return cliTelepeaje; 
        
    	}else {
    		//soy nacional
    		Extranjero extranjero = (Extranjero) usr;
    		//creo cuenta prepaga con saldo 0
    		PrePaga prePaga = new PrePaga( ahora, 0); //nroCuenta verrr
    		ClienteTelepeaje cliTelepeaje = new ClienteTelepeaje(extranjero, prePaga, null);
    		extranjero.setClienteTelepeaje(cliTelepeaje);
    		log.infof("\n######### altaClienteTelepeaje 1 extra #########\n");
            em.persist(extranjero.getClienteTelepeaje().getCtaPrepaga());
            log.infof("\n######### altaClienteTelepeaje 2 extra #########\n");
            em.persist(extranjero.getClienteTelepeaje());
            em.persist(extranjero);
    		
    		return cliTelepeaje; 
    	}
		
	}



	

	@Override
	public long salvarTarjetaPostPaga(PostPaga postPaga, Tarjeta tarjeta) {
		em.persist(tarjeta);
		em.persist(postPaga);
		
		return postPaga.getIdCuenta();
		
	}

	@Override
	public int salvarPasadaPeaje(PasadaPeaje pasada) {
		em.persist(pasada);
	
		return pasada.getIdPasada();
		
	}

	@Override
	public List<Vinculo> findVinculosByUser(Usuario usr) {

		return null;
	}
	
	@Override
	
	public long salvarVehiculo(Vehiculo vehiculo) {
		em.persist(vehiculo);
		return vehiculo.getId();
		
	};


	@Override
	public List<Vehiculo> traerVehiculosUsr(Usuario usr){
	
	    String jpql = "SELECT v FROM Vehiculo_Gestion v WHERE v.usuario = :Usuario";
	    TypedQuery<Vehiculo> query = em.createQuery(jpql, Vehiculo.class);
	    query.setParameter("Usuario", usr);
	    return query.getResultList();
	
	}



	@Override
	public void actualizarVehiculo(Vehiculo v) {
		em.merge(v);
		
	}



	@Override
	public List<PasadaPeaje> traerPasadasVehiculo(Vehiculo v) {
	    String jpql = "SELECT p FROM PasadaPeaje p WHERE p.vehiculo = : vehiculo";
	    TypedQuery<PasadaPeaje> query = em.createQuery(jpql, PasadaPeaje.class);
	    query.setParameter("vehiculo", v);
	    return query.getResultList();
		
	}
	


}//fin