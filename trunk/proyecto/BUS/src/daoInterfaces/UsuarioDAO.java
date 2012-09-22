package daoInterfaces;

import excepcionesB.NotDataFoundException;
import busImpl.Usuario;


public interface UsuarioDAO {
	public Usuario findByName(String usuario) throws NotDataFoundException;

}
