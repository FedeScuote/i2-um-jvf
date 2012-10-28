package busImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import comm.DesafioBatallaNavalVO;
import comm.DesafioVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoInicioJuegoException;
import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.BatallaNavalDAO;
import daoInterfaces.DesafioDAO;
import daoInterfaces.PartidaDAO;
import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;

public class ServiciosBatallaNavalImpl implements ServiciosBatallaNaval{

	private static final String USUARIO_BOT_1 = "jhirata";
	private static final String USUARIO_BOT_2 = "vtuyare";
	private static final String USUARIO_BOT_3 = "fkono";
	private static final String USUARIO_BOT_4 = "jdiaz";

	private JuegoBatallaNaval juego;

	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		juego.agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
		BatallaNavalDAO daoBatallaNaval = getDAO2();
		PartidaDAO daoPartida = getDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		daoBatallaNaval.registrarTablero(juego.getTableroJugador1(), idPartida);
		daoBatallaNaval.registrarTablero(juego.getTableroJugador2(), idPartida);
	}

	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException{
		PartidaDAO dao = getDAO();
		DesafioDAO dao1 = getDAO1();
		BatallaNavalDAO dao2 = getDAO2();
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		Estados resultado=juego.disparar(usuario, coordenadaX, coordenadaY);
		int idPartida=dao.idPartida(usuario.getIdUsuario());
		int idUsuarioOponente=dao.oponente(usuario.getIdUsuario()).getIdUsuarioB();
		String usuarioOponente=dao.oponente(usuario.getIdUsuario()).getUsuarioB();
		dao2.actualizarTablero(idPartida, usuario.getUsuarioB(), juego.getTableroJugador1().isMiTurno(), juego.getTableroJugador1().getCantBarcosSubmarino(), juego.getTableroJugador1().getCantBarcosDestructores(), juego.getTableroJugador1().getCantBarcosCruceros(), juego.getTableroJugador1().getCantBarcosAcorazado(), juego.getTableroJugador1().getCantBarcosSubmarinoColocados(), juego.getTableroJugador1().getCantBarcosDestructoresColocados(), juego.getTableroJugador1().getCantBarcosCrucerosColocados(), juego.getTableroJugador1().getCantBarcosAcorazadoColocados());
		dao2.actualizarTablero(idPartida, usuarioOponente, juego.getTableroJugador2().isMiTurno(), juego.getTableroJugador2().getCantBarcosSubmarino(), juego.getTableroJugador2().getCantBarcosDestructores(), juego.getTableroJugador2().getCantBarcosCruceros(), juego.getTableroJugador2().getCantBarcosAcorazado(), juego.getTableroJugador2().getCantBarcosSubmarinoColocados(), juego.getTableroJugador2().getCantBarcosDestructoresColocados(), juego.getTableroJugador2().getCantBarcosCrucerosColocados(), juego.getTableroJugador2().getCantBarcosAcorazadoColocados());
	}

	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.esMiTurno(usuario);
	}


	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.refrescarTablero(usuario);
	}

	public void terminarPartida(UsuarioVO usuario, boolean gane) throws RemoteException{
		PartidaDAO dao = getDAO();
		int idPartida=dao.idPartida(usuario.getIdUsuario());
		dao.terminarPartida(idPartida, usuario.getIdUsuario(), gane);
	}


	public void iniciarPartida(DesafioBatallaNavalVO desafio, UsuarioVO desafiante) throws RemoteException{
		int[] distribucion = new int[4];
		distribucion[0]=3;
		distribucion[1]=2;
		distribucion[2]=1;
		distribucion[3]=1;
		PartidaDAO dao = getDAO();
		DesafioDAO dao1 = getDAO1();
		BatallaNavalDAO dao2 = getDAO2();
		boolean modoRobot=esBot(desafio.getUsuario().getUsuarioB());
		int idDesafio=desafio.getIdDesafio();
		if(modoRobot){
			idDesafio=dao1.crearDesafio(desafio.getUsuario().getUsuarioB(), desafio.getApuesta());
		}
		int idPartida=dao.concretarDesafio(idDesafio, desafiante.getIdUsuario());
		try {
			Usuario jugador1=Usuario.findByName(desafiante.getUsuarioB());
			Usuario jugador2=Usuario.findByName(desafio.getUsuario().getUsuarioB());
			Tablero tableroJugador1= new Tablero(jugador1);
			Tablero tableroJugador2= new Tablero(jugador2);
			if(modoRobot){
				tableroJugador1.setMiTurno(true);
				tableroJugador2.agregarCeldas(distribucion);
			}
			dao2.registrarTablero(tableroJugador1, idDesafio);
			dao2.registrarTablero(tableroJugador2, idDesafio);
		} catch (NoSeEncuentraUsuarioException e) {
			e.printStackTrace();
		}
	}



	private boolean esBot(String usuario) {
		return usuario.equals(USUARIO_BOT_1)||usuario.equals(USUARIO_BOT_2)||usuario.equals(USUARIO_BOT_3)||usuario.equals(USUARIO_BOT_4);
	}


	private static PartidaDAO getDAO() {
		try {
			return (PartidaDAO) Class.forName("daoImpl.PartidaDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	private static UsuarioDAO getDAO4() {
		try {
			return (UsuarioDAO) Class.forName("daoImpl.UsuarioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}


	private static DesafioDAO getDAO1() {
		try {
			return (DesafioDAO) Class.forName("daoImpl.DesafioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	public int[] distribucion(UsuarioVO usuario) throws RemoteException {
//		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
//		return juego.distribucion();
		int[] distribucion = new int[4];
		distribucion[0]=3;
		distribucion[1]=2;
		distribucion[2]=1;
		distribucion[3]=1;
		return distribucion;
	}

	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.refrescarTableroOponente(usuario);
	}

	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.obtenerListaDisparos(usuario);
	}

	public boolean hundi(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		ArrayList<RegistroDisparo> nuevo=obtenerListaDisparos(usuario);
		if(nuevo!=null){
			return nuevo.get(nuevo.size()-1).getResultado()==Estados.HUNDIDO;
		}else{
			return false;
		}
	}

	public boolean gane(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.gane(usuario);
	}

	public boolean perdi(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		return juego.perdi(usuario);
	}
	private static BatallaNavalDAO getDAO2() {
		try {
			return (BatallaNavalDAO) Class.forName("daoImpl.BatallaNavalDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean hayPartidaEnCurso(UsuarioVO usuario) throws RemoteException {
		PartidaDAO dao = getDAO();
		//BatallaNavalDAO dao1 = getDAO2();
		boolean retorno=dao.partidaPendiente(usuario.getIdUsuario());
//		if(retorno){
//			try {
//				Usuario us=Usuario.findByName(usuario.getUsuarioB());
//				int idPartida=dao.idPartida(usuario.getIdUsuario());
//				Usuario oponente=dao.oponente(usuario.getIdUsuario());
//				JuegoBatallaNaval juego = new JuegoBatallaNaval(us,oponente,true);
//				Tablero tabl=dao1.getTablero(idPartida, usuario.getIdUsuario());
//				juego.setTableroJugador1(dao1.getTablero(idPartida, usuario.getIdUsuario()));
//				juego.setTableroJugador2(dao1.getTablero(idPartida, oponente.getIdUsuarioB()));
//				PartidaBatallaNaval continuacion = new PartidaBatallaNaval(idPartida,juego);
//				this.partidas.add(continuacion);
//			} catch (NoSeEncuentraUsuarioException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		return retorno;
	}

}
