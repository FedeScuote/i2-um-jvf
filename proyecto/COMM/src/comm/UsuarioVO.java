package comm;
import java.io.Serializable;


public class UsuarioVO implements Serializable {

	private String nombreB;

	private String apellidoB;

	public String getApellidoB() {
		return apellidoB;
	}

	public void setApellidoB(String apellidoB) {
		this.apellidoB = apellidoB;
	}

	public String getNombreB() {
		return nombreB;
	}

	public void setNombreB(String nombreB) {
		this.nombreB = nombreB;
	}

	public UsuarioVO(String nombreB, String apellidoB) {
		super();
		this.nombreB = nombreB;
		this.apellidoB = apellidoB;
	}

}
