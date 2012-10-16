package daoInterfaces;

import java.util.ArrayList;


import excepcionesB.NoHayDesafioException;

public interface DesafioDAO {

	//terminado, te devuelve una lista de objetos Desafio con los datos de usuario, monto, estadoD y fechaHoraInicioD.
	public ArrayList getDesafios() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Batalla naval
	public ArrayList getDesafiosUsuariosDisponibleBatallaNaval() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Batalla naval
	public ArrayList getDesafiosUsuariosDisponibleLudo() throws NoHayDesafioException;
	//terminado, idem anterior pero solo para Backgammon
	public ArrayList getDesafiosUsuariosDisponibleBackgammon() throws NoHayDesafioException;

	public ArrayList getDesafiosBatallaNaval() throws NoHayDesafioException;
}
