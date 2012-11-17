package busImpl.batallaNaval;

import java.rmi.RemoteException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import busImpl.Estados;
import busImpl.Usuario;
import comm.CeldaVO;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import daoInterfaces.BatallaNavalDAO;
import daoInterfaces.DesafioDAO;
import daoInterfaces.PartidaDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval{

	private static final String SUBMARINO = "SUBMARINO";
	private static final String DESTRUCTORES = "DESTRUCTORES";
	private static final String CRUCEROS = "CRUCEROS";
	private static final String ACORAZADO = "ACORAZADO";
	private static final int CANTIDAD_INICIAL_SUBMARINO = 0;
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = 0;
	private static final int CANTIDAD_INICIAL_CRUCEROS = 2;
	private static final int CANTIDAD_INICIAL_ACORAZADO = 2;

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
	public JuegoBatallaNaval(Usuario jugador1,Usuario jugador2) {
		super();
		this.tableroJugador1 = new Tablero(jugador1);
		this.listaDisparosAOponente1 = new ArrayList<RegistroDisparo>();
		this.tableroJugador2 = new Tablero(jugador2);
		this.listaDisparosAOponente2 = new ArrayList<RegistroDisparo>();
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

			if (coordenadasEnDirY(coordenadaInicialX, coordenadaFinalX)) {
				try {
					tableroJugador1.agregarCeldasDirY(coordenadaInicialX,coordenadaInicialY, coordenadaFinalY,tipoBarco);
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador1.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalX-coordenadaInicialX));
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else if(coordenadasEnDirX(coordenadaInicialY, coordenadaFinalY)){
				try {
					tableroJugador1.agregarCeldasDirX(coordenadaInicialY,coordenadaInicialX, coordenadaFinalX,tipoBarco);
					log.debug("Agrego barco tipo tipo: "+tipoBarco+"al usuario"+tableroJugador1.getJugador().getUsuarioB());
					log.debug("Largo barco: "+(coordenadaFinalY-coordenadaInicialY));
				} catch (CoordenadasCeldasInvalidasException e) {
					throw new CoordenadasInvalidasException();
				}
			}else{
				throw new CoordenadasInvalidasException();
			}


	}

	public boolean inicioPar(){
		return tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0&&tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0;
	}

	private void quitarBarcoStockJugador1(String tipoBarco) {
		BatallaNavalDAO daoBatallaNaval = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(tableroJugador1.getJugador().getIdUsuarioB());
		if(tableroJugador1.getCantBarcosSubmarinoColocados()==0&&tableroJugador1.getCantBarcosCrucerosColocados()==0&&tableroJugador1.getCantBarcosDestructoresColocados()==0&&tableroJugador1.getCantBarcosAcorazadoColocados()==0&&tableroJugador2.getCantBarcosSubmarinoColocados()==0&&tableroJugador2.getCantBarcosDestructoresColocados()==0&&tableroJugador2.getCantBarcosCrucerosColocados()==0&&tableroJugador2.getCantBarcosAcorazadoColocados()==0){
			tableroJugador1.setMiTurno(true);
			daoBatallaNaval.actualizarTablero(idPartida, tableroJugador1.getJugador().getUsuarioB(),tableroJugador1.isMiTurno(), tableroJugador1.getCantBarcosSubmarino(), tableroJugador1.getCantBarcosDestructores(), tableroJugador1.getCantBarcosCruceros(), tableroJugador1.getCantBarcosAcorazado(), tableroJugador1.getCantBarcosSubmarinoColocados(), tableroJugador1.getCantBarcosDestructoresColocados(), tableroJugador1.getCantBarcosCrucerosColocados(), tableroJugador1.getCantBarcosAcorazadoColocados());
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
			try {
				if(this.modoRobot){
					log.debug("Juego contra robot, seteo mi turno en false y luego disparo");
					//bnDAO.actualizarTurno(tableroJugador1.getJugador().getIdUsuarioB(),false);
				}
				retorno=tableroJugador2.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				if(tableroJugador1.isMiTurno()){
					bnDAO.registrarDisparo(disparo, retorno, idJugador1, idPartida);
				}
				if(this.modoRobot&&tableroJugador1.isMiTurno()){
					Disparo dis= proximoDisparo("");
					log.debug("X="+dis.getFila());
					log.debug("Y="+dis.getColumna());
					Estados retorno1;
					retorno1=tableroJugador1.dispararACelda(dis.getFila(),dis.getColumna());
					bnDAO.registrarDisparo(dis, retorno1, this.tableroJugador2.getJugador().getIdUsuarioB(), idPartida);
					log.debug("el robot dispara, entonces me vuelvo a poner turno en true");
					//bnDAO.actualizarTurno(tableroJugador1.getJugador().getIdUsuarioB(),true);
				}
			} catch (CoordenadasCeldasInvalidasException e) {
				throw new CoordenadasInvalidasException();
			}
		return retorno;
	}

	public boolean gane(UsuarioVO usuario){
		boolean retorno=false;
		int cantHundidos=0;
			for(int i=0;i<listaDisparosAOponente1.size();i++){
				if(listaDisparosAOponente1.get(i).getResultado()==Estados.HUNDIDO){
					cantHundidos++;
				}
			}
			retorno=CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidos;

		return retorno;
	}

	public boolean perdi(UsuarioVO usuario){
		boolean retorno=false;
		retorno=tableroJugador1.getCantBarcosAcorazado()+tableroJugador1.getCantBarcosCruceros()+tableroJugador1.getCantBarcosDestructores()+tableroJugador1.getCantBarcosSubmarino()==0;
		return retorno;
	}

	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException{
		TableroVO nuevo = new TableroVO();
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
		return nuevo;
	}

	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		TableroVO nuevo = new TableroVO();
			for(int i=0; i<tableroJugador1.tabla.length;i++){
				for(int j=0;j<tableroJugador1.tabla.length;j++){
					CeldaVO celda= new CeldaVO();
					celda.setEstado(tableroJugador1.tabla[i][j].estado);
					celda.setId(tableroJugador1.tabla[i][j].getId());
					nuevo.getTabla()[i][j]=celda;
				}
			}

		return nuevo;
	}

	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException{
		return tableroJugador1.isMiTurno();
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
	public Disparo proximoDisparoOptimo() throws RemoteException {
		ArrayList<Disparo> posiblesDisparos = new ArrayList<Disparo>();
		for(int i=0;i<tableroJugador1.getTabla().length;i++){
			for(int j=0;j<tableroJugador1.getTabla().length;j++){
				if ((tableroJugador1.tabla[i][j].estaVacio()||tableroJugador1.tabla[i][j].estaOcupada())){
					Disparo nuevo = new Disparo();
					nuevo.setFila(i);
					nuevo.setColumna(j);
					posiblesDisparos.add(nuevo);
				}
			}
		}
		int nroDisparo = (int) (Math.random() * posiblesDisparos.size());
		return posiblesDisparos.get(nroDisparo);
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
		return listaDisparosAOponente2.get(primerTocadoLuegoDeHundir()).getDisparo();
	}

	public RegistroDisparo obtenerPrimerTocadoLHR() {
		return listaDisparosAOponente2.get(primerTocadoLuegoDeHundir());
	}

	public Disparo proximoDisparo(String idPartida) throws RemoteException {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(-1);
		nuevo.setFila(-1);
		if (!tengoLugarADisparar()) {
				nuevo = proximoDisparoOptimo();
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
			nuevo =proximoDisparoOptimo();
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
			return (BatallaNavalDAO) Class.forName("daoImpl.BatallaNavalDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static UsuarioDAO getUsuarioDAO() {
		try {
			return (UsuarioDAO) Class.forName("daoImpl.UsuarioDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static PartidaDAO getPartidaDAO() {
		try {
			return (PartidaDAO) Class.forName("daoImpl.PartidaDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JuegoBatallaNaval crearJuegoBN(UsuarioVO usuario){
		BatallaNavalDAO daoBatallaNaval = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		UsuarioDAO daoUsuario =getUsuarioDAO();
		int cantHundidosJ1=0;
		int cantHundidosJ2=0;
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		int idJugador1=usuario.getIdUsuario();
		int idJugador2=daoPartida.oponente(usuario.getIdUsuario()).getIdUsuarioB();
		Tablero tableroInicialJugador1=daoBatallaNaval.getTablero(idPartida, idJugador1);
		Tablero tableroInicialJugador2=daoBatallaNaval.getTablero(idPartida, idJugador2);
		boolean modo=daoUsuario.esUsuarioVirtual(daoPartida.oponente(usuario.getIdUsuario()).getUsuarioB());
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
//		if(ganeJ1||ganeJ2){
//			turno1=false;
//			turno2=false;
//		}

		tableroInicialJugador1.setMiTurno(turno1);
		tableroInicialJugador2.setMiTurno(turno2);
		JuegoBatallaNaval juego = new JuegoBatallaNaval(modo,tableroInicialJugador1,listaJugador1,tableroInicialJugador2,listaJugador2);
		return juego;
	}


	public static void main(String[] args) {
		UsuarioVO nuevo= new UsuarioVO("fscouteguazza");
		nuevo.setIdUsuario(3);
		JuegoBatallaNaval juego = crearJuegoBN(nuevo);

	}


}
