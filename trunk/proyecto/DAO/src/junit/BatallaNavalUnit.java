package junit;

import java.sql.ResultSet;
import java.sql.SQLException;

import busImpl.Celda;
import busImpl.Tablero;
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
		//b.cargarDatos

	}

	public void testGetTablero(){
		b.getTablero(33, 1);
	}

	public void testXXX(){
		b.getTablero(21, 1);
	}

	public void testXXX2(){
		//b.getXXX2
	}

	public void testGetIdTablero(){

	}


	public void tearDown(){
		//Se descargan los datos
		//b.limpiarDatos

	}




}
