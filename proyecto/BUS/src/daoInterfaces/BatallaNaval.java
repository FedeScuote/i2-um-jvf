package daoInterfaces;

import java.util.ArrayList;

import busImpl.Disparo;
import busImpl.Estados;
import busImpl.RegistroDisparo;
import busImpl.Tablero;

public interface BatallaNaval {

	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario);
	public Tablero getTablero(int idPartidia,int idUsuario);

	public void registrarDisparo(Disparo disparo, Estados estado);
	public void regstrarTablero(Tablero tablero);


}
