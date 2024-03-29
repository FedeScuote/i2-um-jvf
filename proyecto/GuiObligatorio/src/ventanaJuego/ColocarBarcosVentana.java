package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;
import commExceptions.CoordenadasInvalidasException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import login.LoginVentana;

public class ColocarBarcosVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio

	// porque no va nada en
	// esa linea

	private static final String host = null;

	private UsuarioVO usuario = null;

	private JPanel jContentPane = null;

	private JPanel PanelElegirBarco = null;

	private ButtonGroup GrupoBotones = null; // @jve:decl-index=0:

	private JRadioButton BotonSubmarino = null;

	private JRadioButton BotonDestructor = null;

	private JRadioButton BotonCruzero = null;

	private JRadioButton BotonAcorazado = null;

	private JPanel PanelTablero = null;

	private JButton[][] arrayBotones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private boolean primerClick = true;

	private int xPrimerClick = 0;

	private int yPrimerClick = 0;

	private int[] distribucion;



	private static final String SUBMARINO = "SUBMARINO";

	private static final String DESTRUCTORES = "DESTRUCTORES";

	private static final String CRUCEROS = "CRUCEROS";

	private static final String ACORAZADO = "ACORAZADO";

	private JPanel PanelColocarBarcos = null;

	private JLabel CantidadBarcosAColocar = null;

	private JLabel CantidadSubmarinos = null;

	private JLabel CantidadDestructores = null;

	private JLabel CantidadCruceros = null;

	private JLabel CantidadAcorazado = null;

	private static Logger logger = Logger.getLogger(ColocarBarcosVentana.class);

	/**
	 * This is the default constructor
	 */
	public ColocarBarcosVentana(UsuarioVO usuario) {
		super();
		this.pedirDistribucion();
		initialize();
		logger.debug("Constructor ColocarBarcosVentana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.agregarBotonesAGroup();
		this.usuario = usuario;
		this.crearCabezal(PanelTablero);
		this.crearTablero(PanelTablero, arrayBotones);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.refrescarTableroJugador();

	}
	public ColocarBarcosVentana(UsuarioVO usuario, int[] distribucion) {
		super();
		this.distribucion = distribucion;
		initialize();
		logger.debug("Constructor ColocarBarcosVentana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.agregarBotonesAGroup();
		this.usuario = usuario;
		this.crearCabezal(PanelTablero);
		this.crearTablero(PanelTablero, arrayBotones);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.refrescarTableroJugador();

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelElegirBarco(), BorderLayout.EAST);
			jContentPane.add(getPanelTablero(), BorderLayout.WEST);
			jContentPane.add(getPanelColocarBarcos(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelElegirBarco
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelElegirBarco() {
		if (PanelElegirBarco == null) {
			PanelElegirBarco = new JPanel();
			PanelElegirBarco.setLayout(new BoxLayout(getPanelElegirBarco(),
					BoxLayout.Y_AXIS));
			PanelElegirBarco.add(getBotonSubmarino(), null);
			PanelElegirBarco.add(getBotonDestructor(), null);
			PanelElegirBarco.add(getBotonCruzero(), null);
			PanelElegirBarco.add(getBotonAcorazado(), null);
		}
		return PanelElegirBarco;
	}

	/**
	 * This method initializes BotonSubmarino
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getBotonSubmarino() {
		if (BotonSubmarino == null) {
			BotonSubmarino = new JRadioButton();
			BotonSubmarino.setText("Submarino");
		}
		return BotonSubmarino;
	}

	/**
	 * This method initializes BotonDestructor
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getBotonDestructor() {
		if (BotonDestructor == null) {
			BotonDestructor = new JRadioButton();
			BotonDestructor.setText("Destructor");
		}
		return BotonDestructor;
	}

	/**
	 * This method initializes BotonCruzero
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getBotonCruzero() {
		if (BotonCruzero == null) {
			BotonCruzero = new JRadioButton();
			BotonCruzero.setText("Crucero");
			BotonCruzero.setSelected(true);
		}
		return BotonCruzero;
	}

	/**
	 * This method initializes BotonAcorazado
	 *
	 * @return javax.swing.JRadioButton
	 */
	private JRadioButton getBotonAcorazado() {
		if (BotonAcorazado == null) {
			BotonAcorazado = new JRadioButton();
			BotonAcorazado.setText("Acorazado");
		}
		return BotonAcorazado;
	}

	/**
	 * This method initializes PanelTablero
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelTablero() {
		if (PanelTablero == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			PanelTablero = new JPanel();
			PanelTablero.setLayout(gridLayout);
		}
		return PanelTablero;
	}

	private void agregarBotonesAGroup() {
		GrupoBotones = new ButtonGroup();
		GrupoBotones.add(BotonSubmarino);
		GrupoBotones.add(BotonDestructor);
		GrupoBotones.add(BotonCruzero);
		GrupoBotones.add(BotonAcorazado);
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

	// metodo que me crea mi cabezal con mis letras
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

	// metodo que le paso numero de fila y me agrega la fila con jlabel
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
			String tipoBarco = this.getBotonSelected();
			this.colocarBarco(usuario, xPrimerClick, yPrimerClick, fila,
					columna, tipoBarco);
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
				this.refrescarLabels();
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

	// Metodo que me devuelve que boton esta seleccionado
	private String getBotonSelected() {
		logger.debug("getBotonSelected");
		if (BotonCruzero.isSelected()) {
			return CRUCEROS;
		}
		if (BotonSubmarino.isSelected()) {
			return SUBMARINO;
		}
		if (BotonAcorazado.isSelected()) {
			return ACORAZADO;
		} else {
			return DESTRUCTORES;
		}
	}

	private void actualizarDistribucion() {
		if (getBotonSelected().equals(SUBMARINO)) {
			restarDelArray(distribucion, 0);
		}
		if (getBotonSelected().equals(DESTRUCTORES)) {
			restarDelArray(distribucion, 1);
		}
		if (getBotonSelected().equals(CRUCEROS)) {
			restarDelArray(distribucion, 2);
		}
		if (getBotonSelected().equals(ACORAZADO)) {
			restarDelArray(distribucion, 3);
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

	/**
	 * This method initializes PanelColocarBarcos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelColocarBarcos() {
		if (PanelColocarBarcos == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 4;
			CantidadDestructores = new JLabel();
			CantidadDestructores.setText("DESTRUCTORES:"+getCantidadDestructores());
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 3;
			CantidadSubmarinos = new JLabel();
			CantidadSubmarinos.setText("SUBMARINO:"+getCantidadSubmarinos());
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;

			CantidadCruceros = new JLabel();
			CantidadCruceros.setText("CRUCEROS:"+getCantidadCruceros());
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.gridx = 0;
			gridBagConstraints111.gridy = 1;

			CantidadAcorazado = new JLabel();
			CantidadAcorazado.setText("ACORAZADO:"+getCantidadAcorazado());
			GridBagConstraints gridBagConstraints1111 = new GridBagConstraints();
			gridBagConstraints1111.gridx = 0;
			gridBagConstraints1111.gridy = 0;

			CantidadBarcosAColocar = new JLabel();
			CantidadBarcosAColocar.setText("CANTIDAD DE BARCOS A COLOCAR");
			PanelColocarBarcos = new JPanel();
			PanelColocarBarcos.setLayout(new GridBagLayout());
			PanelColocarBarcos.add(CantidadBarcosAColocar, gridBagConstraints11);
			PanelColocarBarcos.add(CantidadSubmarinos, gridBagConstraints1);
			PanelColocarBarcos.add(CantidadDestructores, gridBagConstraints);
			PanelColocarBarcos.add(CantidadCruceros, gridBagConstraints111);
			PanelColocarBarcos.add(CantidadAcorazado, gridBagConstraints1111);
		}
		return PanelColocarBarcos;
	}
	private Integer getCantidadSubmarinos(){
		return distribucion[0];
	}
	private Integer getCantidadDestructores(){
		return distribucion[1];
	}
	private Integer getCantidadCruceros(){
		return distribucion[2];
	}
	private Integer getCantidadAcorazado(){
		return distribucion[3];
	}
	private void refrescarLabels(){
		logger.debug("refrescarLabels");
		CantidadAcorazado.setText("ACORAZADO:"+getCantidadAcorazado());
		CantidadCruceros.setText("CRUCEROS:"+getCantidadCruceros());
		CantidadDestructores.setText("DESTRUCTORES:"+getCantidadDestructores());
		CantidadSubmarinos.setText("SUBMARINO:"+getCantidadSubmarinos());
	}
//	 METODO PARA REFRESCAR TABLERO DEL JUGADOR
	public void refrescarTableroJugador() {
		logger.debug("refrescarTableroJugador");
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(arrayBotones, stub.refrescarTablero(usuario));
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

} // @jve:decl-index=0:visual-constraint="120,2"
