package busImpl.administracion;

import java.rmi.RemoteException;

import busImpl.usuario.Usuario;

import comm.ServiciosAdministrador;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoSeEncuentraUsuarioException;
import commExceptions.UsuarioDuplicadoException;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoExisteUsuarioException;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;

public class ServiciosAdministradorImpl implements ServiciosAdministrador{

	public void acreditarSaldo(String usuario, int credito) throws RemoteException, NoSeEncuentraUsuarioException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getNivelPrivilegioB()==2){
				daoUsuario.sumarSaldo(credito, user.getIdUsuarioB());
			}else{
				throw new NoSeEncuentraUsuarioException();
			}

		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

	}

	public void agregarUsuario(String usuario, String clave, int nivelPrilegio, String nombre, String apellido, String pais) throws RemoteException, UsuarioDuplicadoException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			daoUsuario.agregarUsuario(usuario, clave, nivelPrilegio, 0, 100, 0, nombre, apellido, pais);
		} catch (YaExisteUsuarioException e) {
			throw new UsuarioDuplicadoException();
		}

	}

	public void cambiarNombre(String usuario, String password, String nuevoUsuario) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getClaveB().equals(password)){
				try {
					daoUsuario.cambiarNombre(usuario, nuevoUsuario);
				} catch (NoExisteUsuarioException e) {
				}
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}

	}

	public void cambiarPassword(String usuario, String oldPassword, String newPassword) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getClaveB().equals(oldPassword)){
				try {
					daoUsuario.cambiarPassword(usuario, newPassword);
				} catch (NoExisteUsuarioException e) {
				}
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NotDataFoundException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

	public void cobrarSaldo(String usuario, String password, int credito) throws RemoteException, MontoInsuficienteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException {
		UsuarioDAO daoUsuario = getUsuarioDAO();
		try {
			Usuario user=daoUsuario.findByName(usuario);
			if(user.getNivelPrivilegioB()==2){
				if(user.getClaveB().equals(password)){
					if(daoUsuario.creditoSuficiente(credito, user.getIdUsuarioB())){
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
				if(user.getNivelPrivilegioB()!=1){
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

}
