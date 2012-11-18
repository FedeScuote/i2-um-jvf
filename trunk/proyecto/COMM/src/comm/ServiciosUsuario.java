package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;

import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;
public interface ServiciosUsuario extends Remote{
	public UsuarioVO login(String usuario,String contraseņa) throws RemoteException, NoSeEncuentraUsuarioException, ContrasenaInvalidaException;
}
