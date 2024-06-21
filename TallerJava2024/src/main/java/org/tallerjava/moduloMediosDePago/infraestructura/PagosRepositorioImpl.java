package org.tallerjava.moduloMediosDePago.infraestructura;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloMediosDePago.dominio.Cliente;
import org.tallerjava.moduloMediosDePago.dominio.Pago;
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

	@Override
	public void salvarPago(Pago pago) {
		em.persist(pago);
		
	}

	
	public double traerImportesPorDia(LocalDateTime fecha) {
		
        String jpql = "SELECT SUM(p.importe) FROM Pago p WHERE p.fecha >= :fechaInicio AND p.fecha < :fechaFin";

        LocalDateTime fechaInicio = fecha.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fechaFin = fecha.withHour(23).withMinute(59).withSecond(59);
        Double totalImportes = em.createQuery(jpql, Double.class)
        		  						.setParameter("fechaInicio", fechaInicio)
        		  						.setParameter("fechaFin", fechaFin)
        		  						.getSingleResult();

        // Manejar el caso donde no hay pagos en la fecha especificada
        return totalImportes != null ? totalImportes : 0.0;
    }


	public List<Pago> traerPagosCliente(int idCliente) {	
        return em.createQuery(
                "SELECT p FROM Pago p WHERE p.cliente.idCliente = :cliente", Pago.class)
                .setParameter("cliente", idCliente)
                .getResultList();
	}
	
	public List<Pago> traerPagosClienteYTag(int idCliente, int tag) {	
        return em.createQuery(
                "SELECT p FROM Pago p WHERE p.cliente.idCliente = :cliente AND p.tag = : tag", Pago.class)
                .setParameter("cliente", idCliente)
                .setParameter("tag", tag)
                .getResultList();
	}


}
