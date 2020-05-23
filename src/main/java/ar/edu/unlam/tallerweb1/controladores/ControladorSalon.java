package ar.edu.unlam.tallerweb1.controladores;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioEliminoSalon;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioSalon;
import ar.edu.unlam.tallerweb1.servicios.ServicioValidacionSeleccionSalon;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroSalonViewModel;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Controller
public class ControladorSalon {
	
    @Inject
    private ServicioSalon servicioSalon;
    @Inject
    private ServicioEliminoSalon servicioEliminoSalon;
    @Inject
    private ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon;

   // @Inject
   // private ServicioCliente servicioCliente;


    
    @RequestMapping(path = "/eliminar-salon", method = RequestMethod.POST)
 	public ModelAndView registroExtras (@ModelAttribute ("Salon") Salon salon, HttpServletRequest request) {
 		servicioEliminoSalon.eliminarSalon(salon);
 		return new ModelAndView("redirect:/elimino-salon"); 
 	}   
    
    
    
    //Admin
    //HAY QUE HACERLO
    /*
    @RequestMapping(path = "/ingreso-salon", method = RequestMethod.GET)
    public ModelAndView ingresoDeSalon() {
        Salon salon = new Salon();
        ModelMap model = new ModelMap();
        model.put("salon", salon);
        return new ModelAndView("ingreso-salon", model);
    }
    //HAY QUE HACERLO
    @RequestMapping(path = "/registro-Salon", method = RequestMethod.POST)
    public ModelAndView registroExtras (@ModelAttribute ("Extras") Extra extra,
                                        HttpServletRequest request) {
    //    if(request.getSession().getAttribute("ROL").equals("1")){
      //  servicioIngresoExtras.ingresarExtras(extra);
        return new ModelAndView("redirect:/ingreso-salon");
   // }
      //      return new ModelAndView("redirect:/home");
    }
    //HAY QUE HACERLO
    @RequestMapping(path = "/listado-final-salon", method = RequestMethod.GET)
    public ModelAndView listadoExtras () {
        ModelMap modelo = new ModelMap();
       // modelo.put("listadoFinal", servicioListarExtras.listarExtras());
        return new ModelAndView("listado-final-salon", modelo);
    }
*/
 
    
    
	//********************************************************	
	// Lado CLIENTE	  ****************************************
	//********************************************************

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Seleccion del Salon  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// FORMA PARTE DE LA SELECCION DE LA RESERVA    
    // Ingreso de los datos para realizar la busqueda que se adecue a lo ingresado
    @RequestMapping(path="/salon")
    public ModelAndView ir(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);
			return new ModelAndView("salon", model);
        }
		
           return new ModelAndView("redirect:/home");
    }
    
    
    
    // Validacion de los datos ingresados para la busqueda de salones y Busqueda de los salones que cumplen con el criterio de los datos ingresados
    @RequestMapping(path="/tomarDatos", method = RequestMethod.GET)
    public ModelAndView tomarDatos(@RequestParam(name="cantidad",required=false) Integer cantidad,
                                   @RequestParam(name="fecha",required=false) String fecha) {
    	ModelMap modelo = new ModelMap();    	
    	String mensajeFinal = servicioValidacionSeleccionSalon.validacionSeleccionSalon(cantidad, fecha);
    	
    	if(mensajeFinal=="") {
    		LocalDate fechaEvento = LocalDate.parse(fecha);
    		Set<Salon> salones=servicioSalon.buscarSalones(cantidad, fechaEvento);
    		List<Zona> zonas=servicioSalon.traerZonas();
  
    		modelo.put("salones",salones);
    		modelo.put("zonas",zonas);
    		modelo.put("cantidad",cantidad);
    		modelo.put("fecha",fecha);
    		modelo.put("isset","capital");
  
    		return new ModelAndView("/salon", modelo);
    	}
    	else {
            modelo.put("cantidad", cantidad);
            modelo.put("fecha", fecha);
            modelo.put("mensajefecha", mensajeFinal);
            
            return new ModelAndView("salon", modelo);
    	}
    }

    // Validacion de la seleccion del Salon
    @RequestMapping(path="/validar", method = RequestMethod.POST)
    public ModelAndView validar(@ModelAttribute("salon") RegistroSalonViewModel salon,
                                @ModelAttribute("horario") String horario,
                                @ModelAttribute("fecha") String fechaString,
                                @ModelAttribute("cantidad") Integer cantidad,
                                HttpServletRequest request) {//esta en la url
        ModelMap modelo = new ModelMap();
        LocalDate fecha = LocalDate.parse(fechaString);
        String mensaje="";
        Long id=salon.getId();
        
        if(id.equals(0L)){
        	mensaje+="Seleccione un sal�n";
        }
        else {
        	Long idUser= Long.parseLong(request.getSession().getAttribute("logueado").toString());
        	Long idReserva= servicioSalon.hacerReserva(idUser ,salon.getId(),fecha,horario,cantidad);

        	request.getSession().setAttribute("idReserva", idReserva);
        	return new ModelAndView("redirect:/listado-menu");
        }

        
        Set<Salon> salones=servicioSalon.buscarSalones(cantidad, fecha);
        List<Zona> zonas=servicioSalon.traerZonas();

        modelo.put("salones",salones);
        modelo.put("zonas",zonas);
        modelo.put("cantidad",cantidad);
        modelo.put("fecha",fecha);
        modelo.put("isset","capital"); 
        modelo.put("mensaje",mensaje);
        return new ModelAndView("salon", modelo);   
    }


    @RequestMapping(path="/VerImagenes", method = RequestMethod.GET)
    public ModelAndView galeria(@RequestParam(name="id",required=false) Integer id) {//esta en la url
        ModelMap modelo = new ModelMap();

           List<Imagenes> imagenes=servicioSalon.galeria(id);
           modelo.put("imagenes",imagenes);

        return new ModelAndView("/galeria", modelo);
    }

    public ServicioSalon getServicioSalon() {
        return servicioSalon;
    }

    public void setServicioSalon(ServicioSalon servicioSalon) {
        this.servicioSalon = servicioSalon;
    }

    public ServicioEliminoSalon getServicioEliminoSalon() {
        return servicioEliminoSalon;
    }

    public void setServicioEliminoSalon(ServicioEliminoSalon servicioEliminoSalon) {
        this.servicioEliminoSalon = servicioEliminoSalon;
    }

    public ServicioValidacionSeleccionSalon getServicioValidacionSeleccionSalon() {
        return servicioValidacionSeleccionSalon;
    }

    public void setServicioValidacionSeleccionSalon(ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon) {
        this.servicioValidacionSeleccionSalon = servicioValidacionSeleccionSalon;
    }
}