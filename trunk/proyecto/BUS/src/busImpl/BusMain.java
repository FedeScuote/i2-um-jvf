package busImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import comm.ServiciosUsuario;


public class BusMain {
	private static BusServer bs;

	public static void main(String[] args) {
		bs = new BusServer();
		try {

			// Estableciendo un security manager
			// if (System.getSecurityManager() == null) {
			// System.setSecurityManager(new RMISecurityManager());
			// }
			// Exportando el objeto
			ServiciosUsuario stub = (ServiciosUsuario) UnicastRemoteObject
					.exportObject(bs, 0);

			try {

				Registry registry = LocateRegistry.createRegistry(1099);
				registry.rebind("Hello", stub);
				System.out.println("Servidor remoto registrado OK");

			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
