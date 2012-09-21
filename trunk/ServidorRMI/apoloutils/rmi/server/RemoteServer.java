package apoloutils.rmi.server;

import apoloutils.rmi.interfaces.RemoteInterface;

public class RemoteServer implements RemoteInterface {
	private int cantidadInvocaciones = 0;

	public String saludar(String nombre){
		System.out.println("Cantidad invocaciones: "+cantidadInvocaciones++);
		return "Hola "+nombre;
	}
}
