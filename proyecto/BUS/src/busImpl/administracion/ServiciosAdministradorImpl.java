package busImpl.administracion;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import busImpl.Reporte;
import busImpl.usuario.Usuario;
import comm.ReporteVO;
import comm.ServiciosAdministrador;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoSeEncuentraUsuarioException;
import commExceptions.UsuarioDuplicadoException;
import daoInterfaces.ReporteDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoExisteUsuarioException;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;

public class ServiciosAdministradorImpl implements ServiciosAdministrador{

	private static Logger log = Logger.getLogger(ServiciosAdministradorImpl.class);

	//metodo que le eprmite acreditar saldo a un uusario
	public void acreditarSaldo(String usuario, int credito) throws RemoteException, NoSeEncuentraUsuarioException {
		log.debug("acredita salgo");
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getNivelPrivilegioB()==1){
				daoUsuario.sumarSaldo(credito, user.getIdUsuarioB());
			}else{
				throw new NoSeEncuentraUsuarioException();
			}

		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

	}

	//metodo para crear un usuario
	public void agregarUsuario(String usuario, String clave, int nivelPrilegio, String nombre, String apellido, String pais) throws RemoteException, UsuarioDuplicadoException {

		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			daoUsuario.agregarUsuario(usuario, clave, nivelPrilegio, 0, 100, 0, nombre, apellido, pais);
			log.debug("agrego usuario nuevo");
		} catch (YaExisteUsuarioException e) {
			throw new UsuarioDuplicadoException();
		}

	}
	//	metodo para crear un usuario
	public void cambiarNombre(String usuario, String password, String nuevoUsuario) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {

		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getClaveB().equals(password)){
				try {
					daoUsuario.cambiarNombre(usuario, nuevoUsuario);
					log.debug("cambio nombre de: "+usuario+" a: "+nuevoUsuario);
				} catch (NoExisteUsuarioException e) {
				}
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

	}
	//metodo para cambiarle la contraseña a un usuario
	public void cambiarPassword(String usuario, String oldPassword, String newPassword) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getClaveB().equals(oldPassword)){
				try {
					daoUsuario.cambiarPassword(usuario, newPassword);
					log.debug("cambio pass de: "+usuario);
				} catch (NoExisteUsuarioException e) {
				}
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

	//metodo para cobrar saldo
	public void cobrarSaldo(String usuario, String password, int credito) throws RemoteException, MontoInsuficienteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getNivelPrivilegioB()==1){
				if(user.getClaveB().equals(password)){
					if(daoUsuario.creditoSuficiente(credito, user.getIdUsuarioB())){
						log.debug("cobra saldo: "+usuario);
						daoUsuario.restarSaldo(credito, user.getIdUsuarioB());
					}else{
						throw new MontoInsuficienteException();
					}
				}else{
					throw new ContrasenaInvalidaException();
				}
			}else{
				throw new NoSeEncuentraUsuarioException();
			}


		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}


	}

	public UsuarioVO login(String usuario, String clave) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getClaveB().equals(clave)){
				if(user.getNivelPrivilegioB()!=1&&user.getVirtualB()==0){
					UsuarioVO nuevo=new UsuarioVO(user.getUsuarioB());
					nuevo.setIdUsuario(user.getIdUsuarioB());
					nuevo.setNombreB(user.getNombreB());
					nuevo.setApellidoB(user.getApellidoB());
					nuevo.setNivelPrivilegio(user.getNivelPrivilegioB());
					return nuevo;
				}else{
					throw new NoSeEncuentraUsuarioException();
				}
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

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
	private static ReporteDAO getReporteDAO() {
		try {
			return (ReporteDAO) Class.forName("daoImpl.ReporteDAODB")
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
	public ReporteVO getReporte() throws RemoteException {
		ReporteDAO daoReporte = getReporteDAO();
		Reporte repor=daoReporte.getReporte();
		ReporteVO nuevo = new ReporteVO();
		nuevo.setMontoTotalComisionesJVF(repor.getMontoTotalComisionesJVF());
		nuevo.setMontoTotalNoVirtuales(repor.getMontoTotalNoVirtuales());
		nuevo.setMontoTotalVirtuales(repor.getMontoTotalVirtuales());
		return nuevo;
	}

}
