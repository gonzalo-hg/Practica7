package mx.uam.ayd.proyecto.presentacion.principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Controller
public class ControlPrincipal {
	
	/**
		 * Este método está mapeado a la raíz del sitio
		 * 
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/")
	    public String getAgregarUsuario(Model model) {
	        
				// Redirige a la vista principal
	    		return "vistaPrincipal/Principal";
	    	
	    }

}
