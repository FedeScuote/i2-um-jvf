package comm;
import java.rmi.Remote;
import java.rmi.RemoteException;

import commExceptions.CoordenadasInvalidasException;

public interface ServiciosBatallaNaval extends Remote {
	//Metodo para agregar barcos al iniciar la partida.
	public void agregarBarco(int nroTablero, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY) throws RemoteException, CoordenadasInvalidasException;

	//Metodo para disparar
	public void disparar(int nroTablero, int coordenadaX,int coordenadaY) throws RemoteException, CoordenadasInvalidasException;

	//Metodo para refrescar imagen de matriz
	public TableroVO refrescarTablero(int nroTablero);



}
