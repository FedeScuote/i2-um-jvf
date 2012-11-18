package ventanaPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import login.Login;
import login.LoginVentana;

import org.apache.log4j.Logger;

import pantallaElegirDesafioOJuego.SegundaPantallaBatallaN;
import util.ImagePanel;
import util.TranslucentPanel;

import comm.RankingVO;
import comm.ServiciosRanking;
import comm.UsuarioVO;
import commExceptions.NoSeEncuentraUsuarioException;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String host = null;

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private UsuarioVO usuario;

	private JPanel jContentPane = null;

	private ImagePanel panelVentanaPrincipal = null;

	private JScrollPane panelRanking = null;

	private JTable tablaRanking = null;

	private JButton jugarLudo = null;

	private JButton jugarBatalla = null;

	private JButton jugarBackgammon = null;

	private static Logger logger = Logger.getLogger(LoginVentana.class);

	private JButton DesconexionBoton = null;
	private static final String LABEL_TITULO_VENTANA_PRINCIPAL = labels.getString("LABEL_TITULO_VENTANA_PRINCIPAL");
	private static final String LABEL_IMAGEN_FONDO_VP = labels.getString("LABEL_IMAGEN_FONDO_VP");
	private static final String LABEL_BOTON_JUGAR_LUDO_VP = labels.getString("LABEL_BOTON_JUGAR_LUDO_VP");
	private static final String LABEL_BOTON_JUGAR_BN_VP = labels.getString("LABEL_BOTON_JUGAR_BN_VP");
	private static final String LABEL_BOTON_JUGAR_BG_VP = labels.getString("LABEL_BOTON_JUGAR_BG_VP");
	private static final String LABEL_TABLA_COLUMNA1 = labels.getString("LABEL_TABLA_COLUMNA1");
	private static final String LABEL_TABLA_COLUMNA2 = labels.getString("LABEL_TABLA_COLUMNA2");
	private static final String LABEL_ERROR_NO_USU_VP = labels.getString("LABEL-ERROR-NO-USU-VP");
	private static final String LABEL_ERROR = labels.getString("LABEL_ERROR");
	private static final String LABEL_BOTON_DESCONECTAR_VP = labels.getString("LABEL-BOTON-DESCONECTAR-VP");
	private static final String SALDO = labels.getString("LABEL_SALDO");

	private JPanel jPanelTransparente = null;

	private JLabel jLabelSaldo = null;

	/**
	 * This is the default constructor
	 */
	public VentanaPrincipal(UsuarioVO usuarioVO) {
		super();
		logger.debug("Constructor VentanaPrincipal");
		usuario=usuarioVO;
		initialize();
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);//mi frame arranca maximizada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jugarLudo.setEnabled(false);
		this.jugarBackgammon.setEnabled(false);

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		logger.debug("Inicializar Ventana Principal");
		this.setContentPane(getJContentPane());
		this.setTitle(LABEL_TITULO_VENTANA_PRINCIPAL);
		this.setBounds(new Rectangle(0, 0, 834, 632));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preguntarRanking();
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelVentanaPrincipal(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelVentanaPrincipal
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelVentanaPrincipal() {
		if (panelVentanaPrincipal == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 5;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints() ;
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.ipadx = 90;
			gridBagConstraints11.ipady = 40;
			gridBagConstraints11.gridy = 4;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.gridheight = 10;
			gridBagConstraints8.anchor = GridBagConstraints.NORTH;
			gridBagConstraints8.gridwidth = 5;
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.ipadx = 48;
			gridBagConstraints3.ipady = 40;
			gridBagConstraints3.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.ipadx = 55;
			gridBagConstraints2.ipady = 40;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.ipadx = 100;
			gridBagConstraints1.ipady = 40;
			gridBagConstraints1.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints1.gridy = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridy = 6;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.ipadx = 322;
			gridBagConstraints.ipady = 215;
			gridBagConstraints.gridx = 0;
			panelVentanaPrincipal = new ImagePanel(new ImageIcon(LABEL_IMAGEN_FONDO_VP).getImage());
			panelVentanaPrincipal.setLayout(new GridBagLayout());
			panelVentanaPrincipal.add(getPanelRanking(), gridBagConstraints);
			panelVentanaPrincipal.add(getJugarLudo(), gridBagConstraints1);
			panelVentanaPrincipal.add(getJugarBatalla(this), gridBagConstraints2);
			panelVentanaPrincipal
					.add(getJugarBackgammon(), gridBagConstraints3);
		//	panelVentanaPrincipal.add(imagenFondo, gridBagConstraints8);
			panelVentanaPrincipal.add(getDesconexionBoton(), gridBagConstraints11);
			panelVentanaPrincipal.add(getJPanelTransparente(), gridBagConstraints7);
		}
		return panelVentanaPrincipal;
	}

	/**
	 * This method initializes panelRanking
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getPanelRanking() {
		if (panelRanking == null) {
			panelRanking = new JScrollPane();
			panelRanking.setViewportView(getTablaRanking());
		}
		return panelRanking;
	}

	/**
	 * This method initializes tablaRanking
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getTablaRanking() {
		if (tablaRanking == null) {
			tablaRanking = new JTable();
		}
		return tablaRanking;
	}

	/**
	 * This method initializes jugarLudo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarLudo() {
		if (jugarLudo == null) {
			jugarLudo = new JButton();
			jugarLudo.setText(LABEL_BOTON_JUGAR_LUDO_VP);
		}
		return jugarLudo;
	}

	/**
	 * This method initializes jugarBatalla
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarBatalla(final VentanaPrincipal pantalla) {
		if (jugarBatalla == null) {
			jugarBatalla = new JButton();
			jugarBatalla.setText(LABEL_BOTON_JUGAR_BN_VP);
			jugarBatalla.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pantalla.dispose();
					SegundaPantallaBatallaN l= new SegundaPantallaBatallaN(pantalla.usuario);
					l.setVisible(true);
				}
			});
		}
		return jugarBatalla;
	}

	/**
	 * This method initializes jugarBackgammon
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJugarBackgammon() {
		if (jugarBackgammon == null) {
			jugarBackgammon = new JButton();
			jugarBackgammon.setText(LABEL_BOTON_JUGAR_BG_VP);
		}
		return jugarBackgammon;
	}
	//METODO PARA LLENAR UNA JTABLE CON UN ARRAY DE OBJETOS
	private void llenarTabla(JTable tabla, ArrayList<RankingVO> lista){
		logger.debug("metodo llenar tabla");
		DefaultTableModel model = new DefaultTableModel() { // me hago mi modelo
			// para que no puedan editar la tabla
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		tabla.setModel(model);
		model.setColumnIdentifiers(new String[] {LABEL_TABLA_COLUMNA1, LABEL_TABLA_COLUMNA2});
		Iterator i = lista.iterator();
		// relleno la tabla con data del arraylist
		while (i.hasNext())
		{
			RankingVO rank= (RankingVO)i.next();
		    model.addRow(new String[] {rank.nroAString(),rank.usuario});
		}
	}
	private void preguntarRanking(){
		try { // intento recibir datos para el ranking
			logger.debug("preguntar Ranking");
			Registry registry = LocateRegistry.getRegistry(host);
			ServiciosRanking stub = (ServiciosRanking) registry.lookup("Ranking");
			ArrayList<RankingVO> response = stub.preguntarRanking();
			int i=0;
			llenarTabla(tablaRanking, response);
		} catch (Exception remoteExceptionrmi) {
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
	/**
	 * This method initializes DesconexionBoton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getDesconexionBoton() {
		if (DesconexionBoton == null) {
			DesconexionBoton = new JButton();
			DesconexionBoton.setText(LABEL_BOTON_DESCONECTAR_VP);
			DesconexionBoton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					Login l = new Login();
					l.setVisible(true);
				}
			});
		}
		return DesconexionBoton;
	}

	/**
	 * This method initializes jPanelTransparente
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelTransparente() {
		if (jPanelTransparente == null) {
			jLabelSaldo = new JLabel();
			jLabelSaldo.setFont(new Font("Saldo", Font.BOLD,24));
			jLabelSaldo.setText(SALDO+usuario.getSaldo());
			jPanelTransparente = new TranslucentPanel(Color.white);
			jPanelTransparente.setLayout(new GridBagLayout());
			jPanelTransparente.add(jLabelSaldo, new GridBagConstraints());
		}
		return jPanelTransparente;
	}

	public static void main(String[] args) {
		VentanaPrincipal l = new VentanaPrincipal(null);
		l.setVisible(true);
	}

} // @jve:decl-index=0:visual-constraint="39,-35"
