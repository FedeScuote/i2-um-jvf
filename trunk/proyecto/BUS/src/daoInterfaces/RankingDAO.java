package daoInterfaces;

import java.util.ArrayList;

import excepcionesB.NoHayRankingException;

public interface RankingDAO {
	//terminado, es una colección de objetos del tipo Ranking
	public ArrayList getRankingLudo()throws NoHayRankingException;
	//terminado es una colección de objetos del tipo Ranking
	public ArrayList getRankingBatallaNaval()throws NoHayRankingException;
	//terminado es una colección de objetos del tipo Ranking
	public ArrayList getRankingBackgammon()throws NoHayRankingException;
	//terminado  es una colección de objetos del tipo Ranking
	public ArrayList getRankingGeneral() throws NoHayRankingException;

}
