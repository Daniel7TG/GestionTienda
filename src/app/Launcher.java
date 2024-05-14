package app;


import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.modelos.services.UsuarioServiceImp;

public class Launcher {

	public static void main(String[] args) {

		new ViewLogin();
//		  new VentanaPrincipal(new UsuarioServiceImp().get("root"));

	  }

}
