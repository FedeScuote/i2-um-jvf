package daoInterfaces;

import java.util.ArrayList;

import excepcionesB.NoHayRankingException;

public interface RankingDAO {
	//terminado, es una colecci�n de objetos del tipo RankingDAODB
	public ArrayList getRankingLudo()throws NoHayRankingException;
	//terminado es una colecci�n de objetos del tipo RankingDAODB
	public ArrayList getRankingBatallaNaval()throws NoHayRankingException;
	//terminado es una colecci�n de objetos del tipo RankingDAODB
	public ArrayList getRankingBackgammon()throws NoHayRankingException;

}
