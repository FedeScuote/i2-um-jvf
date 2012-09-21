package daoImpl;

import busImpl.Usuario;
import excepcionesB.NotDataFoundException;

public class PruebasD {

	/**
	 * @param args
	 * @throws NotDataFoundException
	 */
	public static void main(String[] args){
		UsuarioDAODB ud=new UsuarioDAODB();
		Usuario ub=new Usuario();

				try {
					ub= ud.findByName("jhirata");
				} catch (NotDataFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



		System.out.println(ub.getApellidoB());

	}

}
