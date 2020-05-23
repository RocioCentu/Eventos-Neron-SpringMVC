package ar.edu.unlam.tallerweb1.controladores;
import java.util.List;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioPersonal;
import ar.edu.unlam.tallerweb1.servicios.ServicioResumen;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;


@Controller
public class ControladorResumen {
	

	@Inject
	private ServicioResumen servicioResumen;
	@Inject
	private ServicioPersonal servicioPersonal;
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Muestra el resumen de toda la reserva       ///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(path = "/resumen-final")
	public ModelAndView resumenFinal ( @ModelAttribute("vm") RegistroMenuViewModel vm, HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);

			// Se asigna el personal a la reserva de acuerdo a la cantidad de personas
			// Se determina las diferentes cantidades de personal por categoria (Mozo, Chef, Cocinero, etc)
			String id=request.getSession().getAttribute("idReserva").toString();
			Long reserva= Long.parseLong(id);
	
			servicioPersonal.asignaPersonalAlEvento(reserva);
		
			//Se muestra un resumen de lo seleccionado
			Reserva reservafinal = servicioResumen.buscarDatos(reserva);	
			List<Double> precios = servicioResumen.calculaCostoTotal(reservafinal);
		
			model.put("reservafinal", reservafinal);
			model.put("menuseleccionado", reservafinal.getMenu());
			model.put("extraseleccionado", reservafinal.getExtra());
			model.put("precios", precios);
		
			return new ModelAndView("resumen-seleccion", model);
		}
	
		return new ModelAndView("redirect:/homeAdmin");

	}
	
}