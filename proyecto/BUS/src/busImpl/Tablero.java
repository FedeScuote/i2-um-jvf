package busImpl;

import excepcionesB.CoordenadasCeldasInvalidasException;

public class Tablero {
	//**********************Declaracion de Const******************************//
	private static final int LARGO_TABLERO = 10;
	private static final String SUBMARINO = "SUBMARINO";
	private static final String DESTRUCTORES = "DESTRUCTORES";
	private static final String CRUCEROS = "CRUCEROS";
	private static final String ACORAZADO = "ACORAZADO";
	private static final int CANTIDAD_INICIAL_SUBMARINO = 3;
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = 2;
	private static final int CANTIDAD_INICIAL_CRUCEROS = 1;
	private static final int CANTIDAD_INICIAL_ACORAZADO = 1;
	//***********************************************************************//

	Celda[][] tabla ;

	private Usuario jugador;

	private boolean miTurno;

	private int cantBarcosSubmarino;
	private int cantBarcosDestructores;
	private int cantBarcosCruceros;
	private int cantBarcosAcorazado;

	public void decrementarBarcosCruceros(){
		cantBarcosCruceros--;

	}
	public void decrementarBarcosAcorazado(){
		cantBarcosAcorazado--;

	}
	public void decrementarBarcosDestructores(){
		cantBarcosDestructores--;

	}
	public void decrementarBarcosSubmarino(){
		cantBarcosSubmarino--;

	}


	public int getCantBarcosAcorazado() {
		return cantBarcosAcorazado;
	}

	public int getCantBarcosCruceros() {
		return cantBarcosCruceros;
	}

	public int getCantBarcosDestructores() {
		return cantBarcosDestructores;
	}

	public int getCantBarcosSubmarino() {
		return cantBarcosSubmarino;
	}

	public boolean isMiTurno() {
		return miTurno;
	}

	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}

	public Celda[][] getTabla() {
		return tabla;
	}

	public void setTabla(Celda[][] tabla) {
		this.tabla = tabla;
	}

	public Usuario getJugador() {
		return jugador;
	}

	public void setJugador(Usuario jugador) {
		this.jugador = jugador;
	}

	public void agregarCeldasDirY(int coordenadaX, int coordenadaInicialY,
			int coordenadaFinalY) throws CoordenadasCeldasInvalidasException {
		if (!(coordenadaInicialY + coordenadaFinalY >= tabla.length) && !(coordenadaInicialY > coordenadaFinalY)) {
			for (int i = coordenadaInicialY; i <= coordenadaFinalY; i++) {
				if (tabla[coordenadaX][i].estaOcupada()) {
					throw new CoordenadasCeldasInvalidasException();
				}else{
					tabla[coordenadaX][i].setOcupada();
				}
			}
		} else {

			throw new CoordenadasCeldasInvalidasException();

		}
	}

	public void agregarCeldasDirX(int coordenadaY, int coordenadaInicialX,
			int coordenadaFinalX) throws CoordenadasCeldasInvalidasException {
		if (!(coordenadaInicialX + coordenadaFinalX >= tabla.length) && !(coordenadaInicialX > coordenadaFinalX)) {
			for (int i = coordenadaInicialX; i <= coordenadaFinalX; i++) {
				if (tabla[i][coordenadaY].estaOcupada()) {
					throw new CoordenadasCeldasInvalidasException();
				}else{
					tabla[i][coordenadaY].setOcupada();
				}
			}
		} else {

			throw new CoordenadasCeldasInvalidasException();

		}
	}

	public void dispararACelda(int coordenadaX,int coordenadaY) throws CoordenadasCeldasInvalidasException{
		if(coordenadaX>=tabla.length){
			throw new CoordenadasCeldasInvalidasException();
		}else{
			if(tabla[coordenadaX][coordenadaY].estaDisparada()){
				throw new CoordenadasCeldasInvalidasException();
			}else{
				if(tabla[coordenadaX][coordenadaY].estaOcupada()){
					tabla[coordenadaX][coordenadaY].setDisparada();
				}else{
					tabla[coordenadaX][coordenadaY].setTiroErrado();
				}

			}
		}
	}
	public Tablero(Usuario jugador) {
		super();
		this.tabla = new Celda[LARGO_TABLERO][LARGO_TABLERO];
		this.jugador = jugador;
		this.miTurno = false;
		this.cantBarcosSubmarino = CANTIDAD_INICIAL_SUBMARINO;
		this.cantBarcosDestructores = CANTIDAD_INICIAL_DESTRUCTORES;
		this.cantBarcosCruceros = CANTIDAD_INICIAL_CRUCEROS;
		this.cantBarcosAcorazado = CANTIDAD_INICIAL_ACORAZADO;

	}

}



