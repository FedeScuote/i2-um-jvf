package daoImpl;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import busImpl.Desafio;
import busImpl.Ranking;
import busImpl.Usuario;
import conexion.Conexion;

import daoInterfaces.DesafioDAO;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;

public class DesafioDAODB implements DesafioDAO {
	private static Logger logger = Logger.getLogger(DesafioDAODB.class);
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

	//devuelve la idDesafio de BatallaNaval
	public int crearDesafio(String usuario, int monto) {
		UsuarioDAODB ud=new UsuarioDAODB();
		Usuario u;
		Conexion c=new Conexion();
		int idDesafio=0;
		int idUsuario=0;
		int idJuego=1; //Batalla Naval
		int usuarioGanadorD=0; //El cero representa a ningun usuario

		try {
			u = ud.findByName(usuario);
			idUsuario=u.getIdUsuarioB();
			this.getDesafiosUsuariosDisponibleBatallaNaval(idUsuario);
			logger.info("Ya existe desafio del usuario: "+usuario);

		} catch (NotDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoHayDesafioException e) {
			//ingreso en desafios
			try {
				c.ingresarNuevaTuplaDeTresColumnas2("desafios", "idDesafio", "monto", "fechaHoraInicioD", "estadoD", monto, "now()", "En hora");
				ResultSet r=c.devolverResutado("SELECT MAX(idDesafio)FROM desafios");
				r.next();
				idDesafio=r.getInt("MAX(idDesafio)");
				//ingreso en la relacion usuarios_has_juegos_desafios
				c.ingresarNuevaTuplaDeCuatroColumnasIntEnTablasRelacionadas("usuarios_has_juegos_desafios", "juegos_idJuego", "desafios_idDesafio", "usuarios_idUsuario", "usuarioGanadorD", 1, idDesafio, idUsuario, usuarioGanadorD);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return idDesafio;
	}



	public ArrayList getDesafiosUsuariosDisponibleBatallaNaval(int idUser)throws NoHayDesafioException {
ArrayList a = new ArrayList();

Conexion c = new Conexion();

try {

	ResultSet resultado = c
			.devolverResutado("SELECT idDesafio, monto, fechaHoraInicioD, estadoD, usuarios_idusuario FROM desafios,usuarios_has_juegos_desafios WHERE estadoD='En hora' AND idDesafio=desafios_idDesafio AND juegos_idJuego='1' AND usuarios_idusuario='"+idUser+"'");
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

	public boolean aceptaronDesafio(int idUsuario) {

		Conexion c=new Conexion();
		try {
			ResultSet r=c.devolverResutado("SELECT estadoD FROM desafios,usuarios_has_juegos_desafios WHERE idDesafio=desafios_idDesafio AND usuarios_idusuario='"+idUsuario+"' AND estadoD='En curso'");
			r.next();
			if(r.first()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}



	}








}
