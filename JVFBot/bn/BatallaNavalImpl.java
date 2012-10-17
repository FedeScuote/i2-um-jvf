package bn;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BatallaNavalImpl {

	// Jugador 2 es siempre un oponente
	private static final String IDJUGADOR2 = "OPONENTE";

	// Atributos de la clase
	private Tablero tableroJugador1;
	private Tablero tableroJugador2;
	private ArrayList<RegistroDisparo> listaDisparosAOponente;

	// Geter´s y seter´s
	public ArrayList<RegistroDisparo> getListaDisparosAOponente() {
		return listaDisparosAOponente;
	}

	public void setListaDisparosAOponente(
			ArrayList<RegistroDisparo> listaDisparosAOponente) {
		this.listaDisparosAOponente = listaDisparosAOponente;
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

	public static String getIDJUGADOR2() {
		return IDJUGADOR2;
	}

	// Constructor
	public BatallaNavalImpl(String idJugador1, int[] distribucion) {
		super();
		this.tableroJugador1 = new Tablero(idJugador1, distribucion);
		this.tableroJugador2 = new Tablero(IDJUGADOR2, distribucion);
		this.listaDisparosAOponente = new ArrayList<RegistroDisparo>();
		this.tableroJugador1.agregarCeldas(distribucion);
//		tableroJugador1.getTabla()[7][1].setOcupada();
//		tableroJugador1.getTabla()[7][1].setId(1);
//
//		tableroJugador1.getTabla()[0][9].setOcupada();
//		tableroJugador1.getTabla()[0][9].setId(2);
//
//		tableroJugador1.getTabla()[0][8].setOcupada();
//		tableroJugador1.getTabla()[0][8].setId(2);
//
//		tableroJugador1.getTabla()[4][0].setOcupada();
//		tableroJugador1.getTabla()[4][0].setId(3);
//
//		tableroJugador1.getTabla()[4][1].setOcupada();
//		tableroJugador1.getTabla()[4][1].setId(3);
//
//		tableroJugador1.getTabla()[4][2].setOcupada();
//		tableroJugador1.getTabla()[4][2].setId(3);
//
//		tableroJugador1.getTabla()[0][3].setOcupada();
//		tableroJugador1.getTabla()[0][3].setId(4);
//
//		tableroJugador1.getTabla()[1][3].setOcupada();
//		tableroJugador1.getTabla()[1][3].setId(4);
//
//		tableroJugador1.getTabla()[2][3].setOcupada();
//		tableroJugador1.getTabla()[2][3].setId(4);
//
//		tableroJugador1.getTabla()[3][3].setOcupada();
//		tableroJugador1.getTabla()[3][3].setId(4);

	}

	public Estados disparar(int coordenadaX, int coordenadaY)
			throws RemoteException {
		return tableroJugador1.dispararACelda(coordenadaX, coordenadaY);
	}

	public boolean ganaste(String idPartida) throws RemoteException {
		return tableroJugador2.ganaste();
	}

	public String idJugador() throws RemoteException {
		return tableroJugador1.getJugador();
	}

	public boolean perdiste(String idPartida) {
		return tableroJugador1.perdiste();
	}

	private boolean tengoLugarADisparar() {
		boolean hayTocado = false;
		if (listaDisparosAOponente.size() != 0) {
			hayTocado = primerTocadoLuegoDeHundir() != listaDisparosAOponente
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
			if (coordenadaX<10&&coordenadaY<10&&tableroJugador2.tabla[coordenadaX][coordenadaY].estaVacio()) {
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
		return listaDisparosAOponente.get(listaDisparosAOponente.size() - 1);
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
		return listaDisparosAOponente.get(primerTocadoLuegoDeHundir())
				.getDisparo();
	}

	public RegistroDisparo obtenerPrimerTocadoLHR() {
		return listaDisparosAOponente.get(primerTocadoLuegoDeHundir());
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
			if (primerTocadoLuegoDeHundir() == listaDisparosAOponente.size() - 1) {
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



	public Disparo proximoDisparo2(String idPartida) throws RemoteException {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(-1);
		nuevo.setFila(-1);
		if (!tengoLugarADisparar()) {

				nuevo = proximoDisparo1(idPartida);


		} else {
			if (primerTocadoLuegoDeHundir() == listaDisparosAOponente.size() - 1) {
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


	public void registrarDisparo(Disparo disparo, Estados resultado)
			throws RemoteException {
		tableroJugador2.impactarDisparoEnTablero(disparo.getFila(), disparo
				.getColumna(), resultado);
		tableroJugador2.actualizarCantBarcos();
		RegistroDisparo nuevo = new RegistroDisparo(resultado, disparo);
		listaDisparosAOponente.add(nuevo);
	}

	private int ultimoDisparoQueHundio() {
		int i = listaDisparosAOponente.size() - 1;
		while (i >= 0
				&& listaDisparosAOponente.get(i).getResultado() != Estados.HUNDIDO) {
			i--;
		}
		if (i == -1) {
			i = 0;
		}
		return i;
	}

	private int primerTocadoLuegoDeHundir() {
		int i = ultimoDisparoQueHundio();
		while (i < listaDisparosAOponente.size()
				&& listaDisparosAOponente.get(i).getResultado() != Estados.TOCADO) {
			i++;
		}
//		if (i == listaDisparosAOponente.size()) {
//			i--;
//		}
		return i;
	}

	public static void main(String[] args) throws RemoteException {
		int[] distribucion = { 0, 2, 3, 2 };
		Estados estado1;
	//	Estados estado2;
		ArrayList estado2 =new ArrayList();
		BatallaNavalImpl nuevo=null;
		for(int i=0;i<100;i++){
			nuevo = new BatallaNavalImpl("JVF", distribucion);
		}

		boolean termine=false;
		while(!termine){
			Disparo dis=nuevo.proximoDisparo1("");
			estado2.add(dis);
			if(dis.getFila()==0&&dis.getColumna()==9){
				termine=true;
			}
		}

		estado1=nuevo.tableroJugador1.dispararACelda(7, 1);
		estado1=nuevo.tableroJugador1.dispararACelda(4, 0);
		estado1=nuevo.tableroJugador1.dispararACelda(4, 1);
		estado1=nuevo.tableroJugador1.dispararACelda(4, 2);
		estado1=nuevo.tableroJugador1.dispararACelda(0, 9);
		estado1=nuevo.tableroJugador1.dispararACelda(0, 8);
		estado1=nuevo.tableroJugador1.dispararACelda(0, 3);
		estado1=nuevo.tableroJugador1.dispararACelda(1, 3);
		estado1=nuevo.tableroJugador1.dispararACelda(2, 3);
		estado1=nuevo.tableroJugador1.dispararACelda(3, 3);
		System.out.println(nuevo.tableroJugador1.getCantBarcosAcorazado());
		if(nuevo.tableroJugador1.perdiste()){
			System.out.println("Perdi!!!");
		}
	}

}
