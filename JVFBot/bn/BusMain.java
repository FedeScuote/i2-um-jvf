package bn;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class BusMain {
	private static PartidaBatallaNavalImpl bs;
	private static CopyOfPartidaBatallaNavalImpl bs1;
	public static void main(String[] args) {
		bs = new PartidaBatallaNavalImpl();
		bs1 = new CopyOfPartidaBatallaNavalImpl();

		try {

			BatallaNaval stub = (BatallaNaval) UnicastRemoteObject.exportObject(bs, 0);
			BatallaNaval stub1 = (BatallaNaval) UnicastRemoteObject.exportObject(bs1, 0);
			try {

				Registry registry = LocateRegistry.createRegistry(1099);
				registry.rebind("Hello", stub);
				registry.rebind("Hello1", stub1);
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
