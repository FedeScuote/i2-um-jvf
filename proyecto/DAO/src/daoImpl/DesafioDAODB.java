package daoImpl;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import busImpl.Desafio;
import busImpl.Ranking;
import conexion.Conexion;

import daoInterfaces.DesafioDAO;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesD.NoExisteUsuarioException;

public class DesafioDAODB implements DesafioDAO {

	private int idDesafio;
	private int monto;
	private Date fechaHoraInicioD;
	private String estado; // En hora, En hora-lleno, En curso, Atrasado,

	// Finalizado, Cancelado

	public int getIdDesafio() {
		return idDesafio;
	}

	public void setIdDesafio(int idDesafio) {
		this.idDesafio = idDesafio;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Date getFechaHoraInicioD() {
		return fechaHoraInicioD;
	}

	public void setFechaHoraInicioD(Date fechaHoraInicioD) {
		this.fechaHoraInicioD = fechaHoraInicioD;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ArrayList getDesafios() throws NoHayDesafioException {
		ArrayList a = new ArrayList();

		Conexion c = new Conexion();

		try {

			ResultSet resultado = c
					.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio");
			while (resultado.next()) {
				Desafio d = new Desafio();
				// cargo los datos en el objeto d de Desafio
				int idDesafio = resultado.getInt("idDesafio");
				int monto = resultado.getInt("monto");
				int idUsuario = resultado.getInt("usuarios_idusuario");
				Date fecha = resultado.getDate("fechaHoraInicioD");
				String estadoD = resultado.getString("estadoD");

				UsuarioDAODB ud = new UsuarioDAODB();
				String usuarioDesafio = ud.getUsuario(idUsuario);

				d.setUsuarioDesafio(usuarioDesafio);
				d.setIdDesafio(idDesafio);
				d.setMonto(monto);
				d.setFechaHoraInicioD(fecha);
				d.setEstado(estadoD);
				a.add(d);
			}
			// compruebo si hay desafios
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayDesafioException();

		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.disconnect();
		return a;
	}

	public ArrayList getDesafiosBatallaNaval() throws NoHayDesafioException {
		ArrayList a = new ArrayList();

		Conexion c = new Conexion();

		try {

			ResultSet resultado = c
					.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1' ");
			while (resultado.next()) {
				Desafio d = new Desafio();

				int idDesafio = resultado.getInt("idDesafio");
				int monto = resultado.getInt("monto");
				Date fecha = resultado.getDate("fechaHoraInicioD");
				String estadoD = resultado.getString("estadoD");

				d.setIdDesafio(idDesafio);
				d.setMonto(monto);
				d.setFechaHoraInicioD(fecha);
				d.setEstado(estadoD);
				a.add(d);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayDesafioException();

		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		}

		c.disconnect();
		return a;
	}

	public ArrayList getDesafiosUsuariosDisponibleBatallaNaval()
			throws NoHayDesafioException {
		ArrayList a = new ArrayList();

		Conexion c = new Conexion();

		try {

			ResultSet resultado = c
					.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1'  ");
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

				a.add(d);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayDesafioException();

		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.disconnect();
		return a;
	}

	public ArrayList getDesafiosUsuariosDisponibleLudo()
			throws NoHayDesafioException {
		ArrayList a = new ArrayList();

		Conexion c = new Conexion();

		try {

			ResultSet resultado = c
					.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='2'  ");
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

				UsuarioDAODB ud = new UsuarioDAODB();
				String usuarioDesafio = ud.getUsuario(idUsuario);
				d.setUsuarioDesafio(usuarioDesafio);

				a.add(d);
			}
			a.get(0);

		} catch (SQLException ex) {

			throw new NoHayDesafioException();

		} catch (IndexOutOfBoundsException i) {
			throw new NoHayDesafioException();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.disconnect();
		return a;
	}

	public ArrayList getDesafiosUsuariosDisponibleBackgammon()
	throws NoHayDesafioException {
ArrayList a = new ArrayList();


Conexion c = new Conexion();

try {

	ResultSet resultado = c
			.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='3'  ");
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

		UsuarioDAODB ud = new UsuarioDAODB();
		String usuarioDesafio = ud.getUsuario(idUsuario) ;
		d.setUsuarioDesafio(usuarioDesafio);

		a.add(d);
	}
	a.get(0);

} catch (SQLException ex) {

	throw new NoHayDesafioException();

} catch (IndexOutOfBoundsException i) {
	throw new NoHayDesafioException();
} catch (NoExisteUsuarioException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

c.disconnect();
return a;
}

	public void crearDesafio(String usuario, int monto) {
		// TODO Auto-generated method stub

	}





}
