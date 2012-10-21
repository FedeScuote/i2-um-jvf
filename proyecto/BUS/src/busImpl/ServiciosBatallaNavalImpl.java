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
import daoInterfaces.PartidaDAO;
import daoInterfaces.RankingDAO;

public class ServiciosBatallaNavalImpl implements ServiciosBatallaNaval{

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
//		int idPartida=dao.concretarDesafio(desafio.getIdDesafio(), desafiante.getIdUsuario());
		int idPartida=0;
		Usuario jugador1=null;
		Usuario jugador2=null;
		try {
			jugador1 = Usuario.findByName(desafiante.getUsuarioB());
			jugador2=Usuario.findByName(desafio.getUsuario().getUsuarioB());
		} catch (NoSeEncuentraUsuarioException e) {
			e.printStackTrace();
		}
		JuegoBatallaNaval juego = new JuegoBatallaNaval(jugador1,jugador2);
		PartidaBatallaNaval nueva = new PartidaBatallaNaval(idPartida,juego);
		this.partidas=new ArrayList<PartidaBatallaNaval>();
		this.partidas.add(nueva);
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
