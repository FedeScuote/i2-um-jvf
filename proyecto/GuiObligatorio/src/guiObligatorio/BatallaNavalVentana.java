package guiObligatorio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BatallaNavalVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelJugador = null;

	private JPanel PanelContrincante = null;

	private JButton[][] botonesJugador = new JButton[TAMANO_TABLERO][TAMANO_TABLERO]; // es
																						// mi
																						// matriz
																						// de

	// botones que voy a
	// utilizar para mi
	// tablero
	private JButton[][] botonesContrincante = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z" };

	private JLabel[] labelsLetrasJugador = new JLabel[TAMANO_TABLERO];

	private JLabel[] labelsNumerosJugador = new JLabel[TAMANO_TABLERO];

	private JLabel[] labelsLetrasContrincante = new JLabel[TAMANO_TABLERO];

	private JLabel[] labelsNumerosContrincante = new JLabel[TAMANO_TABLERO];

	private JLabel indicadorTurno = null;

	/**
	 * This is the default constructor
	 */
	public BatallaNavalVentana() {
		super();
		initialize();
		this.crearLabelsLetra(PanelJugador, labelsLetrasJugador);
		this.crearTablero(PanelJugador, botonesJugador); // creo mi tablero
															// en mi panel
															// jugador
		this.crearLabelsLetra(PanelContrincante, labelsLetrasContrincante);
		this.crearTablero(PanelContrincante, botonesContrincante);// creo mi
																	// tablero
																	// en mi
																	// panel
																	// contrincante
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);// mi
																				// frame
																				// arranca
																				// maximizada
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(728, 353);
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
			indicadorTurno.setText("ES TU TURNO");
			indicadorTurno.setHorizontalAlignment(SwingConstants.CENTER);
			indicadorTurno.setHorizontalTextPosition(SwingConstants.CENTER);
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
			clickBoton(x, y);
		}
	}

	private void clickBoton(int fila, int columna) {
		// Metodo que va a disparar o no etc.
	}

	// metodo al cual le paso el panel donde quiero crear un tablero del tamano
	// indicado en los atributos
	private void crearTablero(JPanel panel, JButton[][] botones) {
		// crear botones y agregarlos al panel
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		botones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			for (int j = 1; j < TAMANO_TABLERO; j++) {
				JButton jButton = new JButton();
				jButton.addActionListener(new ListenerBoton(i, j));
				panel.add(jButton);
				botones[i][j] = jButton;
				botones[i][j].setSize(25, 25);
				botones[i][j].setMaximumSize(new Dimension(25, 25));
			}
		}
	}

	private void crearLabelsLetra(JPanel panel, JLabel[] arrayLabels) {
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));

		for (int i = 1; i < TAMANO_TABLERO; i++) {
			JLabel jlabel = new JLabel();
			panel.add(jlabel);
			arrayLabels[i] = jlabel;
		}
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			arrayLabels[i].setText(ALFABETO[i - 1]);// alfabeto menos uno porque
													// arranco del i=1
			arrayLabels[i].setHorizontalAlignment(SwingConstants.CENTER);//hago que mis letras queden
			arrayLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);//justificadas
		}
	}

/*	METODO A IMPLEMENTAR
 * private void crearLabelsNumeros(JPanel panel, JLabel[] arrayLabels) {
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			for (int j = 1; j < TAMANO_TABLERO; j++) {
			JLabel jlabel = new JLabel();
			panel.add(jlabel);
			arrayLabels[i] = jlabel;
			}
		}
		for (int i = 1; i < TAMANO_TABLERO; i++) {
			arrayLabels[i].setText(ALFABETO[i - 1]);// alfabeto menos uno porque
													// arranco del i=1
			arrayLabels[i].setHorizontalAlignment(SwingConstants.CENTER);//hago que mis letras queden
			arrayLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);//justificadas
		}
	}*/

}// @jve:decl-index=0:visual-constraint="10,10"
