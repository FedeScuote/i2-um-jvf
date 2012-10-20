package comm;

import java.io.Serializable;

public class RegistroDisparo implements Serializable {

	private Estados resultado;
	private Disparo disparo;

	public RegistroDisparo(Estados resultado, Disparo disparo) {
		super();
		this.resultado = resultado;
		this.disparo = disparo;
	}


	public Estados getResultado() {
		return resultado;
	}

	public void setResultado(Estados resultado) {
		this.resultado = resultado;
	}

	public Disparo getDisparo() {
		return disparo;
	}

	public void setDisparo(Disparo disparo) {
		this.disparo = disparo;
	}



}
