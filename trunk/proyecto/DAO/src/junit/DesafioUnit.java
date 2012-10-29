package junit;

import daoImpl.DesafioDAODB;
import junit.framework.TestCase;

public class DesafioUnit extends TestCase {
	private DesafioDAODB dd;

	public void setUp(){
		dd=new DesafioDAODB();
	}

	public void testCrearDesafio(){
		dd.crearDesafio("jhirata", 200);

	}



}
