package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import busImpl.Usuario;

import conexion.Conexion;
import daoInterfaces.PartidaDAO;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;

public class PartidaDAODB implements PartidaDAO {
	private static Logger logger = Logger.getLogger(PartidaDAODB.class);

	//Una vez creado el desaf�o, se debe concretar, devuelve idDesafio al concretar el desafio
	public int concretarDesafio(int idDesafio,int idDesafiante){
		logger.debug("Entro a concretarDesafio con par�metros de entrada idDesafio= "+idDesafio+" idDesafiante= "+idDesafiante);
		int idD=0;
		Conexion c=new Conexion();
		try {
			logger.debug("Primero actualizo en la tabla desafios");
			c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "En curso", "idDesafio", idDesafio);
			ResultSet resultado = c.devolverResutado("SELECT juegos_idJuego,usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE desafios_idDesafio='"+idDesafio+"'");
			resultado.next();
			int idJuego=resultado.getInt("juegos_idJuego");
			idD=idDesafio;
			int idUsuario=idDesafiante;
			int usuarioGanadorD=0; //El cero significa que nadie gan� a�n
			logger.debug("Luego ingreso un nuevo registro en la relaci�n a usuarios_has_juegos_desafios");
			logger.debug("idJuego= "+idJuego);
			logger.debug("idDesafiante= "+idUsuario);
			logger.debug("usuarioGanadorD= "+usuarioGanadorD);
			c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", "usuarioGanadorD", idJuego, idDesafio, idUsuario,usuarioGanadorD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return idD;
	}
	//Devuelve si true si existe partida pendiente pas�ndole un idUsuario
	public boolean partidaPendiente(int idUsuario) {
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
		}
		c.disconnect();
		return pendiente;
	}

	//Devuelve la idPartida En curso pasandole un idUsuario, retorna cero si no hay partida.
	public int idPartida(int idUsuario) {
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
		}
		c.disconnect();
		return idPartida;
	}


	//Devuelve un objeto Usuario del oponente, al pasarle la idUsuario
	public Usuario oponente(int idUsuario) {
		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		int idPartida=this.idPartida(idUsuario);
		int idOponente=0;
		try {
			ResultSet r=c.devolverResutado("SELECT usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE usuarios_idusuario !='"+idUsuario+"' AND desafios_idDesafio='"+idPartida+"' AND usuarioGanadorD='0'");
			while(r.next()){
				idOponente=r.getInt("usuarios_idusuario");
			}
			String oponente=ud.getUsuario(idOponente);
			u=ud.findByName(oponente); //carga todos los datos del oponente al objeto usuario u
		} catch (SQLException e) {
			logger.info("A�n no hay contrincante");
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.disconnect();
		return u;
	}




	//Devuelve un objeto Usuario del oponente, al pasarle la idUsuario. Esto se hace, una vez que el usuario ya gan�.
	public Usuario oponente2(int idUsuario, int idDesafio) {
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
			logger.info("Ya se termin� la partida");
		} catch (NoExisteUsuarioException e) {
			logger.info("Ya se termin� la partida");
		} catch (NotDataFoundException e) {
			logger.info("Ya se termin� la partida");
		}
		c.disconnect();
		return u;
	}



	public void terminarPartida(int idPartida, int idUsuario, boolean gane) {
		if(gane){
			try {
				Conexion c=new Conexion();
				PartidaDAODB p=new PartidaDAODB();
				Usuario u=p.oponente2(idUsuario,idPartida);
				int idOponente=u.getIdUsuarioB();
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idUsuario, "usuarioGanadorD", idUsuario);
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idOponente, "usuarioGanadorD", idUsuario);
				c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "Finalizado", "idDesafio", idPartida);
				c.disconnect();
			} catch (SQLException e) {
				logger.info("La partida ya est� terminada");
			}
		}else{
			try {
				Conexion c=new Conexion();
				PartidaDAODB p=new PartidaDAODB();
				Usuario u=p.oponente2(idUsuario,idPartida);
				int idOponente=u.getIdUsuarioB();
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idUsuario, "usuarioGanadorD", idOponente);
				c.actualizarTuplaDeUnaColumna3("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", 1, idPartida, idOponente, "usuarioGanadorD", idOponente);
				c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "Finalizado", "idDesafio", idPartida);
				c.disconnect();
			} catch (SQLException e) {
				logger.info("La partida ya est� terminada");
			}

		}



	}
}
