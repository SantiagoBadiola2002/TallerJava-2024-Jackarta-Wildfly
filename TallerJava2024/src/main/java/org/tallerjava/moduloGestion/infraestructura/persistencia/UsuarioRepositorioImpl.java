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

	private static final Logger log = Logger.getLogger(ClienteAPI.class);

	public void inicializar() {

	}

//	//ESTO NO FUNCA
//	public Vehiculo findVehiculoByTag(int tag) {
//        TypedQuery<Vehiculo> query = em.createQuery(
//                "SELECT v FROM Vehiculo_Gestion v WHERE v.identificador.tag = :tag", Vehiculo.class);
//        query.setParameter("tag", tag);
//        return query.getSingleResult();
//    }

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

	public Usuario findUsuario(int id) {
		try {

			Nacional nacional = (Nacional) em.createQuery("SELECT n FROM nacional n WHERE n.id = :id")
					.setParameter("id", id).getSingleResult();

			if (nacional != null) {
				return nacional;
			}
				
				
		} catch (NoResultException e) {
			try {
			Extranjero extranjero = (Extranjero) em.createQuery("SELECT n FROM extranjero n WHERE n.id = :id")
					.setParameter("id", id).getSingleResult();

				return extranjero;

			}catch (NoResultException e2){
				return null;
			}
		}
		return null;
	}

	@Override
	public Usuario findByTag(int tag) {
		log.info("### findByTag 1 ###\n");
		int id = findIdClienteByTag(tag);
		log.info("### findByTag 2 ### " + id + "\n");
		Usuario usu = findUsuario(id);

		//log.info("$$$ usu nacional encontrado $$$\n" + usu.getNombre() + "$$$$" + usu.getNacionalidad());

		return usu;
	}

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
	public void crearClienteSucive(Nacional usr) {
		ClienteSucive cliSucive = new ClienteSucive(usr);
		// ACTUALIZAE USR EN BD
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
