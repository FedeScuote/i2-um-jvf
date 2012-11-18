package junit;

import daoImpl.DesafioDAODB;
import excepcionesB.NoEsUsuarioVirtualException;
import excepcionesB.NoHaySuficienteCreditoUsuarioException;
import junit.framework.TestCase;

public class DesafioUnit extends TestCase {
	private DesafioDAODB dd;

	public void setUp(){
		dd=new DesafioDAODB();
	}

	public void testCrearDesafio(){
		try {
			dd.crearDesafio("lhirata", 200);
		} catch (NoHaySuficienteCreditoUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void testDesafioDisponible(){

		assertEquals("Debería haber desafio disponible",true,dd.desafioDisponible(11));
	}

	public void testDesafioDisponibleTodosLosUsuarios(){
		int idUsuario=3;
		assertEquals("Debería haber desafio disponible",false,dd.desafioDisponibleTodosLosUsuarios(idUsuario));

	}



}
