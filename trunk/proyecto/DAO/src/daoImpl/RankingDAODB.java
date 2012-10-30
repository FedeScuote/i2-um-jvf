package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.log4j.Logger;

import busImpl.Ranking;

import conexion.Conexion;
import daoInterfaces.RankingDAO;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;

public class RankingDAODB implements RankingDAO {
	private static Logger logger = Logger.getLogger(RankingDAODB.class);
	private String usuario;
	private int ganadas=0;



	public int getGanadas() {
		return ganadas;
	}

	public void setGanadas(int ganadas) {
		this.ganadas = ganadas;
	}



	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	//terminado
	public ArrayList getRankingLudo() throws NoHayRankingException{
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion();

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='2' ORDER BY ganadas DESC");
			while (resultado.next()) {
					UsuarioDAODB u=new UsuarioDAODB();
					Ranking r=new Ranking();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=u.getUsuario(idUsuario);

					r.setUsuario(usuario);
					r.setGanadas(resultado.getInt("ganadas"));
					a.add(r);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayRankingException();

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayRankingException();
		}



		c.disconnect();
		return a;
	}



	//terminado
	public ArrayList getRankingBackgammon() throws NoHayRankingException {
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion();

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='3' ORDER BY ganadas DESC");
			while (resultado.next()) {
					Ranking r=new Ranking();
					UsuarioDAODB u=new UsuarioDAODB();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=u.getUsuario(idUsuario);

					r.setUsuario(usuario);
					r.setGanadas(resultado.getInt("ganadas"));
					a.add(r);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayRankingException();

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayRankingException();
		}



		c.disconnect();
		return a;
	}

	//terminado
	public ArrayList getRankingBatallaNaval() throws NoHayRankingException {
		logger.debug("Entro a getRankingBatallaNaval");
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion();

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='1' ORDER BY ganadas DESC");
			while (resultado.next()) {
					Ranking r=new Ranking();
					UsuarioDAODB u=new UsuarioDAODB();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=u.getUsuario(idUsuario);

					r.setUsuario(usuario);
					r.setGanadas(resultado.getInt("ganadas"));
					a.add(r);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayRankingException();

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayRankingException();
		}


		logger.debug("Me desconecto de la base de datos del método getRankingBatallaNaval");
		c.disconnect();
		return a;

	}
	//terminado
	public ArrayList getRankingGeneral() throws NoHayRankingException {
		logger.debug("Entro a getRankingGeneral");
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion();

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, sum(ganadas) FROM `ranking` WHERE juegos_idJuego='1' OR juegos_idJuego='2' OR juegos_idJuego='3' GROUP BY usuarios_idusuario ORDER BY ganadas DESC");
			while (resultado.next()) {

					UsuarioDAODB u=new UsuarioDAODB();
					Ranking r=new Ranking();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=u.getUsuario(idUsuario);

					r.setUsuario(usuario);
					r.setGanadas(resultado.getInt("sum(ganadas)"));
					a.add(r);

			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayRankingException();

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayRankingException();
		}


		logger.debug("Me desconecto de la base de datos del método getRankingGeneral");
		c.disconnect();
		return a;
	}


	public int getGanadasBatallaNaval(int idUsuario) throws NoHayRankingException {
		logger.debug("Entro a getGanadasBatallaNaval con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c=new Conexion();
		int ganadas=0;
		try {
			ResultSet r=c.devolverResutado("SELECT ganadas FROM ranking WHERE usuarios_idUsuario='"+idUsuario+"' AND juegos_idJuego='1'");
			if(r.first()){
				ganadas=r.getInt("ganadas");
			}else{
				logger.debug("Me desconecto de la base de datos del método getGanadasBatallaNaval");
				c.disconnect();
				throw new NoHayRankingException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("ganadas= "+ganadas);
		logger.debug("Me desconecto de la base de datos del método getGanadasBatallaNaval");
		c.disconnect();
		return ganadas;
	}






}
