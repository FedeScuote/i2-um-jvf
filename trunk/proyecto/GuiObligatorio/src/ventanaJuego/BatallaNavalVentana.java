package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import ventanaPrincipal.VentanaPrincipal;
import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;

public class BatallaNavalVentana extends JFrame {
	// ///////////////////////////////////////VARIABLES Y
	// CONSTANTES//////////////////////////////
	private static final long serialVersionUID = 1L;

	private static final int pause = 2000;

	private static final int pause2 = 4000;

	private static final String host = null;

	private UsuarioVO usuario;

	private JPanel jContentPane = null;

	private JPanel PanelJugador = null;

	private JPanel PanelContrincante = null;

	private boolean esMiTurno = false;

	private Timer temporizador;

	private Timer temporizadorInicioPartida;

	private JButton[][] botonesJugador = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	// botones que voy a
	// utilizar para mi
	// tablero
	private JButton[][] botonesContrincante = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio//
														// porque no va nada
														// en// esa linea

	private JLabel indicadorTurno = null;

	private JLabel indicadorBarcosHundidos = null;

	private Integer cantidadBarcosHundidos = 0;

	private boolean termino = false;

	private static Logger logger = Logger.getLogger(BatallaNavalVentana.class);

	// ///////////////////////////////////////VARIABLES Y
	// CONSTANTES//////////////////////////////

	// ///////////////////////////////////METODOS Y
	// CONSTRUCTORES////////////////////////////

