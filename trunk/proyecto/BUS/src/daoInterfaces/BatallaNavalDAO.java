package daoInterfaces;

import java.util.ArrayList;

import busImpl.Estados;
import busImpl.batallaNaval.Celda;
import busImpl.batallaNaval.Disparo;
import busImpl.batallaNaval.RegistroDisparo;
import busImpl.batallaNaval.Tablero;

public interface BatallaNavalDAO {

	//terminados y optimizados
	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario);
	public void registrarDisparo(Disparo disparo, Estados estado, int idUsuario, int idPartida);
	public Tablero getTablero(int idPartidia,int idUsuario);
	public void registrarTablero(Tablero tablero, int idPartida);
	public void actualizarTablero(int idPartida, String usuario, boolean miTurno,int barcosSubmarinos, int barcosDestructores,int barcosCruceros,int barcosAcorazados,int barcosSubmarinosColocados, int barcosDestructoresColocados,int barcosCrucerosColocados,int barcosAcorazadosColocados );
	public void modificarCeldaTablero(int idUsuario, Celda celda, int xC, int yC);
	public boolean turnoTablero(int idUsuario);
}
