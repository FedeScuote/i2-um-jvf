package daoInterfaces;

import busImpl.Usuario;

public interface PartidaDAO {
	//terminado, devuelve idPartida
	public int concretarDesafio(int idDesafio,int idDesafiante);
	//terminado, devuelve true en caso de que el usuario tenga una partida pendiente, false en caso contrario
	public boolean partidaPendiente(int idUsuario);
	//terminado, devuelve la idPartida o retorna cero si no existe
	public int idPartida(int idUsuario);
	//terminado, devuelve el Usuario oponente o retorna null
	public Usuario oponente(int idUsuario);
	//metodo que finaliza la partida indicando que usuario en q partido si gano o perdio dependiendo del boolean
	//public void terminarPartida(int idPartida,int idUsuario, boolean gane);
}
