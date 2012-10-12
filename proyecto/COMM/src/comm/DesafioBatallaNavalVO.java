package comm;

import java.io.Serializable;

public class DesafioBatallaNavalVO extends DesafioVO implements Serializable{

	final private String Juego = "BATALLA NAVAL";

	@Override
	public String toString() {

		return ((Integer)this.getApuesta()).toString();
	}



}
