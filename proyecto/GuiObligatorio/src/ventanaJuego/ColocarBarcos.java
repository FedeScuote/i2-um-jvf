package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

import org.apache.log4j.Logger;

import util.AnimatedPanelString;
import util.CustomGlassPane;
import util.ImagePanel;
import util.TranslucentPanel;
import util.TransparentButton;
import util.TransparentPanel;

import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;

public class ColocarBarcos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ImagePanel panelColocarBarcos = null;
	private TranslucentPanel panelTablero = null;
	private AnimatedPanelString panelIngresarBarco = null;
	private UsuarioVO usuario;
	private TransparentPanel panelCentro = null;
	

	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio

	// porque no va nada en
	// esa linea

	private static final String HOST = null;


	private boolean primerClick = true;

	private int xPrimerClick = 0;

	private int yPrimerClick = 0;

	private int[] distribucion;

	private static Logger logger = Logger.getLogger(ColocarBarcos.class);
	private TransparentButton botonSubmarino = null;
	private TransparentButton botonDestructor = null;
	private TransparentButton botonCrucero = null;
	private TransparentButton botonAcorazado = null;

	private String barcoSeleccionado = "SUBMARINO";  //  @jve:decl-index=0:
	private CustomGlassPane panelBarcosRestantes = null;
	private JLabel labelRenglon1 = null;
	private JLabel labelRenglon2 = null;
	private JLabel labelSubmarine = null;
	private JLabel labelDestroyer = null;
	private JLabel labelCruiser = null;
	private JLabel labelBattleship = null;
	private JLabel labelRenglon3 = null;
	private JLabel labelRenglon4 = null;
	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");
	private final static int TAMANO_TABLERO = Integer.parseInt(labels.getString("TAMANO_TABLERO"));// (casillas)+(labels)
	private static final String LABEL_ERROR_NO_USU_VP = labels.getString("LABEL-ERROR-NO-USU-VP");
	private static ResourceBundle constantes = ResourceBundle.getBundle("bus");
	private static final String SUBMARINO = constantes.getString("SUBMARINO");
	private static final String DESTRUCTORES = constantes.getString("DESTRUCTORES");
	private static final String CRUCEROS = constantes.getString("CRUCEROS");
	private static final String ACORAZADO = constantes.getString("ACORAZADO");
	private static final String LABEL_NOMBRE_EMPRESA = labels.getString("nombre_empresa");
	private static final String URL_FONDO = labels.getString("LABEL_URLFONDO_COLOCARBARCOS");
	private static final String LABEL_INGRESE_SUS_BARCOS = labels.getString("LABEL_INGRESE_SUS_BARCOS");
	private static final String LABEL_BATTLESHIP = labels.getString("LABEL_BATTLESHIP");
	private static final String LABEL_SUBMARINE = labels.getString("LABEL_SUBMARINE");
	private static final String LABEL_DESTROYER = labels.getString("LABEL_DESTROYER");
	private static final String LABEL_CRUISER = labels.getString("LABEL_CRUISER");
	private static final String ERROR_CONEXION = labels.getString("ERROR_CONEXION");
	private static final String LABEL_ERROR = labels.getString("LABEL_ERROR");
	private static final String COORDENADAS_INVALIDAS = labels.getString("COORDENADAS_INVALIDAS");
	private static final String LABEL_ERROR_DESCONOCIDO = labels.getString("LABEL_ERROR_DESCONOCIDO");
	private static final String LIMITE_BARCOS = labels.getString("LIMITE_BARCOS");
	private static final String URL_BOTON_SUBMARINO = labels.getString("URL_BOTON_SUBMARINO");
	private static final String URL_BOTON_ACORAZADO= labels.getString("URL_BOTON_ACORAZADO");
	private static final String URL_BOTON_CRUCERO = labels.getString("URL_BOTON_CRUCERO");
	private static final String URL_BOTON_DESTRUCTOR = labels.getString("URL_BOTON_DESTRUCTOR");
	private static final String LABEL_INSTRUCCIONES = labels.getString("LABEL_INSTRUCCIONES");
	private static final String LABEL_RENGLON1_INSTRUCCIONES = labels.getString("LABEL_RENGLON1_INSTRUCCIONES");
	private static final String LABEL_RENGLON2_INSTRUCCIONES = labels.getString("LABEL_RENGLON2_INSTRUCCIONES");
	private static final String LABEL_RENGLON3_INSTRUCCIONES = labels.getString("LABEL_RENGLON3_INSTRUCCIONES");
	private static final String LABEL_RENGLON4_INSTRUCCIONES = labels.getString("LABEL_RENGLON4_INSTRUCCIONES");
	private static final String AGUA = constantes.getString("AGUA");
	private static final String TOCADO = constantes.getString("TOCADO");
	private static final String HUNDIDO = constantes.getString("HUNDIDO");
	private static final String OCUPADO = constantes.getString("OCUPADO");
	private JLabel labelRenglon5 = null;
	private JButton[][] arrayBotones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];


	/**
	 * This is the default constructor
	 */
	public ColocarBarcos(UsuarioVO usu) {
		super();
		this.pedirDistribucion();
		initialize();
		usuario=usu;
		logger.debug("Constructor ColocarBarcos" +
				"");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.crearCabezal(panelTablero);
		this.crearTablero(panelTablero, arrayBotones);
		this.refrescarTableroJugador();

	}
	//constructor para la desconexion
	public ColocarBarcos(UsuarioVO usu, int[] distribucion) {
		super();
		this.distribucion = distribucion;
		initialize();
		usuario=usu;
		logger.debug("Constructor ColocarBarcos" +
				"");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.crearCabezal(panelTablero);
		this.crearTablero(panelTablero, arrayBotones);
		this.refrescarTableroJugador();

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(761, 505);
		this.setContentPane(getJContentPane());
		this.setTitle(LABEL_NOMBRE_EMPRESA);
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
			jContentPane.add(getPanelColocarBarcos(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelColocarBarcos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelColocarBarcos() {
		if (panelColocarBarcos == null) {
			panelColocarBarcos = new ImagePanel(new ImageIcon(URL_FONDO).getImage());
			panelColocarBarcos.setLayout(new BorderLayout());
			panelColocarBarcos.add(getPanelTablero(), BorderLayout.WEST);
			panelColocarBarcos.add(getPanelIngresarBarco(), BorderLayout.NORTH);
			panelColocarBarcos.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return panelColocarBarcos;
	}

	/**
	 * This method initializes PanelTablero
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new TranslucentPanel();
			panelTablero.setLayout(new GridBagLayout());
		}
		return panelTablero;
	}

	/**
	 * This method initializes PanelIngresarBarco
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelIngresarBarco() {
		if (panelIngresarBarco == null) {
			panelIngresarBarco = new AnimatedPanelString(LABEL_INGRESE_SUS_BARCOS);
			panelIngresarBarco.setPreferredSize(new Dimension(0, this.getHeight()/3));
			panelIngresarBarco.setLayout(new GridBagLayout());
		}
		return panelIngresarBarco;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 2;
			labelBattleship = new JLabel();
			labelBattleship.setText(LABEL_BATTLESHIP+(Integer)distribucion[3]);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelCruiser = new JLabel();
			labelCruiser.setText(LABEL_CRUISER+(Integer)distribucion[2]);
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 1;
			gridBagConstraints31.gridy = 0;
			labelDestroyer = new JLabel();
			labelDestroyer.setText(LABEL_DESTROYER+(Integer)distribucion[1]);
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 0;
			labelSubmarine = new JLabel();
			labelSubmarine.setText(LABEL_SUBMARINE+(Integer)distribucion[0]);
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints11.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 3;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			panelCentro = new TransparentPanel();
			panelCentro.setLayout(new GridBagLayout());
			panelCentro.add(getBotonSubmarino(), gridBagConstraints);
			panelCentro.add(getBotonDestructor(), gridBagConstraints1);
			panelCentro.add(getBotonCrucero(), gridBagConstraints2);
			panelCentro.add(getBotonAcorazado(), gridBagConstraints3);
			panelCentro.add(getPanelBarcosRestantes(), gridBagConstraints11);
			panelCentro.add(labelSubmarine, gridBagConstraints21);
			panelCentro.add(labelDestroyer, gridBagConstraints31);
			panelCentro.add(labelCruiser, gridBagConstraints4);
			panelCentro.add(labelBattleship, gridBagConstraints5);
		}
		return panelCentro;
	}

//	 metodo que me crea mi cabezal con mis letras
	private void crearCabezal(JPanel panel) {
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		logger.debug("CrearCabezal");
		for (int i = 0; i < TAMANO_TABLERO; i++) {
			JLabel jlabel = new JLabel();
			panel.add(jlabel);
			jlabel.setForeground(Color.white);
			jlabel.setText(ALFABETO[i]); // alfabeto menos uno porque
			jlabel.setHorizontalAlignment(SwingConstants.CENTER);
			jlabel.setVerticalAlignment(SwingConstants.CENTER);
		}
	}

	// metodo al cual le paso el panel donde quiero crear un tablero del tamano
	// indicado en los atributos
	private void crearTablero(JPanel panel, JButton[][] botones) {
		// crear botones y agregarlos al panel
		logger.debug("Metodo Creartablero");
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			this.crearFila(panel, i, botones);

		}
	}

//	 metodo que le paso numero de fila y me agrega la fila con jlabel
	// correspondiente
	private void crearFila(JPanel panel, Integer numeroFila, JButton[][] botones) {
		logger.debug("Crear Fila");
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
				jButton.setBackground(Color.blue);
				jButton.addActionListener(new ListenerBoton(numeroFila, j));
				panel.add(jButton);
				botones[numeroFila][j] = jButton;
			}
		}
	}
//	 clase de mis actionListener que voy a usar en mis botones
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
			clickBoton(x - 1, y - 1);// Manejo menos uno porque EL BUS NO
			// SABE MANEJARSE
		}
	}

	private void clickBoton(int fila, int columna) {
		if (primerClick) {
			xPrimerClick = fila;
			yPrimerClick = columna;
			primerClick = false;
		} else {
			this.colocarBarco(usuario, xPrimerClick, yPrimerClick, fila,
					columna, barcoSeleccionado);
			primerClick = true;
		}

	}

	// metodo para colocarBarco con rmi
	private void colocarBarco(UsuarioVO usuario, int coordenadaInicialX,
			int coordenadaInicialY, int coordenadaFinalX, int coordenadaFinalY,
			String tipoBarco) {
		logger.debug("ColocarBarco"+(Integer)coordenadaInicialX+(Integer)coordenadaInicialY+coordenadaFinalX+coordenadaFinalY);
		if (quedanBarcos(tipoBarco)) {
			try { // try y catch para verificar si esta el usuario o
				// no
				Registry registry = LocateRegistry.getRegistry(HOST);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				stub.agregarBarco(usuario, this
						.mandarCasilleroABus(coordenadaInicialX), this
						.mandarCasilleroABus(coordenadaInicialY), this
						.mandarCasilleroABus(coordenadaFinalX), this
						.mandarCasilleroABus(coordenadaFinalY), tipoBarco);
				this.actualizarDistribucion();
				this.refrescarLabels();
				if(this.agregoEnSentidoX(coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY)){
					this.pintarCasillerosOcupadosX(coordenadaInicialX, yPrimerClick,
					 coordenadaFinalX);
					}else{
						this.pintarCasillerosOcupadosY(coordenadaInicialX, coordenadaInicialY, coordenadaFinalY);
					}
				if (!quedanBarcos()) {
					BatallaNaval l = new BatallaNaval(
							this.usuario);
					l.setVisible(true);
					this.dispose();
				}
			} catch (Exception e) {
				if (e instanceof RemoteException) {
					JOptionPane.showMessageDialog(new JFrame(),
							ERROR_CONEXION, LABEL_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
				if (e instanceof CoordenadasInvalidasException) {
					JOptionPane.showMessageDialog(new JFrame(),
							COORDENADAS_INVALIDAS, LABEL_ERROR,
							JOptionPane.ERROR_MESSAGE);
				} else {
					e.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(),
							LABEL_ERROR_DESCONOCIDO, LABEL_ERROR,
							JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}
		}else{
			JOptionPane.showMessageDialog(new JFrame(),
					LIMITE_BARCOS, LABEL_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	private void restarDelArray(int[] array, int index) {
		logger.debug("restarDelArray de la posicion "+(Integer) index);
		if (array[index] != 0) {
			array[index] -= 1;
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					LIMITE_BARCOS, LABEL_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean quedanBarcos() {
		boolean quedan = false;
		for (int i = 0; i < distribucion.length; i++) {
			if (distribucion[i] != 0) {
				quedan = true;
			}
		}
		return quedan;
	}

	private boolean quedanBarcos(String tipoBarco) {
		logger.debug("quedanBarcos"+tipoBarco);
		if (tipoBarco.equals(SUBMARINO)) {
			return distribucion[0] != 0;
		} else if (tipoBarco.equals(DESTRUCTORES)) {
			return distribucion[1] != 0;
		} else if (tipoBarco.equals(CRUCEROS)) {
			return distribucion[2] != 0;
		} else {
			return distribucion[3] != 0;
		}
	}

	private void pedirDistribucion() {
		logger.debug("PedirDistribucion");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			distribucion = stub.distribucion(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane.showMessageDialog(new JFrame(),
						ERROR_CONEXION, LABEL_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), LABEL_ERROR_DESCONOCIDO,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}
	//METODO QUE DEDUCE SI AGREGO EN SENTIDO X
	private boolean agregoEnSentidoX(int XInicial, int YInicial, int XFinal,int YFinal){
		return YInicial == YFinal;
	}
	//METODO QUE DEDUCE SI AGREGO EN SENTIDO Y
	private boolean agregoEnSentidoY(int XInicial, int YInicial, int XFinal, int YFinal){
		return XInicial== XFinal;
	}
	//METODOS PARA PINTAR LOS CASILLEROS PARA INDICAR DONDE SE AGREGO
	private void pintarCasillerosOcupadosX(int XInicial, int YInicial,
			int XFinal) {
		logger.debug("pintarCasillerosOcupadosX");
		for (int i = XInicial; i <= XFinal; i++) {
				arrayBotones[i+1][YInicial+1].setBackground(Color.black);
		}
		for (int i = XFinal; i <= XInicial; i++) {
			arrayBotones[i+1][YInicial+1].setBackground(Color.black);
	}
	}
	private void pintarCasillerosOcupadosY(int XInicial, int YInicial, int YFinal){
		logger.debug("pintarCasillerosOcupadosY");
		for (int i = YInicial; i <= YFinal; i++) {
			arrayBotones[XInicial+1][i+1].setBackground(Color.black);
		}
		for (int i = YFinal; i <= YInicial; i++) {
			arrayBotones[XInicial+1][i+1].setBackground(Color.black);
		}
	}

	private int mandarCasilleroABus(int i) {
		return i--;
	}
	private void actualizarDistribucion() {
		if (barcoSeleccionado.equals(this.SUBMARINO)) {
			restarDelArray(distribucion, 0);
		}
		if (barcoSeleccionado.equals(this.DESTRUCTORES)) {
			restarDelArray(distribucion, 1);
		}
		if (barcoSeleccionado.equals(this.CRUCEROS)) {
			restarDelArray(distribucion, 2);
		}
		if (barcoSeleccionado.equals(this.ACORAZADO)) {
			restarDelArray(distribucion, 3);
		}
	}

	/**
	 * This method initializes BotonSubmarino
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonSubmarino() {
		if (botonSubmarino == null) {
			botonSubmarino = new TransparentButton(new ImageIcon(URL_BOTON_SUBMARINO).getImage());
			botonSubmarino.setSize(200, 120);
			botonSubmarino.setText("SUBMARINO");
			botonSubmarino.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					barcoSeleccionado = SUBMARINO;
					desClickBotones();
					botonSubmarino.setClicked(true);
				}
			});
		}
		return botonSubmarino;
	}

	/**
	 * This method initializes BotonDestructor
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonDestructor() {
		if (botonDestructor == null) {
			botonDestructor = new TransparentButton(new ImageIcon(URL_BOTON_DESTRUCTOR).getImage());
			botonDestructor.setSize(200, 120);
			botonDestructor.setText("Destructor");
			botonDestructor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					barcoSeleccionado= DESTRUCTORES;
					desClickBotones();
					botonDestructor.setClicked(true);
				}
			});
		}
		return botonDestructor;
	}

	/**
	 * This method initializes BotonCrucero
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonCrucero() {
		if (botonCrucero == null) {
			botonCrucero = new TransparentButton(new ImageIcon(URL_BOTON_CRUCERO).getImage());
			botonCrucero.setSize(200, 120);
			botonCrucero.setText("CRUCERO");
			botonCrucero.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					barcoSeleccionado= CRUCEROS;
					desClickBotones();
					botonCrucero.setClicked(true);
				}
			});
		}
		return botonCrucero;
	}

	/**
	 * This method initializes BotonAcorazado
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonAcorazado() {
		if (botonAcorazado == null) {
			botonAcorazado = new TransparentButton(new ImageIcon(URL_BOTON_ACORAZADO).getImage());
			botonAcorazado.setSize(200, 120);
			botonAcorazado.setText("Acorazado");
			botonAcorazado.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					barcoSeleccionado = ACORAZADO;
					desClickBotones();
					botonAcorazado.setClicked(true);
				}
			});
		}
		return botonAcorazado;
	}
	private void desClickBotones(){
		botonSubmarino.setClicked(false);
		botonDestructor.setClicked(false);
		botonCrucero.setClicked(false);
		botonAcorazado.setClicked(false);
	}

	/**
	 * This method initializes PanelBarcosRestantes
	 *
	 * @return javax.swing.JPanel
	 */
	private CustomGlassPane getPanelBarcosRestantes() {
		if (panelBarcosRestantes == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 5;
			labelRenglon5 = new JLabel();
			labelRenglon5.setText(LABEL_RENGLON4_INSTRUCCIONES);
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 4;
			labelRenglon4 = new JLabel();
			labelRenglon4.setText(LABEL_RENGLON3_INSTRUCCIONES);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			labelRenglon3 = new JLabel();
			labelRenglon3.setText(LABEL_RENGLON2_INSTRUCCIONES);
			labelRenglon2 = new JLabel();
			labelRenglon2.setText(LABEL_RENGLON1_INSTRUCCIONES);
			labelRenglon1 = new JLabel();
			labelRenglon1.setFont(new Font("Arial", Font.BOLD, 24));
			labelRenglon1.setText(LABEL_INSTRUCCIONES);
			panelBarcosRestantes = new CustomGlassPane();
			panelBarcosRestantes.setLayout(new GridBagLayout());
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints1.gridy = 2;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			panelBarcosRestantes.add(labelRenglon1, gridBagConstraints);
			panelBarcosRestantes.add(labelRenglon2, gridBagConstraints1);
			panelBarcosRestantes.add(labelRenglon3, gridBagConstraints6);
			panelBarcosRestantes.add(labelRenglon4, gridBagConstraints7);
			panelBarcosRestantes.add(labelRenglon5, gridBagConstraints12);
		}
		return panelBarcosRestantes;
	}
//	 METODO PARA REFRESCAR TABLERO DEL JUGADOR
	public void refrescarTableroJugador() {
		logger.debug("refrescarTableroJugador");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(arrayBotones, stub.refrescarTablero(usuario));
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane
						.showMessageDialog(new JFrame(), ERROR_CONEXION,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), LABEL_ERROR_DESCONOCIDO,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}
//	 METODO DONDE "PINTA" LOS BOTONES PARA REFRESCAR EL TABLERO
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
	private void refrescarLabels(){
		labelBattleship.setText(LABEL_BATTLESHIP+(Integer)distribucion[3]);
		labelCruiser.setText(LABEL_CRUISER+(Integer)distribucion[2]);
		labelDestroyer.setText(LABEL_DESTROYER+(Integer)distribucion[1]);
		labelSubmarine.setText(LABEL_SUBMARINE+(Integer)distribucion[0]);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
