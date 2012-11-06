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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import util.AnimatedPanelString;
import util.ImagePanel;
import util.TransparentButton;
import util.TransparentPanel;

import comm.ServiciosBatallaNaval;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;

public class ColocarBarcos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ImagePanel PanelColocarBarcos = null;
	private JPanel PanelTablero = null;
	private AnimatedPanelString PanelIngresarBarco = null;
	private UsuarioVO usuario;
	private TransparentPanel PanelCentro = null;
	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio

	// porque no va nada en
	// esa linea

	private static final String host = null;
	private JButton[][] arrayBotones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private boolean primerClick = true;

	private int xPrimerClick = 0;

	private int yPrimerClick = 0;

	private int[] distribucion;

	private static final String SUBMARINO = "SUBMARINO";

	private static final String DESTRUCTORES = "DESTRUCTORES";

	private static final String CRUCEROS = "CRUCEROS";

	private static final String ACORAZADO = "ACORAZADO";

	private static Logger logger = Logger.getLogger(ColocarBarcos.class);
	private TransparentButton BotonSubmarino = null;
	private TransparentButton BotonDestructor = null;
	private TransparentButton BotonCrucero = null;
	private TransparentButton BotonAcorazado = null;

	private String BarcoSeleccionado = "SUBMARINO";
	/**
	 * This is the default constructor
	 */
	public ColocarBarcos(UsuarioVO usu) {
		super();
		initialize();
		usuario=usu;
		logger.debug("Constructor ColocarBarcosVentana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.crearCabezal(PanelTablero);
		this.crearTablero(PanelTablero, arrayBotones);
		this.pedirDistribucion();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(761, 505);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
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
		if (PanelColocarBarcos == null) {
			PanelColocarBarcos = new ImagePanel(new ImageIcon("src/BattleShip-069.jpg").getImage());
			PanelColocarBarcos.setLayout(new BorderLayout());
			PanelColocarBarcos.add(getPanelTablero(), BorderLayout.WEST);
			PanelColocarBarcos.add(getPanelIngresarBarco(), BorderLayout.NORTH);
			PanelColocarBarcos.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return PanelColocarBarcos;
	}

	/**
	 * This method initializes PanelTablero
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelTablero() {
		if (PanelTablero == null) {
			PanelTablero = new JPanel();
			PanelTablero.setLayout(new GridBagLayout());
		}
		return PanelTablero;
	}

	/**
	 * This method initializes PanelIngresarBarco
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelIngresarBarco() {
		if (PanelIngresarBarco == null) {
			PanelIngresarBarco = new AnimatedPanelString("INGRESE SUS BARCOS");
			PanelIngresarBarco.setPreferredSize(new Dimension(0, this.getHeight()/3));
			PanelIngresarBarco.setLayout(new GridBagLayout());
		}
		return PanelIngresarBarco;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (PanelCentro == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			PanelCentro = new TransparentPanel();
			PanelCentro.setLayout(new GridBagLayout());
			PanelCentro.add(getBotonSubmarino(), gridBagConstraints);
			PanelCentro.add(getBotonDestructor(), gridBagConstraints1);
			PanelCentro.add(getBotonCrucero(), gridBagConstraints2);
			PanelCentro.add(getBotonAcorazado(), gridBagConstraints3);
		}
		return PanelCentro;
	}

//	 metodo que me crea mi cabezal con mis letras
	private void crearCabezal(JPanel panel) {
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		logger.debug("CrearCabezal");
		for (int i = 0; i < TAMANO_TABLERO; i++) {
			JLabel jlabel = new JLabel();
			panel.add(jlabel);
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
					columna, BarcoSeleccionado);
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
				Registry registry = LocateRegistry.getRegistry(host);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				stub.agregarBarco(usuario, this
						.mandarCasilleroABus(coordenadaInicialX), this
						.mandarCasilleroABus(coordenadaInicialY), this
						.mandarCasilleroABus(coordenadaFinalX), this
						.mandarCasilleroABus(coordenadaFinalY), tipoBarco);
				this.actualizarDistribucion();
				if(this.agregoEnSentidoX(coordenadaInicialX, coordenadaInicialY, coordenadaFinalX, coordenadaFinalY)){
					this.pintarCasillerosOcupadosX(coordenadaInicialX, yPrimerClick,
					 coordenadaFinalX);
					}else{
						this.pintarCasillerosOcupadosY(coordenadaInicialX, coordenadaInicialY, coordenadaFinalY);
					}
				if (!quedanBarcos()) {
					BatallaNavalVentana l = new BatallaNavalVentana(
							this.usuario);
					l.setVisible(true);
					this.dispose();
				}
			} catch (Exception e) {
				if (e instanceof RemoteException) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Error en la conexion intente de nuevo", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				if (e instanceof CoordenadasInvalidasException) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Coordenadas invalidas intente de nuevo", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					e.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(),
							"ERROR DESCONOCIDO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}
			}
		}else{
			JOptionPane.showMessageDialog(new JFrame(),
					"Ya no se pueden agregar mas barcos de ese tipo", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	private void restarDelArray(int[] array, int index) {
		logger.debug("restarDelArray de la posicion "+(Integer) index);
		if (array[index] != 0) {
			array[index] -= 1;
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					"Ya no se pueden agregar mas barcos de ese tipo", "ERROR",
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
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			distribucion = stub.distribucion(usuario);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Error en la conexion intente de nuevo", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DESCONOCIDO",
								"ERROR", JOptionPane.ERROR_MESSAGE);
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
	}
	private void pintarCasillerosOcupadosY(int XInicial, int YInicial, int YFinal){
		logger.debug("pintarCasillerosOcupadosY");
		for (int i = YInicial; i <= YFinal; i++) {
			arrayBotones[XInicial+1][i+1].setBackground(Color.black);
		}
	}

	private int mandarCasilleroABus(int i) {
		return i--;
	}
	private void actualizarDistribucion() {
		if (BarcoSeleccionado.equals(this.SUBMARINO)) {
			restarDelArray(distribucion, 0);
		}
		if (BarcoSeleccionado.equals(this.DESTRUCTORES)) {
			restarDelArray(distribucion, 1);
		}
		if (BarcoSeleccionado.equals(this.CRUCEROS)) {
			restarDelArray(distribucion, 2);
		}
		if (BarcoSeleccionado.equals(this.ACORAZADO)) {
			restarDelArray(distribucion, 3);
		}
	}

	/**
	 * This method initializes BotonSubmarino
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonSubmarino() {
		if (BotonSubmarino == null) {
			BotonSubmarino = new TransparentButton();
			BotonSubmarino.setText("SUBMARINO");
			BotonSubmarino.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BarcoSeleccionado = SUBMARINO;
				}
			});
		}
		return BotonSubmarino;
	}

	/**
	 * This method initializes BotonDestructor
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonDestructor() {
		if (BotonDestructor == null) {
			BotonDestructor = new TransparentButton();
			BotonDestructor.setText("Destructor");
			BotonDestructor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BarcoSeleccionado= DESTRUCTORES;
				}
			});
		}
		return BotonDestructor;
	}

	/**
	 * This method initializes BotonCrucero
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonCrucero() {
		if (BotonCrucero == null) {
			BotonCrucero = new TransparentButton();
			BotonCrucero.setText("CRUCERO");
		}
		return BotonCrucero;
	}

	/**
	 * This method initializes BotonAcorazado
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonAcorazado() {
		if (BotonAcorazado == null) {
			BotonAcorazado = new TransparentButton();
			BotonAcorazado.setText("Acorazado");
		}
		return BotonAcorazado;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
