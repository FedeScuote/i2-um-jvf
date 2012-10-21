package daoInterfaces;

import java.util.ArrayList;

import busImpl.Disparo;
import busImpl.Estados;
import busImpl.RegistroDisparo;
import busImpl.Tablero;

public interface BatallaNavalDAO {

	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario);
	//construcción
	public Tablero getTablero(int idPartidia,int idUsuario); //falta las celdas
	public void regstrarTablero(Tablero tablero, int idPartida); //falta las celdas
	//terminados
	public void registrarDisparo(Disparo disparo, Estados estado, int idUsuario, int idPartida);



}
