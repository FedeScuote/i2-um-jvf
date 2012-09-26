package comm;

import java.io.Serializable;

public class TableroVO implements Serializable {

	CeldaVO[][] tabla=new CeldaVO[10][10];

	public CeldaVO[][] getTabla() {
		return tabla;
	}

	public void setTabla(CeldaVO[][] tabla) {
		this.tabla = tabla;
	}





}
