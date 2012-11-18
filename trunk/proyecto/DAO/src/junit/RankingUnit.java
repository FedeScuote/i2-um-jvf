package junit;

import daoImpl.RankingDAODB;
import excepcionesB.NoHayRankingException;
import junit.framework.TestCase;
import busImpl.Ranking;

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

	public void testGetRankingGeneral(){
		try {
			assertEquals("Debería dar 8",8,((Ranking)r.getRankingGeneral().get(0)).getGanadas());
			assertEquals("Se espera un valor correcto fscuoteguazza","fscuoteguazza",((Ranking)r.getRankingGeneral().get(0)).getUsuario());

			assertEquals("Debería dar 7",7,((Ranking)r.getRankingGeneral().get(1)).getGanadas());
			assertEquals("Se espera un valor correcto jhirata","jhirata",((Ranking)r.getRankingBatallaNaval().get(1)).getUsuario());




		} catch (NoHayRankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
