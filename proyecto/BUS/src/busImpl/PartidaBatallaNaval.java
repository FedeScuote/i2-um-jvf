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
		juego.agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
	}


	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException{
		try {
			juego.disparar(usuario, coordenadaX, coordenadaY);
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
		return perdi(usuario);
	}

	public boolean gane(UsuarioVO usuario){
		return gane(usuario);
	}


	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) {
		return juego.obtenerListaDisparos(usuario);
	}

}
