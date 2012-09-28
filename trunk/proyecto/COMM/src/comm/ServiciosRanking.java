package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServiciosRanking extends Remote {

	public ArrayList preguntarRanking() throws RemoteException;
}
