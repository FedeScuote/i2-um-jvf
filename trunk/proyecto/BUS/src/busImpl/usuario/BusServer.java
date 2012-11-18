package busImpl.usuario;

import java.rmi.RemoteException;


import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class BusServer implements ServiciosUsuario {
	// implemento mi logear
	public UsuarioVO login(String usuario, String contraseña) throws RemoteException,NoSeEncuentraUsuarioException,ContrasenaInvalidaException {

		try {
			Usuario nuevo = Usuario.findByName(usuario);
			if(nuevo.getNivelPrivilegioB()==2&&nuevo.getVirtualB()==0){
				if(nuevo.getClaveB().equals(contraseña)){
					UsuarioVO retorno =new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
					retorno.setIdUsuario(nuevo.getIdUsuarioB());
					retorno.setUsuarioB(nuevo.getUsuarioB());
					return retorno;
				}else{
					throw new ContrasenaInvalidaException();
				}
			}else{
				throw new NoSeEncuentraUsuarioException();
			}

		} catch (NoSeEncuentraUsuarioException e) {
			throw new NoSeEncuentraUsuarioException();
		}
	}

}
