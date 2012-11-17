package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImagePanel;
import util.TransparentPanel;
import java.awt.Dimension;

public class BatallaNaval extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private ImagePanel PanelBatallaNaval = null;

	private TransparentPanel PanelCentro = null;

	private TransparentPanel PanelSuperior = null;

	private TransparentPanel PanelInferior = null;

	private TransparentPanel PanelEste = null;

	private TransparentPanel PanelOeste = null;

	/**
	 * This is the default constructor
	 */
	public BatallaNaval() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(885, 603);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
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
		if (PanelBatallaNaval == null) {
			PanelBatallaNaval = new ImagePanel(new ImageIcon("src/fondoBatallaNaval.jpg").getImage());
			PanelBatallaNaval.setLayout(new BorderLayout());
			PanelBatallaNaval.add(getPanelCentro(), BorderLayout.CENTER);
			PanelBatallaNaval.add(getPanelSuperior(), BorderLayout.NORTH);
			PanelBatallaNaval.add(getPanelInferior(), BorderLayout.SOUTH);
			PanelBatallaNaval.add(getPanelEste(), BorderLayout.EAST);
			PanelBatallaNaval.add(getPanelOeste(), BorderLayout.WEST);
		}
		return PanelBatallaNaval;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (PanelCentro == null) {
			PanelCentro = new TransparentPanel();
			PanelCentro.setLayout(new GridBagLayout());
		}
		return PanelCentro;
	}

	/**
	 * This method initializes PanelSuperior
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelSuperior() {
		if (PanelSuperior == null) {
			PanelSuperior = new TransparentPanel();
			PanelSuperior.setLayout(new GridBagLayout());
		}
		return PanelSuperior;
	}

	/**
	 * This method initializes PanelInferior
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelInferior() {
		if (PanelInferior == null) {
			PanelInferior = new TransparentPanel();
			PanelInferior.setLayout(new GridBagLayout());
		}
		return PanelInferior;
	}

	/**
	 * This method initializes PanelEste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelEste() {
		if (PanelEste == null) {
			PanelEste = new TransparentPanel();
			PanelEste.setLayout(new GridBagLayout());
		}
		return PanelEste;
	}

	/**
	 * This method initializes PanelOeste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelOeste() {
		if (PanelOeste == null) {
			PanelOeste = new TransparentPanel();
			PanelOeste.setLayout(new GridBagLayout());
		}
		return PanelOeste;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
