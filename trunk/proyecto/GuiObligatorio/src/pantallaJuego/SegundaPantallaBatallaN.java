package pantallaJuego;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import comm.DesafioBatallaNavalVO;
import comm.RankingVO;

public class SegundaPantallaBatallaN extends SegundaPantalla {



	//basicamente el mismo metodo que en la otra ventana.
	private void llenarTabla(JTable tabla, ArrayList<DesafioBatallaNavalVO> lista){
		DefaultTableModel model = new DefaultTableModel();
		tabla.setModel(model);
		model.setColumnIdentifiers(new String[] {"apuesta", "Nick"});
		Iterator i = lista.iterator();
		// relleno la tabla con data del arraylist
		while (i.hasNext())
		{
			DesafioBatallaNavalVO desafio= (DesafioBatallaNavalVO)i.next();
		    model.addRow(new String[] {((Integer)desafio.getApuesta()).toString(),desafio.getUsuarioCreador().toString()});
		}
	}
}
