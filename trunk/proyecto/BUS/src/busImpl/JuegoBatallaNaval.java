package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import comm.CeldaVO;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import daoInterfaces.BatallaNavalDAO;
import daoInterfaces.DesafioDAO;
import daoInterfaces.PartidaDAO;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval{


	private static final String USUARIO_BOT_1 = "jhirata";
	private static final String USUARIO_BOT_2 = "vtuyare";
	private static final String USUARIO_BOT_3 = "fkono";
	private static final String USUARIO_BOT_4 = "jdiaz";

	private static final String SUBMARINO = "SUBMARINO";
	private static final String DESTRUCTORES = "DESTRUCTORES";
	private static final String CRUCEROS = "CRUCEROS";
	private static final String ACORAZADO = "ACORAZADO";
	private static final int CANTIDAD_INICIAL_SUBMARINO = 0;
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = 0;
	private static final int CANTIDAD_INICIAL_CRUCEROS = 0;
	private static final int CANTIDAD_INICIAL_ACORAZADO = 1;

	private boolean modoRobot;
	private Tablero tableroJugador1;
	private ArrayList<RegistroDisparo> listaDisparosAOponente1;
	private Tablero tableroJugador2;
	private ArrayList<RegistroDisparo> listaDisparosAOponente2;

	private static Logger log = Logger.getLogger(JuegoBatallaNaval.class);

	public JuegoBatallaNaval(boolean modoRobot, Tablero tableroJugador1, ArrayList<RegistroDisparo> listaDisparosAOponente1, Tablero tableroJugador2, ArrayList<RegistroDisparo> listaDisparosAOponente2) {
		super();
		this.modoRobot = modoRobot;
		this.tableroJugador1 = tableroJugador1;
		this.listaDisparosAOponente1 = listaDisparosAOponente1;
		this.tableroJugador2 = tableroJugador2;
		this.listaDisparosAOponente2 = listaDisparosAOponente2;
	}

	public boolean esta(UsuarioVO usuario){
		return tableroJugador1.getJugador().getUsuarioB().equals(usuario.getUsuarioB())||tableroJugador1.getJugador().getUsuarioB().equals(usuario.getUsuarioB());
	}


	public int[] distribucion(){
		int[] retorno = new int[4];
		retorno[0]=CANTIDAD_INICIAL_SUBMARINO;
		retorno[1]=CANTIDAD_INICIAL_DESTRUCTORES;
		retorno[2]=CANTIDAD_INICIAL_CRUCEROS;
		retorno[3]=CANTIDAD_INICIAL_ACORAZADO;
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_SUBMARINO);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_DESTRUCTORES);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_CRUCEROS);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_ACORAZADO);
		return retorno;
	}
	public static int[] distribucion1(){
		int[] retorno = new int[4];
		retorno[0]=CANTIDAD_INICIAL_SUBMARINO;
		retorno[1]=CANTIDAD_INICIAL_DESTRUCTORES;
		retorno[2]=CANTIDAD_INICIAL_CRUCEROS;
		retorno[3]=CANTIDAD_INICIAL_ACORAZADO;
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_SUBMARINO);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_DESTRUCTORES);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_CRUCEROS);
		log.debug("Cantidad de submaninos a agregar: "+CANTIDAD_INICIAL_ACORAZADO);
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
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador1.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalX-coordenadaInicialX));
					quitarBarcoStockJugador1(tipoBarco);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador1.agregarCeldasDirX(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX,tipoBarco);
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador1.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalY-coordenadaInicialY));
					quitarBarcoStockJugador1(tipoBarco);
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
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador2.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalX-coordenadaInicialX));
					quitarBarcoStockJugador2(tipoBarco);
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador2.agregarCeldasDirX(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX,tipoBarco);
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador2.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalY-coordenadaInicialY));
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
		BatallaNavalDAO daoBatallaNaval = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		if(tipoBarco.equals(SUBMARINO)){
			tableroJugador1.decrementarBarcosSubmarinoColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador1.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(DESTRUCTORES)){
			tableroJugador1.decrementarBarcosDestructoresColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador1.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(CRUCEROS)){
			tableroJugador1.decrementarBarcosCrucerosColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador1.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(ACORAZADO)){
			tableroJugador1.decrementarBarcosAcorazadoColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador1.getJugador().getUsuarioB());
		}
	}
	private void quitarBarcoStockJugador2(String tipoBarco) {
		if(tipoBarco.equals(SUBMARINO)){
			tableroJugador2.decrementarBarcosSubmarinoColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador2.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(DESTRUCTORES)){
			tableroJugador2.decrementarBarcosDestructoresColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador2.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(CRUCEROS)){
			tableroJugador2.decrementarBarcosCrucerosColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador2.getJugador().getUsuarioB());
		}else if(tipoBarco.equals(ACORAZADO)){
			tableroJugador2.decrementarBarcosAcorazadoColocados();
			log.debug("decremento barco: "+tipoBarco);
			log.debug("al usuario "+tableroJugador2.getJugador().getUsuarioB());
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


	public Estados disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException {
		Estados retorno=null;
		BatallaNavalDAO bnDAO = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		int idJugador1=usuario.getIdUsuario();
		int idJugador2=daoPartida.oponente(usuario.getIdUsuario()).getIdUsuarioB();
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			try {
				retorno=tableroJugador2.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				RegistroDisparo registro= new RegistroDisparo(retorno,disparo);
				bnDAO.registrarDisparo(disparo, retorno, idJugador1, idPartida);
				if(this.modoRobot){
					//Se deberia esperar un tiempo aleatorio relativamente corto para no ocacionar sospechas
					Disparo dis= proximoDisparo("");
					//por construccion
					UsuarioVO usuarioRobot=new UsuarioVO(this.tableroJugador2.getJugador().getUsuarioB());
					usuarioRobot.setIdUsuario(this.tableroJugador2.getJugador().getIdUsuarioB());
					disparar(usuarioRobot,dis.getFila(),dis.getColumna());
				}
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}else{
			try {
				retorno=tableroJugador1.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				RegistroDisparo registro= new RegistroDisparo(retorno,disparo);
				bnDAO.registrarDisparo(disparo, retorno, idJugador1, idPartida);
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		}
		return retorno;
	}

	public boolean gane(UsuarioVO usuario){
		boolean retorno=false;
		int cantHundidos=0;
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			for(int i=0;i<listaDisparosAOponente1.size();i++){
				if(listaDisparosAOponente1.get(i).getResultado()==Estados.HUNDIDO){
					cantHundidos++;
				}
			}
			retorno=CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidos;
		}else{
			for(int i=0;i<listaDisparosAOponente2.size();i++){
				if(listaDisparosAOponente2.get(i).getResultado()==Estados.HUNDIDO){
					cantHundidos++;
				}
			}
			retorno=CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidos;
		}
		return retorno;
	}

	public boolean perdi(UsuarioVO usuario){
		boolean retorno=false;
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			retorno=tableroJugador1.getCantBarcosAcorazado()+tableroJugador1.getCantBarcosCruceros()+tableroJugador1.getCantBarcosDestructores()+tableroJugador1.getCantBarcosSubmarino()==0;
		}else{
			retorno=tableroJugador2.getCantBarcosAcorazado()+tableroJugador2.getCantBarcosCruceros()+tableroJugador2.getCantBarcosDestructores()+tableroJugador2.getCantBarcosSubmarino()==0;
		}
		return retorno;
	}

	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException{
		TableroVO nuevo = new TableroVO();
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			for(int i=0; i<tableroJugador2.tabla.length;i++){
				for(int j=0;j<tableroJugador2.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					if(tableroJugador2.tabla[i][j].estaOcupada()){
						celda.setAgua();
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
					if(tableroJugador1.tabla[i][j].estaOcupada()){
						celda.setAgua();
						nuevo.getTabla()[i][j]=celda;
					}else{
						celda.setEstado(tableroJugador1.tabla[i][j].estado);
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

	public Disparo proximoDisparo1() throws RemoteException {
		Disparo nuevo = new Disparo();
		boolean termine = false;
		int coordenadaX = 0;
		int coordenadaY = 0;
		while (!termine) {
			coordenadaX = (int) (Math.random() * 10);
			coordenadaY = (int) (Math.random() * 10);
			if (coordenadaX<10&&coordenadaY<10&&(tableroJugador1.tabla[coordenadaX][coordenadaY].estaVacio()||tableroJugador1.tabla[coordenadaX][coordenadaY].estaOcupada())){
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
				nuevo=proximoDisparo1();
				if(((nuevo.getFila()%2==0)&&(nuevo.getColumna()%2!=0))||((nuevo.getFila()%2!=0)&&(nuevo.getColumna()%2==0))){
					termine=true;
				}
			}else{
				termine=true;
				nuevo=proximoDisparo1();
			}

		}
		return nuevo;

	}

	private Disparo proximoDisparoSinSubNiDes() throws RemoteException {
		boolean termine=false;
		Disparo nuevo = new Disparo();
		while(!termine){
			if(Math.random()>=0.2){
				nuevo=proximoDisparo1();
				if((nuevo.getFila()==nuevo.getColumna())||(nuevo.getFila()+3==nuevo.getColumna())||(nuevo.getColumna()+3==nuevo.getFila())||(nuevo.getFila()+6==nuevo.getColumna())||(nuevo.getColumna()+6==nuevo.getFila())||(nuevo.getFila()+9==nuevo.getColumna())||(nuevo.getColumna()+9==nuevo.getFila())){
					termine=true;
				}
			}else{
				termine=true;
				nuevo=proximoDisparo1();
			}

		}
		return nuevo;
	}

	public boolean puedoIrHaciaLaDerecha(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() + 1 < tableroJugador2.tabla.length
				&& (tableroJugador2.tabla[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() + 1].estaVacio()|| tableroJugador2.tabla[registro.getDisparo().getFila()][registro
						                                                                             						.getDisparo().getColumna() + 1].estaOcupada())) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaLaIzquierda(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() - 1 >= 0
				&& (tableroJugador1.tabla[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() - 1].estaVacio()||tableroJugador1.tabla[registro.getDisparo().getFila()][registro
						                                                                            						.getDisparo().getColumna() - 1].estaOcupada())) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaAbajo(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getFila() + 1 < tableroJugador1.tabla.length
				&& (tableroJugador1.tabla[registro.getDisparo().getFila() + 1][registro
						.getDisparo().getColumna()].estaVacio()||tableroJugador1.tabla[registro.getDisparo().getFila() + 1][registro
						                                                                            						.getDisparo().getColumna()].estaOcupada())) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaArriba(RegistroDisparo registro) {
		boolean retorno = false;
		if (registro.getDisparo().getFila() - 1 >= 0
				&& (tableroJugador1.tabla[registro.getDisparo().getFila() - 1][registro
						.getDisparo().getColumna()].estaVacio()||tableroJugador1.tabla[registro.getDisparo().getFila() - 1][registro
						                                                                            						.getDisparo().getColumna()].estaOcupada())) {
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

				nuevo = proximoDisparo1();


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
			nuevo =proximoDisparo1();
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


	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) {
		if (tableroJugador1.getJugador().getIdUsuarioB() == usuario.getIdUsuario()) {
			return listaDisparosAOponente1;
		}else{
			return listaDisparosAOponente2;
		}
	}


	private static BatallaNavalDAO getBattallaNavalDAO() {
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

	private static PartidaDAO getPartidaDAO() {
		try {
			return (PartidaDAO) Class.forName("daoImpl.PartidaDAODB")
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

	private Tablero generarTablero(Tablero tableroInicial,ArrayList<RegistroDisparo> listaDeDisparos){
		int i=0;
		while(i<listaDeDisparos.size()){
			try {
				tableroInicial.dispararACelda(listaDeDisparos.get(i).getDisparo().getFila(), listaDeDisparos.get(i).getDisparo().getColumna());
			} catch (CoordenadasCeldasInvalidasException e) {
				e.printStackTrace();
			}
		}
		return tableroInicial;
	}

	public static JuegoBatallaNaval crearJuegoBN(UsuarioVO usuario, boolean modo){
		BatallaNavalDAO daoBatallaNaval = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		int cantHundidosJ1=0;
		int cantHundidosJ2=0;
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		int idJugador1=usuario.getIdUsuario();
		int idJugador2=daoPartida.oponente(usuario.getIdUsuario()).getIdUsuarioB();
		Tablero tableroInicialJugador1=daoBatallaNaval.getTablero(idPartida, idJugador1);
		Tablero tableroInicialJugador2=daoBatallaNaval.getTablero(idPartida, idJugador2);
		modo=JuegoBatallaNaval.esBot(daoPartida.oponente(usuario.getIdUsuario()).getUsuarioB());
		ArrayList<RegistroDisparo> listaJugador1 =daoBatallaNaval.getListaDeTiros(idPartida, idJugador1);
		for(int i=0;i<listaJugador1.size();i++){
			log.debug(listaJugador1.get(i).getDisparo().getFila());
			log.debug(listaJugador1.get(i).getDisparo().getColumna());
			log.debug(listaJugador1.get(i).getResultado());
			if(listaJugador1.get(i).getResultado()==Estados.HUNDIDO){
				cantHundidosJ1++;
			}
		}
		ArrayList<RegistroDisparo> listaJugador2 =daoBatallaNaval.getListaDeTiros(idPartida, idJugador2);
		for(int i=0;i<listaJugador2.size();i++){
			log.debug(listaJugador2.get(i).getDisparo().getFila());
			log.debug(listaJugador2.get(i).getDisparo().getColumna());
			log.debug(listaJugador2.get(i).getResultado());
			if(listaJugador2.get(i).getResultado()==Estados.HUNDIDO){
				cantHundidosJ2++;
			}
		}
		boolean ganeJ1=CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidosJ1;
		boolean ganeJ2=CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidosJ2;
		tableroInicialJugador1.generarTablero(listaJugador2);
		tableroInicialJugador2.generarTablero(listaJugador1);
		boolean turno1=tableroInicialJugador1.isMiTurno();
		boolean turno2=tableroInicialJugador2.isMiTurno();
		if(listaJugador1.size()!=listaJugador2.size()){
			if((listaJugador1.size()>listaJugador2.size())){
				turno2=true;
				turno1=false;
			}else{
				turno1=true;
				turno2=false;
			}
		}
		if(ganeJ1||ganeJ2){
			turno1=false;
			turno2=false;
		}
		tableroInicialJugador1.setMiTurno(turno1);
		tableroInicialJugador2.setMiTurno(turno2);
		JuegoBatallaNaval juego = new JuegoBatallaNaval(modo,tableroInicialJugador1,listaJugador1,tableroInicialJugador2,listaJugador2);
		return juego;
	}

	private static boolean esBot(String usuario) {
		return usuario.equals(USUARIO_BOT_1)||usuario.equals(USUARIO_BOT_2)||usuario.equals(USUARIO_BOT_3)||usuario.equals(USUARIO_BOT_4);
	}

	public static void main(String[] args) {
		UsuarioVO nuevo= new UsuarioVO("fscouteguazza");
		nuevo.setIdUsuario(3);
		JuegoBatallaNaval juego = crearJuegoBN(nuevo, true);

	}


}
