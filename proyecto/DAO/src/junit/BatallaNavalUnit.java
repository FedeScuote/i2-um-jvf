package junit;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	private BatallaNavalDAODB b;


	public void setUp(){
		//se cargan los datos
		b=new BatallaNavalDAODB();
		Estados estado;
		estado=Estados.TOCADO;

		Disparo disparo=new Disparo();
		int idUsuario=1;
		int idPartida=33;
		disparo.setColumna(2);
		disparo.setFila(2);

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		b.registrarDisparo(disparo, estado, idUsuario, idPartida);
		//b.cargarDatos

	}
	public void testModificarCeldaTablero(){
		Celda celda=new Celda();
		celda.setEstado("AGUA");
		celda.setId(33);
		b.modificarCeldaTablero(3, celda, 0, 0);
	}

	public void testGetTablero(){
		b.getTablero(33, 1);
	}

	public void testGetTablero2(){
		b.getTablero(21, 1);
	}

	public void testGetListaDeTiros(){
		b.getListaDeTiros(33, 1);
	}

	public void testGetIdTablero(){

	}


	public void tearDown(){
		//Se descargan los datos
		//b.limpiarDatos

	}




}
