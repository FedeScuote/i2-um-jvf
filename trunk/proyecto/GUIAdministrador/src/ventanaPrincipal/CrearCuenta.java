package ventanaPrincipal;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JPasswordField;

import org.apache.log4j.Logger;

import comm.ServiciosAdministrador;
import comm.UsuarioVO;
import commExceptions.UsuarioDuplicadoException;

import javax.swing.JButton;
import java.awt.Insets;

public class CrearCuenta extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelCrearCuenta = null;

	private JLabel jLabelNick = null;

	private JTextField jTextFieldNick = null;

	private JLabel jLabelNombre = null;

	private JTextField jTextFieldNombre = null;

	private JLabel jLabelApellido = null;

	private JTextField jTextFieldApellido = null;

	private JLabel jLabelPassword = null;

	private JLabel jLabelConfirmacion = null;

	private JLabel jLabelPais = null;

	private JTextField jTextFieldPais = null;

	private JPasswordField jPasswordFieldPWD = null;

	private JPasswordField jPasswordFieldConfirmacion = null;

	private static Logger logger = Logger.getLogger(CrearCuenta.class);

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui"); // @jve:decl-index=0:

	private static final String NICK = labels.getString("LABEL_NICK_A"); // @jve:decl-index=0:

	private static final String NOMBRE = labels.getString("LABEL_NOMBRE_A");

	private static final String APELLIDO = labels.getString("LABEL_APELLIDO_A");

	private static final String PASSWORD = labels.getString("LABEL_PASSWORD_A");

	private static final String CONFIRMACION = labels
			.getString("LABEL_CONFIRMACION_A");

	private static final String PAIS = labels.getString("LABEL_PAIS_A");

	private static final String BOTONCREAR = labels
			.getString("LABEL_CREAR_CUENTA_BOTON");

	private static final String BOTONCREARADMIN = labels
			.getString("LABEL_CREAR_ADMIN_BOTON");

	private static final String BOTONSALIR = labels
			.getString("LABEL_VOLVER_BOTON");

	private static final String host = labels.getString("host"); // @jve:decl-index=0:

	private JButton jButtonCrear = null;

	private JButton jButtonSalir = null;

	private JButton jButtonCrearCuentaAdmin = null;

	private UsuarioVO usuario=null;

	/**
	 * This is the default constructor
	 */
	public CrearCuenta(UsuarioVO usu) {
		super();
		logger.trace("Constructor CrearCuenta");
		initialize();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usuario = usu;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(530, 329);
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
			jContentPane.add(getPanelCrearCuenta(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelCrearCuenta
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelCrearCuenta() {
		if (PanelCrearCuenta == null) {
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 1;
			gridBagConstraints51.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints51.ipadx = 60;
			gridBagConstraints51.ipady = 20;
			gridBagConstraints51.gridy = 13;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 1;
			gridBagConstraints31.insets = new Insets(20, 0, 0, 0);
			gridBagConstraints31.ipadx = 60;
			gridBagConstraints31.ipady = 20;
			gridBagConstraints31.gridy = 14;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.ipadx = 60;
			gridBagConstraints21.insets = new Insets(35, 0, 0, 0);
			gridBagConstraints21.ipady = 20;
			gridBagConstraints21.gridy = 12;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints9.gridy = 9;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.ipadx = 100;
			gridBagConstraints9.gridwidth = 2;
			gridBagConstraints9.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 7;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.ipadx = 100;
			gridBagConstraints7.gridwidth = 2;
			gridBagConstraints7.gridx = 0;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 11;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.ipadx = 100;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridwidth = 2;
			gridBagConstraints10.gridy = 10;
			jLabelPais = new JLabel();
			jLabelPais.setText(PAIS);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridwidth = 2;
			gridBagConstraints8.gridy = 8;
			jLabelConfirmacion = new JLabel();
			jLabelConfirmacion.setText(CONFIRMACION);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridwidth = 2;
			gridBagConstraints6.gridy = 6;
			jLabelPassword = new JLabel();
			jLabelPassword.setText(PASSWORD);
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.ipadx = 100;
			gridBagConstraints5.gridwidth = 2;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.gridy = 4;
			jLabelApellido = new JLabel();
			jLabelApellido.setText(APELLIDO);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 100;
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridwidth = 2;
			gridBagConstraints2.gridy = 2;
			jLabelNombre = new JLabel();
			jLabelNombre.setText(NOMBRE);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.gridy = 0;
			jLabelNick = new JLabel();
			jLabelNick.setText(NICK);
			PanelCrearCuenta = new JPanel();
			PanelCrearCuenta.setLayout(new GridBagLayout());
			PanelCrearCuenta.add(jLabelNick, gridBagConstraints);
			PanelCrearCuenta.add(getJTextFieldNick(), gridBagConstraints1);
			PanelCrearCuenta.add(jLabelNombre, gridBagConstraints2);
			PanelCrearCuenta.add(getJTextFieldNombre(), gridBagConstraints3);
			PanelCrearCuenta.add(jLabelApellido, gridBagConstraints4);
			PanelCrearCuenta.add(getJTextFieldApellido(), gridBagConstraints5);
			PanelCrearCuenta.add(jLabelPassword, gridBagConstraints6);
			PanelCrearCuenta.add(jLabelConfirmacion, gridBagConstraints8);
			PanelCrearCuenta.add(jLabelPais, gridBagConstraints10);
			PanelCrearCuenta.add(getJTextFieldPais(), gridBagConstraints11);
			PanelCrearCuenta.add(getJPasswordFieldPWD(), gridBagConstraints7);
			PanelCrearCuenta.add(getJPasswordFieldConfirmacion(),
					gridBagConstraints9);
			PanelCrearCuenta.add(getJButtonCrear(), gridBagConstraints21);
			PanelCrearCuenta.add(getJButtonSalir(), gridBagConstraints31);
			PanelCrearCuenta.add(getJButtonCrearCuentaAdmin(),
					gridBagConstraints51);
		}
		return PanelCrearCuenta;
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
	 * This method initializes jTextFieldNombre
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNombre() {
		if (jTextFieldNombre == null) {
			jTextFieldNombre = new JTextField();
		}
		return jTextFieldNombre;
	}

	/**
	 * This method initializes jTextFieldApellido
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldApellido() {
		if (jTextFieldApellido == null) {
			jTextFieldApellido = new JTextField();
		}
		return jTextFieldApellido;
	}

	/**
	 * This method initializes jTextFieldPais
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPais() {
		if (jTextFieldPais == null) {
			jTextFieldPais = new JTextField();
		}
		return jTextFieldPais;
	}

	/**
	 * This method initializes jPasswordFieldPWD
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldPWD() {
		if (jPasswordFieldPWD == null) {
			jPasswordFieldPWD = new JPasswordField();
		}
		return jPasswordFieldPWD;
	}

	/**
	 * This method initializes jPasswordFieldConfirmacion
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldConfirmacion() {
		if (jPasswordFieldConfirmacion == null) {
			jPasswordFieldConfirmacion = new JPasswordField();
		}
		return jPasswordFieldConfirmacion;
	}

	/**
	 * This method initializes jButtonCrear
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCrear() {
		if (jButtonCrear == null) {
			jButtonCrear = new JButton();
			jButtonCrear.setText(BOTONCREAR);
			jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					crearCuenta();
				}
			});
		}
		return jButtonCrear;
	}

	/**
	 * This method initializes jButtonSalir
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSalir() {
		if (jButtonSalir == null) {
			jButtonSalir = new JButton();
			jButtonSalir.setText(BOTONSALIR);
			jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					volver();
				}
			});
		}
		return jButtonSalir;
	}

	public void volver() {
		this.dispose();
		VentanaPrincipal l = new VentanaPrincipal(usuario);
		l.setVisible(true);
	}

	private void crearCuenta() {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			stub1.agregarUsuario(this.jTextFieldNick.getText(), this
					.deCharArrayAString(jPasswordFieldPWD.getPassword()), 1,
					jTextFieldNombre.getText(), jTextFieldApellido.getText(),
					jTextFieldPais.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioDuplicadoException e) {
			JOptionPane.showMessageDialog(new JFrame(), "h");
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes jButtonCrearCuentaAdmin
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCrearCuentaAdmin() {
		if (jButtonCrearCuentaAdmin == null) {
			jButtonCrearCuentaAdmin = new JButton();
			jButtonCrearCuentaAdmin.setText(BOTONCREARADMIN);
			jButtonCrearCuentaAdmin
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							crearAdmin();
						}
					});
		}
		return jButtonCrearCuentaAdmin;
	}

	private void crearAdmin(){
		Registry registry;
		if(usuario.getNivelPrivilegio()==2){
		try {
			registry = LocateRegistry.getRegistry(host);
		ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
				.lookup("AdministrationServices");
		stub1.agregarUsuario(this.jTextFieldNick.getText(), this.deCharArrayAString(jPasswordFieldPWD.getPassword()), 2, jTextFieldNombre.getText(), jTextFieldApellido.getText(), jTextFieldPais.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioDuplicadoException e) {
			JOptionPane.showMessageDialog(new JFrame(), "h");
			e.printStackTrace();
		}
		}else{
			JOptionPane.showMessageDialog(new JFrame(), "h");
		}
	}

	// METODO QUE RECIBE UN ARRAY DE CHAR Y DEVUELVE UN STRING
	public static String deCharArrayAString(char[] array) {
		String aux = "";
		for (int i = 0; i < array.length; i++) {
			aux = aux + array[i];
		}
		return aux;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
