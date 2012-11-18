package comm;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import commExceptions.MontoInsuficienteException;
import commExceptions.NoHayDesafiosDisponiblesException;

public interface ServiciosDesafio extends Remote{

	//metodo que devuelve una lista de desafios
	public ArrayList<DesafioBatallaNavalVO> getDesafios() throws RemoteException, NoHayDesafiosDisponiblesException;
	//metodo que permite hacer la espera
	public boolean aceptaronDesafio(UsuarioVO usuario) throws RemoteException;
	//metodo para crear un desafio
	public void crearDesafio(UsuarioVO usuario,int monto) throws RemoteException, MontoInsuficienteException;
	//metodo para cancelar desafio
	public void cancelarDesafio(UsuarioVO usuario) throws RemoteException;

	public boolean desafioDisponible(DesafioVO desafio) throws RemoteException;
}
