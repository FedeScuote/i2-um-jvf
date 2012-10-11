package guiObligatorio;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import comm.DesafioBatallaNaval;
import comm.RankingVO;

public class SegundaPantallaBatallaN extends SegundaPantalla {


	private void llenarTabla(JTable tabla, ArrayList<DesafioBatallaNaval> lista){
		DefaultTableModel model = new DefaultTableModel();
		tabla.setModel(model);
		model.setColumnIdentifiers(new String[] {"nro partidas ganadas", "Nick"});
		Iterator i = lista.iterator();
		// relleno la tabla con data del arraylist
		while (i.hasNext())
		{
			RankingVO rank= (RankingVO)i.next();
		    model.addRow(new String[] {rank.nroAString(),rank.usuario});
		}
	}
}
