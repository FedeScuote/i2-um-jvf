package daoInterfaces;


import java.util.ArrayList;

import excepcionesB.NoExisteUsuarioException;
import excepcionesB.NotDataFoundException;
import excepcionesB.YaExisteUsuarioException;
import busImpl.Usuario;


public interface UsuarioDAO {
	//terminado y probado
	public Usuario findByName(String usuario) throws NotDataFoundException;

	//terminado y probado
	public boolean agregarUsuario(String usuario, String clave, int nivelPrilegio, int virtual, int credito, int partidasGanadas, String nombre, String apellido, String pais) throws YaExisteUsuarioException;

	//terminados y probados
	public ArrayList<Usuario> getUsuariosVirtuales();
	public boolean esUsuarioVirtual(String usuario);

	//terminados y probados, el crédito a restar o sumar debe ser un valor positivo, esto devuelve el resultado
	public int restarSaldo(int credito_a_restar, int idUsuario);
	public int sumarSaldo(int credito_a_sumar, int idUsuario);

	//terminado y probado, devuelve true si el usuario dispone del credito que se solicita
	public boolean creditoSuficiente(int credito, int idUsuario);

	//terminados y probados
	public void cambiarNombre(String usuario, String nuevoUsuario) throws NoExisteUsuarioException;
	public void cambiarPassword(String usuario, String nuevaPassword)throws NoExisteUsuarioException;




}
