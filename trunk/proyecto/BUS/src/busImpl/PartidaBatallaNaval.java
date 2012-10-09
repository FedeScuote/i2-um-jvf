package busImpl;

import java.rmi.RemoteException;

import comm.ServicioPartidaBatallaNaval;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import commExceptions.NoSeEncuentraUsuarioException;

public class PartidaBatallaNaval implements ServiciosBatallaNaval {

	private int idPartida;
	private static Usuario jugador1;
	private static Usuario jugador2;
	private JuegoBatallaNaval juego;

	public int getIdPartida() {
		return idPartida;
	}


	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}


	public Usuario getJugador1() {
		return jugador1;
	}


	public void setJugador1(Usuario jugador1) {
		this.jugador1 = jugador1;
	}


	public Usuario getJugador2() {
		return jugador2;
	}


	public void setJugador2(Usuario jugador2) {
		this.jugador2 = jugador2;
	}




	public void jugarBatallaNaval(UsuarioVO usuario) throws RemoteException{
		try {
			Usuario u= Usuario.findByName(usuario.getUsuarioB());
			if(jugador1==null){
				jugador1=u;
			}else if(jugador2==null){
				jugador2=u;
			}else{
				PartidaBatallaNaval nuevo = new PartidaBatallaNaval();
				nuevo.juego= new JuegoBatallaNaval(jugador1,jugador2);
			}
		} catch (NoSeEncuentraUsuarioException e) {
		}
	}


	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException {
		juego.agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
	}


	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException, NoInicioJuegoException {
		juego.disparar(usuario, coordenadaX, coordenadaY);
	}


	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException {
		return juego.esMiTurno(usuario);
	}




	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		return juego.refrescarTablero(usuario);
	}






}
