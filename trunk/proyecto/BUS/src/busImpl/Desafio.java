
package busImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import comm.DesafioBatallaNavalVO;
import comm.RankingVO;
import comm.ServiciosDesafio;
import commExceptions.NoHayDesafiosDisponiblesException;
import daoInterfaces.DesafioDAO;
import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoHayDesafioException;

public class Desafio implements ServiciosDesafio {

	private String usuarioDesafio;
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


	private static DesafioDAO getDAO() {
		try {
			return (DesafioDAO) Class.forName("daoImpl.DesafioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException{
		try{
			DesafioDAO dao = getDAO();
			ArrayList<DesafioBatallaNavalVO> aux = new ArrayList<DesafioBatallaNavalVO>();
			ArrayList desafiosBDD=dao.getDesafiosBatallaNaval();
			int i=0;
			while(i<desafiosBDD.size()){
				DesafioBatallaNavalVO nuevo = new DesafioBatallaNavalVO();
				nuevo.setApuesta(((Desafio)desafiosBDD.get(i)).getMonto());
				nuevo.setIdDesafio((((Desafio)desafiosBDD.get(i)).getIdDesafio()));
				aux.add(nuevo);
				i++;
			}
			return aux;
		}catch(NoHayDesafioException e){
			 throw new NoHayDesafiosDisponiblesException();
		}

	}
	public String getUsuarioDesafio() {
		return usuarioDesafio;
	}
	public void setUsuarioDesafio(String usuarioDesafio) {
		this.usuarioDesafio = usuarioDesafio;
	}






}
