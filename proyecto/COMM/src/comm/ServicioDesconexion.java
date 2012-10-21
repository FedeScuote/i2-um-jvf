package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicioDesconexion extends Remote{

	public boolean hayPartidaEnCurso(UsuarioVO usuario) throws RemoteException;

}
