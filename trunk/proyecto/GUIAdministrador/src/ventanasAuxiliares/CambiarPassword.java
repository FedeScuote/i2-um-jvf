package ventanasAuxiliares;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ventanaPrincipal.VentanaPrincipal;

import comm.ServiciosAdministrador;
import comm.UsuarioVO;
import commExceptions.ContrasenaInvalidaException;
import commExceptions.NoSeEncuentraUsuarioException;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	private JButton jButtonOK = null;

	private JButton jButtonCancelar = null;

	private UsuarioVO usuario = null;

	private static final int LIMITE = 11;

	private static Logger logger = Logger.getLogger(VentanaPrincipal.class);

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private static final String NICK = labels.getString("LABEL_NICK_A"); // @jve:decl-index=0:

	private static final String PASSWORD = labels.getString("LABEL_PASSWORD_A"); // @jve:decl-index=0:

	private static final String CONFIRMAR = labels
			.getString("LABEL_CONFIRMACION_A");

	private static final String PWDNUEVO = labels.getString("LABEL_PWDNUEVO_A");

	private static final String BOTONCREAR = labels
			.getString("LABEL_ADMIN_CUENTA_A");

	private static final String CANCELAR = labels
			.getString("LABEL_VOLVER_BOTON");

	private static final String host = labels.getString("host");

	private static final String REALIZADO = labels
			.getString("REALIZADOCONEXITO");

	private static final String REMOTE = labels.getString("ERROR_CONEXION");

	private static final String NOUSUEXC = labels
			.getString("LABEL_USUARIO_INVALIDO"); // @jve:decl-index=0:

	private static final String PWDINVALIDO = labels
			.getString("LABEL_PASSWORD_INVALIDO");

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
		this.setSize(497, 316);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
			jPanelCambiarPassword.add(getJTextFieldPasswordViejo(),
					gridBagConstraints3);
			jPanelCambiarPassword.add(jLabelNuevoPassword, gridBagConstraints4);
			jPanelCambiarPassword.add(getJTextFieldNuevoPassword(),
					gridBagConstraints5);
			jPanelCambiarPassword.add(getJButtonOK(), gridBagConstraints8);
			jPanelCambiarPassword
					.add(getJButtonCancelar(), gridBagConstraints9);
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
			jTextFieldNick.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
					if (jTextFieldNick.getText().length() == LIMITE) {
						e.consume();
					}
				}
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}
			});
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
			jTextFieldPasswordViejo.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
					if (jTextFieldNick.getText().length() == LIMITE) {
						e.consume();
					}
				}
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}
			});
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
			jTextFieldNuevoPassword.addKeyListener(new KeyListener() {
				public void keyTyped(KeyEvent e) {
					if (jTextFieldNick.getText().length() == LIMITE) {
						e.consume();
					}
				}
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
				}
			});
		}
		return jTextFieldNuevoPassword;
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
			jButtonOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarPass();
				}
			});
		}
		return jButtonOK;
	}

	private void cambiarPass() {
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			stub1.cambiarPassword(jTextFieldNick.getText(),
					jTextFieldPasswordViejo.getText(), jTextFieldNuevoPassword
							.getText());
			JOptionPane.showMessageDialog(new JFrame(), REALIZADO);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(new JFrame(), REMOTE);
		} catch (NoSeEncuentraUsuarioException e) {
			JOptionPane.showMessageDialog(new JFrame(), NOUSUEXC);
		} catch (ContrasenaInvalidaException e) {
			JOptionPane.showMessageDialog(new JFrame(), PWDINVALIDO);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), PWDINVALIDO);
		}
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

} // @jve:decl-index=0:visual-constraint="10,10"
