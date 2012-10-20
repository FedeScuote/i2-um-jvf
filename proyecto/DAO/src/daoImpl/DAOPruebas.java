package daoImpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import conexion.Conexion;

import excepcionesB.NoHayDesafioException;
import excepcionesB.NoHayRankingException;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteUsuarioException;
import busImpl.Usuario;
import busImpl.Desafio;

public class DAOPruebas {
	private static Logger logger = Logger.getLogger(DAOPruebas.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int idDesafio=19;
		int idDesafiante=2;
		PartidaDAODB p =new PartidaDAODB();
		p.concretarDesafio(idDesafio, idDesafiante);



	/*

		logger.warn("warn");
		logger.info("info");
		logger.fatal("fatal");
		logger.error("error");

		DesafioDAODB d=new DesafioDAODB();
		ArrayList a;
		try {
			a = d.getDesafios();

			for(int i=0;i<a.size();i++){
				System.out.println(((Desafio)a.get(i)).getUsuarioDesafio());
				System.out.println(((Desafio)a.get(i)).getMonto());
				System.out.println(((Desafio)a.get(i)).getFechaHoraInicioD());
				System.out.println(((Desafio)a.get(i)).getFechaHoraInicioD());
			}


		} catch (NoHayDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	/*
		DesafioDAODB d=new DesafioDAODB();
		try {
			ArrayList a=d.getDesafiosUsuariosDisponibleBatallaNaval();
			System.out.println(((Desafio)a.get(0)).getUsuarioDesafio());
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
