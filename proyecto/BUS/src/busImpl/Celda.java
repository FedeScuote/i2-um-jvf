package busImpl;

public class Celda {
	private static final String AGUA = "AGUA";
	private static final String OCUPADO = "OCUPADO";
	private static final String TOCADO = "TOCADO";
	private static final String TIROERRADO = "TIROERRADO";
	String estado; // atributo que dice si hay agua, barco, etc.
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Celda() {
		this.estado = AGUA;
	}


	public boolean estaOcupada(){
		return estado==OCUPADO;
	}
	public boolean estaDisparada(){
		return estado==TOCADO;
	}
	public void setOcupada(){
		estado=OCUPADO;
	}
	public void setDisparada(){
		estado=TOCADO;
	}


}
