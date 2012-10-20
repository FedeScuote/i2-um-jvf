package busImpl;

public class Celda {
	private static final String AGUA = "AGUA";
	private static final String OCUPADO = "OCUPADO";
	private static final String TOCADO = "TOCADO";
	private static final String TIROERRADO = "TIROERRADO";
	private static final String HUNDIDO = "HUNDIDO";
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
		return estado==HUNDIDO;
	}

	public boolean estaVacio(){
		return estado==AGUA;
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
	public void setTiroErrado(){
		estado=TIROERRADO;
	}
	public boolean estaErrado(){
		return estado==TIROERRADO;
	}


}
