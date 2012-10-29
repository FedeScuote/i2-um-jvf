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

	public void testTermiarPartida(){
		p.terminarPartida(33, 1, true);
	}

	public void testTerminarPartida2(){
		p.terminarPartida(34, 6, false);
	}

	public void testConcretarDesafio(){
		p.concretarDesafio(36, 2);
	}

	public void testTerminarPartida(){
		p.terminarPartida(36, 1, false);
	}


}
