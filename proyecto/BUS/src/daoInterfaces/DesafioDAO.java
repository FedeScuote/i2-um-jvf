package daoInterfaces;

import java.util.ArrayList;


import excepcionesB.NoHayDesafioException;

public interface DesafioDAO {

	//construcción
	public ArrayList getDesafios() throws NoHayDesafioException;


}
