package pantallaElegirDesafioOJuego;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import util.EstadoElegirDesafio;
import ventanaJuego.BatallaNavalVentana;
import ventanaJuego.ColocarBarcos;
import ventanaPrincipal.VentanaPrincipal;

import comm.DesafioBatallaNavalVO;
import comm.DesafioVO;
import comm.RankingVO;
import comm.ServiciosBatallaNaval;
import comm.ServiciosDesafio;
import comm.ServiciosRanking;
import comm.UsuarioVO;
import commExceptions.MontoInsuficienteException;
import commExceptions.NoHayDesafiosDisponiblesException;
import commExceptions.NoSeEncuentraUsuarioException;

public class SegundaPantallaBatallaN extends SegundaPantalla {

	private int bandera = 1;

	private final static String host = null;



	private ArrayList<DesafioBatallaNavalVO> arrayDesafio = null;

	private Timer temporizador;

	private static Logger logger = Logger.getLogger(SegundaPantallaBatallaN.class);

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");
	private static final String LABEL_ERROR_NO_USU_VP = labels.getString("LABEL-ERROR-NO-USU-VP");
	private static final String LABEL_ERROR = labels.getString("LABEL_ERROR");
	private static final String LABEL_ERROR_NO_DESAF_VP = labels.getString("LABEL-ERROR-NO-DESAF-VP");
	private static final String LABEL_MONTO = labels.getString("LABEL-MONTO");
	private static final String LABEL_TABLA_COLUMNA2 = labels.getString("LABEL_TABLA_COLUMNA2");
	private static final String ERROR_CONEXION = labels.getString("ERROR_CONEXION");
	private static final String LABEL_ERROR_DESCONOCIDO = labels.getString("LABEL_ERROR_DESCONOCIDO");
	private static final String LABEL_INGRESE_MONTO = labels.getString("LABEL-INGRESE-MONTO");
	private static final String LABEL_CREAR_DESAFIO_BOTON = labels.getString("LABEL-CREAR-DESAFIO-BOTON");
	private static final String ERROR_CREAR_DESAFIO = labels.getString("ERROR_CREAR_DESAFIO");
	private static final String LABEL_INGRESE_MONTO_CORRECTO = labels.getString("LABEL_INGRESE_MONTO_CORRECTO");
	private static final String LABEL_MONTO_INSUFICIENTE = labels.getString("LABEL_MONTO_INSUFICIENTE");


	private final static int pause = (int)Integer.parseInt(labels.getString("LABEL_PAUSE_DESAFIO"));

	public SegundaPantallaBatallaN(final UsuarioVO usuario) {
		super();
		logger.debug("***creo SegundaPantallaBatallaN***");
		this.llenarListaDesafio();
		this.usuario = usuario;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try { // intento recibir datos para el ranking/
					logger.debug("pregunto si aceptaron desafio");
					Registry registry = LocateRegistry.getRegistry(host);
					ServiciosDesafio stub = (ServiciosDesafio) registry
							.lookup("Desafio");

					if(stub.aceptaronDesafio(usuario)&& bandera==1){
						bandera--;
						temporizador.stop();
						((Timer)evt.getSource()).stop();
						aceptaronDesafio();
					}else{
						temporizador.start();
					}

				}
				catch (Exception remoteExceptionrmi) {
					if (remoteExceptionrmi instanceof NoSeEncuentraUsuarioException) {
						JOptionPane.showMessageDialog(new JFrame(), LABEL_ERROR_NO_USU_VP,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
					} else {
						System.err.println("Client exception: "
								+ remoteExceptionrmi.toString());
						remoteExceptionrmi.printStackTrace();
					}
			}
			}
		};

