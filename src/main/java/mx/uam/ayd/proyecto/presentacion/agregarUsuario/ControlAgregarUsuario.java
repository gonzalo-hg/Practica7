package mx.uam.ayd.proyecto.presentacion.agregarUsuario;

import java.util.List;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioGrupo;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

/**
 * 
 * Módulo de control para la historia de usuario AgregarUsuario
 * 
 * @author Gonzalo
 *
 */
@Controller
@Slf4j
public class ControlAgregarUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;
	
	//Inyectamos la dependencia alos grupos
	@Autowired
	private ServicioGrupo servicioGrupo;

	/**
	 * Método invocado cuando se hace una petición GET a la ruta
	 * 
	 * @param Model
	 * @return ruta a la vista redirigida
	 */
	@PrePersist
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.GET)
	public String getAgregarUsuario(Model model) {
		log.info("Iniciando Historia de Usuario: Agrega Usuario.");
		//PEdimos el servicio de Grupo
		List<Grupo> grupoLista = servicioGrupo.recuperaGrupos();
		//pasar el modelo a la vista
		model.addAttribute("grupo",grupoLista);
		return "vistaAgregarUsuario/FormaAgregarUsuario";
	}

	/**
	 * Método invocado cuando se hace una petición POST a la ruta
	 * 
	 * @param nombre
	 * @param apellido
	 * @param grupo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agregarUsuario", method = RequestMethod.POST)
	public String postAgregarUsuario(@RequestParam(value = "nombre", required = true) String nombre,
			@RequestParam(value = "apellido", required = true) String apellido,
			@RequestParam(value="grupo", required = true) String grupo, Model model) {
		System.out.println("Agregando usuario: " + nombre + " " + apellido + " " + grupo);
		try {
			// Invocacion al servicio
			Usuario usuario = servicioUsuario.agregaUsuario(nombre, apellido, grupo);
			
			// Se agrega el usuario al modelo
			model.addAttribute("usuario", usuario);

			// Redirigmos a la vista de Exito
			return "vistaAgregarUsuario/AgregarUsuarioExito";
			
		} catch (Exception e) {
			// TODO: handle exception
			// Ser agrega el mensaje de error al modelo
			model.addAttribute("error", e.getMessage());

			// Redirigimos a la vista error
			return "vistaAgregarUsuario/AgregarUsuarioError";
		}
	}


}
