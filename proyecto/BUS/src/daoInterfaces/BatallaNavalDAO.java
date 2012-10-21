package daoInterfaces;

import java.util.ArrayList;

import busImpl.Disparo;
import busImpl.Estados;
import busImpl.RegistroDisparo;
import busImpl.Tablero;

public interface BatallaNavalDAO {

	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario);
	public Tablero getTablero(int idPartidia,int idUsuario);

	//terminados
	public void registrarDisparo(Disparo disparo, Estados estado, int idUsuario, int idPartida);
	public void regstrarTablero(Tablero tablero, int idPartida);


}
