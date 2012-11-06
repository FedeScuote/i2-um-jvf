package ejemplo;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

public class HolaMundo extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel LabelHolaMundo = null;

	private JButton BotonCerrar = null;

	private ResourceBundle labels = ResourceBundle.getBundle("ejemplo.ejemploProperties");

	private static Logger logger = Logger.getLogger(HolaMundo.class);  //  @jve:decl-index=0:

	private JLabel LabelMono = null;

	/**
	 * This method initializes BotonCerrar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonCerrar() {
		if (BotonCerrar == null) {
			logger.debug("creo Boton");
			BotonCerrar = new JButton();
			BotonCerrar.setText("CERRAR");
			BotonCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					logger.debug("Click cerrar");
					dispose();
				}
			});
		}
		return BotonCerrar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				HolaMundo thisClass = new HolaMundo();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setExtendedState(JFrame.MAXIMIZED_BOTH);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public HolaMundo() {
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
			LabelMono = new JLabel();
			logger.debug("subo Imagen Hola Mono");
			LabelMono.setIcon(new ImageIcon(labels.getString("ruta_imagen")+"/ejemplo/hola_mono.jpg"));
			LabelHolaMundo = new JLabel();
			LabelHolaMundo.setText(labels.getString("hola_mundo"));
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(LabelHolaMundo, BorderLayout.CENTER);
			jContentPane.add(getBotonCerrar(), BorderLayout.SOUTH);
			jContentPane.add(LabelMono, BorderLayout.EAST);
		}
		return jContentPane;
	}

}
