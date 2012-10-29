package junit;

import daoImpl.RankingDAODB;
import excepcionesB.NoHayRankingException;
import junit.framework.TestCase;

public class RankingUnit extends TestCase {
	private RankingDAODB r;

	public void setUp(){
		r=new RankingDAODB();
	}

	public void testGetGanadas1(){
		try {
			r.getGanadasBatallaNaval(3);
		} catch (NoHayRankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//se espera que no haya ranking
	public void testGetGanadas2(){
		try {
			r.getGanadasBatallaNaval(5);
		} catch (NoHayRankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
