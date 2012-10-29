package junit;

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


}
