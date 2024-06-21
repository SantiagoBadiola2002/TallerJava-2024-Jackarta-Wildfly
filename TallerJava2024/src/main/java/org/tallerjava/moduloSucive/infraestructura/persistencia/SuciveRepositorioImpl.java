package org.tallerjava.moduloSucive.infraestructura.persistencia;

import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;
import org.tallerjava.moduloSucive.dominio.Pago;
import org.tallerjava.moduloSucive.dominio.repo.SuciveRepositorio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class SuciveRepositorioImpl implements SuciveRepositorio{

	@PersistenceContext
    private EntityManager em;
	
	private static final Logger log = Logger.getLogger(SuciveRepositorioImpl.class);
	
	@Override
	public void salvar(Pago pago) {
		em.persist(pago);
		
	}

	@Override
	public double traerImportesPorDia(LocalDateTime fecha) {
		
        String jpql = "SELECT SUM(p.importe) FROM Pago_Sucive p WHERE p.fecha >= :fechaInicio AND p.fecha < :fechaFin";

        LocalDateTime fechaInicio = fecha.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fechaFin = fecha.withHour(23).withMinute(59).withSecond(59);
        Double totalImportes = em.createQuery(jpql, Double.class)
        		  						.setParameter("fechaInicio", fechaInicio)
        		  						.setParameter("fechaFin", fechaFin)
        		  						.getSingleResult();

        // Manejar el caso donde no hay pagos en la fecha especificada
        return totalImportes != null ? totalImportes : 0.0;
	}

	@Override
	public List<Pago> traerPagosMatricula(String matricula) {
        return em.createQuery(
                "SELECT p FROM Pago_Sucive p WHERE p.matricula = :matricula", Pago.class)
                .setParameter("matricula", matricula)
                .getResultList();
	}
}
