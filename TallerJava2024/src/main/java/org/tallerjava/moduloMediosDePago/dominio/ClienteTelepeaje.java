package org.tallerjava.moduloMediosDePago.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ClienteTelepeaje{
	
    private long idTelepeaje; 
    private PostPaga ctaPostPaga;
    private PrePaga ctaPrePaga;
    
	public ClienteTelepeaje(long idTelepeaje, PostPaga ctaPostPaga, PrePaga ctaPrePaga) {
		this.idTelepeaje = idTelepeaje;
		this.ctaPostPaga = ctaPostPaga;
		this.ctaPrePaga = ctaPrePaga;
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

	public PrePaga getCtaPrePaga() {
		return ctaPrePaga;
	}

	public void setCtaPrePaga(PrePaga ctaPrePaga) {
		this.ctaPrePaga = ctaPrePaga;
	}
	
}
