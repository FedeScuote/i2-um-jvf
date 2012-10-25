package login;


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
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import util.ImagePanel;
import ventanaJuego.BatallaNavalVentana;
import ventanaPrincipal.VentanaPrincipal;
import comm.ServiciosBatallaNaval;
import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

public class LoginVentana extends JFrame {
	private static final String host = null;

	private static final long serialVersionUID = 1L;

	private ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private JPanel jContentPane = null;

	private ImagePanel panelLogin = null;

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
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);// mi
																				// frame
																				// arranca
																				// maximizada
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle(labels.getString("nombre_empresa"));
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
//			imagenDeFondo.setIcon(new ImageIcon(getClass().getResource(
//					labels.getString("URL_FOTOLOGIN"+".jpg"))));
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
			labelPassword.setText(labels.getString("LABEL_PASSWORD"));//busco en mis properties
			labelUsuario = new JLabel();
			labelUsuario.setText(labels.getString("LABEL_USERNAME"));//busco en mis properties el texto
			panelLogin = new ImagePanel(new ImageIcon("src/LasVegas-Casino.jpg").getImage());
			panelLogin.setLayout(new GridBagLayout());
			panelLogin.setPreferredSize(new Dimension(10, 10));
			panelLogin.setSize(new Dimension(10, 10));
			panelLogin.setVisible(true);
			panelLogin.add(labelPassword, gridBagConstraints1);
			panelLogin.add(getLogin(this), gridBagConstraints11);
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
	private JButton getLogin(final LoginVentana pantalla) {
		if (Login == null) {
			Login = new JButton();
			Login.setText(labels.getString("LABEL_BOTON_LOGIN"));
			Login.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// RECIBO LOS DATOS Y LOS MANDO AL BUS
					String datosUsuario = usuario.getText();
					char[] charPassword = password.getPassword();
					String datosPassword = LoginVentana
							.deCharArrayAString(charPassword);
					// son mis parametros ingreados que voy a autentificar
					try { // try y catch para verificar si esta el usuario o
						// no
						Registry registry = LocateRegistry.getRegistry(host);
						ServiciosUsuario stub1 = (ServiciosUsuario) registry
								.lookup("Hello");
						UsuarioVO response = stub1.login(datosUsuario,
								datosPassword);
						JOptionPane.showMessageDialog(new JFrame(),
						labels.getString("LABEL_BIENVENIDO")+" "+response.getNombreB());
						pantalla.partidaEnCurso(response);

					} catch (Exception error) {
						// si no se encuentra el usuario la excepcion es
						// NoSeEncuentraUsuarioExcption
						if (error instanceof NoSeEncuentraUsuarioException) {
							JOptionPane.showMessageDialog(new JFrame(),labels.getString("LABEL_USUARIO_INVALIDO"), labels.getString("LABEL_ERROR"), JOptionPane.ERROR_MESSAGE);

						} else {
							if (error instanceof ContrasenaInvalidaException) {
								JOptionPane.showMessageDialog(new JFrame(),
										labels.getString("LABEL_PASSWORD_INVALIDO"), labels.getString("LABEL_ERROR"), JOptionPane.ERROR_MESSAGE);
							} else {

								error.printStackTrace();
								JOptionPane.showMessageDialog(new JFrame(),labels.getString("LABEL_ERROR_DESCONOCIDO"), labels.getString("LABEL_ERROR"), JOptionPane.ERROR_MESSAGE);
								pantalla.dispose();
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
	//METODO QUE RECIBE UN ARRAY DE CHAR Y DEVUELVE UN STRING
	public static String deCharArrayAString(char[] array) {
		String aux = "";
		for (int i = 0; i < array.length; i++) {
			aux = aux + array[i];
		}
		return aux;
	}
	//METODO QUE PREGUNTA SI HAY UNA PARTIDA EN CURSO
	private void partidaEnCurso(UsuarioVO usuario){
		try{
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry.lookup("BatallaNavalServices");
			if(stub.hayPartidaEnCurso(usuario)){
				JOptionPane.showMessageDialog(new JFrame(),labels.getString("LABEL_PARTIDA_EN_CURSO"), labels.getString("nombre_empresa"), JOptionPane.INFORMATION_MESSAGE);
				BatallaNavalVentana l = new BatallaNavalVentana(usuario);
				this.setVisible(false);
				l.setVisible(true);
				this.dispose();
			}else{
				this.cambiarVentana(usuario);
			}
		}catch(Exception e){

		}
	}

	private void cambiarVentana(UsuarioVO usuario){
		VentanaPrincipal l = new VentanaPrincipal(usuario);
		l.setVisible(true);
		this.dispose();
	}

	public static void main(String[] args) {
		LoginVentana l = new LoginVentana();
		l.setVisible(true);
	}

} // @jve:decl-index=0:visual-constraint="67,38"
