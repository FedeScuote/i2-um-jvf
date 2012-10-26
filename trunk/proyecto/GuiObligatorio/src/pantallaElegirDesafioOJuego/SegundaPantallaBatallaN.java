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

import util.EstadoElegirDesafio;
import ventanaJuego.ColocarBarcosVentana;

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

	private final static String host = null;

	private final static int pause = 10000;

	private ArrayList<DesafioBatallaNavalVO> arrayDesafio = null;

	private Timer temporizador;

	public SegundaPantallaBatallaN(final UsuarioVO usuario) {
		super();
		this.llenarListaDesafio();
		this.usuario = usuario;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try { // intento recibir datos para el ranking/
					Registry registry = LocateRegistry.getRegistry(host);
					ServiciosDesafio stub = (ServiciosDesafio) registry
							.lookup("Desafio");
//					if(stub.aceptaronDesafio(usuario)){
//						temporizador.stop();
//						iniciarPartida
//					}

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
	}

	private void llenarListaDesafio() {
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
			final SegundaPantallaBatallaN pantalla) {
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
		try { // intento recibir datos para el ranking/
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry
					.lookup("Desafio");
			//stub.crearDesafio(this.usuario);
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

}
