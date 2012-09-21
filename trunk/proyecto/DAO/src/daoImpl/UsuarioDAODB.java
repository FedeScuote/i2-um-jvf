package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import busImpl.Usuario;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NotDataFoundException;

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
		Conexion conexion = new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");
		try {

			ResultSet resultado = conexion
					.devolverResutado("select* from usuarios");
			while (resultado.next()) {

				if ((resultado.getString("usuario")).equals(usuario)) {
					u.setIdUsuarioB(resultado.getInt("idUsuario"));
					u.setUsuarioB(resultado.getString("usuario"));
					u.setNombreB(resultado.getString("nombre"));
					u.setApellidoB(resultado.getString("apellido"));
					u.setNivelPrivilegioB(resultado.getInt("nivelPrivilegio"));
					u.setPaisB(resultado.getString("pais"));
					u.setCreditoB(resultado.getInt("credito"));

					conexion.disconnect();
					return u;
				}

			}
		} catch (SQLException ex) {

		}
		conexion.disconnect();
		return u;

	}

}
