package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import busImpl.Usuario;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;
import excepcionesB.NoExisteUsuarioException;
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
			if(esta){
				return u;
			}else{
				logger.debug("No existe usuario");
				throw new NotDataFoundException();
			}
		} catch (SQLException ex) {
			logger.error("error de my sql");
			throw new NotDataFoundException();

		} finally{
			logger.debug("Me desconecto de la base de datos del método findByName");
			c.disconnect();
		}
	}

	public Usuario findByName2(String usuario,Conexion c) throws NotDataFoundException {
		logger.debug("Entro a findByName con parametro de entrada usuario= "+usuario);
		Usuario u = new Usuario();
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
			if(esta){
				return u;
			}else{
				logger.debug("No existe usuario");
				throw new NotDataFoundException();
			}
		} catch (SQLException ex) {
			logger.error("error de my sql");
			throw new NotDataFoundException();

		} finally{
			logger.debug("Me salgo del método findByName");
		}


	}



	//optimizado
	public boolean agregarUsuario(String usuario, String clave,
			int nivelPrivilegio, int virtual, int credito, int partidasGanadas,
			String nombre, String apellido, String pais)
			throws YaExisteUsuarioException {
		logger.debug("Entro a agregarUsuario con parámetros de entrada usuario= "+usuario+", clave= "+clave+", nivelPrivilegio= "+nivelPrivilegio+", virtual= "+virtual+", credito= "+credito+", partidasGanadas= "+partidasGanadas+", nombre= "+nombre+", apellido= "+apellido+", pais= "+pais);
		Conexion c=new Conexion();
		boolean existe=this.existeUsuario(usuario, c);
		boolean agrego=false;
		if(existe){
			logger.debug("Me desconecto de la base de datos del método agregarUsuario");
			c.disconnect();
			logger.debug("agrego ="+agrego);
			throw new YaExisteUsuarioException();
		}else{
			try {
				c.ingresarNuevaTuplaDeNueveColumnas("usuarios", "idusuario", "usuario", "clave", "nivelPrivilegio", "virtual", "credito", "partidasGanadas", "nombre", "apellido", "pais", usuario, clave, nivelPrivilegio, virtual, credito, partidasGanadas, nombre, apellido, pais);
				agrego=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				logger.debug("agrego ="+agrego);
				logger.debug("Me desconecto de la base de datos del método agregarUsuario");
				c.disconnect();
			}
		}
		return agrego;
	}

	//se pasa el parámetro de conexión para no crear una nueva conexión, además éste método no cierra la conexión
	public boolean existeUsuario(String usuario,Conexion c){
		logger.debug("Entro a existeUsuario con parámetro de entrada usuario= "+usuario+" y Conexion de la base de datos");
		boolean existe=false;
		try {
			ResultSet r=c.devolverResutado("SELECT usuario FROM usuarios WHERE usuario='"+usuario+"'");
			if(r.first()){
				existe=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("existe= "+existe);
		logger.debug("salgo del método existeUsuario");
		return existe;
	}

	//optimizado
	public boolean cambiarPassword(String usuario, String nuevaPassword)throws NoExisteUsuarioException{
		logger.debug("Entro a cambiarPassword con parámetros de entrada usuario= "+usuario+", nuevaPassword= "+nuevaPassword);
		boolean cambio=false;
		Conexion c=new Conexion();
		try {
			boolean existe=this.existeUsuario(usuario, c);
			if(existe){
				c.actualizarTuplaDeUnaColumna5("usuarios", "clave", nuevaPassword, "usuario", usuario);
				cambio=true;
			}else{
				throw new NoExisteUsuarioException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("cambio ="+cambio);
			logger.debug("Me desconecto de la base de datos del método cambiarPassword");
			c.disconnect();
		}
		return cambio;
	}
	//optimizado
	public boolean cambiarNombre(String usuario, String nuevoUsuario)throws NoExisteUsuarioException{
		logger.debug("Entro a cambiarPassword con parámetros de entrada usuario= "+usuario+", nuevoUsuario= "+nuevoUsuario);
		boolean cambio=false;
		Conexion c=new Conexion();
		try {
			boolean existe=this.existeUsuario(usuario, c);
			if(existe){
				c.actualizarTuplaDeUnaColumna5("usuarios", "usuario", nuevoUsuario, "usuario", usuario);
				cambio=true;
			}else{
				throw new NoExisteUsuarioException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("cambio ="+cambio);
			logger.debug("Me desconecto de la base de datos del método cambiarNombre");
			c.disconnect();
		}
		return cambio;
	}


	public String getUsuario(int idUsuario) throws NoExisteUsuarioException {
		logger.debug("Entro a getUsuario con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c = new Conexion();
		ResultSet r = null;
		String usuario = null;
		try {
			r = c.devolverResutado("SELECT usuario FROM usuarios WHERE idusuario='"+ idUsuario + "'");
			if(r.first()){
				usuario = r.getString("usuario");
			}else{
				throw new NoExisteUsuarioException();
			}

		} catch (SQLException ex) {

		} finally{
			logger.debug("usuario= "+usuario);
			logger.debug("Me desconecto de la base de datos del método getUsuario");
			c.disconnect();
		}
		return usuario;
	}
	public String getUsuario2(int idUsuario,Conexion c) throws NoExisteUsuarioException {
		logger.debug("Entro a getUsuario con parámetro de entrada idUsuario= "+idUsuario);
		ResultSet r = null;
		String usuario = null;
		try {
			r = c.devolverResutado("SELECT usuario FROM usuarios WHERE idusuario='"+ idUsuario + "'");
			if(r.first()){
				usuario = r.getString("usuario");
			}else{
				throw new NoExisteUsuarioException();
			}

		} catch (SQLException ex) {

		} finally{
			logger.debug("usuario= "+usuario);
			logger.debug("salgo del método getUsuario2");
		}
		return usuario;
	}

	//optimizado
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
		} finally{
			logger.debug(suficiente);
			logger.debug("Me desconecto de la base de datos del método creditoSuficiente");
			c.disconnect();
		}
		return suficiente;
	}

	public boolean creditoSuficiente2(int credito, int idUsuario, Conexion c){
		logger.debug("Entro a creditoSuficiente con parámetros de entrada credito= "+credito+" idUsuario= "+idUsuario);
		boolean suficiente=false;
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
		} finally{
			logger.debug(suficiente);
			logger.debug("Me salgo del método creditoSuficiente");
		}
		return suficiente;
	}




	//optimizado
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
		} finally{
			logger.debug("resultado= "+resultado);
			logger.debug("Me desconecto de la base de datos del método getResultadoCredito");
			c.disconnect();
		}
		return resultado;
	}

	//optimizado, retorna el valor sumado, siempre y cuando se pase un valor positivo
	public int sumarSaldo(int credito_a_sumar, int idUsuario){
		logger.debug("Entro a sumarSaldo con parámetros de entrada credito= "+credito_a_sumar+" idUsuario= "+idUsuario);
		int resultado=0;
		Conexion c=new Conexion();
		try {
			c.actualizarTuplaCreditoUsuario("usuarios", "credito", credito_a_sumar, "idusuario", idUsuario);
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
		} finally{
			logger.debug("resultado= "+resultado);
			logger.debug("Me desconecto de la base de datos del método sumarSaldo");
			c.disconnect();
		}
		return resultado;
	}
	//optimizado, retorna el valor restado, siempre y cuando se pase un valor positivo
	public int restarSaldo(int credito_a_restar, int idUsuario){
		logger.debug("Entro a restarSaldo con parámetros de entrada credito= "+credito_a_restar+" idUsuario= "+idUsuario);
		int resultado=0;
		Conexion c=new Conexion();
		try {
			c.actualizarTuplaCreditoUsuario1("usuarios", "credito", credito_a_restar, "idusuario", idUsuario);
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
		} finally{
			logger.debug("resultado= "+resultado);
			logger.debug("Me desconecto de la base de datos del método restarSaldo");
			c.disconnect();
		}
		return resultado;
	}

	//optimizado
	public boolean esUsuarioVirtual(String usuario) {
		logger.debug("Entro a esUsuarioVirtual con parámetros de entrada usuario= "+usuario);
		Conexion c=new Conexion();
		boolean virtual=false;
		UsuarioDAODB ud=new UsuarioDAODB();

		try {
			Usuario u=ud.findByName2(usuario,c);
			int idUsuario=u.getIdUsuarioB();
			ResultSet rs=c.devolverResutado("SELECT idUsuario FROM usuarios WHERE idusuario='"+idUsuario+"' AND virtual='1'");
			if(rs.first()){
				virtual=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			logger.debug("No existe usuario");
		} finally{
			logger.debug("virtual= "+virtual);
			logger.debug("Me desconecto de la base de datos del método restarSaldo");
			c.disconnect();
		}
		return virtual;
	}

	//optimizado
	public ArrayList<Usuario> getUsuariosVirtuales() {
		logger.debug("Entro a getUsuariosVirtuales");
		ArrayList<Usuario> a=new ArrayList<Usuario>();
		Conexion c=new Conexion();
		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		try {
			ResultSet rs=c.devolverResutado("SELECT usuario FROM usuarios WHERE virtual='1' AND (usuario) NOT IN(SELECT usuario FROM usuarios,usuarios_has_juegos_desafios,desafios WHERE usuarios_idusuario=idusuario AND desafios_idDesafio=idDesafio AND estadoD='En curso' AND virtual='1')");
			while(rs.next()){
				String usuario=rs.getString("usuario");
				logger.debug("Usuario virtual: "+usuario);
				u=ud.findByName2(usuario,c);
				a.add(u);
			}

		} catch (SQLException e) {
			logger.debug("No existe usuario Virtual disponible");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			logger.debug("No existe usuario Virtual disponible");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método getUsuariosVirtuales");
			c.disconnect();
		}
		return a;
	}


}

