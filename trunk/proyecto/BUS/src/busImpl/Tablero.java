package busImpl;

import java.util.ArrayList;

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
	private static final int LARGO_SUBMARINO = 1;
	private static final int LARGO_DESTRUCTORES = 2;
	private static final int LARGO_CRUCEROS = 3;
	private static final int LARGO_ACORAZADO = 4;
	//***********************************************************************//

	Celda[][] tabla ;

	private Usuario jugador;

	private boolean miTurno;

	private int cantBarcosSubmarino;
	private int cantBarcosDestructores;
	private int cantBarcosCruceros;
	private int cantBarcosAcorazado;

	private int cantBarcosSubmarinoColocados;
	private int cantBarcosDestructoresColocados;
	private int cantBarcosCrucerosColocados;
	private int cantBarcosAcorazadoColocados;

	public void decrementarBarcosCrucerosColocados(){
		cantBarcosCrucerosColocados--;

	}

	public void decrementarBarcosAcorazadoColocados(){
		cantBarcosAcorazadoColocados--;

	}

	public void decrementarBarcosDestructoresColocados(){
		cantBarcosDestructoresColocados--;

	}

	public void decrementarBarcosSubmarinoColocados(){
		cantBarcosSubmarinoColocados--;

	}




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
			int coordenadaFinalY, String tipoBarco) throws CoordenadasCeldasInvalidasException {
		int idBarco=0;
		if(tipoBarco.equals(SUBMARINO)){
			idBarco=Integer.parseInt(LARGO_SUBMARINO+""+cantBarcosSubmarinoColocados);
		}else if(tipoBarco.equals(DESTRUCTORES)){
			idBarco=Integer.parseInt(LARGO_DESTRUCTORES+""+cantBarcosDestructoresColocados);
		}else if(tipoBarco.equals(CRUCEROS)){
			idBarco=Integer.parseInt(LARGO_CRUCEROS+""+cantBarcosCrucerosColocados);
		}else if(tipoBarco.equals(ACORAZADO)){
			idBarco=Integer.parseInt(LARGO_ACORAZADO+""+cantBarcosAcorazadoColocados);
		}
		if (!(coordenadaInicialY > coordenadaFinalY)&& !(coordenadaFinalY >= tabla.length)&&!(coordenadaInicialY<0) ) {
			for (int i = coordenadaInicialY; i <= coordenadaFinalY; i++) {
				if (tabla[coordenadaX][i].estaOcupada()) {
					throw new CoordenadasCeldasInvalidasException();
				}else{
					tabla[coordenadaX][i].setOcupada();
					tabla[coordenadaX][i].setId(idBarco);
				}
			}
		} else {
			throw new CoordenadasCeldasInvalidasException();

		}
	}

	//METODO QUE DADO UNA DISTRIBUCION AGREGA BARCOS AL AZAR
	public void agregarCeldas(int[] distribucion )  {
		for(int i=0;i<4;i++){
				for(int j=0;j<distribucion[i];j++){
				if(Math.random()>=0.5){
					boolean termine=false;
					int coordenadaX;
					int coordenadaY;
					while(!termine){
						coordenadaX=(int)(Math.random()*9);
						coordenadaY=(int)(Math.random()*9);
						if(i==0){
							if(tabla[coordenadaX][coordenadaY].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								termine=true;
							}
						}
						if(i==1){
							if(coordenadaX+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX+1][coordenadaY].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX+1][coordenadaY].setOcupada();
								tabla[coordenadaX+1][coordenadaY].setId(numero);
								termine=true;
							}
						}
						if(i==2){
							if(coordenadaX+2<tabla.length&&coordenadaX+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX+1][coordenadaY].estaVacio()&&tabla[coordenadaX+2][coordenadaY].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX+1][coordenadaY].setOcupada();
								tabla[coordenadaX+1][coordenadaY].setId(numero);
								tabla[coordenadaX+2][coordenadaY].setOcupada();
								tabla[coordenadaX+2][coordenadaY].setId(numero);
								termine=true;
							}
						}
						if(i==3){
							if(coordenadaX+3<tabla.length&&coordenadaX+2<tabla.length&&coordenadaX+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX+1][coordenadaY].estaVacio()&&tabla[coordenadaX+2][coordenadaY].estaVacio()&&tabla[coordenadaX+3][coordenadaY].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX+1][coordenadaY].setOcupada();
								tabla[coordenadaX+1][coordenadaY].setId(numero);
								tabla[coordenadaX+2][coordenadaY].setOcupada();
								tabla[coordenadaX+2][coordenadaY].setId(numero);
								tabla[coordenadaX+3][coordenadaY].setOcupada();
								tabla[coordenadaX+3][coordenadaY].setId(numero);
								termine=true;
							}
						}
					}

				}else{
					boolean termine=false;
					int coordenadaX;
					int coordenadaY;
					while(!termine){
						coordenadaX=(int)(Math.random()*9);
						coordenadaY=(int)(Math.random()*9);
						if(i==0){
							if(tabla[coordenadaX][coordenadaY].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								termine=true;
							}
						}
						if(i==1){
							if(coordenadaY+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX][coordenadaY+1].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX][coordenadaY+1].setOcupada();
								tabla[coordenadaX][coordenadaY+1].setId(numero);
								termine=true;
							}
						}
						if(i==2){
							if(coordenadaY+2<tabla.length&&coordenadaY+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX][coordenadaY+1].estaVacio()&&tabla[coordenadaX][coordenadaY+2].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX][coordenadaY+1].setOcupada();
								tabla[coordenadaX][coordenadaY+1].setId(numero);
								tabla[coordenadaX][coordenadaY+2].setOcupada();
								tabla[coordenadaX][coordenadaY+2].setId(numero);
								termine=true;
							}
						}
						if(i==3){
							if(coordenadaY+3<tabla.length&&coordenadaY+2<tabla.length&&coordenadaY+1<tabla.length&&tabla[coordenadaX][coordenadaY].estaVacio()&&tabla[coordenadaX][coordenadaY+1].estaVacio()&&tabla[coordenadaX][coordenadaY+2].estaVacio()&&tabla[coordenadaX][coordenadaY+3].estaVacio()){
								int numero = Integer.parseInt(i+""+j);
								tabla[coordenadaX][coordenadaY].setOcupada();
								tabla[coordenadaX][coordenadaY].setId(numero);
								tabla[coordenadaX][coordenadaY+1].setOcupada();
								tabla[coordenadaX][coordenadaY+1].setId(numero);
								tabla[coordenadaX][coordenadaY+2].setOcupada();
								tabla[coordenadaX][coordenadaY+2].setId(numero);
								tabla[coordenadaX][coordenadaY+3].setOcupada();
								tabla[coordenadaX][coordenadaY+3].setId(numero);
								termine=true;
							}
						}
					}

				}


			}
			cantBarcosAcorazadoColocados=0;
			cantBarcosCrucerosColocados=0;
			cantBarcosDestructoresColocados=0;
			cantBarcosSubmarinoColocados=0;
		}

	}


	public void agregarCeldasDirX(int coordenadaY, int coordenadaInicialX,
			int coordenadaFinalX, String tipoBarco) throws CoordenadasCeldasInvalidasException {
		int idBarco=0;
		if(tipoBarco.equals(SUBMARINO)){
			idBarco=Integer.parseInt(LARGO_SUBMARINO+""+cantBarcosSubmarinoColocados);
		}else if(tipoBarco.equals(DESTRUCTORES)){
			idBarco=Integer.parseInt(LARGO_DESTRUCTORES+""+cantBarcosDestructoresColocados);
		}else if(tipoBarco.equals(CRUCEROS)){
			idBarco=Integer.parseInt(LARGO_CRUCEROS+""+cantBarcosCrucerosColocados);
		}else if(tipoBarco.equals(ACORAZADO)){
			idBarco=Integer.parseInt(LARGO_ACORAZADO+""+cantBarcosAcorazadoColocados);
		}
		if (!(coordenadaInicialX > coordenadaFinalX)&&!(coordenadaFinalX >= tabla.length)&&!(coordenadaInicialX<0)) {
			for (int i = coordenadaInicialX; i <= coordenadaFinalX; i++) {
				if (tabla[i][coordenadaY].estaOcupada()) {
					//Hay que ver si agrega hasta la ocupada!!!
					throw new CoordenadasCeldasInvalidasException();
				}else{

					tabla[i][coordenadaY].setOcupada();
					tabla[i][coordenadaY].setId(idBarco);
				}

			}
		} else {

			throw new CoordenadasCeldasInvalidasException();

		}
	}


	public Estados dispararACelda(int coordenadaX,int coordenadaY) throws CoordenadasCeldasInvalidasException{
		Estados retorno=null;
		int largoBarco=0;

		if(tabla[coordenadaX][coordenadaY].estaVacio()){
			tabla[coordenadaX][coordenadaY].setTiroErrado();
			retorno=Estados.AGUA;
		}else if(tabla[coordenadaX][coordenadaY].estaOcupada()){
			retorno=(Estados)obtenerEstado(coordenadaX,coordenadaY).get(0);
			if(retorno==Estados.HUNDIDO){
				tabla[coordenadaX][coordenadaY].setHundido();
				largoBarco=(Integer)obtenerEstado(coordenadaX, coordenadaY).get(1);
			}else{
				tabla[coordenadaX][coordenadaY].setDisparada();
			}

		}else{
			throw new CoordenadasCeldasInvalidasException();
		}
		if(retorno==Estados.HUNDIDO){
			if(largoBarco==LARGO_ACORAZADO){
				decrementarBarcosAcorazado();
			}
			if(largoBarco==LARGO_CRUCEROS){
				decrementarBarcosCruceros();
			}
			if(largoBarco==LARGO_DESTRUCTORES){
				decrementarBarcosDestructores();
			}
			if(largoBarco==LARGO_SUBMARINO){
				decrementarBarcosSubmarino();
			}
			if(largoBarco!=LARGO_ACORAZADO&&largoBarco!=LARGO_CRUCEROS&&largoBarco!=LARGO_DESTRUCTORES&&largoBarco!=LARGO_SUBMARINO){
				throw new RuntimeException();
			}
		}
		return retorno;
	}

	//metodo que registra el disparo con el resultado en caso de ser el tablero del opontente

	//metodo para identificar si nos hundieron un barco y en ese caso brindar el largo del barco hundido
	public ArrayList obtenerEstado(int coordenadaX,int coordenadaY){
		ArrayList aux= new ArrayList(2);
		boolean direccionHorizontalDerecha=false;
		boolean direccionHorizontalIzquierda=false;
		boolean direccionVerticalDerecha=false;
		boolean direccionVerticalIzquierda=false;
		int largoDirHorizontalDerecha=0;
		int largoDirHorizontalIzquierda=0;
		int largoDirVerticalDerecha=0;
		int largoDirVerticalIzquierda=0;
		Estados retorno=Estados.TOCADO;
			//comienzo a recorrer en direccion horizontal sentido derecho
			int i=1;
			while(coordenadaX+i<tabla.length&&tabla[coordenadaX+i][coordenadaY].estaDisparada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX+i][coordenadaY].getId()){
				i++;

			}
			largoDirHorizontalDerecha=i;
			if((coordenadaX+i<tabla.length&&tabla[coordenadaX+i][coordenadaY].estaOcupada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX+i][coordenadaY].getId())){
				direccionHorizontalDerecha=false;
			}else{
				direccionHorizontalDerecha=true;
			}
			//recorro en sentido izquierdo
			i=-1;
			while(coordenadaX+i>=0&&tabla[coordenadaX+i][coordenadaY].estaDisparada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX+i][coordenadaY].getId()){
				i--;

			}
			largoDirHorizontalIzquierda=Math.abs(i);
			if((coordenadaX+i>=0&&tabla[coordenadaX+i][coordenadaY].estaOcupada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX+i][coordenadaY].getId())){
				direccionHorizontalIzquierda=false;
			}else{
				direccionHorizontalIzquierda=true;
			}
			i=1;
			while(coordenadaY+i<tabla.length&&tabla[coordenadaX][coordenadaY+i].estaDisparada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX][coordenadaY+i].getId()){
				i++;

			}
			largoDirVerticalDerecha=i;
			if((coordenadaY+i<tabla.length&&tabla[coordenadaX][coordenadaY+i].estaOcupada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX][coordenadaY+i].getId())){
				direccionVerticalDerecha=false;
			}else{
				direccionVerticalDerecha=true;
			}
			//recorro en sentido izquierdo
			i=-1;
			while(coordenadaY+i>=0&&tabla[coordenadaX][coordenadaY+i].estaDisparada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX][coordenadaY+i].getId()){
				i--;

			}
			largoDirVerticalIzquierda=Math.abs(i);
			if((coordenadaY+i>=0&&tabla[coordenadaX][coordenadaY+i].estaOcupada()&&tabla[coordenadaX][coordenadaY].getId()==tabla[coordenadaX][coordenadaY+i].getId())){
				direccionVerticalIzquierda=false;
			}else{
				direccionVerticalIzquierda=true;
			}
			if(direccionHorizontalDerecha&&direccionHorizontalIzquierda&&direccionVerticalDerecha&&direccionVerticalIzquierda){
				retorno=Estados.HUNDIDO;
			}
			aux.add(retorno);
			if(retorno==Estados.HUNDIDO){
				if(largoDirHorizontalDerecha==largoDirHorizontalIzquierda){
					aux.add(largoDirVerticalDerecha+largoDirVerticalIzquierda-1);
				}else{
					aux.add(largoDirHorizontalDerecha+largoDirHorizontalIzquierda-1);
				}
			}
			return aux;
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
		this.cantBarcosSubmarinoColocados = CANTIDAD_INICIAL_SUBMARINO;
		this.cantBarcosDestructoresColocados = CANTIDAD_INICIAL_DESTRUCTORES;
		this.cantBarcosCrucerosColocados = CANTIDAD_INICIAL_CRUCEROS;
		this.cantBarcosAcorazadoColocados = CANTIDAD_INICIAL_ACORAZADO;
		for(int i=0;i<tabla.length;i++){
			for(int j=0;j<tabla.length;j++){
				tabla[i][j]=new Celda();
			}
		}
	}

	public int getCantBarcosAcorazadoColocados() {
		return cantBarcosAcorazadoColocados;
	}

	public int getCantBarcosCrucerosColocados() {
		return cantBarcosCrucerosColocados;
	}

	public int getCantBarcosDestructoresColocados() {
		return cantBarcosDestructoresColocados;
	}

	public int getCantBarcosSubmarinoColocados() {
		return cantBarcosSubmarinoColocados;
	}

}



