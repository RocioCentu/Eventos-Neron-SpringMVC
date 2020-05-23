package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.servicios.*;
import ar.edu.unlam.tallerweb1.validadores.PuntajeValidar;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorPuntuarMenu {
    @Inject
    private ServicioRegistroMenu servicioRegistroMenu;
    @Inject
    private ServicioListarTiposMenu servicioListarTiposMenu;

    @Inject
    private ServicioListadoOpcionesMenu servicioListadoOpcionesMenu;

    PuntajeValidar validarPuntaje =new PuntajeValidar();
    
    
    @RequestMapping(path = "/menus-a-puntuar", method = RequestMethod.GET)
    public ModelAndView puntajesMenu(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);
			model.put("listaopciones", servicioListadoOpcionesMenu.listarOpcionesMenu());
			model.put("secciones", servicioListarTiposMenu.listarTipoDeMenus());

			return new ModelAndView("puntaje-menu", model);
        
		}
		
		return new ModelAndView("redirect:/homeAdmin");
    }

    
    
    @RequestMapping(path = "/puntuar-menu", method = RequestMethod.POST)
    public ModelAndView ingresarPuntaje(HttpServletRequest request,@ModelAttribute("mvSalon") RegistroMenuViewModel mvMenu, BindingResult result
                                       ) {
        ModelMap model = new ModelMap();

        this.validarPuntaje.validate(mvMenu, result);
        if(result.hasErrors()){
            model.put("mensaje" ,"no ingrese valores vacios");
            String nombreUsuario = (request.getSession().getAttribute("nombre").toString());

            model.put("usuario", nombreUsuario);
            model.put("listaopciones", servicioListadoOpcionesMenu.listarOpcionesMenu());
            model.put("secciones", servicioListarTiposMenu.listarTipoDeMenus());
            return new ModelAndView("puntaje-menu", model);
        }



        if(mvMenu.getPuntaje()>0 && mvMenu.getPuntaje()<=10) {
            servicioRegistroMenu.calcularPuntaje(mvMenu.getId(), mvMenu.getPuntaje());

            model.put("mensaje", "gracias por darnos tu opinion del menu :)");
            return new ModelAndView("redirect:/menus-a-puntuar", model);
        }
        model.put("mensaje", "solo puntajes del 1 al 10 porfavor :)");

        return new ModelAndView("redirect:/menus-a-puntuar", model);
    }
}
