package ventanasAuxiliares;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import comm.ServiciosAdministrador;
import comm.UsuarioVO;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoSeEncuentraUsuarioException;

import ventanaPrincipal.VentanaPrincipal;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CambiosSaldo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel jPanelCambiarSaldo = null;

	private JLabel jLabelNick = null;

	private JTextField jTextFieldNick = null;

	private JLabel jLabelPassword = null;

	private JPasswordField jPasswordField = null;

	private JLabel jLabelMonto = null;

	private JTextField jTextFieldMonto = null;

	private JButton jButtonAniadir = null;

	private JButton jButtonCobrar = null;

	private JButton jButtonCancelar = null;

	private static Logger logger = Logger.getLogger(VentanaPrincipal.class);

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private static final String NICK = labels.getString("LABEL_NICK_A");

	private static final String PASSWORD = labels.getString("LABEL_PASSWORD_A");

	private static final String MONTO = labels.getString("LABEL-MONTO");

	private static final String CANCELAR = labels
			.getString("LABEL_VOLVER_BOTON");

	private static final int LIMITE = 10;

	private static final String SUMAR = labels.getString("LABEL_BOTONSUMAR");

	private static final String RESTAR = labels.getString("LABEL_BOTONRESTAR");

	private static final String host = labels.getString("host"); // @jve:decl-index=0:

	private static final String REMOTE = labels.getString("ERROR_CONEXION");  //  @jve:decl-index=0:

	private static final String REALIZADO = labels.getString("REALIZADOCONEXITO");  //  @jve:decl-index=0:

	private static final String NOUSUEXC = labels.getString("LABEL_USUARIO_INVALIDO");

	private static final String NUMCORRE = labels.getString("LABEL_INGRESE_MONTO_CORRECTO");

	private static final String INSUFICIENTE = labels.getString("LABEL_MONTO_INSUFICIENTE");

	private static final String ERRORDESCONOCIDO = labels.getString("LABEL_ERROR_DESCONOCIDO");
	private UsuarioVO usuario=null;
	/**
	 * This is the default constructor
	 */
	public CambiosSaldo(UsuarioVO usu) {
		super();
		initialize();
		usuario = usu;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(743, 403);
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
			jContentPane.add(getJPanelCambiarSaldo(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelCambiarSaldo
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCambiarSaldo() {
		if (jPanelCambiarSaldo == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.ipady = 20;
			gridBagConstraints8.ipadx = 50;
			gridBagConstraints8.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints8.gridy = 8;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.ipady = 20;
			gridBagConstraints7.ipadx = 50;
			gridBagConstraints7.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints7.gridy = 7;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.ipadx = 50;
			gridBagConstraints6.ipady = 20;
			gridBagConstraints6.weightx = 8.0;
			gridBagConstraints6.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints6.gridy = 6;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.ipadx = 100;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
			jLabelMonto = new JLabel();
			jLabelMonto.setText(MONTO);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 100;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			jLabelPassword = new JLabel();
			jLabelPassword.setText(PASSWORD);
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
			jPanelCambiarSaldo = new JPanel();
			jPanelCambiarSaldo.setLayout(new GridBagLayout());
			jPanelCambiarSaldo.add(jLabelNick, gridBagConstraints);
			jPanelCambiarSaldo.add(getJTextFieldNick(), gridBagConstraints1);
			jPanelCambiarSaldo.add(jLabelPassword, gridBagConstraints2);
			jPanelCambiarSaldo.add(getJPasswordField(), gridBagConstraints3);
			jPanelCambiarSaldo.add(jLabelMonto, gridBagConstraints4);
			jPanelCambiarSaldo.add(getJTextFieldMonto(), gridBagConstraints5);
			jPanelCambiarSaldo.add(getJButtonAniadir(), gridBagConstraints6);
			jPanelCambiarSaldo.add(getJButtonCobrar(), gridBagConstraints7);
			jPanelCambiarSaldo.add(getJButtonCancelar(), gridBagConstraints8);
		}
		return jPanelCambiarSaldo;
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
	 * This method initializes jPasswordField
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.addKeyListener(new KeyListener() {
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
		return jPasswordField;
	}

	/**
	 * This method initializes jTextFieldMonto
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMonto() {
		if (jTextFieldMonto == null) {
			jTextFieldMonto = new JTextField();
			jTextFieldMonto.addKeyListener(new KeyListener() {
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
		return jTextFieldMonto;
	}

	/**
	 * This method initializes jButtonAniadir
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAniadir() {
		if (jButtonAniadir == null) {
			jButtonAniadir = new JButton();
			jButtonAniadir.setText(SUMAR);
			jButtonAniadir
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							aniadir();
						}
					});
		}
		return jButtonAniadir;
	}

	private void aniadir() {
		try {
			logger.debug("aniadoSaldo");
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			int monto = Integer.parseInt(jTextFieldMonto.getText());
			stub1.acreditarSaldo(jTextFieldNick.getText(), monto);
			JOptionPane.showMessageDialog(new JFrame(), REALIZADO);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(new JFrame(), REMOTE);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSeEncuentraUsuarioException e) {
			JOptionPane.showMessageDialog(new JFrame(), NOUSUEXC);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JFrame(), NUMCORRE);
		}catch (Exception e){
			JOptionPane.showMessageDialog(new JFrame(), ERRORDESCONOCIDO);
		}
	}

	/**
	 * This method initializes jButtonCobrar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCobrar() {
		if (jButtonCobrar == null) {
			jButtonCobrar = new JButton();
			jButtonCobrar.setText(RESTAR);
			jButtonCobrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cobrar();
				}
			});
		}
		return jButtonCobrar;
	}
	private void cobrar(){
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			int monto = Integer.parseInt(jTextFieldMonto.getText());
			String password = deCharArrayAString(jPasswordField.getPassword());
			stub1.cobrarSaldo(jTextFieldNick.getText(),password, monto);
			JOptionPane.showMessageDialog(new JFrame(), REALIZADO);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(new JFrame(), REMOTE);
		}
		 catch (NoSeEncuentraUsuarioException e) {
			JOptionPane.showMessageDialog(new JFrame(), NOUSUEXC);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JFrame(), NUMCORRE);
		}catch (MontoInsuficienteException e){
			JOptionPane.showMessageDialog(new JFrame(), INSUFICIENTE);
		}catch(Exception e ){
			JOptionPane.showMessageDialog(new JFrame(), ERRORDESCONOCIDO);
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
			jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonCancelar;
	}
	public void retornar(){
		this.dispose();
		VentanaPrincipal l = new VentanaPrincipal(usuario);
		l.setVisible(true);
	}

//	METODO QUE RECIBE UN ARRAY DE CHAR Y DEVUELVE UN STRING
	public static String deCharArrayAString(char[] array) {
		String aux = "";
		for (int i = 0; i < array.length; i++) {
			aux = aux + array[i];
		}
		return aux;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
