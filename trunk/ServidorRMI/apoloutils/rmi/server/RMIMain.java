package apoloutils.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import apoloutils.rmi.interfaces.RemoteInterface;

public class RMIMain {

	private static RemoteServer rs;

	public static void main(String[] args) {
		rs = new RemoteServer();
		try {

			// Estableciendo un security manager
			// if (System.getSecurityManager() == null) {
			// System.setSecurityManager(new RMISecurityManager());
			// }
			// Exportando el objeto
			RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(rs,0);

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
