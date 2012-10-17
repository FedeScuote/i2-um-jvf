package bn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;




public class CopyOfPartidaBatallaNavalImpl implements BatallaNaval {

	private String idPartida;
	private BatallaNavalImpl juego;
	private static final String SUBMARINO = "SUBMARINO";
	private static final String DESTRUCTORES = "DESTRUCTORES";
	private static final String CRUCEROS = "CRUCEROS";
	private static final String ACORAZADO = "ACORAZADO";

	public Estados disparar(String idPartida, Disparo disparo) throws RemoteException {
		return juego.disparar(disparo.getFila(), disparo.getColumna());
	}

	public boolean ganaste(String idPartida) throws RemoteException {
		return juego.ganaste(idPartida);
	}

	public String idJugador() throws RemoteException {
		return "JVF";
	}

	public String iniciarPartida(String idJugador, int[] distribucion) throws RemoteException {
		juego = new BatallaNavalImpl(idJugador, distribucion);
		this.idPartida="1";
		return (this.idPartida)+"";
	}

	public boolean perdiste(String idPartida) throws RemoteException{
		return juego.perdiste(idPartida);
	}

	public Disparo proximoDisparo(String idPartida) throws RemoteException {
		return juego.proximoDisparo1(idPartida);
	}

	public void registrarDisparo(Disparo disparo, Estados resultado)
			throws RemoteException {
		juego.registrarDisparo(disparo, resultado);
	}

	public ArrayList<RegistroDisparo> lista() throws RemoteException {
		return juego.getListaDisparosAOponente();
	}



}
