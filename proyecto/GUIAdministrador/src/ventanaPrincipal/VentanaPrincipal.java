package ventanaPrincipal;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelVentanaPrincipal = null;

	private JButton BotonCrearCuenta = null;

	private JButton BotonAdministrarCuenta = null;

	private JButton BotonCobrar = null;

	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(448, 264);
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
			BotonCrearCuenta.setText("Crear Cuenta");
		}
		return BotonCrearCuenta;
	}

	/**
	 * This method initializes BotonAdministrarCuenta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonAdministrarCuenta() {
		if (BotonAdministrarCuenta == null) {
			BotonAdministrarCuenta = new JButton();
			BotonAdministrarCuenta.setText("Administrar Cuenta");
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
			BotonCobrar.setText("Cobrar");
		}
		return BotonCobrar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
