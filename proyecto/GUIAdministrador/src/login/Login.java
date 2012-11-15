package login;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelLogin = null;

	private JLabel Usuario = null;

	private JTextField UsuarioTextField = null;

	private JLabel PasswordLabel = null;

	private JPasswordField PasswordFieldText = null;

	private JButton BotonLogin = null;

	/**
	 * This method initializes PanelLogin
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelLogin() {
		if (PanelLogin == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.ipadx = 25;
			gridBagConstraints4.ipady = 25;
			gridBagConstraints4.insets = new Insets(25, 0, 0, 0);
			gridBagConstraints4.gridy = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 80;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			PasswordLabel = new JLabel();
			PasswordLabel.setText("Password");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridwidth = 1;
			gridBagConstraints1.ipadx = 80;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			Usuario = new JLabel();
			Usuario.setText("Usuario");
			PanelLogin = new JPanel();
			PanelLogin.setLayout(new GridBagLayout());
			PanelLogin.add(Usuario, gridBagConstraints);
			PanelLogin.add(getUsuarioTextField(), gridBagConstraints1);
			PanelLogin.add(PasswordLabel, gridBagConstraints2);
			PanelLogin.add(getPasswordFieldText(), gridBagConstraints3);
			PanelLogin.add(getBotonLogin(), gridBagConstraints4);
		}
		return PanelLogin;
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
	 * This method initializes PasswordFieldText
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getPasswordFieldText() {
		if (PasswordFieldText == null) {
			PasswordFieldText = new JPasswordField();
		}
		return PasswordFieldText;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login thisClass = new Login();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Login() {
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
			jContentPane.add(getPanelLogin(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

}
