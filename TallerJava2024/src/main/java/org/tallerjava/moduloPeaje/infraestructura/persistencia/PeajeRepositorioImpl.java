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
	

	    @PostConstruct
	    public void init() {
	        
	    }
	    
    @Override
    public Vehiculo findByTag(int tag) {
    	 String sql = "SELECT v FROM peaje_vehiculo v WHERE v.tag = :tag";

         TypedQuery<Vehiculo> findByTag = em.createQuery(sql, Vehiculo.class).setParameter("tag", tag);
         try {
             return findByTag.getSingleResult();
         } catch (NoResultException e) {
             return null;
         }
    }

    @Override
    public Vehiculo findByMatricula(String matricula) {
    	String sql = "SELECT v FROM peaje_vehiculo v WHERE v.matricula = :matricula";
    	
    	TypedQuery<Vehiculo> findByMatricula = em.createQuery(sql, Vehiculo.class).setParameter("matricula", matricula);
    	 try {
             return findByMatricula.getSingleResult();
         } catch (NoResultException e) {
             return null;
         }
    }

    @Override
    public Comun obtenerTarifaComun() {
        String jpql = "SELECT c FROM peaje_tarifa c WHERE c.DTYPE = :comun";
        TypedQuery<Comun> query = em.createQuery(jpql, Comun.class);
        query.setParameter("DTYP", "comun");
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
//    Override
//    public Comun obtenerTarifaComun() {
//        String sql = "SELECT * FROM peaje_tarifa WHERE DTYPE = 'comun'";
//        Query query = em.createNativeQuery(sql, Comun.class);
//        try {
//            return (Comun) query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

    @Override
    public Preferencial obtenerTarifaPreferencial() {
    	String sql = "SELECT p FROM peaje_tarifa p WHERE p.DTYPE = :preferencial";
        TypedQuery<Preferencial> query = em.createQuery(sql, Preferencial.class);
        query.setParameter("DTYP", "preferencial");
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
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
}
