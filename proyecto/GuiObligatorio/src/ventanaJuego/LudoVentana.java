package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LudoVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel panelCentro = null;

	private JButton[][] botonesJugador = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private final static int TAMANO_TABLERO = 15;

	private static Logger logger = Logger.getLogger(LudoVentana.class);

	private JPanel panelTablero = null;

	/**
	 * This is the default constructor
	 */
	public LudoVentana() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Ludo");
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
			jContentPane.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout());
			panelCentro.add(getPanelTablero(), BorderLayout.CENTER);
		}
		return panelCentro;
	}

	/**
	 * This method initializes panelTablero
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setLayout(new GridBagLayout());
		}
		return panelTablero;
	}

	// METODO QUE CREA MI TABLERO DE LUDO
	private void crearTablero() {
		logger.debug("crearTablero");
		panelTablero.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		for (int i = 0; i < TAMANO_TABLERO; i++) {
			for (int j = 0; j < TAMANO_TABLERO; j++) {
				JButton jButton = new JButton();
				jButton.addActionListener(new ListenerBoton(i, j));
				panelTablero.add(jButton);
				botonesJugador[i][j] = jButton;
			}
		}

	}

	private void crearJugadorRojoCasa() {
		int tamanoCasa = (TAMANO_TABLERO - 3) / 2;
		// DEBIDO A QUE 3 ES
		// LA CANTIDAD
		// DE CASILLAS DE DE MOVIMIENTO EN CADA RAMA
		for (int i = 0; i < tamanoCasa; i++) {
			for (int j = 0; j < tamanoCasa; j++) {

			}
		}
	}

	// metodo que verifica si las casillas de mi casa van pintadas
	private void dibujarCasaVerde() {
		botonesJugador[0][2].setBackground(Color.green);
		botonesJugador[1][2].setBackground(Color.green);
		botonesJugador[0][3].setBackground(Color.green);
		botonesJugador[1][3].setBackground(Color.green);
		botonesJugador[2][0].setBackground(Color.green);
		botonesJugador[3][0].setBackground(Color.green);
		botonesJugador[0][2].setBackground(Color.green);
	}

	private void dibujarCasaRoja(){
		
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
	}

}
