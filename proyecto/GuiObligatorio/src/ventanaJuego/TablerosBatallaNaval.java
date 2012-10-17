package ventanaJuego;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
//clase donde voy a tener mis creartableros de batalla naval
public class TablerosBatallaNaval extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	/**
	 * This is the default constructor
	 */
	public TablerosBatallaNaval() {
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
		}
		return jContentPane;
	}


}
