package ar.edu.unlam.tallerweb1.controladores;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioSalon;
import ar.edu.unlam.tallerweb1.validadores.PuntajeValidar;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroSalonViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.modelo.PuntajeSalon;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorPuntuarSalon {
    @Inject
    private ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones ServicioRecomendaciones;
    @Inject
    private ServicioSalon servicioSalon;
 
    PuntajeValidar validarPuntaje =new PuntajeValidar();
    
    @RequestMapping(path = "/buscar", method = RequestMethod.GET)
    public ModelAndView buscarSalonesPorNombre(@RequestParam("input") String input) {
        ModelMap model = new ModelMap();
        List<Salon> salones=servicioSalon.buscar(input);
        model.put("salones",salones);
        model.put("tope",salones.size());
        return new ModelAndView("puntaje-salon", model);
    }


    @RequestMapping(path = "/salones-a-puntuar", method = RequestMethod.GET)
    public ModelAndView mostrarSalones(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		
			List<Salon> salones=ServicioRecomendaciones.ObtenerTodosLosSalones();
			model.put("salones",salones);
			model.put("usuario", nombreUsuario);

        return new ModelAndView("puntaje-salon", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");        
    }

    
    
    @RequestMapping(path = "/puntuar-salon", method = RequestMethod.POST)
    public ModelAndView ingresarPuntaje(HttpServletRequest request,@ModelAttribute("mvSalon") RegistroSalonViewModel mvSalon,
                                        BindingResult result) {
        ModelMap model = new ModelMap();
        this.validarPuntaje.validate(mvSalon, result);
        if(result.hasErrors()){
            model.put("mensaje" ,"no ingrese valores vacios");
            String nombreUsuario = (request.getSession().getAttribute("nombre").toString());

            List<Salon> salones=ServicioRecomendaciones.ObtenerTodosLosSalones();
            model.put("salones",salones);
            model.put("usuario", nombreUsuario);
            return new ModelAndView("puntaje-salon", model);
        }

      if(mvSalon.getPuntaje()>0 && mvSalon.getPuntaje()<=10){
//          Double p=servicioSalon.calcularPuntaje(mvSalon.getId(),puntaje);
          servicioSalon.calcularPuntaje(mvSalon.getId(),mvSalon.getPuntaje());
          
          model.put("mensaje" ,"gracias por darnos tu opinion :)");
          return new ModelAndView("redirect:/salones-a-puntuar", model);
      }


       model.put("mensaje" ,"solo puntuaciones del 1 al 10 porfavor  :)");
        return new ModelAndView("redirect:/salones-a-puntuar", model);
    }



}
