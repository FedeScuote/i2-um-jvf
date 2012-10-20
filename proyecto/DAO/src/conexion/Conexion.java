package conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import propertiesDAO.PConfig;

/**
 *
 * @author usuario
 */
public class Conexion {

	private Connection conn;

	public Conexion() {
		try {
			PConfig p = new PConfig();

			String driver = p.getDriver();
			String url = p.getUrl();
			String usuario = p.getUser();
			String contraseņa = p.getPass();
			// String driver="com.mysql.jdbc.Driver";
			// String url="jdbc:mysql://localhost/jvm";
			// String usuario="root";
			// String contraseņa="";

			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, usuario, contraseņa);
		} catch (InstantiationException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (SQLException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	public void disconnect() {
		if (conn != null) {
			try {
				(conn).close();
				conn = null;
			} catch (SQLException ex) {

			}
		}
	}

	public ResultSet devolverResutado(String consulta) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		return pregunta.executeQuery(consulta);
	}

	public void ingresarNuevaTuplaDeUnaColumna(String tabla, String idTabla,
			String columnaUno, String valorUno) throws SQLException {
		// System.out.println("INSERT INTO "+tabla+"
		// ("+idTabla+","+columnaUno+") VALUES(NULL,'"+valorUno+"')");
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + idTabla + ","
				+ columnaUno + ") VALUES(NULL,'" + valorUno + "')");
	}

	public void ingresarNuevaTuplaDeDosColumnas(String tabla, String idTabla,
			String columnaUno, String columnaDos, String valorUno,
			String valorDos) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + idTabla + ","
				+ columnaUno + "," + columnaDos + ") VALUES(NULL,'" + valorUno
				+ "','" + valorDos + "')");
	}

	public void ingresarNuevaTuplaDeTresColumnas(String tabla, String idTabla,
			String columnaUno, String columnaDos, String columnaTres,
			String valorUno, String valorDos, String valorTres)
			throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + idTabla + ","
				+ columnaUno + "," + columnaDos + "," + columnaTres
				+ ") VALUES(NULL,'" + valorUno + "','" + valorDos + "','"
				+ valorTres + "')");
	}

	public void ingresarNuevaTuplaDeCuatroColumnas(String tabla,
			String idTabla, String columnaUno, String columnaDos,
			String columnaTres, String columnaCuatro, String valorUno,
			String valorDos, String valorTres, String valorCuatro)
			throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + idTabla + ","
				+ columnaUno + "," + columnaDos + "," + columnaTres + ","
				+ columnaCuatro + ") VALUES(NULL,'" + valorUno + "','"
				+ valorDos + "','" + valorTres + "','" + valorCuatro + "')");

	}

	public void ingresarNuevaTuplaDeCincoColumnas(String tabla, String idTabla,
			String columnaUno, String columnaDos, String columnaTres,
			String columnaCuatro, String columnaCinco, String valorUno,
			String valorDos, String valorTres, String valorCuatro,
			String valorCinco) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + idTabla + ","
				+ columnaUno + "," + columnaDos + "," + columnaTres + ","
				+ columnaCuatro + "," + columnaCinco + ") VALUES(NULL,'"
				+ valorUno + "','" + valorDos + "','" + valorTres + "','"
				+ valorCuatro + "','" + valorCinco + "')");

	}

	public void ingresarNuevaTuplaDeDosColumnasEnTablasRelacionadas(
			String tabla, String columnaUno, String columnaDos,
			String valorUno, String valorDos) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + columnaUno + ","
				+ columnaDos + ") VALUES('" + valorUno + "','" + valorDos
				+ "')");
	}

	public void ingresarNuevaTuplaDeTresColumnasEnTablasRelacionadas(
			String tabla, String columnaUno, String columnaDos,
			String columnaTres, String valorUno, String valorDos,
			String valorTres) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + columnaUno + ","
				+ columnaDos + "," + columnaTres + ") VALUES('" + valorUno
				+ "','" + valorDos + "','" + valorTres + "')");
	}

	public void ingresarNuevaTuplaDeTresColumnasIntEnTablasRelacionadas(
			String tabla, String columnaUno, String columnaDos,
			String columnaTres, int valorUno, int valorDos,
			int valorTres) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + columnaUno + ","
				+ columnaDos + "," + columnaTres + ") VALUES('" + valorUno
				+ "','" + valorDos + "','" + valorTres + "')");
	}

	public void ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas(
			String tabla, String columnaUno, String columnaDos,
			String columnaTres, String columnaCuatro, int valorUno, int valorDos,
			int valorTres, int valorCuatro) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("INSERT INTO " + tabla + "(" + columnaUno + ","
				+ columnaDos + "," + columnaTres + "," + columnaCuatro+") VALUES('" + valorUno
				+ "','" + valorDos + "','" + valorTres + "','" + valorCuatro+"')");
	}

	public void borrarTuplaCondicionUnAtributo(String tabla,
			String atributoUno, String valorUno) throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("DELETE FROM " + tabla + " WHERE " + atributoUno
				+ "='" + valorUno + "'");

	}

	public void actualizarTuplaDeUnaColumna(String tabla, String columnaUno,
			String valorUno, String atributoCondicion, int valorCondicion)
			throws SQLException {
		Statement pregunta;
		pregunta = (conn).createStatement();
		pregunta.executeUpdate("UPDATE " + tabla + " SET " + columnaUno + "='"
				+ valorUno + "' WHERE " + atributoCondicion + "='"
				+ valorCondicion + "'");
	}

}
