package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import comm.RankingVO;
import comm.ServiciosRanking;
import commExceptions.NoSeEncuentraUsuarioException;

import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;

public class Ranking implements ServiciosRanking {

	private static ResourceBundle constante = ResourceBundle.getBundle("bus");
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
			return (RankingDAO) Class.forName(constante.getString("CLASS_FOR_NAME_RANKING"))
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		}

	public ArrayList<RankingVO> preguntarRanking() throws RemoteException {
		RankingDAO dao = getDAO();
		ArrayList<RankingVO> aux = new ArrayList<RankingVO>();
		try {
			ArrayList retorno = dao.getRankingGeneral();
			int i=0;
			while(i<retorno.size()){
				RankingVO nuevo = new RankingVO();
				nuevo.nro=((Ranking)retorno.get(i)).getGanadas();
				nuevo.usuario=((Ranking)retorno.get(i)).getUsuario();
				aux.add(nuevo);
				i++;
			}

		} catch (NoHayRankingException e) {
		}
		return aux;
	}








}
