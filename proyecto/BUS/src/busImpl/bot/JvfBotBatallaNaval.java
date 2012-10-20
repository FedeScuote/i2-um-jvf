package busImpl.bot;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comm.Disparo;
import comm.Estados;
import comm.RegistroDisparo;
import comm.ServiciosBatallaNaval;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;



public class JvfBotBatallaNaval {
	private static UsuarioVO usuario;
	private static ServiciosBatallaNaval bn=null;


	private boolean tengoLugarADisparar() throws RemoteException {
		boolean hayTocado = false;
		if (bn.obtenerListaDisparos(usuario).size() != 0) {
			hayTocado = primerTocadoLuegoDeHundir() != bn.obtenerListaDisparos(usuario)
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
			if (coordenadaX<10&&coordenadaY<10&&bn.refrescarTableroOponente(usuario).getTabla()[coordenadaX][coordenadaY].estaVacio()) {
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

	public boolean puedoIrHaciaLaDerecha(RegistroDisparo registro) throws RemoteException {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() + 1 < bn.refrescarTableroOponente(usuario).getTabla().length
				&& bn.refrescarTableroOponente(usuario).getTabla()[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() + 1].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaLaIzquierda(RegistroDisparo registro) throws RemoteException {
		boolean retorno = false;
		if (registro.getDisparo().getColumna() - 1 >= 0
				&& bn.refrescarTableroOponente(usuario).getTabla()[registro.getDisparo().getFila()][registro
						.getDisparo().getColumna() - 1].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaAbajo(RegistroDisparo registro) throws RemoteException {
		boolean retorno = false;
		if (registro.getDisparo().getFila() + 1 < bn.refrescarTableroOponente(usuario).getTabla().length
				&& bn.refrescarTableroOponente(usuario).getTabla()[registro.getDisparo().getFila() + 1][registro
						.getDisparo().getColumna()].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public boolean puedoIrHaciaArriba(RegistroDisparo registro) throws RemoteException {
		boolean retorno = false;
		if (registro.getDisparo().getFila() - 1 >= 0
				&& bn.refrescarTableroOponente(usuario).getTabla()[registro.getDisparo().getFila() - 1][registro
						.getDisparo().getColumna()].estaVacio()) {
			retorno = true;
		}
		return retorno;
	}

	public comm.RegistroDisparo top() throws RemoteException {
		return bn.obtenerListaDisparos(usuario).get(bn.obtenerListaDisparos(usuario).size() - 1);
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

	public comm.Disparo obtenerPrimerTocadoLH() throws RemoteException {
		return bn.obtenerListaDisparos(usuario).get(primerTocadoLuegoDeHundir())
				.getDisparo();
	}

	public comm.RegistroDisparo obtenerPrimerTocadoLHR() throws RemoteException {
		return bn.obtenerListaDisparos(usuario).get(primerTocadoLuegoDeHundir());
	}

	public Disparo proximoDisparo(String idPartida) throws RemoteException {
		Disparo nuevo = new Disparo();
		nuevo.setColumna(-1);
		nuevo.setFila(-1);
		if (!tengoLugarADisparar()) {
			if(bn.refrescarTableroOponente(usuario).getCantBarcosSubmarino()==0){
				if(bn.refrescarTableroOponente(usuario).getCantBarcosDestructores()==0){
					nuevo=proximoDisparoSinSubNiDes();
				}else{
					nuevo=proximoDisparoSinSubmarinos();
				}
			}else{
				nuevo = proximoDisparo1(idPartida);
			}

		} else {
			if (primerTocadoLuegoDeHundir() == bn.refrescarTableroOponente(usuario).getTabla().length - 1) {
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
	private int ultimoDisparoQueHundio() throws RemoteException {
		int i = bn.obtenerListaDisparos(usuario).size() - 1;
		while (i >= 0
				&& bn.obtenerListaDisparos(usuario).get(i).getResultado() != Estados.HUNDIDO) {
			i--;
		}
		if (i == -1) {
			i = 0;
		}
		return i;
	}

	private int primerTocadoLuegoDeHundir() throws RemoteException {
		int i = ultimoDisparoQueHundio();
		while (i < bn.obtenerListaDisparos(usuario).size()
				&& bn.obtenerListaDisparos(usuario).get(i).getResultado() != Estados.TOCADO) {
			i++;
		}
//		if (i == listaDisparosAOponente.size()) {
//			i--;
//		}
		return i;
	}

	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(null);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry.lookup("Hello");
			bn=stub;
			JvfBotBatallaNaval.comenzarJuego();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void comenzarJuego() throws RemoteException, CoordenadasInvalidasException, NoInicioJuegoException {
		JvfBotBatallaNaval prueba = new JvfBotBatallaNaval();
		while(true){
			if(!bn.esMiTurno(usuario)){
				Disparo disparo=prueba.proximoDisparo("");
				bn.disparar(usuario, disparo.getFila(), disparo.getColumna());
			}
		}

	}


}
