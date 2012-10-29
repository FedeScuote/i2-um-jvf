package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;
import busImpl.Usuario;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;
import excepcionesD.NoExisteUsuarioException;
import org.apache.log4j.Logger;

public class UsuarioDAODB implements UsuarioDAO {

	private int idUsuarioD;
	private String usuarioD;
	private String claveD;
	private String nombreD;
	private String apellidoD;
	private int nivelPrivilegioD;
	private String paisD;
	private int creditoD;
	private static Logger logger = Logger.getLogger(UsuarioDAODB.class);

	public Usuario findByName(String usuario) throws NotDataFoundException {
		logger.debug("Entro a findByName con parametro de entrada usuario= "+usuario);
		Usuario u = new Usuario();
		Conexion c = new Conexion();
		try {

			ResultSet resultado = c
					.devolverResutado("SELECT * FROM usuarios WHERE usuario='"+usuario+"'");
			boolean esta=false;
			while (resultado.next()) {
					logger.debug("El usuario tiene los siguientes datos");
					String clave=resultado.getString("clave");
					int idUsuario=resultado.getInt("idUsuario");
					String usuarioString=resultado.getString("usuario");
					String nombre=resultado.getString("nombre");
					String apellido=resultado.getString("apellido");
					int nivelPrivilegio=resultado.getInt("nivelPrivilegio");
					String pais=resultado.getString("pais");
					int credito=resultado.getInt("credito");
					int virtual=resultado.getInt("virtual");
					int partidasGanadas=resultado.getInt("partidasGanadas");

					logger.debug("usuario= "+usuarioString);
					logger.debug("clave= "+clave);
					logger.debug("idUsuario= "+idUsuario);
					logger.debug("nombre= "+nombre);
					logger.debug("apellido= "+apellido);
					logger.debug("nivelPrivilegio= "+nivelPrivilegio);
					logger.debug("pais= "+pais);
					logger.debug("credito= "+credito);
					logger.debug("virtual= "+virtual);
					logger.debug("partidasGanadas= "+partidasGanadas);

					u.setClaveB(clave);
					u.setIdUsuarioB(idUsuario);
					u.setUsuarioB(usuarioString);
					u.setNombreB(nombre);
					u.setApellidoB(apellido);
					u.setNivelPrivilegioB(nivelPrivilegio);
					u.setPaisB(pais);
					u.setCreditoB(credito);
					u.setVirtualB(virtual);
					u.setPartidasGanadasB(partidasGanadas);
					esta=true;
			}
			logger.debug("Me desconecto de la base de datos del método findByName");
			c.disconnect();
			if(esta){
				return u;
			}else{
				throw new NotDataFoundException();
			}
		} catch (SQLException ex) {
			logger.error("error de my sql");
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
		logger.debug("Entro a getUsuario con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c = new Conexion();
		ResultSet r = null;
		String usuario = null;
		try {
			r = c.devolverResutado("SELECT usuario FROM usuarios WHERE idusuario='"+ idUsuario + "'");
			r.next();
			usuario = r.getString("usuario");
		} catch (SQLException ex) {

		}
		logger.debug("Me desconecto de la base de datos del método getUsuario");
		c.disconnect();
		logger.debug("usuario= "+usuario);
		return usuario;
	}

	public boolean creditoSuficiente(int credito, int idUsuario){
		logger.debug("Entro a creditoSuficiente con parámetros de entrada credito= "+credito+" idUsuario= "+idUsuario);
		boolean suficiente=false;
		Conexion c=new Conexion();
		try {
			ResultSet r=c.devolverResutado("SELECT credito FROM usuarios WHERE idusuario="+idUsuario);
			if(r.first()){
				int creditoUsuario=r.getInt("credito");
				if(creditoUsuario>=credito){
					suficiente=true;
				}
			}else{
				throw new NoExisteUsuarioException();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(suficiente);
		logger.debug("Me desconecto de la base de datos del método creditoSuficiente");
		c.disconnect();
		return suficiente;
	}

	public int getResultadoCredito(int credito, int idUsuario){
		logger.debug("Entro a getResultadoCredito con parámetros de entrada credito= "+credito+" idUsuario= "+idUsuario);
		int resultado=0;
		Conexion c=new Conexion();
		try {

			c.actualizarTuplaCreditoUsuario("usuarios", "credito", credito, "idusuario", idUsuario);
			ResultSet r=c.devolverResutado("SELECT credito FROM usuarios WHERE idusuario='"+idUsuario+"'");

			if(r.first()){
				resultado=r.getInt("credito");
			}else{
				throw new NoExisteUsuarioException();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("resultado= "+resultado);
		logger.debug("Me desconecto de la base de datos del método getResultadoCredito");
		c.disconnect();
		return resultado;
	}




}

