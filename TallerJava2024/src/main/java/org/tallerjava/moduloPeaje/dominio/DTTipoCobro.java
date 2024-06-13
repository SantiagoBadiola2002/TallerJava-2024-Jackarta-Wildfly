package org.tallerjava.moduloPeaje.dominio;

public enum DTTipoCobro {
	PREPAGO(1), POSTPAGO(2), SUCIVE(3);

	private int id;

	DTTipoCobro(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}