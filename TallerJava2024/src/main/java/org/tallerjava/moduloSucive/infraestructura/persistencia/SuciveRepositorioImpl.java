package org.tallerjava.moduloSucive.infraestructura.persistencia;

import org.jboss.logging.Logger;
import org.tallerjava.moduloPeaje.interfase.remota.rest.PeajeAPI;
import org.tallerjava.moduloSucive.dominio.Pago;
import org.tallerjava.moduloSucive.dominio.repo.SuciveRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class SuciveRepositorioImpl implements SuciveRepositorio{

	@PersistenceContext
    private EntityManager em;
	
	private static final Logger log = Logger.getLogger(PeajeAPI.class);
	
	@Override
	public void salvar(Pago pago) {
		em.persist(pago);
		
	}
}
