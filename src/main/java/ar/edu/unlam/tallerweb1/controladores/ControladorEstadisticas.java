package ar.edu.unlam.tallerweb1.controladores;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadisticas;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroSalonViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorEstadisticas {
    @Inject
    private ServicioEstadisticas servicioEstadisticas;


    @RequestMapping(path = "/estadisticas", method = RequestMethod.GET)
    public ModelAndView estadisticas() {
        ModelMap model = new ModelMap();
        Map<Long,Integer> datos= servicioEstadisticas.estadisticasSalones();
        model.put("tope",datos.size());

        return new ModelAndView("estadisticas", model);
    }


}
