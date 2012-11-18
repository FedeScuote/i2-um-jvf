package login;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginAdministrarCuenta extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelCentro = null;

	private JLabel LabelUsuarioAdmin = null;

	private JLabel LabelIngreseUsuario = null;

	private JTextField UsuarioTextField = null;

	private JLabel PasswordLabel = null;

	private JTextField PasswordTextField = null;

	private JButton BotonLogin = null;

	private JButton BotonRetorno = null;

	/**
	 * This is the default constructor
	 */
	public LoginAdministrarCuenta() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(674, 353);
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
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.fill = GridBagConstraints.NONE;
			gridBagConstraints6.ipadx = 50;
			gridBagConstraints6.insets = new Insets(20, 0, 0,this.getWidth()/3);
			gridBagConstraints6.anchor = GridBagConstraints.CENTER;
			gridBagConstraints6.gridy = 5;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.ipadx = 50;
			gridBagConstraints5.insets = new Insets(20, this.getWidth()/3, 0, 0);
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridy = 5;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridy = 4;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.ipadx = 100;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridy = 3;
			PasswordLabel = new JLabel();
			PasswordLabel.setText("Password");
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.ipadx = 100;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridx = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(0, 0, 50, 0);
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridy = 0;
			LabelIngreseUsuario = new JLabel();
			LabelIngreseUsuario.setText("Ingrese el usuario a administrar");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridy = 1;
			LabelUsuarioAdmin = new JLabel();
			LabelUsuarioAdmin.setText("Usuario");
			PanelCentro = new JPanel();
			PanelCentro.setLayout(new GridBagLayout());
			PanelCentro.add(LabelUsuarioAdmin, gridBagConstraints);
			PanelCentro.add(LabelIngreseUsuario, gridBagConstraints1);
			PanelCentro.add(getUsuarioTextField(), gridBagConstraints2);
			PanelCentro.add(PasswordLabel, gridBagConstraints3);
			PanelCentro.add(getPasswordTextField(), gridBagConstraints4);
			PanelCentro.add(getBotonLogin(), gridBagConstraints5);
			PanelCentro.add(getBotonRetorno(), gridBagConstraints6);
		}
		return PanelCentro;
	}

	/**
	 * This method initializes UsuarioTextField
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getUsuarioTextField() {
		if (UsuarioTextField == null) {
			UsuarioTextField = new JTextField();
		}
		return UsuarioTextField;
	}

	/**
	 * This method initializes PasswordTextField
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getPasswordTextField() {
		if (PasswordTextField == null) {
			PasswordTextField = new JTextField();
		}
		return PasswordTextField;
	}

	/**
	 * This method initializes BotonLogin
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonLogin() {
		if (BotonLogin == null) {
			BotonLogin = new JButton();
			BotonLogin.setText("LOGIN");
		}
		return BotonLogin;
	}

	/**
	 * This method initializes BotonRetorno
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonRetorno() {
		if (BotonRetorno == null) {
			BotonRetorno = new JButton();
			BotonRetorno.setText("Retorno");
		}
		return BotonRetorno;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
