package comm;

import java.io.Serializable;

public class Disparo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int fila;
	private int columna;
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}

}
