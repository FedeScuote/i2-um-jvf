package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import busImpl.Usuario;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;
import excepcionesD.NoExisteUsuarioException;

public class UsuarioDAODB implements UsuarioDAO {

	private int idUsuarioD;
	private String usuarioD;
	private String claveD;
	private String nombreD;
	private String apellidoD;
	private int nivelPrivilegioD;
	private String paisD;
	private int creditoD;

	public Usuario findByName(String usuario) throws NotDataFoundException {
		Usuario u = new Usuario();
		Conexion conexion = new Conexion();
		try {

			ResultSet resultado = conexion
					.devolverResutado("SELECT * FROM usuarios WHERE usuario='"+usuario+"'");
			boolean esta=false;
			while (resultado.next()) {
					u.setClaveB(resultado.getString("clave"));
					u.setIdUsuarioB(resultado.getInt("idUsuario"));
					u.setUsuarioB(resultado.getString("usuario"));
					u.setNombreB(resultado.getString("nombre"));
					u.setApellidoB(resultado.getString("apellido"));
					u.setNivelPrivilegioB(resultado.getInt("nivelPrivilegio"));
					u.setPaisB(resultado.getString("pais"));
					u.setCreditoB(resultado.getInt("credito"));
					u.setVirtualB(resultado.getInt("virtual"));
					u.setPartidasGanadasB(resultado.getInt("partidasGanadas"));
					esta=true;
			}
			conexion.disconnect();
			if(esta){
				return u;
			}else{
				throw new NotDataFoundException();
			}
		} catch (SQLException ex) {
			System.out.println("error");
			throw new NotDataFoundException();

		}


	}

	public boolean agregarUsuario(String usuario, String clave,
			int nivelPrilegio, int virtual, int credito, int partidasGanadas,
			String nombre, String apellido, String pais)
			throws YaExisteUsuarioException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUsuario(int idUsuario) throws NoExisteUsuarioException {
		Conexion c = new Conexion();

		ResultSet r = null;
		String usuario = null;
		try {
			r = c.devolverResutado("SELECT usuario FROM usuarios WHERE idusuario='"+ idUsuario + "'");

			r.next();
			usuario = r.getString("usuario");

		} catch (SQLException ex) {

		}
		return usuario;

	}




}

