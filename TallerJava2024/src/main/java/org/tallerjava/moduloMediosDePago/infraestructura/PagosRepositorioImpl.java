package org.tallerjava.moduloMediosDePago.infraestructura;

import org.jboss.logging.Logger;
import org.tallerjava.moduloGestion.interfase.remota.rest.ClienteAPI;
import org.tallerjava.moduloMediosDePago.dominio.Cliente;
import org.tallerjava.moduloMediosDePago.dominio.repo.PagosRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PagosRepositorioImpl implements PagosRepositorio{
	
	@PersistenceContext
	private EntityManager em;

	private static final Logger log = Logger.getLogger(ClienteAPI.class);

	public void salvarCliente(Cliente cli) {
		em.persist(cli.getTarjeta());
		em.persist(cli);
		
	}
}
