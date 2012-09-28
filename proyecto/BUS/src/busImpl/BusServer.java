package busImpl;

import java.rmi.RemoteException;

import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class BusServer implements ServiciosUsuario {
	// implemento mi logear
	public UsuarioVO login(String usuario, String contrase�a) throws RemoteException,NoSeEncuentraUsuarioException,ContrasenaInvalidaException {

		try {
			Usuario nuevo = Usuario.findByName(usuario);
			if(nuevo.getClaveB().equals(contrase�a)){
				return new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NoSeEncuentraUsuarioException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

}
