package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioCancelacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosPendientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioResumen;
import ar.edu.unlam.tallerweb1.validadores.MenuValidar;



@Controller
public class ControladorClienteEventosPendientes {

	
	@Inject
	private ServicioEventosPendientes servicioEventosPendientes;
	@Inject
	private ServicioResumen servicioResumen;
	@Inject
	private ServicioCancelacion servicioCancelacion;
	
	

	//*******************************************************************************************************************	
	// Lado CLIENTE	  ***************************************************************************************************
	//*******************************************************************************************************************	 	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado de eventos pendientes del CLIENTE  ///////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Muestra en un listado de todos los eventos pendientes del Cliente
	@RequestMapping(path = "/eventos-pendientes-cliente")
	public ModelAndView eventosPendientesCliente (HttpServletRequest request) {
		
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			LocalDate fechaActual = LocalDate.now();
			
						
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			Long idUser= Long.parseLong(request.getSession().getAttribute("logueado").toString());
			
			Set <Reserva> listadoEventosCliente = new TreeSet();
			listadoEventosCliente = servicioEventosPendientes.listadoDeEventosPendientesDelCliente(fechaActual, idUser);
		
			model.put("usuario", nombreUsuario);
			model.put("listadoPendientesCliente", listadoEventosCliente);

			return new ModelAndView("eventos-pendientes-cliente", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}
	

// -----------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------
	
	// Muestra los detalles (Seleccion y precios) de una Reserva de todo lo seleccionado (Salon-Menu-Extras)
	@RequestMapping(path = "/listado-eventos-pendientes-cliente", method = RequestMethod.POST)
	public ModelAndView listadoEventosPendientesCliente (@ModelAttribute("idreserva") Long idreserva, HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			model.put("usuario", nombreUsuario);			
			
			if(idreserva==0) {
				String mensaje ="Debe seleccionar una reserva";
				model.put("mensajeerror", mensaje);
				LocalDate fechaActual = LocalDate.now();

				Long idUser= Long.parseLong(request.getSession().getAttribute("logueado").toString());
				
				Set <Reserva> listadoEventosCliente = new TreeSet();
				listadoEventosCliente = servicioEventosPendientes.listadoDeEventosPendientesDelCliente(fechaActual, idUser);
			
				model.put("listadoPendientesCliente", listadoEventosCliente);
				return new ModelAndView("eventos-pendientes-cliente", model);	
			}
			Reserva reservafinal = servicioResumen.buscarDatos(idreserva);	
			List<Double> precios = servicioResumen.calculaCostoTotal(reservafinal);
	
			model.put("reservafinal", reservafinal);
			model.put("menuseleccionado", reservafinal.getMenu());
			model.put("extraseleccionado", reservafinal.getExtra());
			model.put("precios", precios);
			return new ModelAndView("resumen-seleccion", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}

	
	
	
	
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Cancelacion de una Reserva  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Muestra las condiciones de cancelacion y las reservas que aun pueden ser canceladas	
	@RequestMapping(path = "/condiciones-cancelacion-reserva")
	public ModelAndView condicionesCancelacionReserva (HttpServletRequest request) {
		
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			LocalDate fechaActual = LocalDate.now();
			
						
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			Long idUser= Long.parseLong(request.getSession().getAttribute("logueado").toString());
			
			Set <Reserva> listadoEventosCliente = new TreeSet();
			listadoEventosCliente = servicioEventosPendientes.listadoDeEventosPendientesDelCliente(fechaActual, idUser);
		
			model.put("usuario", nombreUsuario);
			model.put("listadoPendientesCliente", listadoEventosCliente);

			return new ModelAndView("condiciones-cancelacion-eventos-cliente", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}
	
	
	// Muestra los detalles (Seleccion y precios) de una Reserva de todo lo seleccionado (Salon-Menu-Extras)
	@RequestMapping(path = "/detalle-cancelacion-reserva-seleccionada", method = RequestMethod.POST)
	public ModelAndView detalleCancelacionReservaSeleccionada (@ModelAttribute("idreserva") Long idreserva, HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			if(idreserva==0) {
				String mensaje ="Debe seleccionar una reserva";
				model.put("mensajeerror", mensaje);
				
				LocalDate fechaActual = LocalDate.now();
				
				// Obtengo datos del usuario logueado
				String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
				Long idUser= Long.parseLong(request.getSession().getAttribute("logueado").toString());
				
				Set <Reserva> listadoEventosCliente = new TreeSet();
				listadoEventosCliente = servicioEventosPendientes.listadoDeEventosPendientesDelCliente(fechaActual, idUser);
			
				model.put("usuario", nombreUsuario);
				model.put("listadoPendientesCliente", listadoEventosCliente);

				return new ModelAndView("condiciones-cancelacion-eventos-cliente", model);
				
			}
						
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);

			Reserva reservafinal = servicioResumen.buscarDatos(idreserva);
			
			// Determina los costos parciales y totales de la reserva para mostrar en la vista
			List<Double> precios = servicioResumen.calculaCostoTotal(reservafinal);
			
			// Determina el monto a devolver
			Double montoADevolver = servicioCancelacion.calcularDevolucion(reservafinal, precios);
			
			// Determina el porcentaje aplicado a la devolucion y la cantidad de dias de la cancelacion con respecto a la fecha del evento
			List<Integer> datosDevolucion = servicioCancelacion.datosDevolucion(reservafinal);
	
			model.put("reservafinal", reservafinal);
			model.put("precios", precios);
			model.put("montoadevolver", montoADevolver);
			model.put("datosdevolucion", datosDevolucion);
	
			return new ModelAndView("detalle-cancelacion", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}

	
	
	// Cancelacion de la reserva
	@RequestMapping(path = "/cancelacion-reserva", method = RequestMethod.POST)
	public ModelAndView cancelacionReserva (@ModelAttribute("idreserva") Long idreserva, HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			// Se elimina la reserva
			Reserva reservafinal = servicioResumen.buscarDatos(idreserva);
			servicioCancelacion.eliminarReserva(reservafinal);
			
			// Mensaje
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			model.put("usuario", nombreUsuario);
			
			return new ModelAndView("cancelacion-reserva", model);
		}
	
		return new ModelAndView("redirect:/homeAdmin");
	}
	
	}