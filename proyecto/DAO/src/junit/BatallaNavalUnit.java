package junit;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import busImpl.batallaNaval.*;

import busImpl.Estados;

import busImpl.Usuario;
import conexion.Conexion;
import daoImpl.BatallaNavalDAODB;
import daoImpl.UsuarioDAODB;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteTableroException;
import excepcionesD.NoExisteUsuarioException;
import junit.framework.TestCase;

public class BatallaNavalUnit extends TestCase {
	private static Logger logger = Logger.getLogger(BatallaNavalUnit.class);
	private BatallaNavalDAODB b;


	public void setUp(){
		//se cargan los datos
		b=new BatallaNavalDAODB();
		Estados estado;
		estado=Estados.TOCADO;

		Disparo disparo=new Disparo();
		int idUsuario=3;
		int idPartida=39;
		disparo.setColumna(2);
		disparo.setFila(2);

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		b.registrarDisparo(disparo, estado, idUsuario);
		//b.cargarDatos

	}
	public void testModificarCeldaTablero(){
		Celda celda=new Celda();
		celda.setEstado("AGUA");
		celda.setId(33);
		b.modificarCeldaTablero(3, celda, 0, 0);
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



	public void tearDown(){
		//Se descargan los datos
		//b.limpiarDatos

	}




}
