import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.tallerjava.moduloPeaje.dominio.Vehiculo;

import jakarta.inject.Inject;

@EnableAutoWeld
@AddPackages(PeajeRepositorio.class)
class prueba1 {
	
	@Inject
	private PeajeRepositorio repo;


	@Test
	void test() {
		System.out.println("######Test del Repositorio Modulo Peaje########");
		
		Vehiculo v = repo.findByTag(1);	
		
		System.out.println("Marca vehiculo por tag" + v.getMarca());
		
		
	}
}