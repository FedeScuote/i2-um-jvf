package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import conexion.Conexion;

import busImpl.batallaNaval.*;
import busImpl.Estados;
import busImpl.Usuario;
import daoInterfaces.BatallaNavalDAO;
import excepcionesB.NotDataFoundException;
import excepcionesD.NoExisteTableroException;
import excepcionesB.NoExisteUsuarioException;

public class BatallaNavalDAODB implements BatallaNavalDAO {
	private static Logger logger = Logger.getLogger(BatallaNavalDAODB.class);


	//terminados

	//optimizado
	public ArrayList<RegistroDisparo> getListaDeTiros(int idPartida,int idUsuario) {
		logger.debug("Entro a getListaDeTiros con parametros de entrada idPartida= "+idPartida+" idUsuario= "+idUsuario);
		ArrayList<RegistroDisparo> ard=new ArrayList();
		UsuarioDAODB ud=new UsuarioDAODB();
		Conexion c=new Conexion();
		Estados estado=null;
		int idDesafio=idPartida;
		String jugador;
		int idTablero;

		try {
			jugador = ud.getUsuario2(idUsuario,c);
			idTablero=this.getIdTablero2(idDesafio, jugador,c);
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
				disparo.setColumna(columna);
				RegistroDisparo rd=new RegistroDisparo(estado,disparo);

				logger.debug("idPartida= "+idPartida);
				logger.debug("idUsuario= " +idUsuario);
				logger.debug("idTablero= "+idTablero);
				logger.debug("xD= "+fila);
				logger.debug("yD= "+columna);
				logger.debug("estado= "+estado);

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
		} finally{
			logger.debug("Me desconecto de la base de datos del método getListaDeTiros");
			c.disconnect();
		}

		return ard;
	}

	//optimizado
	public Tablero getTablero(int idPartida, int idUsuario) {
		logger.debug("Entro a getTablero con parámetros de entrada idPartida= "+idPartida+" e idUsuario= "+idUsuario);
		Usuario u=new Usuario();
		UsuarioDAODB ud=new UsuarioDAODB();
		String usuario=null;
		Conexion c=new Conexion();
		try {
			usuario=ud.getUsuario2(idUsuario,c);
			u=ud.findByName2(usuario,c);
		} catch (NoExisteUsuarioException e2) {
			e2.printStackTrace();
		} catch (NotDataFoundException e) {
			e.printStackTrace();
		}
		Tablero t=new Tablero(u);
		try {
			int idDesafio = idPartida;
			int idTablero=this.getIdTablero2(idDesafio, usuario,c);
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

			logger.debug("idDesafio= "+idDesafio);
			logger.debug("idTablero= "+idTablero);
			logger.debug("miTurno= "+miTurno);
			logger.debug("barcosSubmarinos= "+barcosSubmarinos);
			logger.debug("barcosDestructores= "+barcosDestructores);
			logger.debug("barcosCruceros= "+barcosCruceros);
			logger.debug("barcosAcorazados= "+barcosAcorazados);
			logger.debug("barcosSubmarinosColocados= "+barcosSubmarinosColocados);
			logger.debug("barcosCrucerosColocados= "+barcosCrucerosColocados);
			logger.debug("barcosDestructoresColocados= "+barcosDestructoresColocados);
			logger.debug("barcosAcorazadosColocados= "+barcosAcorazadosColocados);
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
			logger.debug("Entro a obtener las celdas");
			while(r2.next()){
				Celda celda=new Celda();
				int xC=r2.getInt("xC");
				int yC=r2.getInt("yC");
				int id=r2.getInt("id");
				//logger.debug("paso el id de celda: "+id);
				String estado=r2.getString("estado");
				logger.debug("estado= "+estado);
				logger.debug("xC= "+xC);
				logger.debug("yC= "+yC);
				logger.debug("id= "+id);
				celda.setEstado(estado);
				celda.setId(id);
				celdaMatriz[xC][yC]=celda;
			}
			t.setTabla(celdaMatriz);
		} catch (NoExisteTableroException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método getTablero");
			c.disconnect();
		}

		return t;
	}

