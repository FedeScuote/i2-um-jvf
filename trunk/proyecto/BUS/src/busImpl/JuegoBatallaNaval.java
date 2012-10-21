package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval{

	private static final String SUBMARINO = "SUBMARINO";
	private static final String DESTRUCTORES = "DESTRUCTORES";
	private static final String CRUCEROS = "CRUCEROS";
	private static final String ACORAZADO = "ACORAZADO";
	private static final int CANTIDAD_INICIAL_SUBMARINO = 3;
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = 2;
	private static final int CANTIDAD_INICIAL_CRUCEROS = 1;
	private static final int CANTIDAD_INICIAL_ACORAZADO = 1;

	private Tablero tableroJugador1;
	private ArrayList<RegistroDisparo> listaDisparosAOponente1;
	private Tablero tableroJugador2;
	private ArrayList<RegistroDisparo> listaDisparosAOponente2;


	public boolean esta(UsuarioVO usuario){
		return tableroJugador1.getJugador().getUsuarioB().equals(usuario.getUsuarioB())||tableroJugador1.getJugador().getUsuarioB().equals(usuario.getUsuarioB());
	}

	public JuegoBatallaNaval(Usuario jugador1, Usuario jugador2) {
		super();
		this.tableroJugador1 = new Tablero(jugador1);
		this.tableroJugador2 = new Tablero(jugador2);
	}

	public int[] distribucion(){
		int[] retorno = new int[4];
		retorno[0]=CANTIDAD_INICIAL_SUBMARINO;
		retorno[1]=CANTIDAD_INICIAL_DESTRUCTORES;
		retorno[2]=CANTIDAD_INICIAL_CRUCEROS;
		retorno[3]=CANTIDAD_INICIAL_ACORAZADO;
		return retorno;
	}

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

	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX,int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco)
			throws RemoteException, CoordenadasInvalidasException {

		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {

			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY,tipoBarco);
					quitarBarcoStockJugador1(tipoBarco);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador1.agregarCeldasDirX(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX,tipoBarco);
					quitarBarcoStockJugador1(tipoBarco   );
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}

		}else{
			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador2.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY,tipoBarco);
					quitarBarcoStockJugador2(tipoBarco);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador2.agregarCeldasDirX(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX,tipoBarco);
					quitarBarcoStockJugador2(tipoBarco);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}
		}

	}


	private void quitarBarcoStockJugador1(String tipoBarco) {
		if(tipoBarco.equals(SUBMARINO)){
			tableroJugador1.decrementarBarcosSubmarinoColocados();
		}else if(tipoBarco.equals(DESTRUCTORES)){
			tableroJugador1.decrementarBarcosDestructoresColocados();
		}else if(tipoBarco.equals(CRUCEROS)){
			tableroJugador1.decrementarBarcosCrucerosColocados();
		}else if(tipoBarco.equals(ACORAZADO)){
			tableroJugador1.decrementarBarcosAcorazadoColocados();
		}
		if(tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0&&tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0){
			tableroJugador2.setMiTurno(true);
		}
	}
	private void quitarBarcoStockJugador2(String tipoBarco) {
		if(tipoBarco.equals(SUBMARINO)){
			tableroJugador2.decrementarBarcosSubmarinoColocados();
		}else if(tipoBarco.equals(DESTRUCTORES)){
			tableroJugador2.decrementarBarcosDestructoresColocados();
		}else if(tipoBarco.equals(CRUCEROS)){
			tableroJugador2.decrementarBarcosCrucerosColocados();
		}else if(tipoBarco.equals(ACORAZADO)){
			tableroJugador2.decrementarBarcosAcorazadoColocados();
		}
		if(tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0&&tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0){
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
				Estados resultado=tableroJugador2.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				RegistroDisparo registro= new RegistroDisparo(resultado,disparo);
				this.listaDisparosAOponente1.add(registro);
				this.tableroJugador1.setMiTurno(false);
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}else{
			try {
				Estados resultado=tableroJugador1.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				RegistroDisparo registro= new RegistroDisparo(resultado,disparo);
				this.listaDisparosAOponente2.add(registro);
				this.tableroJugador2.setMiTurno(false);
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}
	}

	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException{
		TableroVO nuevo = new TableroVO();
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			for(int i=0; i<tableroJugador2.tabla.length;i++){
				for(int j=0;j<tableroJugador2.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					if(celda.estaDisparada()){
						nuevo.getTabla()[i][j]=celda;
					}else{
						celda.setEstado(tableroJugador2.tabla[i][j].estado);
						nuevo.getTabla()[i][j]=celda;
					}
				}
			}
		}else{
			for(int i=0; i<tableroJugador1.tabla.length;i++){
				for(int j=0;j<tableroJugador1.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					if(celda.estaDisparada()){
						nuevo.getTabla()[i][j]=celda;
					}else{
						celda.setEstado(tableroJugador2.tabla[i][j].estado);
						nuevo.getTabla()[i][j]=celda;
					}
				}
			}

		}
		return nuevo;
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

	public static void main(String[] args) {
		UsuarioVO jugador = new UsuarioVO("Yo");
		Usuario jugador1 = new Usuario();
		jugador1.setUsuarioB("Yo");
		Usuario jugador2 = new Usuario();
		jugador2.setUsuarioB("Oponente");
		JuegoBatallaNaval juego = new JuegoBatallaNaval(jugador1,jugador2);
		try {
			juego.agregarBarco(jugador, 5, 5, 5, 6, SUBMARINO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoordenadasInvalidasException e) {
			System.out.println("Coordenadas Invalidas!");
		}
	}

}
