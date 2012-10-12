package daoInterfaces;

import java.util.ArrayList;


import excepcionesB.NoHayDesafioException;

public interface DesafioDAO {

	//terminados
	public ArrayList getDesafios() throws NoHayDesafioException;
	public ArrayList getDesafiosBatallaNaval() throws NoHayDesafioException;
	//en construcci�n
	public ArrayList getDesafiosUsuariosDisponibleBatallaNaval() throws NoHayDesafioException;

}
