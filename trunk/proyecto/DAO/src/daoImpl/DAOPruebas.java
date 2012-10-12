package daoImpl;


import java.util.ArrayList;
import java.util.Date;

import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;
import busImpl.Usuario;
import busImpl.Desafio;

public class DAOPruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		DesafioDAODB d=new DesafioDAODB();
		try {
			ArrayList a=d.getDesafiosBatallaNaval();
			System.out.println(((Desafio)a.get(0)).getIdDesafio());
		} catch (NoHayDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	/*	RankingDAODB r=new RankingDAODB();
		try {
			for(int i=0;i<r.getRankingGeneral().size();i++){

					System.out.println(((RankingDAODB)(r.getRankingGeneral().get(i))).getUsuario());
					System.out.println(((RankingDAODB)(r.getRankingGeneral().get(i))).getGanadas());



			}
		} catch (NoHayRankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*try {
			System.out.println(r.getUsuario(0));
		} catch (NoExisteUsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UsuarioDAODB ud=new UsuarioDAODB();
		Usuario u=new Usuario();


		try {
			u=ud.findByName("jhirat");
			System.out.println(u.getApellidoB());
		} catch (NotDataFoundException e) {

			e.printStackTrace();
		}
		if(u.getApellidoB()==null){
			System.out.println("no encontré datos ja ja");

			//throw new NotDataFoundException();
		}
	   */
	}

}
