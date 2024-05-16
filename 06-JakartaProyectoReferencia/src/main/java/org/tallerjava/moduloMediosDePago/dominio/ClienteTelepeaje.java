package org.tallerjava.moduloMediosDePago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteTelepeaje{
	
    private long idTelepeaje; 
    private PostPaga ctaPostPaga;
    
	public ClienteTelepeaje(long idTelepeaje, PostPaga ctaPostPaga) {
		this.idTelepeaje = idTelepeaje;
		this.ctaPostPaga = ctaPostPaga;
	}
	
	public long getIdTelepeaje() {
		return idTelepeaje;
	}
	
	public void setIdTelepeaje(long idTelepeaje) {
		this.idTelepeaje = idTelepeaje;
	}
	
	public PostPaga getCtaPostPaga() {
		return ctaPostPaga;
	}
	
	public void setCtaPostPaga(PostPaga ctaPostPaga) {
		this.ctaPostPaga = ctaPostPaga;
	}
	
    
}
