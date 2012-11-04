package junit;

import daoImpl.DesafioDAODB;
import daoImpl.DesafioDAODBLudo;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;

public class DesafioLudoUnit {

	private DesafioDAODBLudo ddl;

	public void setUp(){
		ddl=new DesafioDAODBLudo();
	}

	public void testCrearDesafio(){
		try {
			ddl.crearDesafioLudo(1, 200);
		} catch (NoHaySuficienteCreditoUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
