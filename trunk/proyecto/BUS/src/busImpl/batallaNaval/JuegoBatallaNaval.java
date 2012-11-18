package busImpl.batallaNaval;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import busImpl.usuario.Usuario;
import comm.CeldaVO;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import daoInterfaces.BatallaNavalDAO;
import daoInterfaces.PartidaDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.CoordenadasCeldasInvalidasException;

public class JuegoBatallaNaval{

	private static ResourceBundle constante = ResourceBundle.getBundle("bus");
	private static final int CANTIDAD_INICIAL_SUBMARINO = Integer.parseInt(constante.getString("CANTIDAD_INICIAL_SUBMARINO"));
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = Integer.parseInt(constante.getString("CANTIDAD_INICIAL_DESTRUCTORES"));
	private static final int CANTIDAD_INICIAL_CRUCEROS = Integer.parseInt(constante.getString("CANTIDAD_INICIAL_CRUCEROS"));
	private static final int CANTIDAD_INICIAL_ACORAZADO = Integer.parseInt(constante.getString("CANTIDAD_INICIAL_ACORAZADO"));

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
		int idJugador1=usuario.getIdUsuario();
			try {
				if(this.modoRobot){
					log.debug("Juego contra robot, seteo mi turno en false y luego disparo");
				}
				retorno=tableroJugador2.dispararACelda(coordenadaX,coordenadaY);
				Disparo disparo=new Disparo();
				disparo.setFila(coordenadaX);
				disparo.setColumna(coordenadaY);
				if(tableroJugador1.isMiTurno()){
					bnDAO.registrarDisparo(disparo, retorno, idJugador1);
				}
				if(this.modoRobot&&tableroJugador1.isMiTurno()){
					Disparo dis= proximoDisparo("");
					log.debug("X="+dis.getFila());
					log.debug("Y="+dis.getColumna());
					Estados retorno1;
					retorno1=tableroJugador1.dispararACelda(dis.getFila(),dis.getColumna());
					bnDAO.registrarDisparo(dis, retorno1, this.tableroJugador2.getJugador().getIdUsuarioB());
					log.debug("el robot dispara, entonces me vuelvo a poner turno en true");
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
		if (registro.getDisparo().getColumna() + 1 < tableroJugador1.tabla.length&& (tableroJugador1.tabla[registro.getDisparo().getFila()][registro.getDisparo().getColumna() + 1].estaVacio()|| tableroJugador1.tabla[registro.getDisparo().getFila()][registro.getDisparo().getColumna() + 1].estaOcupada())) {
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
		log.debug("Usuario Robot");
		Disparo nuevo = new Disparo();
		nuevo.setColumna(-1);
		nuevo.setFila(-1);
		if (!tengoLugarADisparar()) {
				log.debug("No tengo lugar a disparar, disparo al azar con coordenadas:");
				nuevo = proximoDisparoOptimo();
		} else {
			if (primerTocadoLuegoDeHundir() == listaDisparosAOponente2.size() - 1) {
				log.debug("Tengo lugar a disparar y ultimo disparo toco por primera vez luego hundir");
				if (puedoIrHaciaLaDerecha(top())) {
					log.debug("Puedo ir a la derecha");
					nuevo = siguienteDerecho(top());
				} else if (puedoIrHaciaLaIzquierda(top())) {
					log.debug("No puedo ir a la derecha, si a la izq");
					nuevo = siguienteIzquierdo(top());
				} else if (puedoIrHaciaAbajo(top())) {
					log.debug("No puedo ir a la der, ni izq si abajo");
					nuevo = siguienteAbajo(top());
				} else if (puedoIrHaciaArriba(top())) {
					log.debug("No puedo ir a la der, ni izq, ni abajo si arriba");
					nuevo = siguienteArriba(top());
				}else{
					log.error("Algo no anda bien!!!!");
				}
			} else {
				if (top().getResultado() == Estados.TOCADO) {
					log.debug("Toque en el ultimo tiro");
					if (obtenerPrimerTocadoLH().getFila() == top().getDisparo().getFila()) {
						log.debug("Posible barco en sentido horizontal");
						if (obtenerPrimerTocadoLH().getColumna() < top().getDisparo().getColumna()) {
							log.debug("Intento ir hacia la der");
							if (puedoIrHaciaLaDerecha(top())) {
								log.debug("Efectivamente puedo ir hacia la der");
								nuevo = siguienteDerecho(top());
							} else if (puedoIrHaciaLaIzquierda(obtenerPrimerTocadoLHR())) {
								log.debug("No puedo ir hacia la derecha si a la izq");
								nuevo = siguienteIzquierdo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								log.debug("No puedo ir hacia la derecha ni a la izq entonces voy abajo");
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								log.debug("No puedo ir hacia la derecha ni a la izq ni voy abajo, voy arriba");
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}else{
							if (obtenerPrimerTocadoLH().getColumna() > top().getDisparo().getColumna()) {
								log.debug("Por construccion no tengo mas a disparar hacia derecha");
								if (puedoIrHaciaLaIzquierda(top())) {
									log.debug("Puedo ir a la izq entonces voy");
									nuevo = siguienteIzquierdo(top());
								} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
									log.debug("No puedo ir a la izq entonces voy a abajo");
									nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
								} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
									log.debug("No puedo ir a la izq ni abajo entonces voy arriba");
									nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
								}
							}else{
								log.debug("Algo anda mal!!!");
							}
						}


					} else {
						log.debug("Cominzo a ir en sentido vertical");
						if (obtenerPrimerTocadoLH().getFila() < top().getDisparo().getFila()) {
							if (puedoIrHaciaAbajo(top())) {
								log.debug("voy abajo");
								nuevo = siguienteAbajo(top());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								log.debug("voy arriba");
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}
						if (obtenerPrimerTocadoLH().getFila() > top().getDisparo().getFila()) {
							if (puedoIrHaciaArriba(top())) {
								log.debug("sigo yendo hacia arriba");
								nuevo = siguienteArriba(top());
							}else{
								log.error("algo anda mal!!");
							}
						}

					}

				} else {
					if (obtenerPrimerTocadoLH().getFila() == top().getDisparo().getFila()) {
						if (obtenerPrimerTocadoLH().getColumna() < top().getDisparo().getColumna()) {
							log.debug("No hay mas nada a la derecha");
							if (puedoIrHaciaLaIzquierda(obtenerPrimerTocadoLHR())) {
								log.debug("Voy a la izq");
								nuevo = siguienteIzquierdo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								log.debug("Voy abajo");
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								log.debug("Voy arriba");
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}else{
								log.debug("algo anda mal!!!");
							}
						} else {
							log.debug("No hay mas nada en sentido horizontal");
							if (puedoIrHaciaAbajo(obtenerPrimerTocadoLHR())) {
								log.debug("Voy hacia abajo");
								nuevo = siguienteAbajo(obtenerPrimerTocadoLHR());
							} else if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								log.debug("Voy hacia arriba");
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}else{
								log.debug("algo anda mal!!!");
							}
						}
					} else {
						if (obtenerPrimerTocadoLH().getFila() < top().getDisparo().getFila()) {
							log.debug("no hay mas nada hacia abajo");
							if (puedoIrHaciaArriba(obtenerPrimerTocadoLHR())) {
								nuevo = siguienteArriba(obtenerPrimerTocadoLHR());
							}
						}else{
							log.debug("algo anda mal!!!");
						}

					}

				}

			}

		}
		log.debug("Disparo generado");
		log.debug("X="+nuevo.getFila());
		log.debug("Y="+nuevo.getColumna());
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
			return (BatallaNavalDAO) Class.forName(constante.getString("CLASS_FOR_NAME_BN")).newInstance();
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
			return (UsuarioDAO) Class.forName(constante.getString("CLASS_FOR_NAME_USUARIO")).newInstance();
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
			return (PartidaDAO) Class.forName(constante.getString("CLASS_FOR_NAME_PARTIDA")).newInstance();
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
		log.debug("Usuario: "+usuario.getIdUsuario()+"crea instancia de juego");
		BatallaNavalDAO daoBatallaNaval = getBattallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		UsuarioDAO daoUsuario =getUsuarioDAO();
		Usuario oponente=daoPartida.oponente(usuario.getIdUsuario());
		log.debug("contra el usuario "+usuario.getUsuarioB());
		int idJugador1=usuario.getIdUsuario();
		int idJugador2=oponente.getIdUsuarioB();
		Tablero tableroInicialJugador1=daoBatallaNaval.getTablero(idJugador1);
		Tablero tableroInicialJugador2=daoBatallaNaval.getTablero(idJugador2);
		boolean modo=daoUsuario.esUsuarioVirtual(daoPartida.oponente(usuario.getIdUsuario()).getUsuarioB());
		ArrayList<RegistroDisparo> listaJugador1 =daoBatallaNaval.getListaDeTiros(idJugador1);
		ArrayList<RegistroDisparo> listaJugador2 =daoBatallaNaval.getListaDeTiros(idJugador2);
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
		tableroInicialJugador1.setMiTurno(turno1);
		tableroInicialJugador2.setMiTurno(turno2);
		JuegoBatallaNaval juego = new JuegoBatallaNaval(modo,tableroInicialJugador1,listaJugador1,tableroInicialJugador2,listaJugador2);
		return juego;
	}





}
