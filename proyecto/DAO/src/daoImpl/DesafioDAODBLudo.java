package daoImpl;

import daoInterfaces.DesafioDAOLudo;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import excepcionesB.NoTieneDesafioException;
import excepcionesB.YaTieneOtroDesafioException;

public class DesafioDAODBLudo implements DesafioDAOLudo{

	public void aceptoDesafioLudo(int idUsuario, int idDesafio)
			throws YaTieneOtroDesafioException {
		// TODO Auto-generated method stub

	}

	public void crearDesafio(int idUsuario, int monto)
			throws NoHaySuficienteCreditoUsuarioException {
		// TODO Auto-generated method stub

	}

	public boolean inicioDesafio(int idUsuario) throws NoTieneDesafioException {
		// TODO Auto-generated method stub
		return false;
	}

}
