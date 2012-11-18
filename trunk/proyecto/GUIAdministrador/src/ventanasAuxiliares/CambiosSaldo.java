package ventanasAuxiliares;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

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

	/**
	 * This is the default constructor
	 */
	public CambiosSaldo() {
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
			gridBagConstraints8.gridy = 8;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 7;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 6;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 5;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
			jLabelMonto = new JLabel();
			jLabelMonto.setText("JLabel");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 2;
			jLabelPassword = new JLabel();
			jLabelPassword.setText("JLabel");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabelNick = new JLabel();
			jLabelNick.setText("JLabel");
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
		}
		return jButtonAniadir;
	}

	/**
	 * This method initializes jButtonCobrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCobrar() {
		if (jButtonCobrar == null) {
			jButtonCobrar = new JButton();
		}
		return jButtonCobrar;
	}

	/**
	 * This method initializes jButtonCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCancelar() {
		if (jButtonCancelar == null) {
			jButtonCancelar = new JButton();
		}
		return jButtonCancelar;
	}

}
