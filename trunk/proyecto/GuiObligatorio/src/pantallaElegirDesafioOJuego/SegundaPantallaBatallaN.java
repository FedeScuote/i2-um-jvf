package pantallaElegirDesafioOJuego;

import java.awt.Rectangle;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import comm.DesafioBatallaNavalVO;
import comm.RankingVO;
import comm.ServiciosDesafio;
import commExceptions.NoHayDesafiosDisponiblesException;

public class SegundaPantallaBatallaN extends SegundaPantalla {

	private final static String host = null;


	public SegundaPantallaBatallaN(){
		super();
		this.llenarListaDesafio();
	}
	private void llenarListaDesafio(){
		try { // intento recibir datos para el ranking
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosDesafio stub = (ServiciosDesafio) registry.lookup("Desafio");
			ArrayList<DesafioBatallaNavalVO> response = stub.getDesafios();
			this.llenarTabla(this.desafiosDisponiblesTabla, response);
		}catch(Exception e){
			if(! (e instanceof NoHayDesafiosDisponiblesException)){
			e.printStackTrace();
			}else{
				System.out.println("no hay desafios");
			}
		}
	}
//	METODO PARA LLENAR UNA JTABLE CON UN ARRAY DE OBJETOS
	private void llenarTabla(JTable tabla, ArrayList<DesafioBatallaNavalVO> lista){
		DefaultTableModel model = new DefaultTableModel();
		tabla.setModel(model);
		model.setColumnIdentifiers(new String[] {"Monto", "Nick"});
		Iterator i = lista.iterator();
		// relleno la tabla con data del arraylist
		while (i.hasNext())
		{
			DesafioBatallaNavalVO rank= (DesafioBatallaNavalVO)i.next();
		    model.addRow(new String[] {((Integer)rank.getApuesta()).toString(),((Integer)rank.getIdDesafio()).toString()});
		}
	}
}
