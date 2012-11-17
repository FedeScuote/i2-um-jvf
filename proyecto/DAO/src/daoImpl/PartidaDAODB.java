package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import busImpl.Usuario;

import conexion.Conexion;
import daoInterfaces.PartidaDAO;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.DesafioTerminadoException;
import excepcionesD.NoExisteCreditoSuficiente;
import excepcionesD.NoExisteDesafioException;
import excepcionesD.NoExisteOponenteException;
import excepcionesB.NoExisteUsuarioException;
import excepcionesD.PartidaTerminadaException;

public class PartidaDAODB implements PartidaDAO {
	private static Logger logger = Logger.getLogger(PartidaDAODB.class);

	//Una vez creado el desafío, se debe concretar, devuelve idDesafio al concretar el desafio
	public int concretarDesafio(int idDesafio,int idDesafiante){
		logger.debug("Entro a concretarDesafio con parámetros de entrada idDesafio= "+idDesafio+" idDesafiante= "+idDesafiante);
		int idD=0;
		Conexion c=new Conexion();
		DesafioDAODB dd=new DesafioDAODB();
		boolean desafioTerminado=false;
		boolean creditoSuficiente=false;
		UsuarioDAODB ud=new UsuarioDAODB();
		logger.debug("Verifico crédito suficiente");
		try {
			desafioTerminado=dd.desafioFinalizado(idDesafio);
			if(desafioTerminado){
				throw new DesafioTerminadoException();
			}
			String usuarioDesafiante;
			try {
				usuarioDesafiante = ud.getUsuario(idDesafiante);
				Usuario u= ud.findByName(usuarioDesafiante);
				int credito=u.getCreditoB();
				creditoSuficiente=ud.creditoSuficiente(credito, idDesafiante);
			} catch (NoExisteUsuarioException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotDataFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!creditoSuficiente){
				throw new NoExisteCreditoSuficiente();
			}
			logger.debug("Crédito suficiente!");
			logger.debug("Primero actualizo en la tabla desafios");
			c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "En curso", "idDesafio", idDesafio);
			ResultSet resultado = c.devolverResutado("SELECT juegos_idJuego,usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE desafios_idDesafio='"+idDesafio+"'");
			resultado.next();
			int idJuego=resultado.getInt("juegos_idJuego");
			idD=idDesafio;
			int idUsuario=idDesafiante;
			int usuarioGanadorD=0; //El cero significa que nadie ganó aún
			logger.debug("Luego ingreso un nuevo registro en la relación a usuarios_has_juegos_desafios");
			logger.debug("idJuego= "+idJuego);
			logger.debug("idDesafiante= "+idUsuario);
			logger.debug("usuarioGanadorD= "+usuarioGanadorD);
			c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", "usuarioGanadorD", idJuego, idDesafio, idUsuario,usuarioGanadorD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DesafioTerminadoException e) {
			logger.debug("El desafío ya estaba terminado");
		} catch (NoExisteCreditoSuficiente e) {
			logger.debug("Credito insuficiente");
		} finally{
			logger.debug("Me desconecto de la base de datos del método concretarDesafio");
			c.disconnect();
		}
		return idD;
	}
	//Devuelve true si existe partida pendiente pasándole un idUsuario
	public boolean partidaPendiente(int idUsuario) {
		logger.debug("Entro a partidaPendiente con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c=new Conexion();
		UsuarioDAODB ud=new UsuarioDAODB();
		String jugador;
		boolean pendiente=false;
		try {
			jugador = ud.getUsuario(idUsuario);
			ResultSet r=c.devolverResutado("SELECT usuarioGanadorD FROM desafios, usuarios_has_juegos_desafios WHERE idDesafio=desafios_idDesafio AND usuarios_idusuario='"+idUsuario+"' AND estadoD='En curso' AND usuarioGanadorD='0'");
			if(r.first()){
				pendiente=true;
			}else{
				pendiente=false;
			}
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("pendiente= "+pendiente);
			logger.debug("Me desconecto de la base de datos del método partidaPendiente");
			c.disconnect();
		}
		return pendiente;
	}

	//Devuelve la idPartida En curso pasandole un idUsuario, retorna cero si no hay partida.
	public int idPartida(int idUsuario) {
		logger.debug("Entro a idPartida con parámetro de entrada idUsuario= "+idUsuario);
		int idPartida=0;
		Conexion c=new Conexion();
		try {
			ResultSet r=c.devolverResutado("SELECT desafios_idDesafio FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario='"+idUsuario+"' AND usuarioGanadorD='0'");
			if(r.first()){
				idPartida=r.getInt("desafios_idDesafio");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("idPartida= "+idPartida);
			logger.debug("Me desconecto de la base de datos del método idPartida");
			c.disconnect();
		}
		return idPartida;
	}

	//Optimizado, devuelve la idPartida En curso pasandole un idUsuario, retorna cero si no hay partida.
	public int idPartida2(int idUsuario, Conexion c) {
		logger.debug("Entro a idPartida con parámetro de entrada idUsuario= "+idUsuario);
		int idPartida=0;
		try {
			ResultSet r=c.devolverResutado("SELECT desafios_idDesafio FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario='"+idUsuario+"' AND usuarioGanadorD='0'");
			if(r.first()){
				idPartida=r.getInt("desafios_idDesafio");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("idPartida= "+idPartida);
			logger.debug("Me salgo del método idPartida");
		}
		return idPartida;
	}



	//Devuelve un objeto Usuario del oponente, al pasarle la idUsuario
	public Usuario oponente(int idUsuario) {
		logger.debug("Entro a oponente con parámetro de entrada idUsuario= "+idUsuario);
		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		int idPartida=this.idPartida(idUsuario);
		int idOponente=0;
		try {
			ResultSet r=c.devolverResutado("SELECT usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario !='"+idUsuario+"' AND desafios_idDesafio='"+idPartida+"' AND usuarioGanadorD='0'");
			if(r.first()){
				idOponente=r.getInt("usuarios_idusuario");
				logger.debug("oponente= "+u.getIdUsuarioB());
				String oponente=ud.getUsuario(idOponente);
				u=ud.findByName(oponente); //carga todos los datos del oponente al objeto usuario u
			}else{
				throw new NoExisteOponenteException();
			}

		} catch (SQLException e) {
			logger.info("Aún no hay contrincante");
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteOponenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método oponente");
			c.disconnect();
		}
		return u;
	}




	//Devuelve un objeto Usuario del oponente, al pasarle la idUsuario. Esto se hace, una vez que el usuario ya ganó.
	public Usuario oponente2(int idUsuario, int idDesafio) {
		logger.debug("Entro al oponente2 con parámetros de entrada idUsuario= "+idUsuario+" idDesafio= "+idDesafio);
		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		int idPartida=idDesafio;
		int idOponente=0;
		try {
			ResultSet r=c.devolverResutado("SELECT usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario !='"+idUsuario+"' AND desafios_idDesafio='"+idPartida+"'");
			r.next();
			idOponente=r.getInt("usuarios_idusuario");
			String oponente=ud.getUsuario(idOponente);
			u=ud.findByName(oponente);

		} catch (SQLException e) {
			logger.info("Ya se terminó la partida");
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método oponente2");
			c.disconnect();
		}
		return u;
	}



	public void terminarPartida(int idPartida, int idUsuario, boolean gane) {
		logger.info("Entro a terminarPartida con parámetros de entrada idPartida= "+idPartida+" idUsuario= "+idUsuario+" gane= "+gane);
		Conexion c=new Conexion();
		PartidaDAODB p=new PartidaDAODB();
		RankingDAODB r=new RankingDAODB();
		DesafioDAODB d=new DesafioDAODB();
		UsuarioDAODB ud=new UsuarioDAODB();

		if(gane){
			try {


				boolean partidaTerminada=d.desafioFinalizado(idPartida);
				if(partidaTerminada){
					throw new PartidaTerminadaException();
				}
				Usuario u=p.oponente2(idUsuario,idPartida);
				int idOponente=u.getIdUsuarioB();

				int credito =d.getMontoDesafio(idPartida);
				int nuevoCreditoTotalUsuario=ud.getResultadoCredito(credito, idUsuario);
				logger.debug("Nuevo crédito del usuario ganador= "+nuevoCreditoTotalUsuario);
				int nuevoCreditoTotalUsuario2=ud.getResultadoCredito(-credito, idOponente);
				logger.debug("Nuevo crédito del usuario oponente perdedor= "+nuevoCreditoTotalUsuario2);

				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idUsuario, "usuarioGanadorD", idUsuario);
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idOponente, "usuarioGanadorD", idUsuario);
				c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "Finalizado", "idDesafio", idPartida);



				try {
					int ganadas=r.getGanadasBatallaNaval(idUsuario);
					ganadas++;
					c.actualizarTuplaDeUnaColumna4("ranking","juegos_idJuego", "usuarios_idUsuario",1,idUsuario, "ganadas", ganadas);
					logger.debug("ganadas en total= "+ganadas+" del idUsuario= "+idUsuario);
				} catch (NoHayRankingException e) {
					c.ingresarNuevaTuplaDeTresColumnasIntEnTablasRelacionadas("ranking","usuarios_idUsuario", "juegos_idJuego", "ganadas", idUsuario, 1,1);
					logger.debug("ganadas en total= 1 del idUsuario= "+idUsuario);
				}

			} catch (SQLException e) {

			} catch (NoExisteDesafioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PartidaTerminadaException e) {
				logger.debug("La partida ya está terminada");
			} catch (NoHayDesafioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				logger.debug("Me desconecto de la base de datos del método terminarPartida");
				c.disconnect();
			}
		}else{
			try {

				boolean partidaTerminada=d.desafioFinalizado(idPartida);
				if(partidaTerminada){
					throw new PartidaTerminadaException();
				}
				Usuario u=p.oponente2(idUsuario,idPartida);
				int idOponente=u.getIdUsuarioB();

				int credito =d.getMontoDesafio(idPartida);
				int nuevoCreditoTotalUsuario1=ud.getResultadoCredito(credito, idOponente);
				logger.debug("Nuevo crédito del usuario oponente ganador= "+nuevoCreditoTotalUsuario1);
				int nuevoCreditoTotalUsuario2=ud.getResultadoCredito(-credito, idUsuario);
				logger.debug("Nuevo crédito del usuario perdedor= "+nuevoCreditoTotalUsuario2);

				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idUsuario, "usuarioGanadorD", idOponente);
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idOponente, "usuarioGanadorD", idOponente);
				c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "Finalizado", "idDesafio", idPartida);

				try {
					int ganadas=r.getGanadasBatallaNaval(idOponente);
					ganadas++;
					c.actualizarTuplaDeUnaColumna4("ranking","juegos_idJuego", "usuarios_idUsuario",1,idOponente, "ganadas", ganadas);
					logger.debug("ganadas en total= "+ganadas+" del idUsuario= "+idOponente);
				} catch (NoHayRankingException e) {
					c.ingresarNuevaTuplaDeTresColumnasIntEnTablasRelacionadas("ranking","usuarios_idUsuario", "juegos_idJuego", "ganadas", idOponente, 1,1);
					logger.debug("ganadas en total= 1 del idUsuario= "+idOponente);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoExisteDesafioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PartidaTerminadaException e) {
				logger.info("La partida ya está terminada");
			} catch (NoHayDesafioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				logger.debug("Me desconecto de la base de datos del método terminarPartida");
				c.disconnect();
			}

		}

	}







}
