package busImpl;

import java.rmi.RemoteException;

import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval implements ServiciosBatallaNaval {

	private Tablero tableroJugador1;
	private Tablero tableroJugador2;



	public Tablero getTableroJugador1() {
		return tableroJugador1;
	}

	public void setTableroJugador1(Tablero tableroJugador1) {
		this.tableroJugador1 = tableroJugador1;
	}

	public Tablero getTableroJugador2() {
		return tableroJugador2;
	}

	public void setTableroJugador2(Tablero tableroJugador2) {
		this.tableroJugador2 = tableroJugador2;
	}

	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX,int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY)
			throws RemoteException, CoordenadasInvalidasException {

		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {

			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY);
					quitarBarcoStockJugador1((coordenadaFinalY-coordenadaInicialY)+1);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX);
					quitarBarcoStockJugador1((coordenadaFinalY-coordenadaInicialY)+1);
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
					quitarBarcoStockJugador2((coordenadaFinalY-coordenadaInicialY)+1);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador2.agregarCeldasDirY(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX);
					quitarBarcoStockJugador2((coordenadaFinalY-coordenadaInicialY)+1);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}
		}

	}

	private void quitarBarcoStockJugador1(int largo) {
		if(largo==4){
			tableroJugador1.decrementarBarcosL4();
		}else if(largo==3){
			tableroJugador1.decrementarBarcosL3();
		}else if(largo==2){
			tableroJugador1.decrementarBarcosL2();
		}else if(largo==1){
			tableroJugador1.decrementarBarcosL1();
		}
		if(tableroJugador2.getCantBarcosL1()==0&&tableroJugador2.getCantBarcosL2()==0&&tableroJugador2.getCantBarcosL3()==0&&tableroJugador2.getCantBarcosL4()==4&&tableroJugador1.getCantBarcosL1()==0&&tableroJugador1.getCantBarcosL2()==0&&tableroJugador1.getCantBarcosL3()==0&&tableroJugador1.getCantBarcosL4()==4){
			tableroJugador2.setMiTurno(true);
		}
	}
	private void quitarBarcoStockJugador2(int largo) {
		if(largo==4){
			tableroJugador2.decrementarBarcosL4();
		}else if(largo==3){
			tableroJugador2.decrementarBarcosL3();
		}else if(largo==2){
			tableroJugador2.decrementarBarcosL2();
		}else if(largo==1){
			tableroJugador2.decrementarBarcosL1();
		}
		if(tableroJugador2.getCantBarcosL1()==0&&tableroJugador2.getCantBarcosL2()==0&&tableroJugador2.getCantBarcosL3()==0&&tableroJugador2.getCantBarcosL4()==4&&tableroJugador1.getCantBarcosL1()==0&&tableroJugador1.getCantBarcosL2()==0&&tableroJugador1.getCantBarcosL3()==0&&tableroJugador1.getCantBarcosL4()==4){
			tableroJugador1.setMiTurno(true);
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


	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException, NoInicioJuegoException {

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
		TableroVO nuevo = new TableroVO();
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			for(int i=0; i<tableroJugador1.tabla.length;i++){
				for(int j=0;j<tableroJugador1.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					celda.setEstado(tableroJugador1.tabla[i][j].estado);
					nuevo.getTabla()[i][j]=celda;
				}
			}


		}else{
			for(int i=0; i<tableroJugador2.tabla.length;i++){
				for(int j=0;j<tableroJugador2.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					celda.setEstado(tableroJugador2.tabla[i][j].estado);
					nuevo.getTabla()[i][j]=celda;
				}
			}

		}
		return nuevo;
	}

	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException{
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			return tableroJugador1.isMiTurno();
		}else{
			return tableroJugador2.isMiTurno();
		}


	}

}
