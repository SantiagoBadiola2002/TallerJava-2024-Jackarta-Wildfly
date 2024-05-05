package ModuloDeClases;

public class DataTipoCobro {

	private PREPaga prePaga;
	private POSTPaga postPago;
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
