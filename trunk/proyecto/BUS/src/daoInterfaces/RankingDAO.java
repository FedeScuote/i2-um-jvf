package daoInterfaces;

import java.util.ArrayList;

import excepcionesB.NoHayRankingException;

public interface RankingDAO {
	//terminado,
	public ArrayList getRankingLudo()throws NoHayRankingException;

	//Construcci�n
	public ArrayList getRankingBatallaNaval()throws NoHayRankingException;
	public ArrayList getRankingBackgammon()throws NoHayRankingException;

}
