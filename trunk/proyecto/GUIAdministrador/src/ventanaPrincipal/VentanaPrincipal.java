package ventanaPrincipal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import login.Login;

import org.apache.log4j.Logger;

import comm.UsuarioVO;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelVentanaPrincipal = null;

	private JButton BotonCrearCuenta = null;

	private JButton BotonAdministrarCuenta = null;

	private JButton BotonCobrar = null;

	private JButton botonAumentarSaldo = null;

	private JButton botonEstadisticas = null;

	private static Logger logger = Logger.getLogger(VentanaPrincipal.class);
	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");
	private static final String CREAR_CUENTA = labels.getString("LABEL_CREAR_CUENTA_A");
	private static final String LABEL_ADMIN_CUENTA_A = labels.getString("LABEL_ADMIN_CUENTA_A");
	private static final String LABEL_COBRAR = labels.getString("LABEL_COBRAR");
	private static final String LABEL_ANIADIR_SALDO = labels.getString("LABEL_ANIADIR_SALDO");
	private static final String LABEL_ESTADISTICAS = labels.getString("LABEL_ESTADISTICAS");
	private static final String DESCONECTAR = labels.getString("LABEL_DESCONECTAR");

	private JButton jButtonDesconectar = null;
	private UsuarioVO usuario = null;
	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal(UsuarioVO usu) {
		super();
		logger.trace("Constructor Ventana Principal");
		initialize();
		usuario= usu;
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(579, 374);
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
			logger.debug("content Pane");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelVentanaPrincipal(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelVentanaPrincipal
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelVentanaPrincipal() {
		if (PanelVentanaPrincipal == null) {
			logger.debug("PanelVentanaPrincipal");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 5;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints3.gridy = 4;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints21.gridy = 3;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints.gridy = 0;
			PanelVentanaPrincipal = new JPanel();
			PanelVentanaPrincipal.setLayout(new GridBagLayout());
			PanelVentanaPrincipal.add(getBotonCrearCuenta(), gridBagConstraints);
			PanelVentanaPrincipal.add(getBotonAdministrarCuenta(), gridBagConstraints1);
			PanelVentanaPrincipal.add(getBotonCobrar(), gridBagConstraints2);
			PanelVentanaPrincipal.add(getBotonAumentarSaldo(), gridBagConstraints21);
			PanelVentanaPrincipal.add(getBotonEstadisticas(), gridBagConstraints3);
			PanelVentanaPrincipal.add(getJButtonDesconectar(), gridBagConstraints11);
		}
		return PanelVentanaPrincipal;
	}

	/**
	 * This method initializes BotonCrearCuenta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonCrearCuenta() {
		if (BotonCrearCuenta == null) {
			BotonCrearCuenta = new JButton();
			BotonCrearCuenta.setText(CREAR_CUENTA);
			BotonCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarACrearCuenta();
				}
			});
		}
		return BotonCrearCuenta;
	}
	//METODO QUE CAMBIA A VENTANA CREAR CUENTA
	private void cambiarACrearCuenta(){
		CrearCuenta l = new CrearCuenta(usuario);
		l.setVisible(true);
		this.dispose();
	}

	/**
	 * This method initializes BotonAdministrarCuenta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonAdministrarCuenta() {
		if (BotonAdministrarCuenta == null) {
			BotonAdministrarCuenta = new JButton();
			BotonAdministrarCuenta.setText(LABEL_ADMIN_CUENTA_A);
		}
		return BotonAdministrarCuenta;
	}

	/**
	 * This method initializes BotonCobrar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonCobrar() {
		if (BotonCobrar == null) {
			BotonCobrar = new JButton();
			BotonCobrar.setText(LABEL_COBRAR);
		}
		return BotonCobrar;
	}

	/**
	 * This method initializes botonAumentarSaldo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonAumentarSaldo() {
		if (botonAumentarSaldo == null) {
			botonAumentarSaldo = new JButton();
			botonAumentarSaldo.setText(LABEL_ANIADIR_SALDO);
		}
		return botonAumentarSaldo;
	}

	/**
	 * This method initializes botonEstadisticas
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonEstadisticas() {
		if (botonEstadisticas == null) {
			botonEstadisticas = new JButton();
			botonEstadisticas.setText(LABEL_ESTADISTICAS);
		}
		return botonEstadisticas;
	}

	/**
	 * This method initializes jButtonDesconectar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonDesconectar() {
		if (jButtonDesconectar == null) {
			jButtonDesconectar = new JButton();
			jButtonDesconectar.setText(DESCONECTAR);
			jButtonDesconectar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					desconectar();
				}
			});
		}
		return jButtonDesconectar;
	}
	public void desconectar(){
		this.dispose();
		Login l = new Login();
		l.setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
