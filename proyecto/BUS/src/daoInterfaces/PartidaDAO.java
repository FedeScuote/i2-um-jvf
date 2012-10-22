package daoInterfaces;

public interface PartidaDAO {
	//terminado, devuelve idPartida
	public int concretarDesafio(int idDesafio,int idDesafiante);
	//devuelve true en caso de que el usuario tenga una partida pendiente, false en caso contrario
	public boolean partidaPendiente(int idUsuario);

}
