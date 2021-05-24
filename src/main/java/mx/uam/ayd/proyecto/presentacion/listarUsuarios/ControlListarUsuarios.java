package mx.uam.ayd.proyecto.presentacion.listarUsuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Controller

public class ControlListarUsuarios {
	@Autowired
	private ServicioUsuario servicioUsuario;

	@GetMapping(value = "/recuperaUsuarios")
	public String consulta(Model model) {
		try {
			List<Usuario> usuarios = servicioUsuario.recuperaUsuarios();
			model.addAttribute("usuario", usuarios);//Enviamos al modelo la lista de usuarios
			return "listarUsuarios/ListarUsuarios";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			return "listarUsuarios/ListarUsuariosError";
		}

	}
}
