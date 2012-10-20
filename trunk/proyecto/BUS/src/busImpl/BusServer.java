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
				UsuarioVO retorno =new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
				retorno.setIdUsuario(nuevo.getIdUsuarioB());
				retorno.setUsuarioB(nuevo.getUsuarioB());
				return retorno;
			}else{
				throw new ContrasenaInvalidaException();
			}
		} catch (NoSeEncuentraUsuarioException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

}
