package ventanaJuego;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;

import util.ImagePanel;

import java.awt.GridBagLayout;

public class ColocarBarcos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ImagePanel PanelColocarBarcos = null;
	private JPanel PanelTablero = null;

	/**
	 * This is the default constructor
	 */
	public ColocarBarcos() {
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

}
