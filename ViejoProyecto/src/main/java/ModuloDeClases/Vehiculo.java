package ModuloDeClases;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class Vehiculo {
 
	@Inject
	private Tag tag;
	
	@Inject
	private List<PasadaPorPeaje> pasadasPeaje;
	
	public Vehiculo() {
		
	}
	
	public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

	public List<PasadaPorPeaje> getPasadasPeaje() {
		return pasadasPeaje;
	}

	public void setPasadasPeaje(List<PasadaPorPeaje> pasadasPeaje) {
		this.pasadasPeaje = pasadasPeaje;
	}

    
}
