package busImpl.usuario;

import java.util.ResourceBundle;

import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NotDataFoundException;

public class Usuario {
	private static ResourceBundle constante = ResourceBundle.getBundle("bus");
	private static UsuarioDAO getDAO() {
		try {
			return (UsuarioDAO) Class.forName(constante.getString("CLASS_FOR_NAME_USUARIO"))
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private int idUsuarioB;

	private String usuarioB;

	private String claveB;

	private String nombreB;

	private String apellidoB;

	private int nivelPrivilegioB;

	private String paisB;

	private int creditoB;

	private int virtualB;

	private int partidasGanadasB;

	public static Usuario findByName(String name)throws NoSeEncuentraUsuarioException {
		UsuarioDAO dao = getDAO();
		try {
			return dao.findByName(name);
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

	}

	public int getIdUsuarioB() {
		return idUsuarioB;
	}

	public void setIdUsuarioB(int idUsuarioB) {
		this.idUsuarioB = idUsuarioB;
	}

	public String getUsuarioB() {
		return usuarioB;
	}

	public void setUsuarioB(String usuarioB) {
		this.usuarioB = usuarioB;
	}

	public String getClaveB() {
		return claveB;
	}

	public void setClaveB(String claveB) {
		this.claveB = claveB;
	}

	public String getNombreB() {
		return nombreB;
	}

	public void setNombreB(String nombreB) {
		this.nombreB = nombreB;
	}

	public String getApellidoB() {
		return apellidoB;
	}

	public void setApellidoB(String apellidoB) {
		this.apellidoB = apellidoB;
	}

	public int getNivelPrivilegioB() {
		return nivelPrivilegioB;
	}

	public void setNivelPrivilegioB(int nivelPrivilegioB) {
		this.nivelPrivilegioB = nivelPrivilegioB;
	}

	public String getPaisB() {
		return paisB;
	}

	public void setPaisB(String paisB) {
		this.paisB = paisB;
	}

	public int getCreditoB() {
		return creditoB;
	}

	public void setCreditoB(int creditoB) {
		this.creditoB = creditoB;
	}

	public int getVirtualB() {
		return virtualB;
	}

	public void setVirtualB(int virtualB) {
		this.virtualB = virtualB;
	}

	public int getPartidasGanadasB() {
		return partidasGanadasB;
	}

	public void setPartidasGanadasB(int partidasGanadasB) {
		this.partidasGanadasB = partidasGanadasB;
	}
}
