package busImpl.batallaNaval;

import java.rmi.RemoteException;
import java.util.ArrayList;
import busImpl.Estados;
import busImpl.Usuario;
import comm.DesafioBatallaNavalVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.BatallaNavalDAO;
import daoInterfaces.DesafioDAO;
import daoInterfaces.PartidaDAO;
import daoInterfaces.UsuarioDAO;

public class ServiciosBatallaNavalImpl implements ServiciosBatallaNaval{

	private JuegoBatallaNaval juego;
	private static final int CANTIDAD_INICIAL_SUBMARINO = 0;
	private static final int CANTIDAD_INICIAL_DESTRUCTORES = 0;
	private static final int CANTIDAD_INICIAL_CRUCEROS = 2;
	private static final int CANTIDAD_INICIAL_ACORAZADO = 2;

	//metodo que agrega barcos al tablero, los registro en la base de datos.
	//A partir del tablero y la lista de disparos realizados sobre el
	public void agregarBarco(UsuarioVO usuario, int coordenadaInicialX, int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY, String tipoBarco) throws RemoteException, CoordenadasInvalidasException {
		BatallaNavalDAO daoBatallaNaval = getBatallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		juego.agregarBarco(usuario, coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY, tipoBarco);
		if(juego.inicioPar()&&!esBot(juego.getTableroJugador2().getJugador().getUsuarioB())){
			daoBatallaNaval.actualizarTablero(idPartida, juego.getTableroJugador2().getJugador().getUsuarioB(),true, juego.getTableroJugador2().getCantBarcosSubmarino(), juego.getTableroJugador2().getCantBarcosDestructores(), juego.getTableroJugador2().getCantBarcosCruceros(), juego.getTableroJugador2().getCantBarcosAcorazado(), juego.getTableroJugador2().getCantBarcosSubmarinoColocados(), juego.getTableroJugador2().getCantBarcosDestructoresColocados(), juego.getTableroJugador2().getCantBarcosCrucerosColocados(), juego.getTableroJugador2().getCantBarcosAcorazadoColocados());
		}
	}

	//metodo para dispararle dado un usuario a su oponente
	public void disparar(UsuarioVO usuario, int coordenadaX, int coordenadaY) throws RemoteException, CoordenadasInvalidasException{
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		Estados resultado=juego.disparar(usuario, coordenadaX, coordenadaY);
	}

	//metodo para preguntar el turno de un usuario
	public boolean esMiTurno(UsuarioVO usuario) throws RemoteException {
//		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
//		return juego.esMiTurno(usuario);
		BatallaNavalDAO daoBatallaNaval = getBatallaNavalDAO();
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		int idJugador1=usuario.getIdUsuario();
		int idJugador2=daoPartida.oponente(usuario.getIdUsuario()).getIdUsuarioB();
		ArrayList<RegistroDisparo> listaJugador1 =daoBatallaNaval.getListaDeTiros(idPartida, idJugador1);
		ArrayList<RegistroDisparo> listaJugador2 =daoBatallaNaval.getListaDeTiros(idPartida, idJugador2);
		boolean turno1=daoBatallaNaval.turnoTablero(idJugador1);
		if(listaJugador1.size()!=listaJugador2.size()){
			if((listaJugador1.size()>listaJugador2.size())){
				turno1=false;
			}else{
				turno1=true;
			}
		}
		return turno1;
	}

	//refresca el estado actual del tablero del usuario, los barcos y disparos
	//sobre el tablero
	public TableroVO refrescarTablero(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return juego.refrescarTablero(usuario);
	}

	//metodo que indica si los dos usuarios agregaron ya sus barcos y por ende comenzo la partida
	public boolean inicioPartida(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return juego.inicioPar();
	}

	//metodo que finaliza una partida por medio de un boolean proclamandose el usuario perdedor o ganador
	public void terminarPartida(UsuarioVO usuario, boolean gane) throws RemoteException{
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		daoPartida.terminarPartida(idPartida, usuario.getIdUsuario(), gane);
	}

	//metodo que inicializa un partida por medio de un desafio y un desafiante
	public void iniciarPartida(DesafioBatallaNavalVO desafio, UsuarioVO desafiante) throws RemoteException{
		PartidaDAO daoPartida = getPartidaDAO();
		DesafioDAO daoDesafio = getDesafioDAO();
		BatallaNavalDAO daoBatallaNaval = getBatallaNavalDAO();
		//pregunto a la base de datos si el usuario es virtual o no
		boolean modoRobot=esBot(desafio.getUsuario().getUsuarioB());
		int idDesafio=desafio.getIdDesafio();
		//si es robot como me aceptaron el desafio recien ahi lo registro en la base de datos
		if(modoRobot){
			idDesafio=daoDesafio.crearDesafio(desafio.getUsuario().getUsuarioB(), desafio.getApuesta());
		}
		//creo la partida apartir del desafio y el desafiante
		int idPartida=daoPartida.concretarDesafio(idDesafio, desafiante.getIdUsuario());
		try {
			//creo los jugadores y luego creo los tableros, esta informacion va a ser
			//la que voy a levantar de la base de datos para crear una instancia del juego
			//luego
			Usuario jugador1=Usuario.findByName(desafiante.getUsuarioB());
			Usuario jugador2=Usuario.findByName(desafio.getUsuario().getUsuarioB());
			Tablero tableroJugador1= new Tablero(jugador1);
			Tablero tableroJugador2= new Tablero(jugador2);
			//si el usuario es robot coloco sus barcos en el tablero
			if(modoRobot){
				int[] distribucion = JuegoBatallaNaval.distribucion1();
				tableroJugador1.setMiTurno(true);
				tableroJugador2.agregarCeldas(distribucion);
			}
			//hago el registro y el/los usuario/s ya pueden comenzar agregar sus barcos
			daoBatallaNaval.registrarTablero(tableroJugador1, idPartida);
			daoBatallaNaval.registrarTablero(tableroJugador2, idPartida);
		} catch (NoSeEncuentraUsuarioException e) {
			e.printStackTrace();
		}
	}

