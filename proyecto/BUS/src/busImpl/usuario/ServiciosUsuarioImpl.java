package busImpl.usuario;

import java.rmi.RemoteException;
import java.util.ResourceBundle;


import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class ServiciosUsuarioImpl implements ServiciosUsuario {

	// implemento mi logear

	public UsuarioVO login(String usuario, String contraseņa) throws RemoteException,NoSeEncuentraUsuarioException,ContrasenaInvalidaException {

		try {
			Usuario nuevo = Usuario.findByName(usuario);
			if(nuevo.getNivelPrivilegioB()==1&&nuevo.getVirtualB()==0){
				if(nuevo.getClaveB().equals(contraseņa)){
					UsuarioVO retorno =new UsuarioVO(nuevo.getNombreB(), nuevo.getApellidoB());
					retorno.setIdUsuario(nuevo.getIdUsuarioB());
					retorno.setUsuarioB(nuevo.getUsuarioB());
					retorno.setSaldo(nuevo.getCreditoB());
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
