package daoImpl;


import org.apache.log4j.Logger;

public class DAOPruebas {
	private static Logger logger = Logger.getLogger(DAOPruebas.class);
	/**
	 * @param args
	 */



	public static void main(String[] args) {
		int credito=19;

		int credito05=(int)Math.floor(Math.rint(credito*0.05));
		int credito95=credito-credito05;
		System.out.println(credito95);
		System.out.println(credito05);
//		RankingDAODB r=new RankingDAODB();
//		try {
//			System.out.println("Ranking ganadas jhirata= "+((Ranking)r.getRankingGeneral().get(1)).getUsuario());
//		} catch (NoHayRankingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		UsuarioDAODB ud=new UsuarioDAODB();
//		try {
//			ud.agregarUsuario("dperez2", "dp", 2, 1, 2000, 1, "Diego", "Perez", "Uruguay");
//		} catch (YaExisteUsuarioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


//		PartidaDAODB p=new PartidaDAODB();
//		Conexion c=new Conexion();
//		BatallaNavalDAODB b=new BatallaNavalDAODB();
//		Usuario u=new Usuario();
//
//		b.modificarCeldaTablero(3, null, 2, 2);
		//System.out.println(p.idPartida2(u.getIdUsuarioB(), c));




		//BatallaNavalDAODB td=new BatallaNavalDAODB();

		//logger.debug(td.getTablero(44, 1).getCantBarcosAcorazado());

//		UsuarioDAODB dd=new UsuarioDAODB();
//		ArrayList<Usuario> a=dd.getUsuariosVirtuales();
//		System.out.println((a.get(0)).equals(null));
//		System.out.println((a.get(0)).getApellidoB());


		/*
		BatallaNavalDAODB td=new BatallaNavalDAODB();
		td.actualizarTablero(20, "jhirata", false, 1, 1, 1, 1, 1, 1, 1, 1);

		/*
		PartidaDAODB pd=new PartidaDAODB();
		int idPartida=pd.idPartida(10);
		System.out.println(idPartida);
		/*
		PartidaDAODB pd=new PartidaDAODB();
		boolean pendiente=pd.partidaPendiente(6);
		System.out.println(pendiente);
		/*
		DesafioDAODB dd=new DesafioDAODB();
		boolean aceptaron=dd.aceptaronDesafio(1);
		System.out.println(aceptaron);


		/*
		PartidaDAODB p=new PartidaDAODB();
		p.terminarPartida(17, 4,false);
		//logger.debug(p.oponente(4).getUsuarioB());



		DesafioDAODB d=new DesafioDAODB();
		logger.debug(d.crearDesafio("jrodriguez", 2600));
		logger.debug(d.crearDesafio("dgonzalez", 2600));


		/*

		DesafioDAODB d=new DesafioDAODB();
		try {
			ArrayList desafios=d.getDesafiosUsuariosDisponibleBatallaNaval();

			for(int i=0;i<desafios.size();i++){
				System.out.println(((Desafio)desafios.get(i)).getUsuarioDesafio());

			}

		} catch (NoHayDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PartidaDAODB p=new PartidaDAODB();
		int idDesafio=17;
		int idDesafiante=8;
		p.concretarDesafio(idDesafio, idDesafiante);

		*/

		/*//pruebas registrar celdas
		Conexion c=new Conexion();
		Celda[][] celda=new Celda[10][10];
		Celda caux=new Celda();
		int largoi=celda.length;
		int largoj=celda[0].length;
		int idTablero=6;

		int a=0;
		for(int i=0; i<largoi; i++ ){
			for(int j=0;j<largoj;j++){
				a++;
				celda[i][j]=caux;
				System.out.println(celda[i][j].getEstado()+" "+a);

				caux=celda[i][j];
				int xC=i;
				int yC=j;
				int id=caux.getId();
				String estado=caux.getEstado();
				try {
					c.ingresarNuevaTuplaDeCincoColumnas2("celdas", "idCelda", "t_batalla_naval_idTBatallaNaval", "xC", "yC", "id", "estado", idTablero,xC ,yC , id, estado);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
		/*





		Celda[][] celda=new Celda[10][10];
		Celda caux=new Celda();
		int largoi=celda.length;
		int largoj=celda[0].length;

		for(int i=0; i<largoi; i++ ){
			for(int j=0;j<largoj;j++){

				celda[i][j]=caux;



			}
		}


		//prueba registrar Tablero

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		int idPartida=20;

		Tablero tablero=new Tablero(null);
		tablero.setTabla(celda);
		tablero.setCantBarcosAcorazado(2);
		tablero.setCantBarcosAcorazadoColocados(2);
		tablero.setCantBarcosCruceros(2);
		tablero.setCantBarcosCrucerosColocados(2);
		tablero.setCantBarcosDestructores(2);
		tablero.setCantBarcosDestructoresColocados(2);
		tablero.setCantBarcosSubmarino(2);
		tablero.setCantBarcosSubmarinoColocados(2);
		Usuario u=new Usuario();
		u.setUsuarioB("vtuyare");
		tablero.setJugador(u);
		tablero.setMiTurno(true);
		b.regstrarTablero(tablero, idPartida);

		int idUsuario;
		try {
			idUsuario = b.getIdTablero(idPartida, "vtuyare");
			Tablero t=b.getTablero(idPartida, idUsuario);
			Celda[][] celda2=t.getTabla();
			int a=0;
			for(int i=0; i<largoi; i++ ){
				for(int j=0;j<largoj;j++){

					a++;
					System.out.println(celda2[i][j].getEstado()+" "+a);


				}
			}





		} catch (NoExisteTableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	/*

		Tablero tablero2=new Tablero(null);
		tablero2.setTabla(c);
		tablero2.setCantBarcosAcorazado(2);
		tablero2.setCantBarcosAcorazadoColocados(2);
		tablero2.setCantBarcosCruceros(2);
		tablero2.setCantBarcosCrucerosColocados(2);
		tablero2.setCantBarcosDestructores(2);
		tablero2.setCantBarcosDestructoresColocados(2);
		tablero2.setCantBarcosSubmarino(2);
		tablero2.setCantBarcosSubmarinoColocados(2);
		Usuario u2=new Usuario();
		u2.setUsuarioB("vtuyare");
		tablero.setJugador(u2);
		tablero.setMiTurno(false);




		b.regstrarTablero(tablero2, idPartida);







	/*

	PartidaDAODB p=new PartidaDAODB();
	System.out.println(p.partidaPendiente(2));

	/*

	BatallaNavalDAODB b=new BatallaNavalDAODB();
	Disparo disparo=new Disparo();
	disparo.setColumna(0);
	disparo.setFila(0);
	Estados estado=Estados.AGUA;
	int idUsuario=1;
	int idPartida=23;
	b.registrarDisparo(disparo, estado, idUsuario, idPartida);



	/*


	BatallaNavalDAODB b=new BatallaNavalDAODB();
	Tablero t=b.getTablero(23, 1);
	Celda[][] celda=t.getTabla();

	logger.debug(t.getJugador().getUsuarioB());
	logger.debug(t.getCantBarcosAcorazado());

	logger.debug(celda[0][0].getEstado());
	logger.debug(celda[0][0].getId());




	/*
		Celda[][] c=new Celda[2][5];
		Celda d=new Celda();

		d.setOcupada();
		c[0][0]=d;


		int largoi=c.length;
		int largoj=c[0].length;


		for(int i=0; i<largoi; i++ ){
			for(int j=0;j<largoj;j++){
				if(c[i][j]==null){
					System.out.println("Celda["+i+"]["+j+"] vacía");
				}else{
					System.out.println(c[i][j].getEstado());
				}

			}
		}
	*/





	/*

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		Tablero t=b.getTablero(23, 1);
		//System.out.println((t.getTabla());
		System.out.println(t.getCantBarcosCruceros());
	*/

	/*

		Celda[][] c=new Celda[2][5];
		Celda d=new Celda();

		d.setOcupada();
		c[0][0]=d;

		BatallaNavalDAODB b=new BatallaNavalDAODB();

		Tablero tablero=new Tablero(null);
		tablero.setTabla(c);
		tablero.setCantBarcosAcorazado(2);
		tablero.setCantBarcosAcorazadoColocados(2);
		tablero.setCantBarcosCruceros(2);
		tablero.setCantBarcosCrucerosColocados(2);
		tablero.setCantBarcosDestructores(2);
		tablero.setCantBarcosDestructoresColocados(2);
		tablero.setCantBarcosSubmarino(2);
		tablero.setCantBarcosSubmarinoColocados(2);
		Usuario u=new Usuario();
		u.setUsuarioB("jhirata");
		tablero.setJugador(u);
		tablero.setMiTurno(true);
		int idPartida=23;
		b.regstrarTablero(tablero, idPartida);


		/*

		Estados estado;
		estado=Estados.AGUA;

		Disparo disparo=new Disparo();
		int idUsuario=99;
		int idPartida=23;
		disparo.setColumna(2);
		disparo.setFila(2);

		BatallaNavalDAODB b=new BatallaNavalDAODB();
		b.registrarDisparo(disparo, estado, idUsuario, idPartida);

*/

	/*
		//Concretar desafios
		int idDesafio=34;
		int idDesafiante=8;
		PartidaDAODB p =new PartidaDAODB();
		p.concretarDesafio(idDesafio, idDesafiante);



	/*

		logger.warn("warn");
		logger.info("info");
		logger.fatal("fatal");
		logger.error("error");

		DesafioDAODB d=new DesafioDAODB();
		ArrayList a;
		try {
			a = d.getDesafios();

			for(int i=0;i<a.size();i++){
				System.out.println(((Desafio)a.get(i)).getUsuarioDesafio());
				System.out.println(((Desafio)a.get(i)).getMonto());
				System.out.println(((Desafio)a.get(i)).getFechaHoraInicioD());
				System.out.println(((Desafio)a.get(i)).getFechaHoraInicioD());
			}


		} catch (NoHayDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	/*
		DesafioDAODB d=new DesafioDAODB();
		try {
			ArrayList a=d.getDesafiosUsuariosDisponibleBatallaNaval();
			System.out.println(((Desafio)a.get(0)).getUsuarioDesafio());
		} catch (NoHayDesafioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	/*	RankingDAODB r=new RankingDAODB();
		try {
			for(int i=0;i<r.getRankingGeneral().size();i++){

					System.out.println(((RankingDAODB)(r.getRankingGeneral().get(i))).getUsuario());
					System.out.println(((RankingDAODB)(r.getRankingGeneral().get(i))).getGanadas());



			}
		} catch (NoHayRankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*try {
			System.out.println(r.getUsuario(0));
		} catch (NoExisteUsuarioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UsuarioDAODB ud=new UsuarioDAODB();
		Usuario u=new Usuario();


		try {
			u=ud.findByName("jhirat");
			System.out.println(u.getApellidoB());
		} catch (NotDataFoundException e) {

			e.printStackTrace();
		}
		if(u.getApellidoB()==null){
			System.out.println("no encontré datos ja ja");

			//throw new NotDataFoundException();
		}
	   */
	}

}
