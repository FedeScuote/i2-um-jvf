package daoInterfaces;

import java.util.ArrayList;

import busImpl.batallaNaval.Celda;
import busImpl.batallaNaval.Disparo;
import busImpl.batallaNaval.Estados;
import busImpl.batallaNaval.RegistroDisparo;
import busImpl.batallaNaval.Tablero;

public interface BatallaNavalDAO {

	//terminados y optimizados
	public ArrayList<RegistroDisparo> getListaDeTiros(int idUsuario);
	public void registrarDisparo(Disparo disparo, Estados estado, int idUsuario);
	public Tablero getTablero(int idUsuario);
	public void registrarTablero(Tablero tablero, int idPartida);
	public void actualizarTablero(int idUsuario, boolean miTurno,int barcosSubmarinos, int barcosDestructores,int barcosCruceros,int barcosAcorazados,int barcosSubmarinosColocados, int barcosDestructoresColocados,int barcosCrucerosColocados,int barcosAcorazadosColocados );
	public void modificarCeldaTablero(int idUsuario, Celda celda, int xC, int yC);
	public boolean turnoTablero(int idUsuario);
	//acutaliza el turno siempre y cuando exista un desafío en curso
	public void actualizarTurno(int idUsuario, boolean turno);
}
