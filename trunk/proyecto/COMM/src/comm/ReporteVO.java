package comm;

import java.io.Serializable;

public class ReporteVO implements Serializable {
	private int montoTotalNoVirtuales=0;
	private int montoTotalVirtuales=0;
	private int montoTotalComisionesJVF=0;

	public int getMontoTotalNoVirtuales() {
		return montoTotalNoVirtuales;
	}
	public void setMontoTotalNoVirtuales(int montoTotalNoVirtuales) {
		this.montoTotalNoVirtuales = montoTotalNoVirtuales;
	}
	public int getMontoTotalVirtuales() {
		return montoTotalVirtuales;
	}
	public void setMontoTotalVirtuales(int montoTotalVirtuales) {
		this.montoTotalVirtuales = montoTotalVirtuales;
	}
	public int getMontoTotalComisionesJVF() {
		return montoTotalComisionesJVF;
	}
	public void setMontoTotalComisionesJVF(int montoTotalComisionesJVF) {
		this.montoTotalComisionesJVF = montoTotalComisionesJVF;
	}
}
