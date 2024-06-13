package org.tallerjava.moduloMediosDePago.infraestructura;

import org.jboss.logging.Logger;
import org.tallerjava.moduloMediosDePago.dominio.Cliente;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;
import org.tallerjava.moduloMediosDePago.dominio.Vehiculo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class PagosRepositorioImpl implements PagosRepositorio{
	
	@PersistenceContext
	private EntityManager em;

	private static final Logger log = Logger.getLogger(PagosRepositorioImpl.class);

	public void salvarCliente(Cliente cli) {
		em.persist(cli.getTarjeta());
		em.persist(cli);
		
	}
	
	public void salvarVehiculo(Vehiculo v) {
		em.persist(v);
	}
	
	public Cliente findByIdCliente(int idCliente) {
		
		System.out.println("HASTA ACA Medios");
   	 String sql = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente";

        TypedQuery<Cliente> findByIdCliente = em.createQuery(sql, Cliente.class).setParameter("idCliente", idCliente);
        try {
            return findByIdCliente.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
		
		
	}


}
