package busImpl.batallaNaval;

import java.util.ResourceBundle;

public class Celda {
	private static ResourceBundle constante = ResourceBundle.getBundle("bus");
	private static final String AGUA = constante.getString("AGUA");
	private static final String OCUPADO = constante.getString("OCUPADO");
	private static final String TOCADO = constante.getString("TOCADO");
	private static final String TIROERRADO = constante.getString("TIROERRADO");
	private static final String HUNDIDO = constante.getString("HUNDIDO");
	
	String estado; // atributo que dice si hay agua, barco, etc.
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHundido(){
		this.estado=HUNDIDO;
	}


	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Celda() {
		this.estado = AGUA;
	}

	public boolean estaHundido(){
		return estado.equals(HUNDIDO);
	}

	public boolean estaVacio(){
		return estado.equals(AGUA);
	}
	public boolean estaOcupada(){
		return estado.equals(OCUPADO);
	}
	public boolean estaDisparada(){
		return estado.equals(TOCADO);
	}
	public void setOcupada(){
		estado=OCUPADO;
	}
	public void setDisparada(){
		estado=TOCADO;
	}
	public void setTiroErrado(){
		estado=TIROERRADO;
	}
	public boolean estaErrado(){
		return estado==TIROERRADO;
	}


}
