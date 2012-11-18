package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commExceptions.ContrasenaInvalidaException;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoSeEncuentraUsuarioException;
import commExceptions.UsuarioDuplicadoException;






public interface ServiciosAdministrador extends Remote{

	public UsuarioVO login(String usuario,String clave) throws RemoteException,NoSeEncuentraUsuarioException, ContrasenaInvalidaException;
	public void agregarUsuario(String usuario, String clave, int nivelPrilegio, String nombre, String apellido, String pais) throws RemoteException, UsuarioDuplicadoException;
	public void cambiarNombre(String usuario,String password, String nuevoUsuario) throws RemoteException,NoSeEncuentraUsuarioException, ContrasenaInvalidaException;
	public void cambiarPassword(String usuario,String oldPassword, String newPassword) throws RemoteException,NoSeEncuentraUsuarioException, ContrasenaInvalidaException;
	public void cobrarSaldo(String usuario, String password,int credito) throws RemoteException, MontoInsuficienteException,NoSeEncuentraUsuarioException, ContrasenaInvalidaException;
	public void acreditarSaldo(String usuario, int credito) throws RemoteException,NoSeEncuentraUsuarioException;
	public ReporteVO getReporte() throws RemoteException;
}
