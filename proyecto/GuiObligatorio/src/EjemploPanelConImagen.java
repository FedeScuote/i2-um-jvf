
	import java.awt.Color;
import java.awt.Dimension;
	import java.awt.Graphics;
import java.awt.GridLayout;
	import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

	import javax.swing.ImageIcon;
import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class EjemploPanelConImagen extends JFrame{
	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = { " ", "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };// primero el vacio// porque no va nada en// esa linea

	private JLabel indicadorTurno = null;

	private JButton[][] arrayBotones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];



	  public static void main(String[] args) {
	    ImagePanel panel = new ImagePanel(new ImageIcon("src/LasVegas-Casino.jpg").getImage());

	    EjemploPanelConImagen frame = new EjemploPanelConImagen();
	    frame.getContentPane().add(panel);
	    frame.setVisible(true);
	    frame.crearTablero(panel, frame.arrayBotones);
	  }

		// metodo al cual le paso el panel donde quiero crear un tablero del tamano
		// indicado en los atributos
		private void crearTablero(JPanel panel, JButton[][] botones) {
			// crear botones y agregarlos al panel
			panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
			for (int i = 1; i < TAMANO_TABLERO; i++) {
				this.crearFila(panel, i, botones);

			}
		}

		// metodo que me crea mi cabezal con mis letras
		private void crearCabezal(JPanel panel) {
			panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));

			for (int i = 0; i < TAMANO_TABLERO; i++) {
				JLabel jlabel = new JLabel();
				panel.add(jlabel);
				jlabel.setText(ALFABETO[i]); // alfabeto menos uno porque
				jlabel.setHorizontalAlignment(SwingConstants.CENTER);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);
			}
		}

		// metodo que le paso numero de fila y me agrega la fila con jlabel
		// correspondiente
		private void crearFila(JPanel panel, Integer numeroFila, JButton[][] botones) {
			for (int j = 0; j < TAMANO_TABLERO; j++) {
				if (j == 0) {
					JLabel jlabel = new JLabel();
					panel.add(jlabel);
					jlabel.setText(numeroFila.toString()); // alfabeto menos uno
					// porque
					jlabel.setHorizontalAlignment(SwingConstants.CENTER);
					jlabel.setVerticalAlignment(SwingConstants.CENTER);
				} else {
					JButton jButton = new JButton();
					jButton.setBackground(Color.blue);
					jButton.addActionListener(new ListenerBoton(numeroFila, j));
					panel.add(jButton);
					botones[numeroFila][j] = jButton;
				}
			}
		}

		// clase de mis actionListener que voy a usar en mis botones
		private class ListenerBoton implements ActionListener {

			private int x;

			private int y;

			public ListenerBoton(int i, int j) {
				// en el constructor se almacena la fila y columna que se presiona
				x = i;
				y = j;
			}

			public void actionPerformed(ActionEvent e) {
				// cuando se presiona un boton se ejecutara este metodo
				System.out.println(((Integer)x).toString()+((Integer)y).toString());;// Manejo menos uno porque EL BUS NO
				// SABE MANEJARSE
			}
		}



}

	class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }




}
