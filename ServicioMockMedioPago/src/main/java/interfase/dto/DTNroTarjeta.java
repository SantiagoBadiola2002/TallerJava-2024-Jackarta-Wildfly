package interfase.dto;

import lombok.Data;

@Data
public class DTNroTarjeta {
	
	String nroTarjeta;
	
	public DTNroTarjeta() {
		
	}
	
	public String getNroTarjeta() {
		return nroTarjeta;
	}

}
