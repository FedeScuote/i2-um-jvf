import java.util.ResourceBundle;


public class EjemploProperties {




	public static void main(String[] args) {
		ResourceBundle labels = ResourceBundle.getBundle("Gui");
		System.out.println(labels.getString("LABEL_TURNO"));
	}

}
