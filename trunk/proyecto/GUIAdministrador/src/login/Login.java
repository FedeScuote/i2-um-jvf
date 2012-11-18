package login;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import ventanaPrincipal.VentanaPrincipal;

import comm.ServiciosAdministrador;
import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelLogin = null;

	private JLabel Usuario = null;

	private JTextField UsuarioTextField = null;

	private JLabel PasswordLabel = null;

	private JPasswordField PasswordFieldText = null;

	private JButton BotonLogin = null;

	private static Logger logger = Logger.getLogger(Login.class);  //  @jve:decl-index=0:
	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");
	private static final String host = labels.getString("host");
	private static final String LABEL_USUARIO = labels.getString("LABEL_USUARIO_A");
	private static final String LABEL_PASSWORD = labels.getString("LABEL_PWD_A");
	private static final String LABEL_LOGIN = labels.getString("LABEL_LOGIN_BOTON_A");
	private static final String	LABEL_USUARIO_INVALIDO = labels.getString("LABEL_USUARIO_INVALIDO");
	private static final String	LABEL_ERROR = labels.getString("LABEL_ERROR");
	private static final String LABEL_PASSWORD_INVALIDO = labels.getString("LABEL_PASSWORD_INVALIDO");
	private static final String LABEL_ERROR_DESCONOCIDO = labels.getString("LABEL_ERROR_DESCONOCIDO");
	/**
	 * This method initializes PanelLogin
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelLogin() {
		if (PanelLogin == null) {
			logger.debug("PanelLogin");
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
			PasswordLabel.setText(LABEL_PASSWORD);
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
			Usuario.setText(LABEL_USUARIO);
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
			BotonLogin.setText(LABEL_LOGIN);
			//ANIADO MI ACTIONLISTENER
			BotonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logearse();
				}
			});
		}
		return BotonLogin;
	}
	//METODO DONDE INTENTO LOGEAR
	private void logearse(){
		String datosUsuario = UsuarioTextField.getText();
		char[] charPassword = PasswordFieldText.getPassword();
		String datosPassword = Login
				.deCharArrayAString(charPassword);
		// son mis parametros ingreados que voy a autentificar
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			UsuarioVO response = stub1.login(datosUsuario,
					datosPassword);
			logger.debug("Se logeo");
			VentanaPrincipal l = new VentanaPrincipal(response);
			this.dispose();
			l.setVisible(true);
		} catch (Exception error) {
			// si no se encuentra el usuario la excepcion es
			// NoSeEncuentraUsuarioExcption
			if (error instanceof NoSeEncuentraUsuarioException) {
				JOptionPane.showMessageDialog(new JFrame(),LABEL_USUARIO_INVALIDO, LABEL_ERROR, JOptionPane.ERROR_MESSAGE);

			} else {
				if (error instanceof ContrasenaInvalidaException) {
					JOptionPane.showMessageDialog(new JFrame(),
							LABEL_PASSWORD_INVALIDO,LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					logger.error("error de conexion");
					error.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(),
							LABEL_ERROR_DESCONOCIDO, LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
					this.dispose();
				}

			}
		}
	}
//	METODO QUE RECIBE UN ARRAY DE CHAR Y DEVUELVE UN STRING
	public static String deCharArrayAString(char[] array) {
		String aux = "";
		for (int i = 0; i < array.length; i++) {
			aux = aux + array[i];
		}
		return aux;
	}


	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		initialize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
