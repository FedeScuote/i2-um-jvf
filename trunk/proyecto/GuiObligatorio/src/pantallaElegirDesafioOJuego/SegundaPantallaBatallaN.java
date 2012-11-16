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

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import util.EstadoElegirDesafio;
import ventanaJuego.BatallaNavalVentana;
import ventanaJuego.ColocarBarcosVentana;
import ventanaPrincipal.VentanaPrincipal;

import comm.DesafioBatallaNavalVO;
import comm.DesafioVO;
import comm.RankingVO;
import comm.ServiciosBatallaNaval;
import comm.ServiciosDesafio;
import comm.ServiciosRanking;
import comm.UsuarioVO;
import commExceptions.NoHayDesafiosDisponiblesException;
import commExceptions.NoSeEncuentraUsuarioException;

public class SegundaPantallaBatallaN extends SegundaPantalla {

	private int bandera = 1;

	private final static String host = null;

	private final static int pause = 1000;

	private ArrayList<DesafioBatallaNavalVO> arrayDesafio = null;

	private Timer temporizador;

	private static Logger logger = Logger.getLogger(SegundaPantallaBatallaN.class);

	public SegundaPantallaBatallaN(final UsuarioVO usuario) {
		super();
		logger.debug("***creo SegundaPantallaBatallaN***");
		this.llenarListaDesafio();
		this.usuario = usuario;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try { // intento recibir datos para el ranking/
					Registry registry = LocateRegistry.getRegistry(host);
					ServiciosDesafio stub = (ServiciosDesafio) registry
							.lookup("Desafio");

					if(stub.aceptaronDesafio(usuario)&& bandera==1){
						bandera--;
						temporizador.stop();
						aceptaronDesafio();
					}else{
						temporizador.start();
					}

				}
				catch (Exception remoteExceptionrmi) {
					if (remoteExceptionrmi instanceof NoSeEncuentraUsuarioException) {
						System.out.println("no existe usuario");
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
		temporizador.stop();
		this.dispose();
		ColocarBarcosVentana l = new ColocarBarcosVentana(this.usuario);
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
				System.out.println("no hay desafios");
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
		model.setColumnIdentifiers(new String[] { "Monto", "Nick" });
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
							ColocarBarcosVentana l = new ColocarBarcosVentana(
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
						"Error en la conexion intente de nuevo", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				e.printStackTrace();
				JOptionPane
						.showMessageDialog(new JFrame(), "ERROR DESCONOCIDO",
								"ERROR", JOptionPane.ERROR_MESSAGE);
				this.dispose();
			}
		}
	}

	private void crearDesafio(UsuarioVO desafiante) {
		logger.debug("crearDesafio");
		try { // intento recibir datos para el ranking/
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry
					.lookup("Desafio");
			String monto = JOptionPane.showInputDialog(new JFrame(), "INGRESE UN MONTO", "Crear Desafio", JOptionPane.QUESTION_MESSAGE);
			if(pasarStringAInt(monto) != -1){
				stub.crearDesafio(this.usuario,this.pasarStringAInt(monto));
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
						"NO SE PUDO CREAR EL DESAFIO", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

			temporizador.start();
		}
		catch (Exception remoteExceptionrmi) {
			if (remoteExceptionrmi instanceof NoSeEncuentraUsuarioException) {
				System.out.println("no existe usuario");
			} else {
				System.err.println("Client exception: "
						+ remoteExceptionrmi.toString());
				remoteExceptionrmi.printStackTrace();
			}

		}

	}
	private int pasarStringAInt(String montoString){
		logger.debug("pasarStringAInt");
		int montoInt =0;
		try{
			montoInt = Integer.parseInt(montoString);
			return montoInt;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),
					"Ingrese un numero correcto", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

}
