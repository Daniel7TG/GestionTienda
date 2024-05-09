package app;

import java.util.ArrayList;
import java.util.List;

import app.UI.vista.general.VentanaPrincipal;
import app.UI.vista.general.ViewLogin;
import app.enums.Permission;
import app.modelos.Usuario;
import app.modelos.services.UsuarioServiceImp;

public class Launcher {

	  public static void main(String[] args) {
//	        List<Permission> permisos = new ArrayList<>();
//	        permisos.add(Permission.ADMIN);
//	        Usuario sukkki = new Usuario("Sukkki", "Saotome", "tu_telefono", 1, null, 1, permisos, "sukkki", "Saotome.");
//
//	        List<Usuario> usuarios = new ArrayList<>();
//	        usuarios.add(sukkki);
//
//	        new ViewLogin();

		  new VentanaPrincipal(new UsuarioServiceImp().get("Sukkki"));

	  }
	
}
