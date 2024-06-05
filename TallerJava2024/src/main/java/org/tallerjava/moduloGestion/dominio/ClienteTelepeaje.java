package org.tallerjava.moduloGestion.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "gestion_clienteTelepeaje")
public class ClienteTelepeaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClienteTelepeaje;
	
	@OneToOne
	private Usuario usuario;

	@OneToOne
	private PrePaga ctaPrepaga;

	@OneToOne
	private PostPaga ctaPostPaga;
	
	public ClienteTelepeaje() {
		
	}

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
