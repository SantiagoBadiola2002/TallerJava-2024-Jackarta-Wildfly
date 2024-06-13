package org.tallerjava.moduloPeaje.infraestructura.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.dominio.*;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.interfase.remota.rest.PeajeAPI;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PeajeRepositorioImpl implements PeajeRepositorio {
	@PersistenceContext
    private EntityManager em;
	
	private static final Logger log = Logger.getLogger(PeajeAPI.class);
	
	    
    @Override
    public Vehiculo findByTag(int tag) {
    	
    	System.out.println("HASTA ACA");
    	 String sql = "SELECT v FROM Vehiculo v WHERE v.identificador.tag = :tag";

         TypedQuery<Vehiculo> findByTag = em.createQuery(sql, Vehiculo.class).setParameter("tag", tag);
         try {
             return findByTag.getSingleResult();
         } catch (NoResultException e) {
             return null;
         }
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
    	String sql = "SELECT v FROM Vehiculo v WHERE v.matricula = :matricula";
    	
    	TypedQuery<Vehiculo> findByMatricula = em.createQuery(sql, Vehiculo.class).setParameter("matricula", matricula);
    	 try {
             return findByMatricula.getSingleResult();
         } catch (NoResultException e) {
             return null;
         }
    }

    @Override
    public Preferencial obtenerTarifaPreferencial() {
        TypedQuery<Preferencial> findLatest = em.createQuery(
                "select t from Preferencial t order by t.fechaAplicacion desc ", Preferencial.class);
                 findLatest.setMaxResults(1);
        try {
            return findLatest.getSingleResult();
        } catch (NoResultException e) {
            log.error("Error de inconsistencia de datos, siempre tiene que existir una tarifa Preferencial.");
            return null;
        }
    }

    @Override
    public Comun obtenerTarifaComun() {
        TypedQuery<Comun> findLatest = em.createQuery(
                "select t from Comun t order by t.fechaAplicacion desc ", Comun.class);
        findLatest.setMaxResults(1);
        try {
            return findLatest.getSingleResult();
        } catch (NoResultException e) {
            log.error("Error de inconsistencia de datos, siempre tiene que existir una tarifa Comun.");
            return null;
        }
    }
    
    @Override
    @Transactional
    public void actualizarTarifaComun(double valor) { //ESTA FUNCA
    	try {
            em.createNativeQuery("UPDATE peaje_tarifa SET valor = :valor WHERE DTYPE = 'comun'")
              .setParameter("valor", valor)
              .executeUpdate();
        } catch (Exception e) {
            throw new PersistenceException("Error al actualizar la tarifa común", e);
        }
    }
    
    @Override
    @Transactional
    public void actualizarTarifaPreferencial(double valor) {
    	try {
            em.createNativeQuery("UPDATE peaje_tarifa SET valor = :valor WHERE DTYPE = 'preferencial'")
              .setParameter("valor", valor)
              .executeUpdate();
        } catch (Exception e) {
            throw new PersistenceException("Error al actualizar la tarifa común", e);
        }
    }
    
    @Override
    public void saveVehiculo(Vehiculo vehiculo) {
        em.persist(vehiculo);
    }
}
