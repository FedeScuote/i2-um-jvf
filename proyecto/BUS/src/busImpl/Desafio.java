
package busImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import busImpl.batallaNaval.JuegoBatallaNaval;
import busImpl.usuario.Usuario;
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
import excepcionesB.NoHaySuficienteCreditoUsuarioException;

public class Desafio implements ServiciosDesafio {
	private static ResourceBundle constante = ResourceBundle.getBundle("bus");
	private static Logger log = Logger.getLogger(Desafio.class);
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

	//metodo que retorno una lista de desafios, de usarios virtuales y/o reales
	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException{
		log.debug("obtengo los desafios propuestos");
		DesafioDAO dao = getDesafioDAO();
		UsuarioDAO dao1 = getUsuarioDAO();
		ArrayList<DesafioBatallaNavalVO> aux = new ArrayList<DesafioBatallaNavalVO>();
		try{
			ArrayList desafiosBDD=dao.getDesafiosUsuariosDisponibleBatallaNaval();
			int i=0;
			while(i<desafiosBDD.size()){
				log.debug("busco los desafios reales");
				DesafioBatallaNavalVO nuevo = new DesafioBatallaNavalVO();
				UsuarioVO usu = new UsuarioVO(((Desafio)desafiosBDD.get(i)).getUsuarioDesafio());
				usu.setIdUsuario(((Desafio)desafiosBDD.get(i)).getIdUsuario());
				nuevo.setApuesta(((Desafio)desafiosBDD.get(i)).getMonto());
				nuevo.setIdDesafio((((Desafio)desafiosBDD.get(i)).getIdDesafio()));
				nuevo.setUsuario(usu);
				aux.add(nuevo);
				i++;
			}
			if(desafiosBDD.size()<5){
				ArrayList<DesafioBatallaNavalVO> aux1=generarDesafios(dao1.getUsuariosVirtuales());
				for(int j=0;j<aux1.size();j++){
					aux.add(aux1.get(j));
				}
			}
			return aux;
		}catch(NoHayDesafioException e){
			log.debug("No hay desafios reales");
			return generarDesafios(dao1.getUsuariosVirtuales());
		}
	}
	//metodo auxuliar que genera desafios por parte de usuarios virtuales
	private ArrayList<DesafioBatallaNavalVO> generarDesafios(ArrayList<Usuario> usuariosVirtuales) {
		log.debug("cantidad de usuarios reales desafiantes menor que 5");
		log.debug("genero desafios por parte de usuarios virtuales");
		ArrayList<DesafioBatallaNavalVO> desafios=new ArrayList<DesafioBatallaNavalVO>();
		int cantDesafios=0;
		if(usuariosVirtuales.size()!=0){
		if(usuariosVirtuales.size()>=5){
			log.debug("tengo mas de 5 usuarios virtuales disponibles");
			cantDesafios=5;
		}else{
			log.debug("no tengo mas de 5 usuarios virtuales disponibles");
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
		try {
			dao.crearDesafio(usuario.getUsuarioB(), monto);
		} catch (NoHaySuficienteCreditoUsuarioException e) {
			throw new MontoInsuficienteException();
		}
	}

	public ArrayList<DesafioVO> getDesafiosLudo() throws RemoteException {
		return null;
	}

	private static DesafioDAO getDesafioDAO() {
		try {
			return (DesafioDAO) Class.forName(constante.getString("CLASS_FOR_NAME_DESAFIO"))
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
			return (UsuarioDAO) Class.forName(constante.getString("CLASS_FOR_NAME_USUARIO"))
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
		DesafioDAO dao = getDesafioDAO();
		dao.cancelarDesafio(usuario.getIdUsuario());
	}
	public boolean desafioDisponible(DesafioVO desafio) throws RemoteException {
		DesafioDAO dao = getDesafioDAO();
		return dao.desafioDisponible(desafio.getUsuario().getIdUsuario());
	}




}
