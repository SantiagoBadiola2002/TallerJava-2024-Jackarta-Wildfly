package ModuloDeClases;

import jakarta.inject.Named;

@Named
public class Tag {
	
	String idUnico;
	
	public Tag() {
		
	}
	
	public Tag(String idUnico) {
		this.idUnico = idUnico;
	}

	public String getIdUnico() {
		return idUnico;
	}

	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}
	
	

}
