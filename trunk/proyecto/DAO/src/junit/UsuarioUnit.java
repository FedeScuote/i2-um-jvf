package junit;

import java.util.ArrayList;

import busImpl.Usuario;
import daoImpl.UsuarioDAODB;
import junit.framework.TestCase;

public class UsuarioUnit extends TestCase {
	private UsuarioDAODB ud;
	private int credito;

	public void setUp(){
		ud=new UsuarioDAODB();
		credito=100;
	}

	public void testGetResultadoCredito(){
		ud.getResultadoCredito(credito, 1);
	}
	public void testGetResultadoCredito2(){
		ud.getResultadoCredito(-credito, 1);
	}
	public void testGetUsuariosVirtuales(){
		ArrayList<Usuario> a=ud.getUsuariosVirtuales();
		System.out.println(a.get(0).getApellidoB());
	}
	public void testEsUsuarioVirtual(){
		ud.esUsuarioVirtual("pvaztourem");
	}


}
