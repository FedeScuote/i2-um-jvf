package bn;

public class Celda {
	private static final String VACIO="VACIO";
	private static final String AGUA = "AGUA";
	private static final String OCUPADO= "OCUPADO";
	private static final String TOCADO = "TOCADO";
	private static final String HUNDIDO = "HUNDIDO";
	String estado; // atributo que dice si hay agua, barco, etc.
	int id;


	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Celda() {
		this.estado = VACIO;
		this.id=0;
	}
	public void setAgua(){
		estado=AGUA;

	}

	public boolean estaHundido(){
		return estado.equals(HUNDIDO);
	}

	public void setHundido(){
		estado=OCUPADO;
	}

	public boolean estaAgua(){
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
	public boolean estaVacio(){
		return estado.equals(VACIO);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
