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
import daoInterfaces.DesafioDAO;
import daoInterfaces.PartidaDAO;
import daoInterfaces.RankingDAO;

public class ServiciosBatallaNavalImpl implements ServiciosBatallaNaval{

	private static final int ID_USUARIO_BOT_1 = 1;
	private static final int ID_USUARIO_BOT_2 = 2;
	private static final int ID_USUARIO_BOT_3 = 7;
	private static final int ID_USUARIO_BOT_4 = 9;

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
		boolean modoRobot=esBot(desafio.getUsuario().getIdUsuario());
		if(modoRobot){
			dao1.crearDesafio(desafio.getUsuario().getUsuarioB(), desafio.getApuesta());
		}
//		int idPartida=dao.concretarDesafio(desafio.getIdDesafio(), desafiante.getIdUsuario());
		try {
			int idPartida=0;
			Usuario jugador1=Usuario.findByName(desafiante.getUsuarioB());
			Usuario jugador2=Usuario.findByName(desafio.getUsuario().getUsuarioB());
			JuegoBatallaNaval juego = new JuegoBatallaNaval(jugador1,jugador2,modoRobot);
			PartidaBatallaNaval nueva = new PartidaBatallaNaval(idPartida,juego);
			this.partidas=new ArrayList<PartidaBatallaNaval>();
			this.partidas.add(nueva);
		} catch (NoSeEncuentraUsuarioException e) {
			e.printStackTrace();
		}

	}



	private boolean esBot(int idUsuario) {
		// TODO Auto-generated method stub
		return idUsuario==ID_USUARIO_BOT_1||idUsuario==ID_USUARIO_BOT_2||idUsuario==ID_USUARIO_BOT_3||idUsuario==ID_USUARIO_BOT_4;
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

}
