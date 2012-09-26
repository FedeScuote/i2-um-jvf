package busImpl;

import java.rmi.RemoteException;

import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class ServicioUsuarioImpl implements ServiciosUsuario {

	public UsuarioVO login(String usuario, String contraseņa) throws RemoteException,NoSeEncuentraUsuarioException, ContrasenaInvalidaException {

		try {
			Usuario nuevo = Usuario.findByName(usuario);
			if(nuevo.getClaveB().equals(contraseņa)){
				return new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NoSeEncuentraUsuarioException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

}
