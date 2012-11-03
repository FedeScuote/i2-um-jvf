package daoInterfaces;

import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import excepcionesB.NoTieneDesafioException;
import excepcionesB.YaTieneOtroDesafioException;

public interface DesafioDAOLudo {
	//Se crea desafio siempre y cuando haya credito disponiblre
	public void crearDesafio(int idUsuario, int monto)throws NoHaySuficienteCreditoUsuarioException;
	//cada usuario va aceptando desafio hasta que hayan cuatro jugadores en el ludo
	public void aceptoDesafioLudo(int idUsuario, int idDesafio)throws YaTieneOtroDesafioException;
	//devuelve true cuando ya se inició una partida
	public boolean inicioDesafio(int idUsuario)throws NoTieneDesafioException;

}
