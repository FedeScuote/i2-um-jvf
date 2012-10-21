package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import busImpl.bot.JvfBotBatallaNaval;
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
		this.listaDisparosAOponente1=new ArrayList<RegistroDisparo>();
		this.listaDisparosAOponente2=new ArrayList<RegistroDisparo>();
		this.tableroJugador1 = new Tablero(jugador1);
		this.tableroJugador2 = new Tablero(jugador2);
		this.tableroJugador2.agregarCeldas(distribucion());
		this.tableroJugador1.setMiTurno(true);
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
//		if(tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0&&tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0){
//			tableroJugador2.setMiTurno(true);
//		}
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
//		if(tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0&&tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0){
//			tableroJugador1.setMiTurno(true);
//		}
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
				Disparo dis= proximoDisparo("");
				UsuarioVO usu=new UsuarioVO(this.tableroJugador2.getJugador().getUsuarioB());
				usu.setIdUsuario(this.tableroJugador2.getJugador().getIdUsuarioB());
				disparar(usu,dis.getFila(),dis.getColumna());
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
				this.tableroJugador1.setMiTurno(true);
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
					if(celda.estaOcupada()){
						nuevo.getTabla()[i][j]=celda;
					}else{
						celda.setEstado(tableroJugador2.tabla[i][j].estado);
						nuevo.getTabla()[i][j]=celda;
					}
				}
			}
		}else{
			for(int i=0; i<tableroJugador2.tabla.length;i++){
				for(int j=0;j<tableroJugador2.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					if(celda.estaOcupada()){
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
					celda.setId(tableroJugador1.tabla[i][j].getId());
					nuevo.getTabla()[i][j]=celda;
				}
			}
		}else{
			for(int i=0; i<tableroJugador2.tabla.length;i++){
				for(int j=0;j<tableroJugador2.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					celda.setEstado(tableroJugador2.tabla[i][j].estado);
					celda.setId(tableroJugador2.tabla[i][j].getId());
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

	private boolean tengoLugarADisparar() {
		boolean hayTocado = false;
		if (listaDisparosAOponente2.size() != 0) {
			hayTocado = primerTocadoLuegoDeHundir() != listaDisparosAOponente2
					.size();
		}
		return hayTocado;

	}

	public Disparo proximoDisparo1(String idPartida) throws RemoteException {
		Disparo nuevo = new Disparo();
		boolean termine = false;
		int coordenadaX = 0;
		int coordenadaY = 0;
		while (!termine) {
			coordenadaX = (int) (Math.random() * 10);
			coordenadaY = (int) (Math.random() * 10);
			if (coordenadaX<10&&coordenadaY<10&&tableroJugador2.tabla[coordenadaX][coordenadaY].estaVacio()||tableroJugador2.tabla[coordenadaX][coordenadaY].estaOcupada()) {
				termine = true;
			}
		}
		nuevo.setFila(coordenadaX);
		nuevo.setColumna(coordenadaY);
		return nuevo;
	}

	private Disparo proximoDisparoSinSubmarinos() throws RemoteException {
		boolean termine=false;
		Disparo nuevo = new Disparo();
		while(!termine){
			if(Math.random()>=0.2){
				nuevo=proximoDisparo1("");
				if(((nuevo.getFila()%2==0)&&(nuevo.getColumna()%2!=0))||((nuevo.getFila()%2!=0)&&(nuevo.getColumna()%2==0))){
					termine=true;
				}
			}else{
				termine=true;
				nuevo=proximoDisparo1("");
			}

		}
		return nuevo;

	}

	private Disparo proximoDisparoSinSubNiDes() throws RemoteException {
		boolean termine=false;
		Disparo nuevo = new Disparo();
		while(!termine){
			if(Math.random()>=0.2){
				nuevo=proximoDisparo1("");
				if((nuevo.getFila()==nuevo.getColumna())||(nuevo.getFila()+3==nuevo.getColumna())||(nuevo.getColumna()+3==nuevo.getFila())||(nuevo.getFila()+6==nuevo.getColumna())||(nuevo.getColumna()+6==nuevo.getFila())||(nuevo.getFila()+9==nuevo.getColumna())||(nuevo.getColumna()+9==nuevo.getFila())){
					termine=true;
				}
			}else{
				termine=true;
				nuevo=proximoDisparo1("");
			}

		}
		return nuevo;
	}

	public boolean puedoIrHaciaLaDerecha(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() + 1 < tableroJugador2.tabla.length
				&& tableroJugador2.tabla[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() + 1].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaLaIzquierda(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() - 1 >= 0
				&& tableroJugador2.tabla[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() - 1].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaAbajo(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getFila() + 1 < tableroJugador2.tabla.length
				&& tableroJugador2.tabla[registro.getDisparo().getFila() + 1][registro
						.getDisparo().getColumna()].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaArriba(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getFila() - 1 >= 0
				&& tableroJugador2.tabla[registro.getDisparo().getFila() - 1][registro
						.getDisparo().getColumna()].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public RegistroDisparo top() {
		return listaDisparosAOponente2.get(listaDisparosAOponente2.size() - 1);
	}

	public Disparo siguienteDerecho(RegistroDisparo registro) {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(registro.getDisparo().getColumna() + 1);
		nuevo.setFila(registro.getDisparo().getFila());
		return nuevo;
	}

	public Disparo siguienteIzquierdo(RegistroDisparo registro) {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(registro.getDisparo().getColumna() - 1);
		nuevo.setFila(registro.getDisparo().getFila());
		return nuevo;
	}

	public Disparo siguienteAbajo(RegistroDisparo registro) {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(registro.getDisparo().getColumna());
		nuevo.setFila(registro.getDisparo().getFila() + 1);
		return nuevo;
	}

	public Disparo siguienteArriba(RegistroDisparo registro) {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(registro.getDisparo().getColumna());
		nuevo.setFila(registro.getDisparo().getFila() - 1);
		return nuevo;
	}

	public Disparo obtenerPrimerTocadoLH() {
		return listaDisparosAOponente2.get(primerTocadoLuegoDeHundir())
				.getDisparo();
	}

	public RegistroDisparo obtenerPrimerTocadoLHR() {
		return listaDisparosAOponente2.get(primerTocadoLuegoDeHundir());
	}

	public Disparo proximoDisparo(String idPartida) throws RemoteException {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(-1);
		nuevo.setFila(-1);
		if (!tengoLugarADisparar()) {
			if(tableroJugador2.getCantBarcosSubmarino()==0){
				if(tableroJugador2.getCantBarcosDestructores()==0){
					nuevo=proximoDisparoSinSubNiDes();
				}else{
					nuevo=proximoDisparoSinSubmarinos();
				}
			}else{
				nuevo = proximoDisparo1(idPartida);
			}

		} else {
			if (primerTocadoLuegoDeHundir() == listaDisparosAOponente2.size() - 1) {
				if (puedoIrHaciaLaDerecha(top())) {
					nuevo = siguienteDerecho(top());
				} else if (puedoIrHaciaLaIzquierda(top())) {
					nuevo = siguienteIzquierdo(top());
				} else if (puedoIrHaciaAbajo(top())) {
					nuevo = siguienteAbajo(top());
				} else if (puedoIrHaciaArriba(top())) {
					nuevo = siguienteArriba(top());
				}
			} else {
				if (top().getResultado() == Estados.TOCADO) {
					if (obtenerPrimerTocadoLH().getFila() == top().getDisparo()
							.getFila()) {
						if (obtenerPrimerTocadoLH().getColumna() < top()
								.getDisparo().getColumna()) {
							if (puedoIrHaciaLaDerecha(top())) {
								nuevo = siguienteDerecho(top());
							} else if (puedoIrHaciaLaIzquierda(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteIzquierdo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}
						if (obtenerPrimerTocadoLH().getColumna() > top()
								.getDisparo().getColumna()) {
							if (puedoIrHaciaLaIzquierda(top())) {
								nuevo = siguienteIzquierdo(top());
							} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}

					} else {
						if (obtenerPrimerTocadoLH().getFila() < top()
								.getDisparo().getFila()) {
							if (puedoIrHaciaAbajo(top())) {
								nuevo = siguienteAbajo(top());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}
						if (obtenerPrimerTocadoLH().getFila() > top()
								.getDisparo().getFila()) {
							if (puedoIrHaciaArriba(top())) {
								nuevo = siguienteArriba(top());
							}
						}

					}

				} else {
					if (obtenerPrimerTocadoLH().getFila() == top().getDisparo()
							.getFila()) {
						if (obtenerPrimerTocadoLH().getColumna() < top()
								.getDisparo().getColumna()) {
							if (puedoIrHaciaLaIzquierda(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteIzquierdo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						} else {
							if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}
					} else {
						if (obtenerPrimerTocadoLH().getFila() < top()
								.getDisparo().getFila()) {
							if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}

					}

				}

			}

		}
		if(nuevo.getColumna()==-1){
			nuevo =proximoDisparo1(idPartida);
		}

		return nuevo;

	}

	private int ultimoDisparoQueHundio() {
		int i = listaDisparosAOponente2.size() - 1;
		while (i >= 0
				&& listaDisparosAOponente2.get(i).getResultado() != Estados.HUNDIDO) {
			i--;
		}
		if (i == -1) {
			i = 0;
		}
		return i;
	}

	private int primerTocadoLuegoDeHundir() {
		int i = ultimoDisparoQueHundio();
		while (i < listaDisparosAOponente2.size()
				&& listaDisparosAOponente2.get(i).getResultado() != Estados.TOCADO) {
			i++;
		}
		return i;
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
