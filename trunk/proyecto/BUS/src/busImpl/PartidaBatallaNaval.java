package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import comm.ServicioPartidaBatallaNaval;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.BatallaNavalDAO;

public class PartidaBatallaNaval{

	private int idPartida;
	private JuegoBatallaNaval juego;



	public PartidaBatallaNaval(int idPartida, JuegoBatallaNaval juego) {
		super();
		this.idPartida = idPartida;
		this.juego = juego;
	}


	public int getIdPartida() {
		return idPartida;
	}


	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException {
		BatallaNavalDAO dao = getDAO();
		juego.agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
//		dao.regstrarTablero(this.juego.getTableroJugador1(), this.idPartida);
//		dao.regstrarTablero(this.juego.getTableroJugador2(), this.idPartida);

	}


	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException{
		try {
			BatallaNavalDAO dao = getDAO();
			juego.disparar(usuario, coordenadaX, coordenadaY);
//			dao.regstrarTablero(this.juego.getTableroJugador1(), this.idPartida);
//			dao.regstrarTablero(this.juego.getTableroJugador2(), this.idPartida);
		} catch (NoInicioJuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException {
		return juego.esMiTurno(usuario);
	}




	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		return juego.refrescarTablero(usuario);
	}

	public TableroVO refrescarTableOponente(UsuarioVO usuario) throws RemoteException {
		return juego.refrescarTableroOponente(usuario);
	}

	public boolean estaEnPartida(UsuarioVO usuario){
		return juego.esta(usuario);
	}

	public int[] distribucion(){
		return juego.distribucion();
	}

	public boolean perdi(UsuarioVO usuario){
		return juego.perdi(usuario);
	}

	public boolean gane(UsuarioVO usuario){
		return juego.gane(usuario);
	}


	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) {
		return juego.obtenerListaDisparos(usuario);
	}

	private static BatallaNavalDAO getDAO() {
		try {
			return (BatallaNavalDAO) Class.forName("daoImpl.BatallaNavalDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
