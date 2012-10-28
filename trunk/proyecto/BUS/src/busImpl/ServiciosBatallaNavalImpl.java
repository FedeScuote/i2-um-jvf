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
		juego=JuegoBatallaNaval.crearJuegoBN(usuario, true);
		Estados resultado=juego.disparar(usuario, coordenadaX, coordenadaY);
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
		int[] distribucion = JuegoBatallaNaval.distribucion1();
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
		int[] distribucion = JuegoBatallaNaval.distribucion1();
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
		return dao.partidaPendiente(usuario.getIdUsuario());
	}

}
