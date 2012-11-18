package junit;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import conexion.Conexion;

import busImpl.batallaNaval.Celda;
import busImpl.batallaNaval.Disparo;
import busImpl.batallaNaval.Estados;
import daoExcepciones.NoExisteTableroException;
import daoImpl.BatallaNavalDAODB;

public class BatallaNavalUnit extends TestCase {
	private static Logger logger = Logger.getLogger(BatallaNavalUnit.class);
	private BatallaNavalDAODB b;
	private Conexion c;

	public void setUp(){
		//se cargan los datos
		b=new BatallaNavalDAODB();
		c=new Conexion();
		Estados estado;
		estado=Estados.TOCADO;

		Disparo disparo=new Disparo();
		int idUsuario=3;
		disparo.setColumna(2);
		disparo.setFila(2);

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		b.registrarDisparo(disparo, estado, idUsuario);


	}
	public void testModificarCeldaTablero(){
		Celda celda=new Celda();
		celda.setEstado("TOCADO");
		celda.setId(33);
		b.modificarCeldaTablero(3, celda, 0, 0);
		System.out.println((b.getTablero(3).getTabla())[0][0].getEstado());

		assertEquals("Debería dar TOCADO","TOCADO",(b.getTablero(3).getTabla())[0][0].getEstado());


	}

	public void testGetTablero(){
		b.getTablero(1);
		assertEquals("Debería dar 1 barco acorazado",1,b.getTablero(3).getCantBarcosAcorazado());
	}

	public void testGetTablero2(){
		b.getTablero(1);
	}

	public void testGetListaDeTiros(){
		assertEquals("La columna debería dar 0",2,b.getListaDeTiros(3).get(1).getDisparo().getColumna());
		assertEquals("La fila debería dar 4",2,b.getListaDeTiros(3).get(1).getDisparo().getFila());

	}

	public void testGetIdTablero(){
		try {
			assertEquals("Debería dar 15",15,b.getIdTablero(39, "fscuoteguazza"));
		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testTurnoTableroTrue(){

		assertEquals("Debería dar false",true,b.turnoTableroOld(3));
	}

	public void testTurnoTableroFalse(){
		assertEquals("Debería dar false",false,b.turnoTableroOld(6));
	}

	public void testTurnoTablero2True(){
		assertEquals("Debería dar true",true,b.turnoTablero(3));
	}

	public void testTurnoTablero2False(){
		assertEquals("Debería dar false",false,b.turnoTablero(6));
	}

	public void testActualizarTurno(){
		b.actualizarTurno(3, true);
		assertEquals("Debería dar true",true,b.getTablero(3).isMiTurno());
	}




}
