package busImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

import busImpl.administracion.ServiciosAdministradorImpl;
import busImpl.batallaNaval.ServiciosBatallaNavalImpl;
import busImpl.usuario.ServiciosUsuarioImpl;

import comm.ServiciosAdministrador;
import comm.ServiciosBatallaNaval;
import comm.ServiciosDesafio;
import comm.ServiciosRanking;
import comm.ServiciosUsuario;


public class BusMain {
	private static ServiciosUsuarioImpl bs;
	private static Ranking bsRanking;
	private static Desafio bsDesafio;
	private static ServiciosBatallaNavalImpl bsBN;
	private static ServiciosAdministrador bsAdmin;
	private static ResourceBundle lookup = ResourceBundle.getBundle("bus");
	public static void main(String[] args) {
		bs = new ServiciosUsuarioImpl();
		bsRanking = new Ranking();
		bsDesafio = new Desafio();
		bsBN= new ServiciosBatallaNavalImpl();
		bsAdmin= new ServiciosAdministradorImpl();
		try {
			ServiciosUsuario stub = (ServiciosUsuario) UnicastRemoteObject.exportObject(bs, 0);
			ServiciosRanking stubRanking = (ServiciosRanking) UnicastRemoteObject.exportObject(bsRanking, 0);
			ServiciosDesafio stubDesafio = (ServiciosDesafio) UnicastRemoteObject.exportObject(bsDesafio, 0);
			ServiciosBatallaNaval stubBN = (ServiciosBatallaNaval) UnicastRemoteObject.exportObject(bsBN, 0);
			ServiciosAdministrador stubAdmin = (ServiciosAdministrador) UnicastRemoteObject.exportObject(bsAdmin, 0);
			try {

				Registry registry = LocateRegistry.createRegistry(Integer.parseInt(lookup.getString("PUERTO_RMI")));
				registry.rebind(lookup.getString("RANKING_LOOKUP"), stubRanking);
				registry.rebind(lookup.getString("USUARIO_LOOKUP"), stub);
				registry.rebind(lookup.getString("DESAFIO_LOOKUP"), stubDesafio);
				registry.rebind(lookup.getString("BN_LOOKUP"), stubBN);
				registry.rebind(lookup.getString("ADMIN_LOOKUP"), stubAdmin);
				System.out.println(lookup.getString("MENSAJE_LOOKUP"));

			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
