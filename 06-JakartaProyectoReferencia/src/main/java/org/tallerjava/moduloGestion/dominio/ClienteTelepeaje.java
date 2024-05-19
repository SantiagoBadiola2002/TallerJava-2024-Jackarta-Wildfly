package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteTelepeaje{
	
	private Usuario usuario;
	private PrePaga ctaPrepaga;
    private PostPaga ctaPostPaga;
	
    public ClienteTelepeaje(Usuario usr, PrePaga prePaga, PostPaga postPaga) {
    	this.usuario = usr; 
		this.ctaPrepaga = prePaga;
		this.ctaPostPaga = postPaga;
	}
    
	public PrePaga getCtaPrepaga() {
		return this.ctaPrepaga;
	}
	
	public PostPaga getCtaPostpaga() {
		return this.ctaPostPaga;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
		
	}

	public PostPaga getCtaPostPaga() {
		return ctaPostPaga;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setCtaPrepaga(PrePaga ctaPrepaga) {
		this.ctaPrepaga = ctaPrepaga;
	}

	public void setCtaPostPaga(PostPaga ctaPostPaga) {
		this.ctaPostPaga = ctaPostPaga;
	}
	
	
}