	//metodo que inidica si es robot o no internamente
	private boolean esBot(String usuario) {
		UsuarioDAO dao =getUsuarioDAO();
		return dao.esUsuarioVirtual(usuario);
	}

	//metodo para indicar al gui si es robot o no, en el caso que sea robot
	//tanto cuando pierda o cuando gane finaliza la partida que en el caso de no robot
	//lo hace el perdedor
	public boolean esBot(UsuarioVO usuario) throws RemoteException{
		UsuarioDAO dao =getUsuarioDAO();
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return dao.esUsuarioVirtual(juego.getTableroJugador2().getJugador().getUsuarioB());
	}


	//metodo que devuelve la distribucion de barcos para mostrarle al usuario
	//la cantidad de barcos a agregar
	public int[] distribucion(UsuarioVO usuario) throws RemoteException {
		int[] distribucion = JuegoBatallaNaval.distribucion1();
		return distribucion;
	}
	public int[] distribucionPlus(UsuarioVO usuario) throws RemoteException {
		int[] distribucion = new int[4];
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		distribucion[0]=juego.getTableroJugador1().getCantBarcosSubmarinoColocados();
		distribucion[1]=juego.getTableroJugador1().getCantBarcosDestructoresColocados();
		distribucion[2]=juego.getTableroJugador1().getCantBarcosCrucerosColocados();
		distribucion[3]=juego.getTableroJugador1().getCantBarcosAcorazadoColocados();
		return distribucion;
	}
	//refresca el tablero el tablero del oponente
	//que es consumido por el gui
	public TableroVO refrescarTableroOponente(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return juego.refrescarTableroOponente(usuario);
	}

	//metodo que devuelve la lista de disparos hechos por un usuario
	public ArrayList<RegistroDisparo> obtenerListaDisparos(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return juego.obtenerListaDisparos(usuario);
	}

	//metodo que inidica si hundi en mi ultimo disparo
	//basicamente se lleva una lista de disparos con el estado del
	//disparo, si el ultimo disparo tiene estado hundi significa que hundi un barco
	public boolean hundi(UsuarioVO usuario) throws RemoteException {
		ArrayList<RegistroDisparo> nuevo=obtenerListaDisparos(usuario);
		if(nuevo!=null){
			return nuevo.get(nuevo.size()-1).getResultado()==Estados.HUNDIDO;
		}else{
			return false;
		}
	}

	//metodo que dado un usuario indica si gano
	public boolean gane(UsuarioVO usuario) throws RemoteException {
		int cantHundidosJ1=0;
		PartidaDAO daoPartida = getPartidaDAO();
		int idPartida=daoPartida.idPartida(usuario.getIdUsuario());
		int idJugador1=usuario.getIdUsuario();
		BatallaNavalDAO daoBatallaNaval = getBatallaNavalDAO();
		ArrayList<RegistroDisparo> listaJugador1 =daoBatallaNaval.getListaDeTiros(idPartida, idJugador1);
		for(int i=0;i<listaJugador1.size();i++){
			if(listaJugador1.get(i).getResultado()==Estados.HUNDIDO){
				cantHundidosJ1++;
			}
		}
		return (CANTIDAD_INICIAL_ACORAZADO+CANTIDAD_INICIAL_CRUCEROS+CANTIDAD_INICIAL_DESTRUCTORES+CANTIDAD_INICIAL_SUBMARINO==cantHundidosJ1);
	}

	//	metodo que dado un usuario indica si perdio
	public boolean perdi(UsuarioVO usuario) throws RemoteException {
		juego=JuegoBatallaNaval.crearJuegoBN(usuario);
		return juego.perdi(usuario);
	}

	//metodo que indica si hay una partida en curso de batallaNaval
	public boolean hayPartidaEnCurso(UsuarioVO usuario) throws RemoteException {
		PartidaDAO dao = getPartidaDAO();
		return dao.partidaPendiente(usuario.getIdUsuario());
	}

	private static BatallaNavalDAO getBatallaNavalDAO() {
		try {
		return (BatallaNavalDAO) Class.forName("daoImpl.BatallaNavalDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static PartidaDAO getPartidaDAO() {
		try {
			return (PartidaDAO) Class.forName("daoImpl.PartidaDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static UsuarioDAO getUsuarioDAO() {
		try {
			return (UsuarioDAO) Class.forName("daoImpl.UsuarioDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	private static DesafioDAO getDesafioDAO() {
		try {
			return (DesafioDAO) Class.forName("daoImpl.DesafioDAODB").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}



}
