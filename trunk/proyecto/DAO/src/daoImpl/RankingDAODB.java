package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

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
		ArrayList b=new ArrayList();


		Conexion c=new Conexion("com.mysql.jdbc.Driver","jdbc:mysql://localhost/jvm", "root", "");

		try {

			ResultSet resultado = c.devolverResutado("SELECT DISTINCT juegos_idJuego, desafios_idDesafio, usuarioGanadorD FROM  `usuarios_has_juegos_desafios` WHERE juegos_idJuego='2'");
			while (resultado.next()) {
					Integer intObj=new Integer(resultado.getInt("usuarioGanadorD"));
					b.add(intObj);
			}

		} catch (SQLException ex) {

			throw new NoHayRankingException();

		}

		for(int i=0;i<b.size();i++){
			RankingDAODB r=new RankingDAODB();
			String auxUsuario=null;
			int auxGanadas=0;
			//System.out.println("fila i="+i);
			//System.out.println(b.size());
			for(int j=0;j<b.size();j++){
				int idUsuario=((Integer) b.get(i)).intValue();
				//System.out.println("fila j="+j);
				try {
					auxUsuario=this.getUsuario(idUsuario);
				} catch (NoExisteUsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(((Integer)b.get(j)).intValue()==(idUsuario)){
					auxGanadas++;
				}
			}

			r.setUsuario(auxUsuario);
			r.setGanadas(auxGanadas);
			a.add(r);


		}
		HashSet hs=new HashSet();
		hs.addAll(a);
		a.clear();
		a.addAll(hs);









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
		c.disconnect();
		return usuario;
	}






}
