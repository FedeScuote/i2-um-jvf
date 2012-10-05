package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import busImpl.Ranking;

import conexion.Conexion;
import daoInterfaces.RankingDAO;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;

public class RankingDAODB implements RankingDAO {

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


	//construccion
	public ArrayList getRankingLudo() throws NoHayRankingException{
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='2' ORDER BY ganadas DESC");
			while (resultado.next()) {
					RankingDAODB r1=new RankingDAODB();
					Ranking r=new Ranking();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=r1.getUsuario(idUsuario);
					//System.out.println(usuario);
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



	public String getUsuario(int idUsuario) throws NoExisteUsuarioException{
		String usuario=null;
		Conexion c=new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");
		try {
			ResultSet rs=c.devolverResutado("select usuario from usuarios where idUsuario="+idUsuario);
			rs.next();
			usuario=rs.getString("usuario");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new NoExisteUsuarioException();

		}
		//c.disconnect();
		return usuario;
	}

	public ArrayList getRankingBackgammon() throws NoHayRankingException {
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='3' ORDER BY ganadas DESC");
			while (resultado.next()) {
					RankingDAODB r=new RankingDAODB();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=r.getUsuario(idUsuario);
					//System.out.println(usuario);
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


	public ArrayList getRankingBatallaNaval() throws NoHayRankingException {
		ArrayList a=new ArrayList();

		ArrayList usuarios=new ArrayList();
		ArrayList ganados=new ArrayList();


		Conexion c=new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");

		try {

			ResultSet resultado = c.devolverResutado("SELECT usuarios_idusuario, ganadas FROM  `ranking` WHERE juegos_idJuego='1' ORDER BY ganadas DESC");
			while (resultado.next()) {
					RankingDAODB r=new RankingDAODB();
					int idUsuario=resultado.getInt("usuarios_idusuario");
					String usuario=r.getUsuario(idUsuario);
					//System.out.println(usuario);
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






}
