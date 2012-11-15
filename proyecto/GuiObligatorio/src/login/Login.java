package login;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RepaintManager;

import org.apache.log4j.Logger;

import comm.ServiciosBatallaNaval;
import comm.ServiciosUsuario;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;

import util.CustomGlassPane;
import util.ImagePanel;
import util.AnimatedPanel;
import util.TransparentPanel;
import ventanaJuego.BatallaNavalVentana;
import ventanaPrincipal.VentanaPrincipal;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String host = null;
	private JPanel jContentPane = null;
	private ImagePanel PanelLogin = null;
	private TransparentPanel PanelNorte = null;
	private TransparentPanel PanelSur = null;
	private TransparentPanel PanelEste = null;
	private TransparentPanel PanelOeste = null;
	private AnimatedPanel PanelCentro = null;
	private JLabel labelUsuario = null;
	private JLabel labelPassword = null;
	private JButton botonLogin = null;
	private JTextField usuario = null;
	private JPasswordField password = null;
	private static Logger logger = Logger.getLogger(LoginVentana.class);
	private ResourceBundle labels = ResourceBundle.getBundle("Gui");  //  @jve:decl-index=0:
	/**
	 * This method initializes PanelLogin
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelLogin() {
		if (PanelLogin == null) {
			PanelLogin = new ImagePanel(new ImageIcon("src/fichas_de_un_casino-1920x1200.jpg").getImage());
			PanelLogin.setLayout(new BorderLayout());
			PanelLogin.add(getPanelNorte(), BorderLayout.NORTH);
			PanelLogin.add(getPanelSur(), BorderLayout.SOUTH);
			PanelLogin.add(getPanelEste(), BorderLayout.EAST);
			PanelLogin.add(getPanelOeste(), BorderLayout.WEST);
			PanelLogin.add(getPanelCentro(), BorderLayout.CENTER);
		}
		return PanelLogin;
	}

	/**
	 * This method initializes PanelNorte
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelNorte() {
		if (PanelNorte == null) {
			PanelNorte = new TransparentPanel();
			PanelNorte.setLayout(new GridBagLayout());
			PanelNorte.setPreferredSize(new Dimension(0, this.getHeight()/3));

		}
		return PanelNorte;
	}

	/**
	 * This method initializes PanelSur
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelSur() {
		if (PanelSur == null) {
			PanelSur = new TransparentPanel();
			PanelSur.setLayout(new GridBagLayout());
			PanelSur.setPreferredSize(new Dimension(0, this.getHeight()/3));
		}
		return PanelSur;
	}

	/**
	 * This method initializes PanelEste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelEste() {
		if (PanelEste == null) {
			PanelEste = new TransparentPanel();
			PanelEste.setLayout(new GridBagLayout());
			PanelEste.setPreferredSize(new Dimension(this.getWidth()/5, 0));

		}
		return PanelEste;
	}

	/**
	 * This method initializes PanelOeste
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelOeste() {
		if (PanelOeste == null) {
			PanelOeste = new TransparentPanel();
			PanelOeste.setLayout(new GridBagLayout());
			PanelOeste.setPreferredSize(new Dimension(this.getWidth()/5, 0));
		}
		return PanelOeste;
	}

	/**
	 * This method initializes PanelCentro
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCentro() {
		if (PanelCentro == null) {
			PanelCentro = new AnimatedPanel();
			PanelCentro.setOpaque(false);
			PanelCentro.setLayout(new GridBagLayout());
			GridBagConstraints gridBagConstraints12 =  new GridBagConstraints() ;
			gridBagConstraints12.gridx = 9;
			gridBagConstraints12.gridy = 0;
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
			labelPassword = new JLabel();
			labelPassword.setForeground(Color.white);
			labelPassword.setText(labels.getString("LABEL_PASSWORD"));//busco en mis properties
			labelUsuario = new JLabel();
			labelUsuario.setForeground(Color.white);
			labelUsuario.setText(labels.getString("LABEL_USERNAME"));//busco en mis properties el texto
			PanelCentro.add(labelPassword, gridBagConstraints1);
			PanelCentro.add(getLogin(this), gridBagConstraints11);
			PanelCentro.add(getUsuario(), gridBagConstraints21);
			PanelCentro.add(labelUsuario, gridBagConstraints);
			PanelCentro.add(getPassword(), gridBagConstraints10);
		}
		return PanelCentro;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.debug("Empieza el programa");
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
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(1033, 534);
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

	// BOTON LOGIN
	private JButton getLogin(final Login pantalla) {
		if (botonLogin == null) {
			botonLogin = new JButton();
			botonLogin.setText(labels.getString("LABEL_BOTON_LOGIN"));
			botonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// RECIBO LOS DATOS Y LOS MANDO AL BUS
					CustomGlassPane glassPane = new CustomGlassPane();
					pantalla.setGlassPane(glassPane);
					String datosUsuario = usuario.getText();
					char[] charPassword = password.getPassword();
					String datosPassword = Login
							.deCharArrayAString(charPassword);
					// son mis parametros ingreados que voy a autentificar
					try { // try y catch para verificar si esta el usuario o
						// no
						Registry registry = LocateRegistry.getRegistry(host);
						ServiciosUsuario stub1 = (ServiciosUsuario) registry
								.lookup("Hello");
						UsuarioVO response = stub1.login(datosUsuario,
								datosPassword);
						logger.debug("Se logeo");
						JOptionPane.showMessageDialog(glassPane,
								labels.getString("LABEL_BIENVENIDO")+" "+response.getNombreB());
					    partidaEnCurso(response);

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
								logger.error("error de conexion");
								error.printStackTrace();
								JOptionPane.showMessageDialog(new JFrame(),
										labels.getString("LABEL_PASSWORD_INVALIDO"), labels.getString("LABEL_ERROR"), JOptionPane.ERROR_MESSAGE);
								pantalla.dispose();
							}

						}
					}

				}
			});
		}
		return botonLogin;
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
				logger.debug("Partida en curso?");
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
