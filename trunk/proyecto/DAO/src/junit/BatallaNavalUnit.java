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
	private BatallaNavalDAODB b=new BatallaNavalDAODB();


	public Tablero getTablero(int idPartida, int idUsuario) {

		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		String usuario=null;
		try {
			usuario=ud.getUsuario(idUsuario);
			u=ud.findByName(usuario);
		} catch (NoExisteUsuarioException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tablero t=new Tablero(u);
		try {

			int idDesafio = idPartida;
			int idTablero=this.getIdTablero(idDesafio, usuario);
			Conexion c=new Conexion();
			ResultSet r = null;
			ResultSet r2= null;

			String jugador;
			boolean miTurno2;
			int miTurno=0;
			int barcosSubmarinos=0;
			int barcosDestructores=0;
			int barcosCruceros=0;
			int barcosAcorazados=0;
			int barcosSubmarinosColocados=0;
			int barcosDestructoresColocados=0;
			int barcosCrucerosColocados=0;
			int barcosAcorazadosColocados=0;

			//obtengo los registros del tablero de Batalla Naval
			r = c
			.devolverResutado("SELECT jugador, miTurno, barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados FROM t_batalla_naval WHERE desafios_idDesafio='"
					+ idDesafio + "' AND jugador='"+usuario+"'");

			if(r.first()){
				jugador = r.getString("jugador");
				miTurno= r.getInt("miTurno");

				barcosSubmarinos=r.getInt("barcosSubmarinos");
				barcosDestructores=r.getInt("barcosDestructores");
				barcosCruceros=r.getInt("barcosCruceros");
				barcosAcorazados=r.getInt("barcosAcorazados");
				barcosSubmarinosColocados=r.getInt("barcosSubmarinosColocados");
				barcosDestructoresColocados=r.getInt("barcosDestructoresColocados");
				barcosCrucerosColocados=r.getInt("barcosCrucerosColocados");
				barcosAcorazadosColocados=r.getInt("barcosAcorazadosColocados");
			}else{
				throw new NoExisteTableroException();
			}

			if(miTurno==1){
				miTurno2=true;
			}else{
				miTurno2=false;
			}
			t.setMiTurno(miTurno2);
			t.setCantBarcosSubmarino(barcosSubmarinos);
			t.setCantBarcosSubmarinoColocados(barcosSubmarinosColocados);
			t.setCantBarcosCruceros(barcosCruceros);
			t.setCantBarcosCrucerosColocados(barcosCrucerosColocados);
			t.setCantBarcosDestructores(barcosDestructores);
			t.setCantBarcosDestructoresColocados(barcosDestructoresColocados);
			t.setCantBarcosAcorazado(barcosAcorazados);
			t.setCantBarcosAcorazadoColocados(barcosAcorazadosColocados);

			//obtengo los registros de celdas
			r2=c.devolverResutado("SELECT xC, yC, id, estado FROM celdas WHERE t_batalla_naval_idTBatallaNaval="+idTablero);
			Celda[][] celdaMatriz=new Celda[10][10];
			while(r2.next()){
				Celda celda=new Celda();
				int xC=r2.getInt("xC");
				int yC=r2.getInt("yC");
				int id=r2.getInt("id");
				//logger.debug("paso el id de celda: "+id);
				String estado=r2.getString("estado");
				celda.setEstado(estado);
				celda.setId(id);
				celdaMatriz[xC][yC]=celda;
			}
			t.setTabla(celdaMatriz);
			c.disconnect();

		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;

	}




	public int getIdTablero(int idDesafio,String jugador) throws NoExisteTableroException {

		Conexion c = new Conexion();

		ResultSet r = null;
		int idTablero = 0;
		try {
			r = c
					.devolverResutado("SELECT idTBatallaNaval FROM t_batalla_naval WHERE desafios_idDesafio='"
							+ idDesafio + "' AND jugador='"+jugador+"'");

			if(r.first()){
				idTablero = r.getInt("idTBatallaNaval");
			}else{
				throw new NoExisteTableroException();
			}
		} catch (SQLException ex) {
			throw new NoExisteTableroException();

		}
		return idTablero;

	}

	public void testGetTablero(){
		b.getTablero(33, 1);
	}



}
