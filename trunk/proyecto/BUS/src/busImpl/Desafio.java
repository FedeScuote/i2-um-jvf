package busImpl;

import java.util.Date;

public class Desafio {

	private int idDesafio;
	private int monto;
	private Date fechaHoraInicioD;
	private String estado; //En hora, En hora-lleno, En curso, Atrasado, Finalizado, Cancelado
	public int getIdDesafio() {
		return idDesafio;
	}
	public void setIdDesafio(int idDesafio) {
		this.idDesafio = idDesafio;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public Date getFechaHoraInicioD() {
		return fechaHoraInicioD;
	}
	public void setFechaHoraInicioD(Date fechaHoraInicioD) {
		this.fechaHoraInicioD = fechaHoraInicioD;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}


}
