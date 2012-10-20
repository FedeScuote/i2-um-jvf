package comm;

import java.io.Serializable;

public class TableroVO implements Serializable {

	CeldaVO[][] tabla=new CeldaVO[10][10];
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




	public CeldaVO[][] getTabla() {
		return tabla;
	}

	public void setTabla(CeldaVO[][] tabla) {
		this.tabla = tabla;
	}

	public TableroVO() {
		super();
		for(int i=0;i<tabla.length;i++){
			for(int j=0;j<tabla.length;j++){
				tabla[i][j]=new CeldaVO();
			}
		}
	}








}
