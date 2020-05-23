package ar.edu.unlam.tallerweb1.controladores;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CategoriaPersonal;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.servicios.ServicioEliminoPersonal;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosPendientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioPersonal;
import ar.edu.unlam.tallerweb1.servicios.ServicioPersonalImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSalon;
import ar.edu.unlam.tallerweb1.validadores.MenuSeleccionValidar;
import ar.edu.unlam.tallerweb1.validadores.ReasignaPersonalValidar;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroReasignacionPersonalViewModel;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroSalonViewModel;


@Controller
public class ControladorPersonal {

	@Inject
	private ServicioPersonal servicioPersonal;
	
	@Inject
	private ServicioSalon servicioSalon;
	
	@Inject
	private ServicioEventosPendientes servicioEventosPendientes;
	
	@Inject
	private ServicioEliminoPersonal servicioEliminoPersonal;
	
	private ReasignaPersonalValidar reasignaPersonalValidar = new ReasignaPersonalValidar();
	

	  public void setServicioEventosPendientes(ServicioEventosPendientes servicioEventosPendientes) {
		this.servicioEventosPendientes = servicioEventosPendientes;
	  }

	  public void setServicioPersonal(ServicioPersonal servicioPersonal) {   //JULI
			this.servicioPersonal = servicioPersonal;
		}
	  

	@RequestMapping(path = "/eliminar-personal", method = RequestMethod.POST)
	 	public ModelAndView registroExtras (@ModelAttribute ("Personal") Personal personal,	HttpServletRequest request) {
	 		servicioEliminoPersonal.eliminarPersonal(personal);
	 		return new ModelAndView("redirect:/elimino-personal"); 
	 	}



	
	//********************************************************	
	// Lado ADMININSTRADOR  **********************************
	//********************************************************	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado de eventos pendientes a realizarse  //////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(path = "/listado-eventos-pendientes")
	public ModelAndView listarEventosPendientesDeRealizarse (HttpServletRequest request) {
		ModelMap model = new ModelMap();
		LocalDate fechaActual = LocalDate.now(); //Fecha actual (hoy)
		
		if(request.getSession().getAttribute("ROL").equals("1")) {
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());		
			
			// Se usa TreeSet para pisar los registros duplicados que trae la consultra de la BD
			Set <Reserva> listadoEventos = new TreeSet();
			listadoEventos = servicioEventosPendientes.listadoDeEventosPendientes(fechaActual);
			
			model.put("usuario", nombreUsuario);
			model.put("listadopendientes", listadoEventos);

			return new ModelAndView("eventos-pendientes", model);
		}
		
		return new ModelAndView("redirect:/home");
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado del personal asignado a un evento seleccionado, de los pendientes a realizarse    ////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(path = "/listado-personal-asignado", method = RequestMethod.POST)
	public ModelAndView listarPersonalAsignadoAUnEvento (@ModelAttribute("idreserva") Long idreserva, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		LocalDate fechaActual = LocalDate.now(); //Fecha actual (hoy)

		if(request.getSession().getAttribute("ROL").equals("1")) {
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		
			if(idreserva==0) {
	        	Set <Reserva> listadoEventos = new TreeSet();
				listadoEventos = servicioEventosPendientes.listadoDeEventosPendientes(fechaActual);
				
				model.put("mensajeerror", "Debe seleccionar un evento");
				model.put("usuario", nombreUsuario);
				model.put("listadopendientes", listadoEventos);

				return new ModelAndView("eventos-pendientes", model);	
	        }
			else {

			// Obtengo el listado de los cargos del personal
			List<CategoriaPersonal> cargosPersonal = servicioPersonal.consultaCargosDelPersonal();
			
			model.put("usuario", nombreUsuario);
			model.put("idreserva", idreserva);
			model.put("cargos", cargosPersonal);
			model.put("listadop", servicioPersonal.listadoPersonalAsignado(idreserva));

			return new ModelAndView("personal-asignado", model);
			}
		}
		
		return new ModelAndView("redirect:/home");			
	}		
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// REASIGNACION DEL PERSONAL A CUBRIR UN EVENTO       ///////////////////////////////////////////////////////////////
	// El personal que por diferentes motivos no puede asistir a cubrir el evento, es dado de baja   ////////////////////
	// y reemplazado por otro personal generandose un nuevo listado modificado   ////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(path = "/reasigna-personal", method = RequestMethod.POST)
	public ModelAndView reasignarPersonalAUnEvento (@ModelAttribute("vmReasignaPersonal") RegistroReasignacionPersonalViewModel vmReasignaPersonal, HttpServletRequest request, BindingResult result) {
		ModelMap model = new ModelMap();

		if(request.getSession().getAttribute("ROL").equals("1")) {
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		
			this.reasignaPersonalValidar.validate(vmReasignaPersonal, result);
			
	        if(result.hasErrors()){
			// Obtengo el listado de los cargos del personal
			List<CategoriaPersonal> cargosPersonal = servicioPersonal.consultaCargosDelPersonal();

			model.put("idreserva", vmReasignaPersonal.getIdreserva());
			model.put("cargos", cargosPersonal);
			model.put("listadop", servicioPersonal.listadoPersonalAsignado(vmReasignaPersonal.getIdreserva()));

			return new ModelAndView("personal-reasignado", model);
	        }
	        else {
	        	List<CategoriaPersonal> cargosPersonal = servicioPersonal.consultaCargosDelPersonal();

				model.put("idreserva", vmReasignaPersonal.getIdreserva());
				model.put("cargos", cargosPersonal);
				model.put("listadop", servicioPersonal.reasingacionDelPersonalAUnEvento(vmReasignaPersonal.getIdpersonal(), vmReasignaPersonal.getIdreserva()));

				return new ModelAndView("personal-reasignado", model);
	        }
		}
		
		return new ModelAndView("redirect:/home");	
	}
	
	
	
	
	
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado general de las veces trabajadas por cada empleado   //////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(path = "/trabajo-personal", method = RequestMethod.GET)
	public ModelAndView listarTrabajoPersonal(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		// Obtengo datos del usuario logueado
		String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		model.put("usuario", nombreUsuario);
		
		if(request.getSession().getAttribute("ROL").equals("1")) {

			// Obtengo el listado de assitencia ordenado por Id
			Map<Personal, Integer> listadoAsistencia = new HashMap();
			listadoAsistencia = servicioPersonal.obtencionListadoDeAsistenciasPersonal();
		
			// El Map luego es ordenado de forma ascendente considerando la asistencia del personal ("value" de la coleccion Map)
			Map<Personal, Integer> conteoOrdenadoAscendentementePorAsistencia = servicioPersonal.OrdenaAscendentementePersonal(listadoAsistencia);
			
			// Obtengo el listado de los cargos del personal
			List<CategoriaPersonal> cargosPersonal = servicioPersonal.consultaCargosDelPersonal();

//			model.put("asistencia", conteoOrdenadoAscendentementePorAsistencia);
			model.put("asistencia", conteoOrdenadoAscendentementePorAsistencia);
			model.put("cargos", cargosPersonal);
			
			return new ModelAndView("trabajo-personal", model);
		}

		return new ModelAndView("redirect:/home");
	}
	
	
	
	


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado general del personal  ///////  NO ES DE UTILIDAD PARA LA APLICACION POR AHORA ////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@RequestMapping(path = "/listar-personal", method = RequestMethod.GET)
	public ModelAndView listarPersonalAsignadoAlEvento() {

		ModelMap model = new ModelMap();
		model.put("listado", servicioPersonal.controlDeServiciosPrestados());
		return new ModelAndView("listar-personal", model);
	}


}
