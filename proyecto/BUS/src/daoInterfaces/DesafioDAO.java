package daoInterfaces;

import java.util.ArrayList;


import excepcionesB.NoEsUsuarioVirtualException;
import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;

public interface DesafioDAO {

	//terminado, te devuelve una lista de objetos Desafio con los datos de usuario, monto, estadoD y fechaHoraInicioD.
	public ArrayList getDesafios() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Batalla naval
	public ArrayList getDesafiosUsuariosDisponibleBatallaNaval() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Batalla naval
	public ArrayList getDesafiosUsuariosDisponibleLudo() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Backgammon
	public ArrayList getDesafiosUsuariosDisponibleBackgammon() throws NoHayDesafioException;
	//terminado, devuelve idDesafio, si existe desafio retorna cero
	public int crearDesafio(String usuario,int monto)throws NoHaySuficienteCreditoUsuarioException;
	//terminado, metodo para saber si luego de creado un desafio alguien lo acepto lo que transformo el desafio en una partida
	public boolean aceptaronDesafio(int idUsuario);

	//metodo que cancela un desafio propuesto por ese usuario
	public void cancelarDesafio(int idUsuario);

	public ArrayList getDesafiosBatallaNaval() throws NoHayDesafioException;

	//devuelve true si el usuario virutal no tiene desafio en curso, devuelve true si el usuario no virtual tiene un desafio "En hora"
	public boolean desafioDisponibleTodosLosUsuarios(int idUsuario);

}
