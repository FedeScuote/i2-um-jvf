package pantallaElegirDesafioOJuego;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import comm.DesafioBatallaNavalVO;
import comm.DesafioVO;
import comm.ServiciosDesafio;
import comm.UsuarioVO;
import commExceptions.NoHayDesafiosDisponiblesException;

public class SegundaPantallaLudo extends SegundaPantalla {

	private final static String host = null;

	private final static int pause = 1000;

	private ArrayList<DesafioVO> arrayDesafio = null;

	private Timer temporizador;

	private static Logger logger = Logger.getLogger(SegundaPantallaLudo.class);

	public SegundaPantallaLudo (final UsuarioVO usuario){
		super();
		logger.debug("***Creo SegundaPantallaLudo");
		this.llenarListaDesafio();
		this.usuario = usuario;

	}

	private void llenarListaDesafio() {
		logger.debug("llenarListaDesafio");
		try { // intento recibir datos para el ranking/
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry
					.lookup("Desafio");
			this.arrayDesafio = stub.getDesafiosLudo();
			this.llenarTabla(this.desafiosDisponiblesTabla, arrayDesafio, this);
		} catch (Exception e) {
			if (!(e instanceof NoHayDesafiosDisponiblesException)) {
				e.printStackTrace();
			} else {
				System.out.println("no hay desafios");
			}
		}
	}

//	 METODO PARA LLENAR UNA JTABLE CON UN ARRAY DE OBJETOS
	private void llenarTabla(JTable tabla,
			ArrayList<DesafioVO> lista,
			final SegundaPantallaLudo pantalla){
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
			DesafioVO rank = (DesafioVO) i.next();
			model.addRow(new String[] {
					((Integer) rank.getApuesta()).toString(),
					(rank.getUsuario().getUsuarioB()) });
		}


	}
}
