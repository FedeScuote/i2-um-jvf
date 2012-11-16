package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import busImpl.Desafio;
import busImpl.Usuario;
import conexion.Conexion;
import daoInterfaces.DesafioDAOLudo;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import excepcionesB.NoTieneDesafioException;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaTieneOtroDesafioException;
import excepcionesD.DesafioTerminadoException;
import excepcionesD.NoExisteCreditoSuficiente;
import excepcionesD.NoExisteDesafioException;
import excepcionesB.NoExisteUsuarioException;

public class DesafioDAODBLudo implements DesafioDAOLudo {
	private static Logger logger = Logger.getLogger(DesafioDAODBLudo.class);

	public void aceptoDesafioLudo(int idUsuario, int idDesafio)
			throws YaTieneOtroDesafioException, NoHaySuficienteCreditoUsuarioException {

	}

	@SuppressWarnings("static-access")
	public void crearDesafioLudo(int idUsuario, int monto)throws NoHaySuficienteCreditoUsuarioException {
		logger.debug("Entro a crearDesafioLudo con parámetros de entrada idUsuario= "+ idUsuario + " monto= " + monto);
		UsuarioDAODB ud = new UsuarioDAODB();
		Usuario u = null;
		Conexion c = new Conexion();
		DesafioDAODB dd = new DesafioDAODB();
		int idDesafio = 0;
		int idJuego = 2; // Ludo
		int usuarioGanadorD = 0; // El cero representa a ningun usuario

		try {

			boolean creditoSuficiente;
			String usuario = ud.getUsuario(idUsuario);
			u = ud.findByName(usuario);
			int credito = u.getCreditoB();
			creditoSuficiente = ud.creditoSuficiente(credito, idUsuario);
			if (!creditoSuficiente) {
				throw new NoHaySuficienteCreditoUsuarioException();
			}
			logger.debug("Crédito suficiente!");

			this.getDesafiosUsuariosDisponibleLudo(idUsuario);
			logger.debug("Ya existe desafio del idUsuario: " + idUsuario);

		} catch (NoHayDesafioException e) {
			// ingreso en desafios
			try {
				logger.debug("primero ingreso en la tabla desafios");
				logger.debug("idDesafio= " + idDesafio);
				logger.debug("monto= " + monto);
				logger.debug("estado= En hora");

				c.ingresarNuevaTuplaDeTresColumnas2("desafios", "idDesafio",
						"monto", "fechaHoraInicioD", "estadoD", monto, "now()",
						"En hora");
				ResultSet r = c
						.devolverResutado("SELECT MAX(idDesafio)FROM desafios");
				r.next();
				idDesafio = r.getInt("MAX(idDesafio)");
				// ingreso en la relacion usuarios_has_juegos_desafios
				logger
						.debug("luego ingreso en la relación usuarios_has_juegos_desafios");
				logger.debug("idUsuario= " + idUsuario);
				logger.debug("juegos_idJuego= 2");
				logger.debug("desafios_idDesafio= " + idDesafio);
				logger.debug("usuarioGanadorD= " + usuarioGanadorD);

				c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas(
						"usuarios_has_juegos_desafios", "juegos_idJuego",
						"desafios_idDesafio", "usuarios_idUsuario",
						"usuarioGanadorD", 2, idDesafio, idUsuario,
						usuarioGanadorD);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (idDesafio == 0) {
			logger.debug("No se creo desafio");
		} else {
			logger.debug("idDesafio= " + idDesafio);
		}
		logger
				.debug("Me desconecto de la base de datos del método crearDesafios");
		c.disconnect();

	}

	public boolean inicioDesafioLudo(int idUsuario)
			throws NoTieneDesafioException {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList getDesafiosUsuariosDisponibleLudo(int idUser)throws NoHayDesafioException {
		logger.debug("Entro a getDesafiosUsuariosDisponibleLudo con parámetro de entrada idUsuario= "+ idUser);
		ArrayList a = new ArrayList();
		Conexion c = new Conexion();
		try {
			int desafios=0;
			ResultSet resultado = c.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='2' AND usuarios_idusuario='"+ idUser + "'");
			while (resultado.next()) {
				Desafio d = new Desafio();

				int idDesafio = resultado.getInt("idDesafio");
				int idUsuario = resultado.getInt("usuarios_idusuario");
				int monto = resultado.getInt("monto");
				Date fecha = resultado.getDate("fechaHoraInicioD");
				String estadoD = resultado.getString("estadoD");

				d.setIdDesafio(idDesafio);
				d.setMonto(monto);
				d.setFechaHoraInicioD(fecha);
				d.setEstado(estadoD);
				d.setIdUsuario(idUsuario);
				UsuarioDAODB ud = new UsuarioDAODB();
				String usuarioDesafio = ud.getUsuario(idUsuario);
				d.setUsuarioDesafio(usuarioDesafio);
				desafios++;

				a.add(d);
			}
			if(desafios==0){
				throw new NoHayDesafioException();
			}

		} catch (SQLException ex) {
			throw new NoHayDesafioException();
		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Me desconecto de la base de datos del método getDesafiosUsuariosDisponibleLudo");
		c.disconnect();
		return a;
	}

}
