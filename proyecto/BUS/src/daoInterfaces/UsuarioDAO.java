package daoInterfaces;


import java.util.ArrayList;

import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;
import busImpl.Usuario;


public interface UsuarioDAO {
	//Implementado
	public Usuario findByName(String usuario) throws NotDataFoundException;

	//No implementado
	public boolean agregarUsuario(String usuario, String clave, int nivelPrilegio, int virtual, int credito, int partidasGanadas, String nombre, String apellido, String pais) throws YaExisteUsuarioException;


	public ArrayList<Usuario> getUsuariosVirtuales();

	public boolean esUsuarioVirtual(String usuario);




}
