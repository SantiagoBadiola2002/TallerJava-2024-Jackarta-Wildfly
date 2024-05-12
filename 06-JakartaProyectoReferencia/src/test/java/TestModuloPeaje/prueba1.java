package TestModuloPeaje;

import static org.junit.jupiter.api.Assertions.*;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.infraestructura.persistencia.PeajeRepositorioImpl;

import jakarta.inject.Inject;

@EnableAutoWeld
@AddPackages(PeajeRepositorioImpl.class)
class prueba1 {
	
	
	@Inject
    private PeajeRepositorio repo;

	@Test
	@DisplayName("######Test del Repositorio Modulo Peaje######")
	void test() {
		
		
		Vehiculo v = repo.findByTag(1);	
		assertEquals("ford", v.getMarca());
		
		Vehiculo v2 = repo.findByMatricula("2");
		assertEquals("ford2", v2.getMarca());
		
		double tarfiaPreferencial = repo.obtenerTarifaPreferencial().getValor();
		double valor = 100.0;
		assertEquals(valor, tarfiaPreferencial);
		
		//System.out.print("Tarifa comun: " + repo.obtenerTarifaComun());
		
	}
}