package busImpl.Junit;

import java.rmi.RemoteException;

import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;

import busImpl.batallaNaval.Disparo;
import busImpl.batallaNaval.Estados;
import busImpl.batallaNaval.JuegoBatallaNaval;
import busImpl.usuario.Usuario;
import junit.framework.TestCase;

public class JuegoBatallaNavalUnit extends TestCase{
	private JuegoBatallaNaval bn;

	public void setUp(){
		Usuario jugador1= new Usuario();
		jugador1.setUsuarioB("Jugador 1");
		jugador1.setIdUsuarioB(1);
		Usuario jugador2= new Usuario();
		jugador1.setUsuarioB("Jugador 2");
		jugador2.setIdUsuarioB(2);
		bn = new JuegoBatallaNaval(jugador1,jugador2);
	}

	public void testAgregarBarco(){
		UsuarioVO jugador2= new UsuarioVO("Jugador 2");
		jugador2.setIdUsuario(2);
		try {
			bn.agregarBarco(jugador2, 2, 1, 1, 3, "CRUCERO");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof CoordenadasInvalidasException);
		}
		try {
			bn.agregarBarco(jugador2, 1, 1, 1, 3, "CRUCERO");
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}
	public void testDispararBarco(){
		UsuarioVO jugador1= new UsuarioVO("Jugador 1");
		jugador1.setIdUsuario(1);
		int dis[]=bn.distribucion();
		bn.getTableroJugador2().agregarCeldas(dis);
		for(int i=0;i<30;i++){
			try{
				Disparo disp=bn.proximoDisparoOptimo();
				assertTrue(disp.getFila()>=0&&disp.getFila()<10&&disp.getColumna()>=0&&disp.getColumna()<10);
			}catch(Exception e){
				fail();
			}

		}
	}
}
