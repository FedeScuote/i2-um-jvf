package busImpl;

import commExceptions.NoSeEncuentraUsuarioException;

public class BUSPruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Usuario u=new Usuario();
		try {
			u=Usuario.findByName("jhirat");
		} catch (NoSeEncuentraUsuarioException e) {
			System.out.println("oou no se encuentra usuario");

		}
	}

}
