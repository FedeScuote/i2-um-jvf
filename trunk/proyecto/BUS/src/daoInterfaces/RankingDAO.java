package daoInterfaces;

import java.util.ArrayList;

import excepcionesB.NoHayRankingException;

public interface RankingDAO {
	//construcción
	public ArrayList getRankingLudo()throws NoHayRankingException;
}
