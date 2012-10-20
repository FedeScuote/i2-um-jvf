package ventanaJuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import comm.CeldaVO;
import comm.ServiciosBatallaNaval;
import comm.TableroVO;
import comm.UsuarioVO;

public class BatallaNavalVentana extends JFrame{

	private static final long serialVersionUID = 1L;

	private static final int pause = 200;

	private static final String host = null;

	private UsuarioVO usuario;

	private JPanel jContentPane = null;

	private JPanel PanelJugador = null;

	private JPanel PanelContrincante = null;

	private boolean esMiTurno = false;

	private Timer temporizador;

	private JButton[][] botonesJugador;

	// botones que voy a
	// utilizar para mi
	// tablero
	private JButton[][] botonesContrincante = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];

	private final static int TAMANO_TABLERO = 10 + 1;// (casillas)+(labels)

	private final static String[] ALFABETO = {" ", "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z" };// primero el vacio

	// porque no va nada en
	// esa linea
	private JLabel indicadorTurno = null;

	/**
	 * This is the default constructor
	 */
	public BatallaNavalVentana() {
		super();
		initialize();
		this.crearCabezal(PanelJugador);
		this.crearTablero(PanelJugador, botonesJugador); // creo mi tablero
		// en mi panel
		// jugador
		this.crearCabezal(PanelContrincante);
		this.crearTablero(PanelContrincante, botonesContrincante);// creo mi
		// tablero
		// en mi
		// panel
		// contrincante
		refrescarTableroJugador();
		refrescarTableroOponente();
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);// mi
		// frame
		// arranca
		// maximizada
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				esMiTurno = preguntarTurno(usuario);
				if (!esMiTurno) {
					temporizador.restart();
					indicadorTurno.setText("NO ES TU TURNO");
					indicadorTurno.setForeground(Color.RED);
				} else {
					indicadorTurno.setText("ES TU TURNO");
					indicadorTurno.setForeground(Color.GREEN);
				}
			}
		};
		 temporizador = new Timer(pause, taskPerformer);
		 temporizador.setInitialDelay(pause);
		 temporizador.addActionListener(taskPerformer);
		 temporizador.start();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
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
			BorderLayout borderLayout = new BorderLayout();
			indicadorTurno = new JLabel();
			indicadorTurno.setText("ES TU TURNO");
			indicadorTurno.setForeground(Color.GREEN);
			indicadorTurno.setHorizontalAlignment(SwingConstants.CENTER);
			indicadorTurno.setHorizontalTextPosition(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getPanelJugador(), BorderLayout.WEST);
			jContentPane.add(getPanelContrincante(), BorderLayout.EAST);
			jContentPane.add(indicadorTurno, BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelJugador
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelJugador() {
		if (PanelJugador == null) {
			PanelJugador = new JPanel();
			PanelJugador.setLayout(new GridBagLayout());
			PanelJugador.setEnabled(true);
		}
		return PanelJugador;
	}

	/**
	 * This method initializes PanelContrincante
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelContrincante() {
		if (PanelContrincante == null) {
			PanelContrincante = new JPanel();
			PanelContrincante.setLayout(new GridBagLayout());
		}
		return PanelContrincante;
	}

	// metodo al cual le paso el panel donde quiero crear un tablero del tamano
	// indicado en los atributos
	private void crearTablero(JPanel panel, JButton[][] botones) {
		// crear botones y agregarlos al panel
		panel.setLayout(new GridLayout(TAMANO_TABLERO, TAMANO_TABLERO));
		botones = new JButton[TAMANO_TABLERO][TAMANO_TABLERO];
		for (int i = 1; i < TAMANO_TABLERO; i++) {
				this.crearFila(panel, i, botones);

		}

	}
	//metodo que me crea mi cabezal con mis letras
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
	//metodo que le paso numero de fila y me agrega la fila con jlabel correspondiente
	private void crearFila (JPanel panel, Integer numeroFila, JButton[][] botones){
		for (int j = 0; j<TAMANO_TABLERO; j++){
			if(j==0){
				JLabel jlabel = new JLabel();
				panel.add(jlabel);
				jlabel.setText(numeroFila.toString()); // alfabeto menos uno porque
				jlabel.setHorizontalAlignment(SwingConstants.CENTER);
				jlabel.setVerticalAlignment(SwingConstants.CENTER);
			}else{
				JButton jButton = new JButton();
				jButton.addActionListener(new ListenerBoton(numeroFila, j));
				panel.add(jButton);
				botones[numeroFila][j] = jButton;
			}
		}
	}
	//clase de mis actionListener que voy a usar en mis botones
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
			clickBoton(x, y);
		}
	}

	private void clickBoton(int fila, int columna) {
		if (esMiTurno) {
			try { // try y catch para verificar si esta el usuario o
				// no
				Registry registry = LocateRegistry.getRegistry(host);
				ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
						.lookup("BatallaNavalServices");
				stub.disparar(this.usuario, fila, columna);
				JOptionPane
						.showMessageDialog(new JFrame(), "DISPARO REALIZADO");
				if(stub.hundi(this.usuario)){
					JOptionPane.showMessageDialog(new JFrame(),"HAS HUNDIDO UN BARCO", "ENHORABUENA", JOptionPane.INFORMATION_MESSAGE);
				}
				refrescarTableroJugador();
				refrescarTableroOponente();
				temporizador.start();
			} catch (Exception e) {

			}
		}
	}


	// METODO PARA PREGUNTAR TURNO
	public boolean preguntarTurno(UsuarioVO usuario) {
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			refrescarTableroJugador();
			refrescarTableroOponente();
			return stub.esMiTurno(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void refrescarTableroJugador(){
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesJugador,stub.refrescarTablero(usuario));
		} catch (Exception e) {
			if(e instanceof RemoteException){
				JOptionPane.showMessageDialog(new JFrame(),"ERROR DE CONEXION", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(),"ERROR DESCONOCIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			}
		}
	}
	public void refrescarTableroOponente(){
		try { // try y catch para verificar si esta el usuario o
			// no
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			cambiarBotones(botonesJugador,stub.refrescarTableroOponente(usuario));
		} catch (Exception e) {
			if(e instanceof RemoteException){
				JOptionPane.showMessageDialog(new JFrame(),"ERROR DE CONEXION", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else{
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(),"ERROR DESCONOCIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			}
		}
	}
	public void cambiarBotones(JButton[][] botones, TableroVO tablero){
		CeldaVO[][] tabla = tablero.getTabla();
		for (int i=0 ; i<tabla.length;i++){
			for (int j = 0; j < tabla.length; j++) {
				if(tabla[i][j].getEstado().equals("AGUA")){
					botones[i][j].setBackground(Color.BLUE);
				}else if (tabla[i][j].getEstado().equals("OCUPADO")) {
					botones[i][j].setBackground(Color.BLACK);
				}else if(tabla[i][j].getEstado().equals("TOCADO")){
					botones[i][j].setBackground(Color.GREEN);
				}else{
					botones[i][j].setBackground(Color.RED); // esto quiere decir
					//que si no era ninguno de los otros erre el tiro
				}

			}
		}
	}


}// @jve:decl-index=0:visual-constraint="10,10"
