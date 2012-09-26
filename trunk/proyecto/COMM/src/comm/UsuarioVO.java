package comm;
import java.io.Serializable;


public class UsuarioVO implements Serializable {

	private int idUsuario;

	private String usuarioB;

	private String nombreB;

	private String apellidoB;

	public String getUsuarioB() {
		return usuarioB;
	}

	public void setUsuarioB(String usuarioB) {
		this.usuarioB = usuarioB;
	}

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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
			return this.getNombreB();
	}
}
