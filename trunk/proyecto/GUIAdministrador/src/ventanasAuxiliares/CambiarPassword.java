package ventanasAuxiliares;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ventanaPrincipal.VentanaPrincipal;

import comm.UsuarioVO;

public class CambiarPassword extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanelCambiarPassword = null;

	private JLabel jLabelNick = null;

	private JTextField jTextFieldNick = null;

	private JLabel jLabelPasswordViejo = null;

	private JTextField jTextFieldPasswordViejo = null;

	private JLabel jLabelNuevoPassword = null;

	private JTextField jTextFieldNuevoPassword = null;

	private JLabel jLabelConfirmar = null;

	private JTextField jTextFieldConfirmar = null;

	private JButton jButtonOK = null;

	private JButton jButtonCancelar = null;

	private UsuarioVO usuario = null;

	private static Logger logger = Logger.getLogger(VentanaPrincipal.class);

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private static final String NICK = labels.getString("LABEL_NICK_A");  //  @jve:decl-index=0:
	private static final String PASSWORD = labels.getString("LABELS_PASSWORD_A");  //  @jve:decl-index=0:
	private static final String CONFIRMAR = labels.getString("LABELS_CONFIRMAR_A");
	private static final String PWDNUEVO = labels.getString("LABEL_PWDNUEVO_A");
	private static final String BOTONCREAR = labels.getString("LABEL_ADMIN_CUENTA_A");
	private static final String CANCELAR = labels.getString("LABEL_VOLVER_BOTON");
				/**
	 * This is the default constructor
	 */
	public CambiarPassword(UsuarioVO usu) {
		super();
		usuario = usu;
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
			jContentPane.add(getJPanelCambiarPassword(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelCambiarPassword
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCambiarPassword() {
		if (jPanelCambiarPassword == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.ipadx = 50;
			gridBagConstraints9.ipady = 20;
			gridBagConstraints9.gridy = 9;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.ipadx = 50;
			gridBagConstraints8.ipady = 20;
			gridBagConstraints8.gridy = 8;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 7;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.ipadx = 100;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 6;
			jLabelConfirmar = new JLabel();
			jLabelConfirmar.setText(CONFIRMAR);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.ipadx = 100;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
			jLabelNuevoPassword = new JLabel();
			jLabelNuevoPassword.setText(PWDNUEVO);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 100;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			jLabelPasswordViejo = new JLabel();
			jLabelPasswordViejo.setText(PASSWORD);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabelNick = new JLabel();
			jLabelNick.setText(NICK);
			jPanelCambiarPassword = new JPanel();
			jPanelCambiarPassword.setLayout(new GridBagLayout());
			jPanelCambiarPassword.add(jLabelNick, gridBagConstraints);
			jPanelCambiarPassword.add(getJTextFieldNick(), gridBagConstraints1);
			jPanelCambiarPassword.add(jLabelPasswordViejo, gridBagConstraints2);
			jPanelCambiarPassword.add(getJTextFieldPasswordViejo(), gridBagConstraints3);
			jPanelCambiarPassword.add(jLabelNuevoPassword, gridBagConstraints4);
			jPanelCambiarPassword.add(getJTextFieldNuevoPassword(), gridBagConstraints5);
			jPanelCambiarPassword.add(jLabelConfirmar, gridBagConstraints6);
			jPanelCambiarPassword.add(getJTextFieldConfirmar(), gridBagConstraints7);
			jPanelCambiarPassword.add(getJButtonOK(), gridBagConstraints8);
			jPanelCambiarPassword.add(getJButtonCancelar(), gridBagConstraints9);
		}
		return jPanelCambiarPassword;
	}

	/**
	 * This method initializes jTextFieldNick
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNick() {
		if (jTextFieldNick == null) {
			jTextFieldNick = new JTextField();
		}
		return jTextFieldNick;
	}

	/**
	 * This method initializes jTextFieldPasswordViejo
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPasswordViejo() {
		if (jTextFieldPasswordViejo == null) {
			jTextFieldPasswordViejo = new JTextField();
		}
		return jTextFieldPasswordViejo;
	}

	/**
	 * This method initializes jTextFieldNuevoPassword
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNuevoPassword() {
		if (jTextFieldNuevoPassword == null) {
			jTextFieldNuevoPassword = new JTextField();
		}
		return jTextFieldNuevoPassword;
	}

	/**
	 * This method initializes jTextFieldConfirmar
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldConfirmar() {
		if (jTextFieldConfirmar == null) {
			jTextFieldConfirmar = new JTextField();
		}
		return jTextFieldConfirmar;
	}

	/**
	 * This method initializes jButtonOK
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOK() {
		if (jButtonOK == null) {
			jButtonOK = new JButton();
			jButtonOK.setText(BOTONCREAR);
		}
		return jButtonOK;
	}

	/**
	 * This method initializes jButtonCancelar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCancelar() {
		if (jButtonCancelar == null) {
			jButtonCancelar = new JButton();
			jButtonCancelar.setText(CANCELAR);
		}
		return jButtonCancelar;
	}

}
