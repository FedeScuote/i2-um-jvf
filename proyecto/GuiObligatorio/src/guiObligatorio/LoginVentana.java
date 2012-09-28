package guiObligatorio;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

public class LoginVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel panelLogin = null;

	private JLabel labelUsuario = null;

	private JLabel labelPassword = null;

	private JButton Login = null;

	private JTextField usuario = null;

	private JLabel imagenDeFondo = null;

	private JPasswordField password = null;

	/**
	 * This is the default constructor
	 */
	public LoginVentana() {
		super();
		initialize();
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);//mi frame arranca maximizada
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(819, 573);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
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
			jContentPane.add(getPanelLogin(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelLogin
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelLogin() {
		if (panelLogin == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.NONE;
			gridBagConstraints10.gridy = 4;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.ipadx = 100;
			gridBagConstraints10.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints10.gridx = 9;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 9;
			gridBagConstraints2.ipadx = 0;
			gridBagConstraints2.ipady = 0;
			gridBagConstraints2.gridheight = 7;
			gridBagConstraints2.insets = new Insets(0, 0, 1, 0);
			gridBagConstraints2.gridy = 0;
			imagenDeFondo = new JLabel();
			imagenDeFondo.setText("");
			imagenDeFondo.setIcon(new ImageIcon(getClass().getResource(
					"/guiObligatorio/LasVegas-Casino.jpg")));
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 9;
			gridBagConstraints.gridy = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.NONE;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints21.ipadx = 100;
			gridBagConstraints21.gridx = 9;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 9;
			gridBagConstraints11.ipadx = 100;
			gridBagConstraints11.ipady = 15;
			gridBagConstraints11.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints11.gridwidth = 1;
			gridBagConstraints11.fill = GridBagConstraints.NONE;
			gridBagConstraints11.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints11.gridy = 5;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 9;
			gridBagConstraints1.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints1.gridy = 3;
			labelPassword = new JLabel();
			labelPassword.setText("Password");
			labelUsuario = new JLabel();
			labelUsuario.setText("Usuario");
			panelLogin = new JPanel();
			panelLogin.setLayout(new GridBagLayout());
			panelLogin.setPreferredSize(new Dimension(10, 10));
			panelLogin.setSize(new Dimension(10, 10));
			panelLogin.setVisible(true);
			panelLogin.add(labelPassword, gridBagConstraints1);
			panelLogin.add(getLogin(), gridBagConstraints11);
			panelLogin.add(getUsuario(), gridBagConstraints21);
			panelLogin.add(labelUsuario, gridBagConstraints);
			panelLogin.add(getPassword(), gridBagConstraints10);
			panelLogin.add(imagenDeFondo, gridBagConstraints2);
		}
		return panelLogin;
	}

	/**
	 * This method initializes Login
	 *
	 * @return javax.swing.JButton
	 */
	// BOTON LOGIN
	private JButton getLogin() {
		if (Login == null) {
			Login = new JButton();
			Login.setName("");
			Login.setText("LOGIN");
			Login.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// RECIBO LOS DATOS Y LOS MANDO AL BUS
					String datosUsuario = usuario.getText();
					char[] charPassword = password.getPassword();
					String datosPassword = "";
					for(int i =0; i<charPassword.length; i++){
						datosPassword = datosPassword + charPassword[i];
					}

					String host = null;

					try { // try y catch para verificar si esta el usuario o
							// no
						Registry registry = LocateRegistry.getRegistry(host);
						ServiciosUsuario stub = (ServiciosUsuario) registry
								.lookup("Hello");
						UsuarioVO response = stub.login(datosUsuario,
								datosPassword);
						System.out.println(response);
					} catch (Exception remoteExceptionrmi) {
						//si no se encuentra el usuario la excepcion es NoSeEncuentraUsuarioExcption
						if (remoteExceptionrmi instanceof NoSeEncuentraUsuarioException) {
							JOptionPane.showMessageDialog(new JFrame(), "usuario invalido");

						}else{
						if(remoteExceptionrmi instanceof ContrasenaInvalidaException){
							JOptionPane.showMessageDialog(new JFrame(), "password invalido");
						}
						else {

							System.err.println("Client exception: "
									+ remoteExceptionrmi.toString());
							remoteExceptionrmi.printStackTrace();
						}

						}
											}

				}
			});
		}
		return Login;
	}

	/**
	 * This method initializes usuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getUsuario() {
		if (usuario == null) {
			usuario = new JTextField();
		}
		return usuario;
	}

	/**
	 * This method initializes password
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
		}
		return password;
	}

	public static void main(String[] args) {
		LoginVentana l = new LoginVentana();
		l.setVisible(true);
	}
} // @jve:decl-index=0:visual-constraint="67,38"
