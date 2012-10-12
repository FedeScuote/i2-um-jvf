package pantallaElegirDesafioOJuego;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JButton;

import comm.DesafioBatallaNavalVO;
import comm.RankingVO;
import comm.ServiciosDesafio;
import comm.ServiciosRanking;
import commExceptions.NoHayDesafiosDisponiblesException;

import java.awt.Color;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class SegundaPantalla extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String host = null;

	private JPanel jContentPane = null;

	private JPanel panelSegundaPantalla = null;

	protected JLabel desafiosDisponiblesLabel = null;

	protected JList desafiosDisponiblesLista = null;

	private JLabel torneosDisponiblesLabel = null;

	private JList torneosDisponiblesLista = null;

	private JButton crearDesafioBoton = null;

	private JButton BotonRetorno = null;

	/**
	 * This is the default constructor
	 */
	public SegundaPantalla() {
		super();
		initialize();
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);//mi frame arranca maximizada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setBounds(new Rectangle(0, 0, 816, 619));
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setHgap(0);
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.black);
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getPanelSegundaPantalla(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelSegundaPantalla
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelSegundaPantalla() {
		if (panelSegundaPantalla == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.ipadx = 100;
			gridBagConstraints11.ipady = 20;
			gridBagConstraints11.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 3;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.ipady = 20;
			gridBagConstraints1.anchor = GridBagConstraints.NORTH;
			gridBagConstraints1.gridy = 3;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.NONE;
			gridBagConstraints8.gridy = 3;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.weighty = 1.0;
			gridBagConstraints8.ipadx = 300;
			gridBagConstraints8.ipady = 300;
			gridBagConstraints8.insets = new Insets(0, 0, 260, 0);
			gridBagConstraints8.anchor = GridBagConstraints.WEST;
			gridBagConstraints8.gridx = 4;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 4;
			gridBagConstraints7.anchor = GridBagConstraints.CENTER;
			gridBagConstraints7.gridy = 1;
			torneosDisponiblesLabel = new JLabel();
			torneosDisponiblesLabel.setText("Torneos Disponibles");
			torneosDisponiblesLabel.setForeground(Color.white);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.NONE;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.weighty = 1.0;
			gridBagConstraints6.ipadx = 300;
			gridBagConstraints6.ipady = 300;
			gridBagConstraints6.insets = new Insets(0, 0, 260, 0);
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridwidth = 1;
			gridBagConstraints5.gridheight = 1;
			gridBagConstraints5.anchor = GridBagConstraints.CENTER;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints5.gridy = 1;
			desafiosDisponiblesLabel = new JLabel();
			desafiosDisponiblesLabel.setText("Desafios Disponibles");
			desafiosDisponiblesLabel.setForeground(Color.white);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.gridwidth = 0;
			gridBagConstraints.gridheight = 0;
			gridBagConstraints.gridy = 5;
			panelSegundaPantalla = new JPanel();
			panelSegundaPantalla.setLayout(new GridBagLayout());
			panelSegundaPantalla.setBackground(Color.black);
			panelSegundaPantalla.add(desafiosDisponiblesLabel, gridBagConstraints5);
			panelSegundaPantalla.add(getDesafiosDisponiblesLista(), gridBagConstraints6);
			panelSegundaPantalla.add(torneosDisponiblesLabel, gridBagConstraints7);
			panelSegundaPantalla.add(getTorneosDisponiblesLista(), gridBagConstraints8);
			panelSegundaPantalla.add(getCrearDesafioBoton(), gridBagConstraints1);
			panelSegundaPantalla.add(getBotonRetorno(), gridBagConstraints11);
		}
		return panelSegundaPantalla;
	}

	/**
	 * This method initializes desafiosDisponiblesLista
	 *
	 * @return javax.swing.JList
	 */
	private JList getDesafiosDisponiblesLista() {
		if (desafiosDisponiblesLista == null) {
			desafiosDisponiblesLista = new JList();
		}
		return desafiosDisponiblesLista;
	}

	/**
	 * This method initializes torneosDisponiblesLista
	 *
	 * @return javax.swing.JList
	 */
	private JList getTorneosDisponiblesLista() {
		if (torneosDisponiblesLista == null) {
			torneosDisponiblesLista = new JList();
		}
		return torneosDisponiblesLista;
	}

	/**
	 * This method initializes crearDesafioBoton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getCrearDesafioBoton() {
		if (crearDesafioBoton == null) {
			crearDesafioBoton = new JButton();
			crearDesafioBoton.setText("Crear Desafio");
		}
		return crearDesafioBoton;
	}

	/**
	 * This method initializes BotonRetorno
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonRetorno() {
		if (BotonRetorno == null) {
			BotonRetorno = new JButton();
			BotonRetorno.setText("RETORNO");
		}
		return BotonRetorno;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
