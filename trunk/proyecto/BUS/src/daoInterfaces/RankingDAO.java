package daoInterfaces;

import java.util.ArrayList;

import excepcionesB.NoHayRankingException;

public interface RankingDAO {
	//construcci�n
	public ArrayList getRankingLudo()throws NoHayRankingException;
}
