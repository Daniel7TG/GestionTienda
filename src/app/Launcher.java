package app;

<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;

import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.enums.Permission;
import app.modelos.Usuario;
import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;

=======
import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
>>>>>>> 2cc7f1049877e82da2723963f17d03afcd25d311
import app.modelos.services.UsuarioServiceImp;

public class Launcher {

	public static void main(String[] args) {

<<<<<<< HEAD
		  new VentanaPrincipal(new UsuarioServiceImp().get("Sukkki"));

	  }

//	public static void main(String[] args) {
//
//		new ViewLogin();
//		
//	}

=======
		new ViewLogin();
		
	}
>>>>>>> 2cc7f1049877e82da2723963f17d03afcd25d311
	
}
