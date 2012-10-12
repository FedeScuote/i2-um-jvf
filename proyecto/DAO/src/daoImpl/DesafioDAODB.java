package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import busImpl.Ranking;
import conexion.Conexion;

import daoInterfaces.DesafioDAO;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesD.NoExisteUsuarioException;

public class DesafioDAODB implements DesafioDAO {

	private int desafiosDisponibles=0;

	public ArrayList getDesafios() throws NoHayDesafioException {
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

			throw new NoHayDesafioException();

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		}



		c.disconnect();
		return a;
	}

}
