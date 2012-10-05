package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import comm.RankingVO;
import comm.ServiciosRanking;
import commExceptions.NoSeEncuentraUsuarioException;

import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;

public class Ranking implements ServiciosRanking {

	private String usuario;
	private int ganadas=0;


	public int getGanadas() {
		return ganadas;
	}

	public void setGanadas(int ganadas) {
		this.ganadas = ganadas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private static RankingDAO getDAO() {


		try {
			return (RankingDAO) Class.forName("daoImpl.RankingDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	public ArrayList<RankingVO> preguntarRanking() throws RemoteException {
		RankingDAO dao = getDAO();
		try {
			ArrayList<RankingVO> aux = new ArrayList<RankingVO>();
			ArrayList retorno = dao.getRankingLudo();
			int i=0;
			while(i<retorno.size()){
				RankingVO nuevo = new RankingVO();
				nuevo.nro=((Ranking)retorno.get(i)).getGanadas();
				nuevo.usuario=((Ranking)retorno.get(i)).getUsuario();
				aux.add(nuevo);
				i++;
			}
			return aux;
		//	return dao.findByName(name);
		} catch (NoHayRankingException e) {
			System.out.println("error");
		}
		return null;
	}








}