		temporizador = new Timer(pause, taskPerformer);
		temporizador.setInitialDelay(pause);
		temporizador.addActionListener(taskPerformer);
		temporizador.setRepeats(false);
		this.crearDesafioBoton.addActionListener(new ListenerBotonCrearDesafio());
	}

	private void aceptaronDesafio(){
		logger.debug("aceptaronDesafio");
		temporizador=null;
		this.dispose();
		ColocarBarcos l = new ColocarBarcos(this.usuario);
		l.setVisible(true);
	}


	private class ListenerBotonCrearDesafio implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// cuando se presiona un boton se ejecutara este metodo
			clickBotonCrearDesafio();//pongo menos uno para que el bus lo recibe de 0 a 9
		}
	}

	// METODO DE MIS BOTONES
	private void clickBotonCrearDesafio() {
		logger.debug("clickBotonCrearDesafio");
		this.crearDesafio(this.usuario);
	}

	//METODO PARA RECIBIR Y LLAMO AL LLENARTABLA
	private void llenarListaDesafio() {
		logger.debug("llenarListaDesafio");
		try { // intento recibir datos para el ranking/
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry
					.lookup("Desafio");
			this.arrayDesafio = stub.getDesafios();
			this.llenarTabla(this.desafiosDisponiblesTabla, arrayDesafio, this);
		} catch (Exception e) {
			if (!(e instanceof NoHayDesafiosDisponiblesException)) {
				e.printStackTrace();
			} else {
				JOptionPane.showMessageDialog(new JFrame(), LABEL_ERROR_NO_DESAF_VP,
						LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// METODO PARA LLENAR UNA JTABLE CON UN ARRAY DE OBJETOS
	private void llenarTabla(JTable tabla,
			ArrayList<DesafioBatallaNavalVO> lista,
			final SegundaPantallaBatallaN pantalla){
		logger.debug("llenarTabla");
		DefaultTableModel model = new DefaultTableModel() { // me hago mi modelo
			// para que no puedan editar la tabla
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		tabla.setModel(model);
		model.setColumnIdentifiers(new String[] { LABEL_MONTO, LABEL_TABLA_COLUMNA2 });
		Iterator i = lista.iterator();
		// relleno la tabla con data del arraylist
		while (i.hasNext()) {
			DesafioBatallaNavalVO rank = (DesafioBatallaNavalVO) i.next();
			model.addRow(new String[] {
					((Integer) rank.getApuesta()).toString(),
					(rank.getUsuario().getUsuarioB()) });
		}

		// añado mi action listener para ver aceptar un desafio
		desafiosDisponiblesTabla
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							JTable target = (JTable) e.getSource();
							int row = target.getSelectedRow();
							pantalla.iniciarPartida(pantalla.arrayDesafio
									.get(row), pantalla.usuario);
							pantalla.dispose();
							ColocarBarcos l = new ColocarBarcos(
									pantalla.usuario);
							l.setVisible(true);
						}
					}
				});

	}

	// metodo para iniciar mi partida
	private void iniciarPartida(DesafioBatallaNavalVO desafio,
			UsuarioVO desafiante) {
		logger.debug("iniciarPartida");
		try { // intento recibir datos para el ranking
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosBatallaNaval stub = (ServiciosBatallaNaval) registry
					.lookup("BatallaNavalServices");
			stub.iniciarPartida(desafio, desafiante);
		} catch (Exception e) {
			if (e instanceof RemoteException) {
				JOptionPane.showMessageDialog(new JFrame(),
						ERROR_CONEXION, LABEL_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), LABEL_ERROR_DESCONOCIDO,
								LABEL_ERROR, JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}
	//METODO PARA CREAR UN DESAFIO
	private void crearDesafio(UsuarioVO desafiante) {
		logger.debug("crearDesafio");
		try { // intento recibir datos para el ranking/
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry
					.lookup("Desafio");
			String monto = JOptionPane.showInputDialog(new JFrame(), LABEL_INGRESE_MONTO, LABEL_CREAR_DESAFIO_BOTON, JOptionPane.QUESTION_MESSAGE);
			if(pasarStringAInt(monto) > 0 ){
				stub.crearDesafio(this.usuario,this.pasarStringAInt(monto));
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
						ERROR_CREAR_DESAFIO, LABEL_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}

			temporizador.start();
		}
		catch (Exception remoteExceptionrmi) {
			if (remoteExceptionrmi instanceof NoSeEncuentraUsuarioException) {
				JOptionPane.showMessageDialog(new JFrame(),
						LABEL_ERROR_NO_USU_VP, LABEL_ERROR,
						JOptionPane.ERROR_MESSAGE);

			}else if (remoteExceptionrmi instanceof MontoInsuficienteException) {
				JOptionPane.showMessageDialog(new JFrame(),
						LABEL_MONTO_INSUFICIENTE, LABEL_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				System.err.println("Client exception: "
						+ remoteExceptionrmi.toString());
				remoteExceptionrmi.printStackTrace();
			}

		}

	}
	//METODO PASAR STRING A INT
	private int pasarStringAInt(String montoString){
		logger.debug("pasarStringAInt");
		int montoInt =0;
		try{
			montoInt = Integer.parseInt(montoString);
			return montoInt;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),
					LABEL_INGRESE_MONTO_CORRECTO, LABEL_ERROR,
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

}
