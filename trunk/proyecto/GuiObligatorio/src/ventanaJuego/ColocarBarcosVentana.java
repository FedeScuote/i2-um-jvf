package ventanaJuego;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;

public class ColocarBarcosVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelElegirBarco = null;

	private ButtonGroup GrupoBotones = null;  //  @jve:decl-index=0:

	private JRadioButton BotonSubmarino = null;

	private JRadioButton BotonDestructor = null;

	private JRadioButton BotonCruzero = null;

	private JRadioButton BotonAcorazado = null;

	private JPanel PanelTablero = null;

	/**
	 * This is the default constructor
	 */
	public ColocarBarcosVentana() {
		super();
		initialize();
		this.agregarBotonesAGroup();
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
			PanelElegirBarco.setLayout(new BoxLayout(getPanelElegirBarco(), BoxLayout.Y_AXIS));
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
			BotonCruzero.setText("Cruzero");
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
	private void agregarBotonesAGroup(){
		GrupoBotones = new ButtonGroup();
		GrupoBotones.add(BotonSubmarino);
		GrupoBotones.add(BotonDestructor);
		GrupoBotones.add(BotonCruzero);
		GrupoBotones.add(BotonAcorazado);
	}

}  //  @jve:decl-index=0:visual-constraint="120,2"
