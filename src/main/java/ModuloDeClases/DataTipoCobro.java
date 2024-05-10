package ModuloDeClases;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class DataTipoCobro {

	@Inject
	private PREPaga prePaga;
	
	@Inject
	private POSTPaga postPago;
	
	@Inject
	private ClienteSucive clienteSucive;
	

	public DataTipoCobro(PREPaga prePaga, POSTPaga postPago, ClienteSucive clienteSucive) {
		super();
		this.prePaga = prePaga;
		this.postPago = postPago;
		this.clienteSucive = clienteSucive;
	}

	public PREPaga getPrePaga() {
		return prePaga;
	}

	public POSTPaga getPostPago() {
		return postPago;
	}

	public ClienteSucive getClienteSucive() {
		return clienteSucive;
	}


}
