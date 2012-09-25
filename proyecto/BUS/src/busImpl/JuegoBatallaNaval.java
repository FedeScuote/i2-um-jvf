package busImpl;

import java.rmi.RemoteException;

import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval implements ServiciosBatallaNaval {

	private Tablero tableroJugador1;
	private Tablero tableroJugador2;


	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX,int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY)
			throws RemoteException, CoordenadasInvalidasException {

		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {

			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}

		}else{
			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador2.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador2.agregarCeldasDirY(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}
		}

	}

	private Boolean coordenadasEnDirY(int coordenadaInicialX,
			int coordenadaFinalX) {
		return coordenadaInicialX == coordenadaFinalX;
	}

	private Boolean coordenadasEnDirX(int coordenadaInicialY,
			int coordenadaFinalY) {
		return coordenadaInicialY == coordenadaFinalY;
	}


	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException {


		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			try {
				tableroJugador1.dispararACelda(coordenadaX,coordenadaY);
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}else{
			try {
				tableroJugador2.dispararACelda(coordenadaX,coordenadaY);
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}



	}

	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		return null; //a implementar

	}

}
