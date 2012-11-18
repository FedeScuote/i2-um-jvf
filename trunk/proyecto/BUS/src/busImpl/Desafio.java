
package busImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import comm.DesafioBatallaNavalVO;
import comm.DesafioVO;
import comm.RankingVO;
import comm.ServiciosDesafio;
import comm.UsuarioVO;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoHayDesafiosDisponiblesException;
import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.DesafioDAO;
import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoHayDesafioException;

public class Desafio implements ServiciosDesafio {

	private String usuarioDesafio;
	private int idUsuario;
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

	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException{
		DesafioDAO dao = getDesafioDAO();
		UsuarioDAO dao1 = getUsuarioDAO();
		ArrayList<DesafioBatallaNavalVO> aux = new ArrayList<DesafioBatallaNavalVO>();
		try{
			ArrayList desafiosBDD=dao.getDesafiosUsuariosDisponibleBatallaNaval();
			int i=0;
			while(i<desafiosBDD.size()){
				DesafioBatallaNavalVO nuevo = new DesafioBatallaNavalVO();
				UsuarioVO usu = new UsuarioVO(((Desafio)desafiosBDD.get(i)).getUsuarioDesafio());
				usu.setIdUsuario(((Desafio)desafiosBDD.get(i)).getIdUsuario());
				nuevo.setApuesta(((Desafio)desafiosBDD.get(i)).getMonto());
				nuevo.setIdDesafio((((Desafio)desafiosBDD.get(i)).getIdDesafio()));
				nuevo.setUsuario(usu);
				aux.add(nuevo);
				i++;
			}
			return aux;
		}catch(NoHayDesafioException e){
			return generarDesafios(dao1.getUsuariosVirtuales());
		}
	}

	private ArrayList<DesafioBatallaNavalVO> generarDesafios(ArrayList<Usuario> usuariosVirtuales) {
		ArrayList<DesafioBatallaNavalVO> desafios=new ArrayList<DesafioBatallaNavalVO>();
		int cantDesafios=1+(int)(Math.random()*usuariosVirtuales.size());
		if(cantDesafios>usuariosVirtuales.size()){
			cantDesafios=usuariosVirtuales.size();
		}
		for(int i=0;i<cantDesafios;i++){
			DesafioBatallaNavalVO nuevo = new DesafioBatallaNavalVO();
			UsuarioVO usuarioVOBot = new UsuarioVO(usuariosVirtuales.get(i).getUsuarioB());
			usuarioVOBot.setIdUsuario(usuariosVirtuales.get(i).getIdUsuarioB());
			int apuesta=0;
			apuesta=(int)(Math.random()*100)+50;
			nuevo.setApuesta(apuesta);
			nuevo.setUsuario(usuarioVOBot);
			desafios.add(nuevo);
		}
		return desafios;
	}

	public String getUsuarioDesafio() {
		return usuarioDesafio;
	}

	public void setUsuarioDesafio(String usuarioDesafio) {
		this.usuarioDesafio = usuarioDesafio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean aceptaronDesafio(UsuarioVO usuario) throws RemoteException {
		DesafioDAO dao = getDesafioDAO();
		return dao.aceptaronDesafio(usuario.getIdUsuario());
	}

	public void crearDesafio(UsuarioVO usuario, int monto) throws RemoteException, MontoInsuficienteException {
		DesafioDAO dao = getDesafioDAO();
		dao.crearDesafio(usuario.getUsuarioB(), monto);
	}

	public ArrayList<DesafioVO> getDesafiosLudo() throws RemoteException {
		return null;
	}

	private static DesafioDAO getDesafioDAO() {
		try {
			return (DesafioDAO) Class.forName("daoImpl.DesafioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static UsuarioDAO getUsuarioDAO() {
		try {
			return (UsuarioDAO) Class.forName("daoImpl.UsuarioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void cancelarDesafio(UsuarioVO usuario) throws RemoteException {
		// TODO Auto-generated method stub

	}




}
