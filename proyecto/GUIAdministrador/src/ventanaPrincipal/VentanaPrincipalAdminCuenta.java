package ventanaPrincipal;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaPrincipalAdminCuenta extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelCentro = null;

	private JLabel LabelCambiarUsuario = null;

	private JTextField CambiarUsuarioTF = null;

	private JLabel LabelConfirmarUsu = null;

	private JTextField ConfirmarUsuarioTF = null;

	private JLabel LabelCambiarPSW = null;

	private JTextField CambiarPSWTF = null;

	private JLabel LabelConfirmarPSW = null;

	private JTextField ConfirmarPSWTF = null;

	/**
	 * This is the default constructor
	 */
	public VentanaPrincipalAdminCuenta() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(461, 281);
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
			jContentPane.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (PanelCentro == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 7;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.ipadx = 170;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridy = 6;
			LabelConfirmarPSW = new JLabel();
			LabelConfirmarPSW.setText("JLabel");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.ipadx = 170;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(60, 0, 0, 0);
			gridBagConstraints4.gridy = 4;
			LabelCambiarPSW = new JLabel();
			LabelCambiarPSW.setText("JLabel");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 170;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 1, 0);
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 170;
			gridBagConstraints1.ipady = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 1);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.ipadx = 0;
			gridBagConstraints.ipady = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.gridy = 0;
			LabelConfirmarUsu = new JLabel();
			LabelConfirmarUsu.setText("JLabel");
			LabelCambiarUsuario = new JLabel();
			LabelCambiarUsuario.setText("JLabel");
			PanelCentro = new JPanel();
			PanelCentro.setLayout(new GridBagLayout());
			PanelCentro.add(LabelCambiarUsuario, gridBagConstraints);
			PanelCentro.add(getCambiarUsuarioTF(), gridBagConstraints1);
			PanelCentro.add(LabelConfirmarUsu, gridBagConstraints2);
			PanelCentro.add(getConfirmarUsuarioTF(), gridBagConstraints3);
			PanelCentro.add(LabelCambiarPSW, gridBagConstraints4);
			PanelCentro.add(getCambiarPSWTF(), gridBagConstraints5);
			PanelCentro.add(LabelConfirmarPSW, gridBagConstraints6);
			PanelCentro.add(getConfirmarPSWTF(), gridBagConstraints7);
		}
		return PanelCentro;
	}

	/**
	 * This method initializes CambiarUsuarioTF
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getCambiarUsuarioTF() {
		if (CambiarUsuarioTF == null) {
			CambiarUsuarioTF = new JTextField();
		}
		return CambiarUsuarioTF;
	}

	/**
	 * This method initializes ConfirmarUsuarioTF
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getConfirmarUsuarioTF() {
		if (ConfirmarUsuarioTF == null) {
			ConfirmarUsuarioTF = new JTextField();
		}
		return ConfirmarUsuarioTF;
	}

	/**
	 * This method initializes CambiarPSWTF
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getCambiarPSWTF() {
		if (CambiarPSWTF == null) {
			CambiarPSWTF = new JTextField();
		}
		return CambiarPSWTF;
	}

	/**
	 * This method initializes ConfirmarPSWTF
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getConfirmarPSWTF() {
		if (ConfirmarPSWTF == null) {
			ConfirmarPSWTF = new JTextField();
		}
		return ConfirmarPSWTF;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