	/**
	 * This is the default constructor
	 */
	public BatallaNavalVentana(final UsuarioVO usuario) {
		super();
		logger.debug("***creo BatallaNavalVentana***");
		initialize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;
		this.crearCabezal(PanelJugador);
		this.crearTablero(PanelJugador, botonesJugador); // creo mi tablero
		// en mi panel
		// jugador
		this.crearCabezal(PanelContrincante);
		this.crearTablero(PanelContrincante, botonesContrincante);// creo mi
		// tablero
		// en mi
		// panel
		// contrincante
		refrescarTableroJugador();
		refrescarTableroOponente();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);// mi
		// frame
		// arranca
		// maximizada
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				esMiTurno = preguntarTurno(usuario);
				if (!esMiTurno && termino == false) {
					indicadorTurno.setText("NO ES TU TURNO");
					indicadorTurno.setForeground(Color.RED);
					temporizador.restart();
				} else if (termino == false) {
					indicadorTurno.setText("ES TU TURNO");
					indicadorTurno.setForeground(Color.GREEN);
				}
			}
		};
		ActionListener ListenerInicioPartida = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					logger.debug("temporizadorInicioTurno");
					Registry registry = LocateRegistry.getRegistry(host);
					ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
							.lookup("BatallaNavalServices");
					if(stub.inicioPartida(usuario)){
						logger.debug("Pregunto Si es mi turno");
						temporizador.start();
						temporizadorInicioPartida.stop();
					}else{
						temporizadorInicioPartida.restart();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		temporizadorInicioPartida = new Timer(pause2, ListenerInicioPartida);
		temporizadorInicioPartida.setInitialDelay(pause2);
		temporizadorInicioPartida.addActionListener(ListenerInicioPartida);
		temporizadorInicioPartida.setRepeats(false);
		temporizadorInicioPartida.start();
		temporizador = new Timer(pause, taskPerformer);
		temporizador.setInitialDelay(pause);
		temporizador.addActionListener(taskPerformer);
		temporizador.setRepeats(false);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			indicadorTurno = new JLabel();
			indicadorTurno.setText("NO ES TU TURNO");
			indicadorTurno.setForeground(Color.RED);
			indicadorTurno.setHorizontalAlignment(SwingConstants.CENTER);
			indicadorTurno.setHorizontalTextPosition(SwingConstants.CENTER);
			indicadorBarcosHundidos = new JLabel();
			indicadorBarcosHundidos.setText("BARCOS HUNDIDOS"
					+ cantidadBarcosHundidos);
			indicadorBarcosHundidos.setForeground(Color.magenta);
			indicadorBarcosHundidos
					.setHorizontalAlignment(SwingConstants.CENTER);
			indicadorBarcosHundidos
					.setHorizontalTextPosition(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getPanelJugador(), BorderLayout.WEST);
			jContentPane.add(getPanelContrincante(), BorderLayout.EAST);
			jContentPane.add(indicadorTurno, BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelJugador
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelJugador() {
		if (PanelJugador == null) {
			PanelJugador = new JPanel();
			PanelJugador.setLayout(new GridBagLayout());
			PanelJugador.setEnabled(true);
		}
		return PanelJugador;
	}

	/**
	 * This method initializes PanelContrincante
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelContrincante() {
		if (PanelContrincante == null) {
			PanelContrincante = new JPanel();
			PanelContrincante.setLayout(new GridBagLayout());
		}
		return PanelContrincante;
	}

	// metodo al cual le paso el panel donde quiero crear un tablero del tamano
	// indicado en los atributos
	private void crearTablero(JPanel panel, JButton[][] botones) {
		// crear botones y agregarlos al panel
		logger.debug("crear Tablero");
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			this.crearFila(panel, i, botones);
		}

	}

	// metodo que me crea mi cabezal con mis letras
	private void crearCabezal(JPanel panel) {
		logger.debug("CrearCabezal");
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));

		for (int i = 0; i < TAMANO_TABLERO; i++) {
			JLabel jlabel = new JLabel();
			panel.add(jlabel);
			jlabel.setText(ALFABETO[i]); // alfabeto menos uno porque
			jlabel.setHorizontalAlignment(SwingConstants.CENTER);
			jlabel.setVerticalAlignment(SwingConstants.CENTER);
		}
	}

	// metodo que le paso numero de fila y me agrega la fila con jlabel
	// correspondiente
	private void crearFila(JPanel panel, Integer numeroFila, JButton[][] botones) {
		for (int j = 0; j < TAMANO_TABLERO; j++) {
			if (j == 0) {
				JLabel jlabel = new JLabel();
				panel.add(jlabel);
				jlabel.setText(numeroFila.toString()); // alfabeto menos uno
				// porque
				jlabel.setHorizontalAlignment(SwingConstants.CENTER);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);
			} else {
				JButton jButton = new JButton();
				if (panel.equals(PanelContrincante)) {// solo añado mis
					// listeners si es panel
					// contr
					jButton.addActionListener(new ListenerBoton(numeroFila, j));
				}
				panel.add(jButton);
				botones[numeroFila][j] = jButton;
			}
		}
	}

	// clase de mis actionListener que voy a usar en mis botones
	private class ListenerBoton implements ActionListener {

		private int x;

		private int y;

		public ListenerBoton(int i, int j) {
			// en el constructor se almacena la fila y columna que se presiona
			x = i;
			y = j;
		}

		public void actionPerformed(ActionEvent e) {
			// cuando se presiona un boton se ejecutara este metodo
			clickBoton(x - 1, y - 1);// pongo menos uno para que el bus lo
										// recibe de 0 a 9
		}
	}

	// METODO DE MIS BOTONES
	private void clickBoton(int fila, int columna) {
		logger.debug("ClickBoton");
		if (esMiTurno) {
			try { // try y catch para verificar si esta el usuario o
				// no
				Registry registry = LocateRegistry.getRegistry(host);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				stub.disparar(this.usuario, fila, columna);
				esMiTurno = false;
				this.refrescarTableroOponente();
				indicadorTurno.setText("NO ES TU TURNO");
				indicadorTurno.setForeground(Color.RED);
				if (stub.hundi(this.usuario)) {
					cantidadBarcosHundidos++;
					JOptionPane.showMessageDialog(new JFrame(),
							"HAS HUNDIDO UN BARCO", "ENHORABUENA",
							JOptionPane.INFORMATION_MESSAGE);
				}
				temporizador.restart();
			} catch (Exception e) {

			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "NO ES TU TURNO",
					"ESPERA", JOptionPane.ERROR_MESSAGE);
		}
	}

	// METODO PARA PREGUNTAR TURNO
	public boolean preguntarTurno(UsuarioVO usuario) {
		logger.debug("preguntarTurno");
		if (termino == false && this.gane()) {
			termino=true;
			JOptionPane.showMessageDialog(new JFrame(), "HAS GANADO",
					"ENHORABUENA", JOptionPane.INFORMATION_MESSAGE);
			temporizador.stop();
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry(host);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				if (stub.esBot(this.usuario)) {
					this.terminoPartida(true);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.dispose();
			VentanaPrincipal l = new VentanaPrincipal(this.usuario);
			l.setVisible(true);
			return true;
		} else if (termino == false && this.perdi()) {
			termino=true;
			JOptionPane.showMessageDialog(new JFrame(), "HAS PERDIDO",
					"LO SIENTO", JOptionPane.INFORMATION_MESSAGE);
			temporizador.stop();
			this.terminoPartida(false);// solo termina mi partida cuando perdi
			this.dispose();
			VentanaPrincipal l = new VentanaPrincipal(this.usuario);
			l.setVisible(true);
			return true;
		} else if (termino == false) {
			try { // try y catch para verificar si esta el usuario o
				// no
				Registry registry = LocateRegistry.getRegistry(host);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				refrescarTableroJugador();
				refrescarTableroOponente();
				if (stub.esMiTurno(usuario)) {
					temporizador.stop();
				}
				return stub.esMiTurno(usuario);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	// METODO PARA REFRESCAR TABLERO DEL JUGADOR
	public void refrescarTableroJugador() {
		logger.debug("refrescarTableroJugador");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesJugador, stub.refrescarTablero(usuario));
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DE CONEXION",
								"ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DESCONOCIDO",
								"ERROR", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}

	// METODO PARA REFRESCAR TABLERO DEL OPONENTE
	public void refrescarTableroOponente() {
		logger.debug("refrescarTableroOponente");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesContrincante, stub
					.refrescarTableroOponente(usuario));
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DE CONEXION",
								"ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DESCONOCIDO",
								"ERROR", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}

	// METODO DONDE "PINTA" LOS BOTONES PARA REFRESCAR EL TABLERO
	public void cambiarBotones(JButton[][] botones, TableroVO tablero) {
		CeldaVO[][] tabla = tablero.getTabla();
		int iArrayBotones = 0;
		int jArrayBotones = 0;
		for (int i = 0; i < tabla.length; i++) {
			iArrayBotones++;
			jArrayBotones = 0;
			for (int j = 0; j < tabla.length; j++) {
				jArrayBotones++;
				if (tabla[i][j].getEstado().equals("AGUA")) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.BLUE);
				} else if (tabla[i][j].getEstado().equals("OCUPADO")) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.BLACK);
				} else if (tabla[i][j].getEstado().equals("TOCADO")) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.GREEN);
				} else if (tabla[i][j].getEstado().equals("HUNDIDO")) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.GREEN);
				} else {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.RED); // esto quiere decir
					// que si no era ninguno de los otros erre el tiro
				}

			}
		}
	}

	// METODO PARA PREGUNTAR SI GANE
	public boolean gane() {
		logger.debug("gane");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			return stub.gane(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DE CONEXION",
								"ERROR", JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();
			return false;
		}
	}

	private boolean perdi() {
		logger.debug("perdi");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			return stub.perdi(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DE CONEXION",
								"ERROR", JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();
			return false;
		}
	}

	private void terminoPartida(boolean gane) {
		logger.debug("terminoPartida");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			stub.terminarPartida(usuario, gane);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DE CONEXION",
								"ERROR", JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();

		}
	}

}// @jve:decl-index=0:visual-constraint="10,10"
