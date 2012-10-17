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

	private JLabel torneosDisponiblesLabel = null;

	private JButton crearDesafioBoton = null;

	private JButton BotonRetorno = null;

	private JScrollPane desafiosDisponiblesPane = null;

	protected JTable desafiosDisponiblesTabla = null;

	private JScrollPane TorneosDisponiblesPane = null;

	protected JTable TorneosDisponiblesTabla = null;

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
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.gridx = 4;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 3;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.gridx = 1;
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
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 4;
			gridBagConstraints7.anchor = GridBagConstraints.CENTER;
			gridBagConstraints7.gridy = 1;
			torneosDisponiblesLabel = new JLabel();
			torneosDisponiblesLabel.setText("Torneos Disponibles");
			torneosDisponiblesLabel.setForeground(Color.white);
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
			panelSegundaPantalla.add(torneosDisponiblesLabel, gridBagConstraints7);
			panelSegundaPantalla.add(getCrearDesafioBoton(), gridBagConstraints1);
			panelSegundaPantalla.add(getBotonRetorno(), gridBagConstraints11);
			panelSegundaPantalla.add(getDesafiosDisponiblesPane(), gridBagConstraints3);
			panelSegundaPantalla.add(getTorneosDisponiblesPane(), gridBagConstraints4);
		}
		return panelSegundaPantalla;
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

	/**
	 * This method initializes desafiosDisponiblesPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getDesafiosDisponiblesPane() {
		if (desafiosDisponiblesPane == null) {
			desafiosDisponiblesPane = new JScrollPane();
			desafiosDisponiblesPane.setViewportView(getDesafiosDisponiblesTabla());
		}
		return desafiosDisponiblesPane;
	}

	/**
	 * This method initializes desafiosDisponiblesTabla
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getDesafiosDisponiblesTabla() {
		if (desafiosDisponiblesTabla == null) {
			desafiosDisponiblesTabla = new JTable();
		}
		return desafiosDisponiblesTabla;
	}

	/**
	 * This method initializes TorneosDisponiblesPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getTorneosDisponiblesPane() {
		if (TorneosDisponiblesPane == null) {
			TorneosDisponiblesPane = new JScrollPane();
			TorneosDisponiblesPane.setViewportView(getTorneosDisponiblesTabla());
		}
		return TorneosDisponiblesPane;
	}

	/**
	 * This method initializes TorneosDisponiblesTabla
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTorneosDisponiblesTabla() {
		if (TorneosDisponiblesTabla == null) {
			TorneosDisponiblesTabla = new JTable();
		}
		return TorneosDisponiblesTabla;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"