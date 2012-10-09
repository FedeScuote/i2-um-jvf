package comm;

import java.io.Serializable;

public class RankingVO implements Serializable {


	public Integer nro;
	public String usuario;

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String nroAString(){
		return nro.toString();
	}
}
