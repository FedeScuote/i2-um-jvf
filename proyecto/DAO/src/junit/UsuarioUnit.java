package junit;

import java.util.ArrayList;

import junit.framework.TestCase;
import busImpl.usuario.Usuario;
import daoImpl.UsuarioDAODB;
import excepcionesB.NoExisteUsuarioException;
import excepcionesB.YaExisteUsuarioException;

public class UsuarioUnit extends TestCase {
	private UsuarioDAODB ud;
	private int credito;

	public void setUp(){
		ud=new UsuarioDAODB();
		credito=100;
	}

	public void testGetResultadoCredito(){
		System.out.println("credito= "+ud.getResultadoCredito(credito, 1));
	}
	public void testGetResultadoCredito2(){
		ud.getResultadoCredito(-credito, 1);
	}

	public void testSumarSaldo(){
		ud.sumarSaldo(300, 1);
	}
	public void testRestarSaldo(){
		ud.restarSaldo(300, 1);
	}

	public void testGetUsuariosVirtuales(){
		ArrayList<Usuario> a=ud.getUsuariosVirtuales();
		System.out.println(a.get(0).getApellidoB());
	}
	public void testEsUsuarioVirtual(){
		ud.esUsuarioVirtual("pvaztourem");
	}
	public void testAgregarUsuario(){
		try {
			ud.agregarUsuario("lhirata", "lh", 2, 0, 3000, 0, "Lisa", "Hirata", "Uruguay");
		} catch (YaExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testCambiarNombre(){
		try {
			ud.cambiarNombre("johnhirata", "jhirata");
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testCambiarPassword(){
		try {
			ud.cambiarPassword("jhirata", "jh");
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
