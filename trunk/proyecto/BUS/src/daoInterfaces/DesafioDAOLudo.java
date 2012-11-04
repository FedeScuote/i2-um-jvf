package daoInterfaces;

import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import excepcionesB.NoTieneDesafioException;
import excepcionesB.YaTieneOtroDesafioException;

public interface DesafioDAOLudo {
	//terminado y probado, se crea desafio siempre y cuando haya credito disponible
	public void crearDesafioLudo(int idUsuario, int monto)throws NoHaySuficienteCreditoUsuarioException;

	//cada usuario va aceptando desafio hasta que hayan cuatro jugadores en el ludo
	public void aceptoDesafioLudo(int idUsuario, int idDesafio)throws YaTieneOtroDesafioException, NoHaySuficienteCreditoUsuarioException;

	//devuelve true cuando ya se inició una partida
	public boolean inicioDesafioLudo(int idUsuario)throws NoTieneDesafioException;

}
