package busImpl;

import java.rmi.RemoteException;

import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.NoSeEncuentraUsuarioException;

public class ServicioUsuarioImpl implements ServiciosUsuario {

	public UsuarioVO login(String usuario, String contraseña) throws RemoteException,NoSeEncuentraUsuarioException {

		try {
			Usuario nuevo = Usuario.findByName(usuario);
			return new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
		} catch (NoSeEncuentraUsuarioException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

}
