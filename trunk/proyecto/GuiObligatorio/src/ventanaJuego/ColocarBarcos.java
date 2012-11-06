package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.AnimatedPanelString;
import util.ImagePanel;

import comm.UsuarioVO;

public class ColocarBarcos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ImagePanel PanelColocarBarcos = null;
	private JPanel PanelTablero = null;
	private AnimatedPanelString PanelIngresarBarco = null;
	private UsuarioVO usuario;
	/**
	 * This is the default constructor
	 */
	public ColocarBarcos(UsuarioVO usu) {
		super();
		initialize();
		usuario=usu;
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
