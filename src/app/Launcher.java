package app;



import java.util.ArrayList;
import java.util.List;

import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.enums.Permission;
import app.modelos.Usuario;
import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;

import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.modelos.services.UsuarioServiceImp;

public class Launcher {

	public static void main(String[] args) {


		  new VentanaPrincipal(new UsuarioServiceImp().get("Sukkki"));

	  }

//	public static void main(String[] args) {
//
//		new ViewLogin();
//		
//	}



	
}
