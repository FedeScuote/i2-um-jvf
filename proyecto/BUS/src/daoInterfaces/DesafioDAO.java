package daoInterfaces;

import java.util.ArrayList;


import excepcionesB.NoHayDesafioException;

public interface DesafioDAO {

	//construcci�n
	public ArrayList getDesafios() throws NoHayDesafioException;


}
