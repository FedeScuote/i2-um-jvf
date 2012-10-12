package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import commExceptions.NoHayDesafiosDisponiblesException;

public interface ServiciosDesafio extends Remote{

	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException;

}
