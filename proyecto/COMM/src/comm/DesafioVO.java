package comm;

import java.io.Serializable;

public class DesafioVO implements Serializable {

	private int idDesafio;

	private int apuesta;

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public int getIdDesafio() {
		return idDesafio;
	}

	public void setIdDesafio(int idDesafio) {
		this.idDesafio = idDesafio;
	}


}
