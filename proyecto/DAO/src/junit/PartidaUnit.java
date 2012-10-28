package junit;

import junit.framework.TestCase;
import daoImpl.PartidaDAODB;


public class PartidaUnit extends TestCase {
	PartidaDAODB p;

	public void setUp(){
		p=new PartidaDAODB();
	}

	public void testOponente(){
		p.oponente(1);
	}

	public void testOponente2(){
		p.oponente(2);
	}

}
