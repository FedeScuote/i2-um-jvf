package comm;
import java.rmi.Remote;
import java.rmi.RemoteException;

import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;

public interface ServiciosBatallaNaval extends Remote {

	public void jugarBatallaNaval(UsuarioVO usuario) throws RemoteException;
	//Metodo para agregar barcos al iniciar la partida
	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException;

	//Metodo para disparar
	public void disparar(UsuarioVO usuario, int coordenadaX,int coordenadaY) throws RemoteException, CoordenadasInvalidasException, NoInicioJuegoException;

	//Metodo recibir distribucion
	public int[] distribucion(UsuarioVO usuario);

	//Metodo para refrescar imagen de matriz
	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException;

	//Metodo para indicar el turno de un jugador
	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException;

	///iniciar partida
	//Metodo para comenzar una partida a partir de un desafio!
	public void iniciarPartida(DesafioVO desafio, UsuarioVO desafiante) throws RemoteException;



}
