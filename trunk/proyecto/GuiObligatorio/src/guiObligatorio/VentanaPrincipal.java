package guiObligatorio;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel panelVentanaPrincipal = null;

	private JScrollPane panelRanking = null;

	private JTable tablaRanking = null;

	private JButton jugarLudo = null;

	private JButton jugarBatalla = null;

	private JButton jugarBackgammon = null;

	private JLabel imagenFondo = null;

	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("Ventana Principal");
		this.setBounds(new Rectangle(0, 0, 834, 632));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			jContentPane.add(getPanelVentanaPrincipal(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelVentanaPrincipal
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelVentanaPrincipal() {
		if (panelVentanaPrincipal == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridheight = 10;
			gridBagConstraints8.anchor = GridBagConstraints.NORTH;
			gridBagConstraints8.gridwidth = 5;
			gridBagConstraints8.gridy = 0;
			imagenFondo = new JLabel();
			imagenFondo.setText("");
			imagenFondo.setBackground(Color.white);
			imagenFondo.setIcon(new ImageIcon(getClass().getResource("/guiObligatorio/Sin t�tulo.jpg")));
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.ipadx = 48;
			gridBagConstraints3.ipady = 40;
			gridBagConstraints3.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.ipadx = 55;
			gridBagConstraints2.ipady = 40;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.ipady = 40;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints1.gridy = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridy = 4;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.ipadx = 322;
			gridBagConstraints.ipady = 215;
			gridBagConstraints.gridx = 0;
			panelVentanaPrincipal = new JPanel();
			panelVentanaPrincipal.setLayout(new GridBagLayout());
			panelVentanaPrincipal.add(getPanelRanking(), gridBagConstraints);
			panelVentanaPrincipal.add(getJugarLudo(), gridBagConstraints1);
			panelVentanaPrincipal.add(getJugarBatalla(), gridBagConstraints2);
			panelVentanaPrincipal.add(getJugarBackgammon(), gridBagConstraints3);
			panelVentanaPrincipal.add(imagenFondo, gridBagConstraints8);
		}
		return panelVentanaPrincipal;
	}

	/**
	 * This method initializes panelRanking
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getPanelRanking() {
		if (panelRanking == null) {
			panelRanking = new JScrollPane();
			panelRanking.setViewportView(getTablaRanking());
		}
		return panelRanking;
	}

	/**
	 * This method initializes tablaRanking
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTablaRanking() {
		if (tablaRanking == null) {
			tablaRanking = new JTable();
		}
		return tablaRanking;
	}

	/**
	 * This method initializes jugarLudo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarLudo() {
		if (jugarLudo == null) {
			jugarLudo = new JButton();
			jugarLudo.setText("Jugar Ludo");
		}
		return jugarLudo;
	}

	/**
	 * This method initializes jugarBatalla
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarBatalla() {
		if (jugarBatalla == null) {
			jugarBatalla = new JButton();
			jugarBatalla.setText("Jugar Batalla Naval");
		}
		return jugarBatalla;
	}

	/**
	 * This method initializes jugarBackgammon
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarBackgammon() {
		if (jugarBackgammon == null) {
			jugarBackgammon = new JButton();
			jugarBackgammon.setText("Jugar BackGammon");
		}
		return jugarBackgammon;
	}

}  //  @jve:decl-index=0:visual-constraint="39,-35"