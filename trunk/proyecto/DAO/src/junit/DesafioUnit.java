package junit;

import daoImpl.DesafioDAODB;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import junit.framework.TestCase;

public class DesafioUnit extends TestCase {
	private DesafioDAODB dd;

	public void setUp(){
		dd=new DesafioDAODB();
	}

	public void testCrearDesafio(){
		try {
			dd.crearDesafio("jhirata", 200);
		} catch (NoHaySuficienteCreditoUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
