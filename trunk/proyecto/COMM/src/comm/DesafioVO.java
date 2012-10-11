package comm;

import java.io.Serializable;

public class DesafioVO implements Serializable {

	UsuarioVO usuarioCreador;

	int apuesta;

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public UsuarioVO getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(UsuarioVO usuario) {
		this.usuarioCreador = usuario;
	}

}
