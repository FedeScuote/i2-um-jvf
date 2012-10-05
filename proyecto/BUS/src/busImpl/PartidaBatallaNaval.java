package busImpl;

import java.rmi.RemoteException;

import comm.ServicioPartidaBatallaNaval;
import comm.UsuarioVO;
import commExceptions.NoSeEncuentraUsuarioException;

public class PartidaBatallaNaval implements ServicioPartidaBatallaNaval {

	private int idPartida;
	private JuegoBatallaNaval juego = new JuegoBatallaNaval();
	private int cantJ=0;


	public void jugarBatallaNaval(UsuarioVO usuario) throws RemoteException{
		try {
			Usuario u= Usuario.findByName(usuario.getUsuarioB());
			if(cantJ==0){
			juego.getTableroJugador1().setJugador(u);

			}else{
				juego.getTableroJugador2().setJugador(u);
			}
		} catch (NoSeEncuentraUsuarioException e) {
		}
	}






}
