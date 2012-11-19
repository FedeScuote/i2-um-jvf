package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import util.AnimatedPanelString;
import util.ImagePanel;
import util.TranslucentPanel;
import util.TransparentPanel;
import ventanaPrincipal.VentanaPrincipal;

import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;

public class BatallaNaval extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private static final int pause = 2000;

	private static final int pause2 = 4000;

	private static final String HOST = null;

	private UsuarioVO usuario;

	private boolean esMiTurno = false;

	private Timer temporizador;

	private Timer temporizadorInicioPartida;





	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio//
														// porque no va nada
														// en// esa linea



	private Integer cantidadBarcosHundidos = 0;

	private boolean termino = false;

	private static Logger logger = Logger.getLogger(BatallaNavalVentana.class);  //  @jve:decl-index=0:

	private ImagePanel panelBatallaNaval = null;

	private TranslucentPanel panelCentro = null;

	private AnimatedPanelString panelSuperior = null;

	private TransparentPanel panelInferior = null;

	private TranslucentPanel panelEste = null;

	private TranslucentPanel panelOeste = null;

	private JLabel indicadorTurno = null;

	private JLabel jLabelBarcosHundidos = null;

	private UsuarioVO contrincante = null;

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");
	private static ResourceBundle constantes = ResourceBundle.getBundle("bus");
	private final static String ESTUTURNO = labels.getString("LABEL_ESTURNO");
	private final static String NOESTUTURNO = labels.getString("LABEL_NOESTURNO");
	private final static String LOSIENTO = labels.getString("LABEL_LOSIENTO");
	private final static String HASPERDIDO = labels.getString("LABEL_PERDIDO");
	private final static String TITULO = labels.getString("nombre_empresa");
	private final static String URL = labels.getString("LABEL_URLFONDOJUEGO");
	private final static String HASHUNDIDO = labels.getString("LABEL_BARCOSHUNDIDOS1");
	private final static String BARCOS = labels.getString("LABEL_BARCOSHUNDIDOS2");
	private final static String NOMBREJUEGO = labels.getString("LABEL_NOMBREJUEGO");
	private final static String HASHUNDIDOUNBARCO = labels.getString("LABEL_HUNDIDO");
	private final static String GANAR = labels.getString("LABEL_GANADO");
	private final static String PERDER = labels.getString("LABEL_PERDIDO");
	private final static String ENHORABUENA = labels.getString("LABEL_ENHORABUENA");
	private final static String ESPERA = labels.getString("LABEL_ESPERA");
	private final static String LABEL_ERROR = labels.getString("LABEL_ERROR");
	private final static String ERRORDECONEXION = labels.getString("ERROR_CONEXION");
	private final static String ERRORDESCONOCIDO = labels.getString("LABEL_ERROR_DESCONOCIDO");
	private final static String AGUA = constantes.getString("AGUA");
	private final static String TOCADO = constantes.getString("TOCADO");
	private final static String HUNDIDO = constantes.getString("HUNDIDO");
	private final static String OCUPADO = constantes.getString("OCUPADO");
	private final static String TIROERRADO = constantes.getString("TIROERRADO");
	private final static int TAMANO_TABLERO = Integer.parseInt(labels.getString("TAMANO_TABLERO"));
	private JButton[][] botonesJugador = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	// botones que voy a
	// utilizar para mi
	// tablero
	private JButton[][] botonesContrincante = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];


	/**
	 * This is the default constructor
	 */
	public BatallaNaval(final UsuarioVO usuario) {
		super();
		initialize();
		logger.debug("***creo BatallaNavalVentana***");
		this.usuario = usuario;
		this.crearCabezal(panelEste);
		this.crearTablero(panelEste, botonesContrincante); // creo mi tablero
		// en mi panel
		// jugador
		this.crearCabezal(panelOeste);
		this.crearTablero(panelOeste, botonesJugador);// creo mi
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
					indicadorTurno.setText(NOESTUTURNO);
					panelCentro.repaint();
					temporizador.restart();
				} else if (termino == false) {
					indicadorTurno.setText(ESTUTURNO);
					panelCentro.repaint();
					refrescarTableroJugador();
					refrescarTableroOponente();
					if (termino == false && perdi()) {
						termino=true;
						JOptionPane.showMessageDialog(new JFrame(), HASPERDIDO,
								LOSIENTO, JOptionPane.INFORMATION_MESSAGE);
						temporizador.stop();
						terminoPartida(false);// solo termina mi partida cuando perdi
						dispose();
						VentanaPrincipal l = new VentanaPrincipal(usuario);
						l.setVisible(true);
					}
				}
			}
		};
		ActionListener ListenerInicioPartida = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					logger.debug("temporizadorInicioTurno");
					Registry registry = LocateRegistry.getRegistry(HOST);
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
					logger.error("error");
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
		this.setSize(1206, 603);
		this.setContentPane(getJContentPane());
		this.setTitle(TITULO);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBatallaNaval(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelBatallaNaval
	 *
	 * @return javax.swing.JPanel
	 */
	private ImagePanel getPanelBatallaNaval() {
		if (panelBatallaNaval == null) {
			panelBatallaNaval = new ImagePanel(new ImageIcon(URL).getImage());
			panelBatallaNaval.setLayout(new BorderLayout());
			panelBatallaNaval.add(getPanelCentro(), BorderLayout.CENTER);
			panelBatallaNaval.add(getPanelSuperior(), BorderLayout.NORTH);
			panelBatallaNaval.add(getPanelInferior(), BorderLayout.SOUTH);
			panelBatallaNaval.add(getPanelEste(), BorderLayout.EAST);
			panelBatallaNaval.add(getPanelOeste(), BorderLayout.WEST);
		}
		return panelBatallaNaval;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			jLabelBarcosHundidos = new JLabel();
			jLabelBarcosHundidos.setText(HASHUNDIDO+" "+cantidadBarcosHundidos+" "+BARCOS);
			jLabelBarcosHundidos.setOpaque(false);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridy = 0;
			indicadorTurno = new JLabel();
			indicadorTurno.setText(NOESTUTURNO);
			indicadorTurno.setOpaque(false);
			panelCentro = new TranslucentPanel(Color.white);
			panelCentro.setLayout(new GridBagLayout());
			panelCentro.add(indicadorTurno, gridBagConstraints8);
			panelCentro.add(jLabelBarcosHundidos, gridBagConstraints);
		}
		return panelCentro;
	}

	/**
	 * This method initializes PanelSuperior
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelSuperior() {
		if (panelSuperior == null) {
			panelSuperior = new AnimatedPanelString(NOMBREJUEGO);
			panelSuperior.setPreferredSize(new Dimension(0, this.getHeight()/5));
			panelSuperior.setLayout(new GridBagLayout());
		}
		return panelSuperior;
	}

	/**
	 * This method initializes PanelInferior
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new TransparentPanel();
			panelInferior.setLayout(new GridBagLayout());
		}
		return panelInferior;
	}

	/**
	 * This method initializes PanelEste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelEste() {
		if (panelEste == null) {
			panelEste = new TranslucentPanel();
			panelEste.setLayout(new GridBagLayout());
			panelEste.setPreferredSize(new Dimension(this.getWidth()/2,0));
		}
		return panelEste;
	}

	/**
	 * This method initializes PanelOeste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelOeste() {
		if (panelOeste == null) {
			panelOeste = new TranslucentPanel();
			panelOeste.setLayout(new GridBagLayout());
			panelOeste.setPreferredSize(new Dimension(this.getWidth()/2,0));
		}
		return panelOeste;
	}

//	 metodo al cual le paso el panel donde quiero crear un tablero del tamano
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
			jlabel.setForeground(Color.white);
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
				jlabel.setForeground(Color.white);
				jlabel.setHorizontalAlignment(SwingConstants.CENTER);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);
			} else {
				JButton jButton = new JButton();
				if (panel.equals(panelEste)) {// solo añado mis
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
				Registry registry = LocateRegistry.getRegistry(HOST);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				stub.disparar(this.usuario, fila, columna);
				esMiTurno = false;
				this.refrescarTableroOponente();
				indicadorTurno.setText(NOESTUTURNO);
				if (stub.hundi(this.usuario)) {
					cantidadBarcosHundidos++;
					jLabelBarcosHundidos.setText(HASHUNDIDO+" "+cantidadBarcosHundidos+" "+BARCOS);
					JOptionPane.showMessageDialog(new JFrame(),
							HASHUNDIDOUNBARCO, ENHORABUENA,
							JOptionPane.INFORMATION_MESSAGE);
				}
				//termino es para que salten eventos extras del timer
				if (termino == false && this.gane()) {
					termino=true;
					JOptionPane.showMessageDialog(new JFrame(), GANAR,
							ENHORABUENA, JOptionPane.INFORMATION_MESSAGE);
					temporizador.stop();
					try {
						if (stub.esBot(this.usuario)) {
							this.terminoPartida(true);
						}
					} catch (Exception e) {
						logger.error("error");
						e.printStackTrace();
					}

					this.dispose();
					VentanaPrincipal l = new VentanaPrincipal(this.usuario);
					l.setVisible(true);
				}
				temporizador.restart();
			} catch (Exception e) {

			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), NOESTUTURNO,
					ESPERA, JOptionPane.ERROR_MESSAGE);
		}
	}

	// METODO PARA PREGUNTAR TURNO
	public boolean preguntarTurno(UsuarioVO usuario) {
		logger.debug("preguntarTurno");
		  if (termino == false) {
			try { // try y catch para verificar si esta el usuario o
				// no
				Registry registry = LocateRegistry.getRegistry(HOST);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
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
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesJugador, stub.refrescarTablero(usuario));
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDESCONOCIDO,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}

	// METODO PARA REFRESCAR TABLERO DEL OPONENTE
	public void refrescarTableroOponente() {
		logger.debug("refrescarTableroOponente");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesContrincante, stub
					.refrescarTableroOponente(usuario));
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDESCONOCIDO,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
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
				if (tabla[i][j].getEstado().equals(AGUA)) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.BLUE);
				} else if (tabla[i][j].getEstado().equals(OCUPADO)) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.BLACK);
				} else if (tabla[i][j].getEstado().equals(TOCADO)) {
					botones[iArrayBotones][jArrayBotones]
							.setBackground(Color.GREEN);
				} else if (tabla[i][j].getEstado().equals(HUNDIDO)) {
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
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			return stub.gane(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();
			return false;
		}
	}

	private boolean perdi() {
		logger.debug("perdi");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			return stub.perdi(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();
			return false;
		}
	}

	private void terminoPartida(boolean gane) {
		logger.debug("terminoPartida");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");

			stub.terminarPartida(usuario, gane);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();

		}
	}
	private void preguntarContrincante(){
		try{
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			contrincante = stub.contrincante(usuario);
		}catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERRORDECONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			}

			e.printStackTrace();

		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
