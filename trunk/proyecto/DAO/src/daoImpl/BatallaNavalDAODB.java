package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import conexion.Conexion;

import busImpl.Celda;
import busImpl.Disparo;
import busImpl.Estados;
import busImpl.RegistroDisparo;
import busImpl.Tablero;
import busImpl.Usuario;
import daoInterfaces.BatallaNavalDAO;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteTableroException;
import excepcionesD.NoExisteUsuarioException;

public class BatallaNavalDAODB implements BatallaNavalDAO {
	private static Logger logger = Logger.getLogger(DAOPruebas.class);


	//terminados

	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario) {
		ArrayList<RegistroDisparo> ard=new ArrayList();
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		Estados estado=null;
		int idDesafio=idPartida;
		String jugador;
		int idTablero;

		try {
			jugador = ud.getUsuario(idUsuario);
			idTablero=this.getIdTablero(idDesafio, jugador);
			ResultSet r=c.devolverResutado("SELECT resultadoD, xD, yD FROM disparos WHERE t_batalla_naval_idTBatallaNaval="+idTablero);
			while(r.next()){
				Disparo disparo=new Disparo();
				String resultado=r.getString("resultadoD");
				int fila=r.getInt("xD");
				int columna=r.getInt("yD");

				if(resultado.equals("AGUA")){
					estado=Estados.AGUA;
				}else if(resultado.equals("HUNDIDO")){
					estado=Estados.HUNDIDO;
				}else if(resultado.equals("TOCADO")){
					estado=Estados.TOCADO;
				}

				disparo.setFila(fila);
				disparo.setColumna(r.getInt(columna));
				RegistroDisparo rd=new RegistroDisparo(estado,disparo);
				ard.add(rd);
			}

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ard;
	}

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

			r.next();
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
			Celda celda=new Celda();
			Celda[][] celdaMatriz=new Celda[10][10];
			while(r2.next()){
				int xC=r2.getInt("xC");
				int yC=r2.getInt("yC");
				int id=r2.getInt("id");
				logger.debug("paso el id de celda: "+id);
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

	public void registrarDisparo(Disparo disparo, Estados estado,int idUsuario, int idPartida) {
		int xD = disparo.getFila();
		int yD = disparo.getColumna();
		String resultadoD = estado.toString();
		int idDesafio = idPartida;

		Conexion c = new Conexion();
		BatallaNavalDAODB b = new BatallaNavalDAODB();
		UsuarioDAODB ud=new UsuarioDAODB();


		try {
			String jugador=ud.getUsuario(idUsuario);
			int idTablero = b.getIdTablero(idDesafio,jugador);
			c.ingresarNuevaTuplaDeCincoColumnas2("disparos", "idDisparo",
					"t_batalla_naval_idTBatallaNaval", "idUsuarioD",
					"resultadoD", "xD", "yD", idTablero, idUsuario, resultadoD,
					xD, yD);

			//c.actualizarTuplaDeUnaColumna2("celdas", "t_batalla_naval_idTBatallaNaval", "xC", "yC", idTablero, xD, yD, "estado", resultadoD);

		} catch (SQLException e) {
			// logger.error("Error de sql");
			e.printStackTrace();
		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.disconnect();

	}

	public void regstrarTablero(Tablero tablero, int idPartida) {
		int idDesafio=idPartida;
		String jugador=((Usuario)(tablero.getJugador())).getUsuarioB();
		int idTablero=0;
		try {
			idTablero = this.getIdTablero(idDesafio, jugador);
			boolean miTurno=tablero.isMiTurno();
			int miTurno2=3;
			if(miTurno){
				miTurno2=1;
			}else{
				miTurno2=0;
			}
			int barcosSubmarinos=tablero.getCantBarcosSubmarino();
			int barcosDestructores=tablero.getCantBarcosDestructores();
			int barcosCruceros=tablero.getCantBarcosDestructores();
			int barcosAcorazados=tablero.getCantBarcosAcorazado();
			int barcosSubmarinosColocados=tablero.getCantBarcosSubmarinoColocados();
			int barcosDestructoresColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosCrucerosColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosAcorazadosColocados=tablero.getCantBarcosAcorazadoColocados();
			Conexion c=new Conexion();
			try {
				//primero, actualizo en el Tablero
				c.actualizarTuplaDeNueveColumnas("t_batalla_naval", "idTBatallaNaval", "jugador", idTablero, jugador,"miTurno", "barcosSubmarinos", "barcosDestructores", "barcosCruceros", "barcosAcorazados", "barcosSubmarinosColocados", "barcosDestructoresColocados", "barcosCrucerosColocados", "barcosAcorazadosColocados", miTurno2,barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados);
				Celda[][] celda=tablero.getTabla();
				Celda caux=new Celda();
				int largoi=celda.length;
				int largoj=celda[0].length;
				try {
					idTablero = this.getIdTablero(idDesafio,jugador);
				} catch (NoExisteTableroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//borro todas las celdas
				c.borrarTuplasCondicionUnAtributo("celdas", "t_batalla_naval_idTBatallaNaval",idTablero);

				//luego, agrego las Celdas nuevas
				for(int i=0; i<largoi; i++ ){
					for(int j=0;j<largoj;j++){
						if(celda[i][j]==null){
							logger.info("Celda["+i+"]["+j+"] vacía del Tablero de Batalla Naval de "+jugador);
						}else{
							caux=celda[i][j];
							int xC=i;
							int yC=j;
							int id=caux.getId();
							String estado=caux.getEstado();
							c.ingresarNuevaTuplaDeCincoColumnas2("celdas", "idCelda", "t_batalla_naval_idTBatallaNaval", "xC", "yC", "id", "estado", idTablero,xC ,yC , id, estado);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.disconnect();


		} catch (NoExisteTableroException e1) {

			boolean miTurno=tablero.isMiTurno();

			int miTurno2=3;
			if(miTurno){
				miTurno2=1;
			}else{
				miTurno2=0;
			}

			int barcosSubmarinos=tablero.getCantBarcosSubmarino();
			int barcosDestructores=tablero.getCantBarcosDestructores();
			int barcosCruceros=tablero.getCantBarcosDestructores();
			int barcosAcorazados=tablero.getCantBarcosAcorazado();
			int barcosSubmarinosColocados=tablero.getCantBarcosSubmarinoColocados();
			int barcosDestructoresColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosCrucerosColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosAcorazadosColocados=tablero.getCantBarcosAcorazadoColocados();
			Conexion c=new Conexion();
			try {
				//primero, agrego en el Tablero
				c.ingresarNuevaTuplaDeOnceColumnas("t_batalla_naval", "idTBatallaNaval", "desafios_idDesafio", "jugador", "miTurno", "barcosSubmarinos", "barcosDestructores", "barcosCruceros", "barcosAcorazados", "barcosSubmarinosColocados", "barcosDestructoresColocados", "barcosCrucerosColocados", "barcosAcorazadosColocados",idDesafio, jugador, miTurno2, barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados);
				Celda[][] celda=tablero.getTabla();
				Celda caux=new Celda();
				int largoi=celda.length;
				int largoj=celda[0].length;
				try {
					idTablero = this.getIdTablero(idDesafio,jugador);
				} catch (NoExisteTableroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//luego, agrego en las Celdas
				for(int i=0; i<largoi; i++ ){
					for(int j=0;j<largoj;j++){

						if(celda[i][j]==null){
							logger.info("Celda["+i+"]["+j+"] vacía del Tablero de Batalla Naval de "+jugador);
						}else{
							caux=celda[i][j];
							int xC=i;
							int yC=j;
							int id=caux.getId();
							String estado=caux.getEstado();
							c.ingresarNuevaTuplaDeCincoColumnas2("celdas", "idCelda", "t_batalla_naval_idTBatallaNaval", "xC", "yC", "id", "estado", idTablero,xC ,yC , id, estado);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.disconnect();
		}



	}

	public int getIdTablero(int idDesafio,String jugador) throws NoExisteTableroException {

		Conexion c = new Conexion();

		ResultSet r = null;
		int idTablero = 0;
		try {
			r = c
					.devolverResutado("SELECT idTBatallaNaval FROM t_batalla_naval WHERE desafios_idDesafio='"
							+ idDesafio + "' AND jugador='"+jugador+"'");

			r.next();
			idTablero = r.getInt("idTBatallaNaval");

		} catch (SQLException ex) {
			throw new NoExisteTableroException();

		}
		return idTablero;

	}

}
