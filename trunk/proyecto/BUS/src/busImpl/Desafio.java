
package busImpl;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import comm.DesafioBatallaNavalVO;
import comm.RankingVO;
import comm.ServiciosDesafio;
import comm.UsuarioVO;
import commExceptions.NoHayDesafiosDisponiblesException;
import commExceptions.NoSeEncuentraUsuarioException;
import daoInterfaces.DesafioDAO;
import daoInterfaces.RankingDAO;
import daoInterfaces.UsuarioDAO;
import excepcionesB.NoHayDesafioException;

public class Desafio implements ServiciosDesafio {


	private static final String USUARIO_BOT_1 = "jhirata";
	private static final String USUARIO_BOT_2 = "vtuyare";
	private static final String USUARIO_BOT_3 = "fkono";
	private static final String USUARIO_BOT_4 = "jdiaz";

	private String usuarioDesafio;
	private int idUsuario;
	private int idDesafio;
	private int monto;
	private Date fechaHoraInicioD;
	private String estado; //En hora, En hora-lleno, En curso, Atrasado, Finalizado, Cancelado
	public int getIdDesafio() {
		return idDesafio;
	}
	public void setIdDesafio(int idDesafio) {
		this.idDesafio = idDesafio;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public Date getFechaHoraInicioD() {
		return fechaHoraInicioD;
	}
	public void setFechaHoraInicioD(Date fechaHoraInicioD) {
		this.fechaHoraInicioD = fechaHoraInicioD;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}


	private static DesafioDAO getDAO() {
		try {
			return (DesafioDAO) Class.forName("daoImpl.DesafioDAODB")
					.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException{
		DesafioDAO dao = getDAO();
		ArrayList<DesafioBatallaNavalVO> aux = new ArrayList<DesafioBatallaNavalVO>();
		try{
			ArrayList desafiosBDD=dao.getDesafiosUsuariosDisponibleBatallaNaval();
			int i=0;
			while(i<desafiosBDD.size()){
				DesafioBatallaNavalVO nuevo = new DesafioBatallaNavalVO();
				UsuarioVO usu = new UsuarioVO(((Desafio)desafiosBDD.get(i)).getUsuarioDesafio());
				usu.setIdUsuario(((Desafio)desafiosBDD.get(i)).getIdUsuario());
				nuevo.setApuesta(((Desafio)desafiosBDD.get(i)).getMonto());
				nuevo.setIdDesafio((((Desafio)desafiosBDD.get(i)).getIdDesafio()));
				nuevo.setUsuario(usu);
				aux.add(nuevo);
				i++;
			}
			return aux;
		}catch(NoHayDesafioException e){
			DesafioBatallaNavalVO nuevo1 = new DesafioBatallaNavalVO();
			DesafioBatallaNavalVO nuevo2 = new DesafioBatallaNavalVO();
			DesafioBatallaNavalVO nuevo3 = new DesafioBatallaNavalVO();
			DesafioBatallaNavalVO nuevo4 = new DesafioBatallaNavalVO();
			UsuarioVO usuarioVOBot1 = new UsuarioVO(USUARIO_BOT_1);
			UsuarioVO usuarioVOBot2 = new UsuarioVO(USUARIO_BOT_2);
			UsuarioVO usuarioVOBot3 = new UsuarioVO(USUARIO_BOT_3);
			UsuarioVO usuarioVOBot4 = new UsuarioVO(USUARIO_BOT_4);
			nuevo1.setUsuario(usuarioVOBot1);
			nuevo2.setUsuario(usuarioVOBot2);
			nuevo3.setUsuario(usuarioVOBot3);
			nuevo4.setUsuario(usuarioVOBot4);
			int apuesta=0;
			apuesta=((int)Math.random()*100)+50;
			nuevo1.setApuesta(apuesta);
			apuesta=((int)Math.random()*100)+30;
			nuevo2.setApuesta(apuesta);
			apuesta=((int)Math.random()*100)+40;
			nuevo3.setApuesta(apuesta);
			apuesta=((int)Math.random()*100)+25;
			nuevo4.setApuesta(apuesta);
			aux.add(nuevo1);
			aux.add(nuevo2);
			aux.add(nuevo3);
			aux.add(nuevo4);
			int index=(int)(Math.random()*3);
			aux.remove(index);
			return aux;
		}

	}
	public String getUsuarioDesafio() {
		return usuarioDesafio;
	}
	public void setUsuarioDesafio(String usuarioDesafio) {
		this.usuarioDesafio = usuarioDesafio;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public boolean aceptaronDesafio(UsuarioVO usuario) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	public void crearDesafio(UsuarioVO usuario, int monto) throws RemoteException {
		// TODO Auto-generated method stub

	}






}
