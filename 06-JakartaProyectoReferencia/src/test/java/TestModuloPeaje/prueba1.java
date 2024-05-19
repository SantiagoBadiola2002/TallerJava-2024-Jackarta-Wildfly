package TestModuloPeaje;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tallerjava.moduloGestion.dominio.*;
import org.tallerjava.moduloGestion.infraestructura.persistencia.UsuarioRepositorioImpl;
import org.tallerjava.moduloPeaje.dominio.repo.PeajeRepositorio;
import org.tallerjava.moduloPeaje.infraestructura.persistencia.PeajeRepositorioImpl;

import jakarta.inject.Inject;

@EnableAutoWeld
@AddPackages(PeajeRepositorioImpl.class)
class prueba1 {
	
		
	 private UsuarioRepositorioImpl usuarioRepositorio;

	    @BeforeEach
	    public void setUp() {
	        usuarioRepositorio = new UsuarioRepositorioImpl();
	        usuarioRepositorio.usuarios = new ArrayList<>();
	        usuarioRepositorio.inicializar();
	    }

	    @Test
	    public void testInicializar() {
	        assertFalse(usuarioRepositorio.usuarios.isEmpty(), "La lista de usuarios no debería estar vacía después de inicializar.");
	    }

	    @Test
	    public void testFindByTag() {
	        Usuario usuario = usuarioRepositorio.findByTag(1);
	        assertNotNull(usuario, "El usuario debería encontrarse por su tag.");
	        assertEquals(1, usuario.getId(), "El nombre del usuario debería ser 'pepe'.");
	    }

	    @Test
	    public void testFindCuentasByTag() {
	        List<Cuenta> cuentas = usuarioRepositorio.findCuentasByTag(1);
	        assertNotNull(cuentas, "La lista de cuentas no debería ser nula.");
	        assertEquals(1, cuentas.size(), "Debería haber 1 cuenta asociada al tag 1.");
	    }

	    @Test
	    public void testCrearClienteTelepeaje() {
	        Usuario usuario = new Nacional(2, "juan", "juan@gmail.com", new ArrayList<>(), null);
	        ClienteTelepeaje clienteTelepeaje = usuarioRepositorio.crearClienteTelepeaje(usuario);
	        assertNotNull(clienteTelepeaje, "El cliente Telepeaje no debería ser nulo.");
	        assertNotNull(usuario.getClienteTelepeaje(), "El usuario debería tener un cliente Telepeaje asociado.");
	    }

	    @Test
	    public void testVicularUsuarioVehiculo() {
//	        Usuario usuario = new Nacional(3, "maria", "maria@gmail.com", new ArrayList<>(), null);
//	        Identificador identificador = new Identificador(2, "ABC 1234", 2005);
//	        Vehiculo vehiculo = new Vehiculo(2, identificador, null);
//	        usuarioRepositorio.vicularUsuarioVehiculo(usuario, vehiculo);
//
//	        List<Vehiculo> vehiculos = usuarioRepositorio.findVehiculoByUser(usuario);
//	        assertFalse(vehiculos.isEmpty(), "La lista de vehículos no debería estar vacía.");
//	        assertEquals(1, vehiculos.size(), "Debería haber un vehículo vinculado al usuario.");
	    }
}