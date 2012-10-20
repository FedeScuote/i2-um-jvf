package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import conexion.Conexion;
import daoInterfaces.PartidaDAO;

public class PartidaDAODB implements PartidaDAO {
	private static Logger logger = Logger.getLogger(DAOPruebas.class);

	//devuelve idDesafio
	public int concretarDesafio(int idDesafio,int idDesafiante){
		int idD=0;
		Conexion c=new Conexion();
		try {
			c.actualizarTuplaDeUnaColumna("desafios", "estadoD", "En curso", "idDesafio", idDesafio);
			ResultSet resultado = c.devolverResutado("SELECT juegos_idJuego,usuarios_idusuario FROM usuarios_has_juegos_desafios WHERE desafios_idDesafio='"+idDesafio+"'");
			resultado.next();
			int idJuego=resultado.getInt("juegos_idJuego");
			int idUsuario=idDesafiante;
			logger.debug(idJuego);
			int usuarioGanadorD=0; //El cero significa que nadie ganó aún
			c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idusuario", "usuarioGanadorD", idJuego, idDesafio, idUsuario,usuarioGanadorD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return idD;
	}
}
