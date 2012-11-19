package ventanasAuxiliares;

import java.awt.BorderLayout;
import java.awt.Font;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ventanaPrincipal.VentanaPrincipal;

import comm.ReporteVO;
import comm.ServiciosAdministrador;
import comm.UsuarioVO;

public class Estadisticas extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabelRobots = null;

	private JLabel jLabelComision = null;

	private JLabel jLabelUsuarios = null;

	private static ResourceBundle labels = ResourceBundle.getBundle("Gui");

	private int robots = 0;
	private int usuarios = 0;
	private int comision = 0;
	private static final String HOST = labels.getString("host");

	private static final String ROBOTS = labels.getString("ROBOTS");  //  @jve:decl-index=0:
	private static final String USUARIOS = labels.getString("USUARIOS");
	private static final String COMISION = labels.getString("COMISION");
	private static final String CANCELAR = labels
	.getString("LABEL_VOLVER_BOTON");
	private UsuarioVO usuario=null;

	private JButton jButtonOK = null;

	/**
	 * This is the default constructor
	 */
	public Estadisticas(UsuarioVO usu) {
		super();
		usuario = usu;
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(635, 252);
		this.obtenerDatos();
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelUsuarios = new JLabel();
			Font nueva = new Font("ARIAL",Font.ITALIC,20);
			jLabelUsuarios.setFont(nueva);
			jLabelUsuarios.setText(USUARIOS+(Integer)usuarios);
			jLabelComision = new JLabel();
			jLabelComision.setFont(nueva);
			jLabelComision.setText(COMISION+(Integer)comision);
			jLabelRobots = new JLabel();
			jLabelRobots.setFont(nueva);
			jLabelRobots.setText(ROBOTS + (Integer)robots);
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jLabelRobots, BorderLayout.CENTER);
			jContentPane.add(jLabelComision, BorderLayout.EAST);
			jContentPane.add(jLabelUsuarios, BorderLayout.WEST);
			jContentPane.add(getJButtonOK(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonOK
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOK() {
		if (jButtonOK == null) {
			jButtonOK = new JButton();
			jButtonOK.setSize(200,200);
			jButtonOK.setText(CANCELAR);
			jButtonOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					VentanaPrincipal l = new VentanaPrincipal(usuario);
					l.setVisible(true);
				}
			});
		}
		return jButtonOK;
	}
	private void obtenerDatos(){
		try {
			Registry registry = LocateRegistry.getRegistry(HOST);
			ServiciosAdministrador stub1 = (ServiciosAdministrador) registry
					.lookup("AdministrationServices");
			ReporteVO report= stub1.getReporte();
			this.robots = report.getMontoTotalVirtuales();
			this.comision = report.getMontoTotalComisionesJVF();
			this.usuarios = report.getMontoTotalNoVirtuales();
		}  catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
