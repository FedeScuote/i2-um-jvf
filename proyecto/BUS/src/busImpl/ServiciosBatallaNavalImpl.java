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

public class ServiciosBatallaNavalImpl implements ServiciosBatallaNaval{

	private static final String USUARIO_BOT_1 = "jhirata";
	private static final String USUARIO_BOT_2 = "vtuyare";
	private static final String USUARIO_BOT_3 = "fkono";
	private static final String USUARIO_BOT_4 = "jdiaz";

	private ArrayList<PartidaBatallaNaval> partidas = new ArrayList<PartidaBatallaNaval>();

	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException {
		obtenerPartida(usuario).agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
	}

	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException{
		obtenerPartida(usuario).disparar(usuario, coordenadaX, coordenadaY);
	}

	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).esMiTurno(usuario);
	}


	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).refrescarTablero(usuario);
	}


	public void iniciarPartida(DesafioBatallaNavalVO desafio, UsuarioVO desafiante) throws RemoteException{
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
			JuegoBatallaNaval juego = new JuegoBatallaNaval(jugador1,jugador2,modoRobot);
			PartidaBatallaNaval nueva = new PartidaBatallaNaval(idPartida,juego);
			this.partidas.add(nueva);
			dao2.regstrarTablero(juego.getTableroJugador1(), idPartida);
			dao2.regstrarTablero(juego.getTableroJugador2(), idPartida);
		} catch (NoSeEncuentraUsuarioException e) {
			e.printStackTrace();
		}

//		try {
//			Usuario jugador1=Usuario.findByName(desafiante.getUsuarioB());
//			Usuario jugador2=Usuario.findByName(desafio.getUsuario().getUsuarioB());
//			JuegoBatallaNaval juego = new JuegoBatallaNaval(jugador1,jugador2,true);
//			PartidaBatallaNaval nueva = new PartidaBatallaNaval(0,juego);
//			this.partidas.add(nueva);
//			} catch (NoSeEncuentraUsuarioException e) {
//			e.printStackTrace();
//		}
	}



	private boolean esBot(String usuario) {
		// TODO Auto-generated method stub
		return usuario.equals(USUARIO_BOT_1)||usuario.equals(USUARIO_BOT_2)||usuario.equals(USUARIO_BOT_3)||usuario.equals(USUARIO_BOT_4);
	}

	private PartidaBatallaNaval obtenerPartida(UsuarioVO usuario){
		PartidaBatallaNaval retorno=null;
		for(int i=0;i<this.partidas.size();i++){
			if(partidas.get(i).estaEnPartida(usuario)){
				retorno=partidas.get(i);
			}
		}
		return retorno;
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
		return obtenerPartida(usuario).distribucion();
	}

	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).refrescarTableOponente(usuario);
	}

	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).obtenerListaDisparos(usuario);
	}

	public boolean hundi(UsuarioVO usuario) throws RemoteException {
		ArrayList<RegistroDisparo> nuevo=obtenerListaDisparos(usuario);
		if(nuevo!=null){
			return nuevo.get(nuevo.size()-1).getResultado()==Estados.HUNDIDO;
		}else{
			return false;
		}
	}

	public boolean gane(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).gane(usuario);
	}

	public boolean perdi(UsuarioVO usuario) throws RemoteException {
		return obtenerPartida(usuario).perdi(usuario);
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
		BatallaNavalDAO dao1 = getDAO2();
		boolean retorno=dao.partidaPendiente(usuario.getIdUsuario());
		if(retorno){
			try {
				Usuario us=Usuario.findByName(usuario.getUsuarioB());
				int idPartida=dao.idPartida(usuario.getIdUsuario());
				Usuario oponente=dao.oponente(usuario.getIdUsuario());
				JuegoBatallaNaval juego = new JuegoBatallaNaval(us,oponente,true);
				juego.setTableroJugador1(dao1.getTablero(idPartida, usuario.getIdUsuario()));
				juego.setTableroJugador2(dao1.getTablero(idPartida, oponente.getIdUsuarioB()));
				PartidaBatallaNaval continuacion = new PartidaBatallaNaval(idPartida,juego);
				partidas.add(continuacion);
			} catch (NoSeEncuentraUsuarioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return retorno;
	}

}