	//optimizado
	public void registrarDisparo(Disparo disparo, Estados estado,int idUsuario, int idPartida) {
		logger.debug("Entro a registrarDisparo con parámetros de entrada idUsuario= "+idUsuario+" idPartida= "+idPartida);
		int xD = disparo.getFila();
		int yD = disparo.getColumna();
		String resultadoD = estado.toString();
		int idDesafio = idPartida;
		logger.debug("El parámetro de entrada disparo se compone de los siguientes parámetros");
		logger.debug("xD= "+xD);
		logger.debug("yD= "+yD);
		logger.debug("resultadoD= "+resultadoD);


		Conexion c = new Conexion();
		BatallaNavalDAODB b = new BatallaNavalDAODB();
		UsuarioDAODB ud=new UsuarioDAODB();


		try {
			String jugador=ud.getUsuario2(idUsuario,c);
			int idTablero = b.getIdTablero2(idDesafio,jugador,c);
			logger.debug("Ingreso datos en tabla disparos");
			c.ingresarNuevaTuplaDeCincoColumnas2("disparos", "idDisparo",
					"t_batalla_naval_idTBatallaNaval", "idUsuarioD",
					"resultadoD", "xD", "yD", idTablero, idUsuario, resultadoD,
					xD, yD);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoExisteTableroException e) {
			e.printStackTrace();
		} catch (NoExisteUsuarioException e) {
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método registrarDisparo");
			c.disconnect();
		}

	}
	//optimizado
	public void registrarTablero(Tablero tablero, int idPartida) {
		logger.debug("Entro a registrarTablero con parámetros de entrada idPartida= "+idPartida);
		int idDesafio=idPartida;
		String jugador=((Usuario)(tablero.getJugador())).getUsuarioB();
		int idTablero=0;
		Conexion c=new Conexion();
		try {
			idTablero = this.getIdTablero2(idDesafio, jugador,c);
			boolean miTurno=tablero.isMiTurno();
			int miTurno2=3;
			if(miTurno){
				miTurno2=1;
			}else{
				miTurno2=0;
			}
			int barcosSubmarinos=tablero.getCantBarcosSubmarino();
			int barcosDestructores=tablero.getCantBarcosDestructores();
			int barcosCruceros=tablero.getCantBarcosCruceros();
			int barcosAcorazados=tablero.getCantBarcosAcorazado();
			int barcosSubmarinosColocados=tablero.getCantBarcosSubmarinoColocados();
			int barcosDestructoresColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosCrucerosColocados=tablero.getCantBarcosCrucerosColocados();
			int barcosAcorazadosColocados=tablero.getCantBarcosAcorazadoColocados();
			logger.debug("El parámetro de entrada tablero se compone de los siguientes parámetros");
			logger.debug("idDesafio= "+idDesafio);
			logger.debug("miTurno, cuando 0 es false y 1 es true= "+miTurno2);
			logger.debug("barcosSubmarinos= "+barcosSubmarinos);
			logger.debug("barcosDestructores= "+barcosDestructores);
			logger.debug("barcosCruceros= "+barcosCruceros);
			logger.debug("barcosAcorazados= "+barcosAcorazados);
			logger.debug("barcosSubmarinosColocados= "+barcosSubmarinosColocados);
			logger.debug("barcosCrucerosColocados= "+barcosCrucerosColocados);
			logger.debug("barcosDestructoresColocados= "+barcosDestructoresColocados);
			logger.debug("barcosAcorazadosColocados= "+barcosAcorazadosColocados);

			try {
				//primero, actualizo en el Tablero
				logger.debug("Actualizo en tablero de Batalla Naval");
				logger.debug("idTablero= "+idTablero);
				c.actualizarTuplaDeNueveColumnas("t_batalla_naval", "idTBatallaNaval", "jugador", idTablero, jugador,"miTurno", "barcosSubmarinos", "barcosDestructores", "barcosCruceros", "barcosAcorazados", "barcosSubmarinosColocados", "barcosDestructoresColocados", "barcosCrucerosColocados", "barcosAcorazadosColocados", miTurno2,barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados);
				Celda[][] celda=tablero.getTabla();
				Celda caux=new Celda();
				int largoi=celda.length;
				int largoj=celda[0].length;
				try {
					idTablero = this.getIdTablero2(idDesafio,jugador,c);
				} catch (NoExisteTableroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//borro todas las celdas
				logger.debug("Borro las celdas correspondiente al tablero de Batalla Naval");
				c.borrarTuplasCondicionUnAtributo("celdas", "t_batalla_naval_idTBatallaNaval",idTablero);
				logger.debug("Agrego en las celdas");
				//luego, agrego las Celdas nuevas
				for(int i=0; i<largoi; i++ ){
					for(int j=0;j<largoj;j++){
						if(celda[i][j]==null){
							logger.debug("Celda["+i+"]["+j+"] vacía del Tablero de Batalla Naval de "+jugador);
						}else{
							caux=celda[i][j];
							int xC=i;
							int yC=j;
							int id=caux.getId();
							String estado=caux.getEstado();
							logger.debug("estado= "+estado);
							logger.debug("xC= "+xC);
							logger.debug("yC= "+yC);
							logger.debug("id= "+id);
							c.ingresarNuevaTuplaDeCincoColumnas2("celdas", "idCelda", "t_batalla_naval_idTBatallaNaval", "xC", "yC", "id", "estado", idTablero,xC ,yC , id, estado);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} catch (NoExisteTableroException e1) {
			logger.debug("No existe tablero de Batalla Naval por lo que se va a crear uno");
			boolean miTurno=tablero.isMiTurno();

			int miTurno2=3;
			if(miTurno){
				miTurno2=1;
			}else{
				miTurno2=0;
			}

			int barcosSubmarinos=tablero.getCantBarcosSubmarino();
			int barcosDestructores=tablero.getCantBarcosDestructores();
			int barcosCruceros=tablero.getCantBarcosCruceros();
			int barcosAcorazados=tablero.getCantBarcosAcorazado();
			int barcosSubmarinosColocados=tablero.getCantBarcosSubmarinoColocados();
			int barcosDestructoresColocados=tablero.getCantBarcosDestructoresColocados();
			int barcosCrucerosColocados=tablero.getCantBarcosCrucerosColocados();
			int barcosAcorazadosColocados=tablero.getCantBarcosAcorazadoColocados();
			logger.debug("El parámetro de entrada tablero se compone de los siguientes parámetros");
			logger.debug("idDesafio= "+idDesafio);
			logger.debug("miTurno, cuando 0 es false y 1 es true= "+miTurno2);
			logger.debug("barcosSubmarinos= "+barcosSubmarinos);
			logger.debug("barcosDestructores= "+barcosDestructores);
			logger.debug("barcosCruceros= "+barcosCruceros);
			logger.debug("barcosAcorazados= "+barcosAcorazados);
			logger.debug("barcosSubmarinosColocados= "+barcosSubmarinosColocados);
			logger.debug("barcosCrucerosColocados= "+barcosCrucerosColocados);
			logger.debug("barcosDestructoresColocados= "+barcosDestructoresColocados);
			logger.debug("barcosAcorazadosColocados= "+barcosAcorazadosColocados);

			try {
				//primero, agrego en el Tablero
				logger.debug("Agrego en tablero de Batalla Naval");
				c.ingresarNuevaTuplaDeOnceColumnas("t_batalla_naval", "idTBatallaNaval", "desafios_idDesafio", "jugador", "miTurno", "barcosSubmarinos", "barcosDestructores", "barcosCruceros", "barcosAcorazados", "barcosSubmarinosColocados", "barcosDestructoresColocados", "barcosCrucerosColocados", "barcosAcorazadosColocados",idDesafio, jugador, miTurno2, barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados);
				Celda[][] celda=tablero.getTabla();
				Celda caux=new Celda();
				int largoi=celda.length;
				int largoj=celda[0].length;
				try {
					idTablero = this.getIdTablero2(idDesafio,jugador,c);
					logger.debug("idTablero= "+idTablero);
				} catch (NoExisteTableroException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//luego, agrego en las Celdas
				logger.debug("Agrego en las celdas");
				for(int i=0; i<largoi; i++ ){
					for(int j=0;j<largoj;j++){

						if(celda[i][j]==null){
							logger.debug("Celda["+i+"]["+j+"] vacía del Tablero de Batalla Naval de "+jugador);
						}else{
							caux=celda[i][j];
							int xC=i;
							int yC=j;
							int id=caux.getId();
							String estado=caux.getEstado();
							logger.debug("estado= "+estado);
							logger.debug("xC= "+xC);
							logger.debug("yC= "+yC);
							logger.debug("id= "+id);
							c.ingresarNuevaTuplaDeCincoColumnas2("celdas", "idCelda", "t_batalla_naval_idTBatallaNaval", "xC", "yC", "id", "estado", idTablero,xC ,yC , id, estado);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally{
			logger.debug("Me desconecto de la base de datos del método registrarTablero");
			c.disconnect();
		}



	}

	//actualiza tablero pero no celdas, //optimizado
	public void actualizarTablero(int idPartida, String usuario, boolean miTurno,int barcosSubmarinos, int barcosDestructores,int barcosCruceros,int barcosAcorazados,int barcosSubmarinosColocados, int barcosDestructoresColocados,int barcosCrucerosColocados,int barcosAcorazadosColocados ){
		logger.debug("Entro a actualizarTablero con parámetros de entrada idPartida= "+idPartida+" usuario= "+usuario+" miTurno= "+miTurno+" barcosSubmarino= "+barcosSubmarinos+" barcosDestructores= "+barcosDestructores+" barcosCruceros= "+barcosCruceros+" barcosAcorzados= "+barcosAcorazados+" barcosSubmarinosColocados= "+barcosSubmarinosColocados+" barcosDestructoresColocados= "+barcosDestructoresColocados+" barcosCrucerosColocados= "+barcosCrucerosColocados+" barcosAcorazadosColocados= "+barcosAcorazadosColocados);
		Conexion c=new Conexion();
		int idDesafio=idPartida;
		int idTablero=0;
		try {
			idTablero = this.getIdTablero2(idDesafio, usuario,c);
			int miTurno2=3;
			if(miTurno){
				miTurno2=1;
			}else{
				miTurno2=0;
			}
			c.actualizarTuplaDeNueveColumnas("t_batalla_naval", "idTBatallaNaval", "jugador", idTablero, usuario,"miTurno", "barcosSubmarinos", "barcosDestructores", "barcosCruceros", "barcosAcorazados", "barcosSubmarinosColocados", "barcosDestructoresColocados", "barcosCrucerosColocados", "barcosAcorazadosColocados", miTurno2,barcosSubmarinos, barcosDestructores, barcosCruceros, barcosAcorazados, barcosSubmarinosColocados, barcosDestructoresColocados, barcosCrucerosColocados, barcosAcorazadosColocados);
		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método actualizarTablero");
			c.disconnect();
		}
	}


	public int getIdTablero(int idDesafio,String jugador) throws NoExisteTableroException {
		logger.debug("Entro a getIdTablero con parámetros de entrada idDesafio= "+idDesafio+" jugador= "+jugador);
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
		} finally{
			logger.debug("idTablero= "+idTablero);
			logger.debug("Me desconecto de la base de datos del método getIdTablero");
			c.disconnect();
		}
		return idTablero;

	}

	public int getIdTablero2(int idDesafio,String jugador, Conexion c) throws NoExisteTableroException {
		logger.debug("Entro a getIdTablero con parámetros de entrada idDesafio= "+idDesafio+" jugador= "+jugador);
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
		} finally{
			logger.debug("idTablero= "+idTablero);
			logger.debug("salgo del método getIdTablero2");
		}
		return idTablero;
	}

	//optimizado
	public void modificarCeldaTablero(int idUsuario, Celda celda, int xC, int yC) {
		logger.debug("Entro a modificarCeldaTablero con parámetros de entrada idUsuario= "+idUsuario+" xC= "+xC+" yC="+yC);
		logger.debug("El parámetro de entrada celda se compone de los siguientes parámetros");
		logger.debug("estado= "+celda.getEstado());
		logger.debug("id= "+celda.getId());
		Conexion c=new Conexion();
		PartidaDAODB p=new PartidaDAODB();
		UsuarioDAODB u=new UsuarioDAODB();
		int idDesafio=p.idPartida2(idUsuario,c);
		String jugador;
		try {
			jugador = u.getUsuario2(idUsuario,c);
			int idTablero=this.getIdTablero2(idDesafio, jugador,c);
			int id=celda.getId();
			String estado=celda.getEstado();
			c.actualizarTuplaDeDosColumna("celdas", "xC", "yC", "t_batalla_naval_idTBatallaNaval", xC, yC, idTablero, "id", id, "estado", estado);

		} catch (NoExisteUsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método modificarCeldaTablero");
			c.disconnect();
		}

	}
	//optimizado, sería el método más usado durante el juego
	public boolean turnoTablero(int idUsuario) {
		logger.debug("Entro a turnoTablero con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c=new Conexion();
		try {
			ResultSet r=c.devolverResutado("SELECT miTurno FROM t_batalla_naval,usuarios_has_juegos_desafios,desafios,usuarios WHERE t_batalla_naval.desafios_idDesafio=idDesafio AND usuarios_has_juegos_desafios.desafios_idDesafio=idDesafio AND usuarioGanadorD='0' AND jugador=usuario AND idusuario=usuarios_idusuario AND usuarios_idusuario='"+idUsuario+"'");
			if(r.first()){
				int miTurno=r.getInt("miTurno");
				return miTurno==1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método turnoTablero");
			c.disconnect();
		}
		return false;
	}

	public boolean turnoTableroOld(int idUsuario) {
		logger.debug("Entro a turnoTablero con parámetro de entrada idUsuario= "+idUsuario);
		Conexion c=new Conexion();
		PartidaDAODB p=new PartidaDAODB();
		UsuarioDAODB u=new UsuarioDAODB();

		try {
			String usuario=u.getUsuario2(idUsuario,c);
			int idDesafio=p.idPartida2(idUsuario, c);
			int idTablero=this.getIdTablero2(idDesafio, usuario, c);
			logger.debug("usuario= "+usuario);
			logger.debug("idDesafio= "+idDesafio);
			logger.debug("idTablero= "+idTablero);
			ResultSet r=c.devolverResutado("SELECT miTurno FROM t_batalla_naval WHERE idTBatallaNaval='"+idTablero+"'");
			if(r.first()){
				int miTurno=r.getInt("miTurno");
				return miTurno==1;
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
		} finally{
			logger.debug("Me desconecto de la base de datos del método turnoTablero");
			c.disconnect();
		}
		return false;
	}
	//acutaliza el turno siempre y cuando exista un desafío en curso
	public boolean actualizarTurno(int idUsuario, boolean turno) {
		logger.debug("Entro a actualizarTurno con parámetro de entrada idUsuario= "+idUsuario+" turno= "+turno);
		Conexion c=new Conexion();
		try {
			if(turno){
				c.actualizarValores("UPDATE t_batalla_naval,usuarios,usuarios_has_juegos_desafios,desafios SET miTurno=1 WHERE t_batalla_naval.desafios_idDesafio=idDesafio AND usuarios_has_juegos_desafios.desafios_idDesafio=idDesafio AND usuarioGanadorD='0' AND jugador=usuario AND idusuario=usuarios_idusuario AND usuarios_idusuario='"+idUsuario+"'");
			}else{
				c.actualizarValores("UPDATE t_batalla_naval,usuarios,usuarios_has_juegos_desafios,desafios SET miTurno=0 WHERE t_batalla_naval.desafios_idDesafio=idDesafio AND usuarios_has_juegos_desafios.desafios_idDesafio=idDesafio AND usuarioGanadorD='0' AND jugador=usuario AND idusuario=usuarios_idusuario AND usuarios_idusuario='"+idUsuario+"'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			logger.debug("Me desconecto de la base de datos del método actualizarTurno");
			c.disconnect();
		}
		return false;
	}







}
