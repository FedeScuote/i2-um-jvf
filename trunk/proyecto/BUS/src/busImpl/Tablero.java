package busImpl;

import excepcionesB.CoordenadasCeldasInvalidasException;

public class Tablero {

	Celda[][] tabla = new Celda[10][10];
	private Usuario jugador;
	private boolean miTurno;
	private int cantBarcosL4=3;
	private int cantBarcosL3=2;
	private int cantBarcosL2=1;
	private int cantBarcosL1=1;

	public void decrementarBarcosL1(){
		cantBarcosL1--;

	}
	public void decrementarBarcosL2(){
		cantBarcosL2--;

	}
	public void decrementarBarcosL3(){
		cantBarcosL3--;

	}
	public void decrementarBarcosL4(){
		cantBarcosL4--;

	}
	public int getCantBarcosL4() {
		return cantBarcosL4;
	}

	public int getCantBarcosL3() {
		return cantBarcosL3;
	}

	public int getCantBarcosL2() {
		return cantBarcosL2;
	}

	public int getCantBarcosL1() {
		return cantBarcosL1;
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

}



