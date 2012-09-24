package busImpl;

import excepcionesB.CoordenadasCeldasInvalidasException;

public class Tablero {

	Celda[][] tabla;
	private Usuario jugador;

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
			tabla[coordenadaX][coordenadaY].setDisparada();
			}
		}
	}

}



