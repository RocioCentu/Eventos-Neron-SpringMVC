package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.servicios.ServicioSalon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import java.util.ArrayList;

@Controller
public class ControladorLogin {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface ServicioLogin, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioLogin servicioLogin;
	@Inject
	private ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones ServicioRecomendaciones;


	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			
		// Guardo datos en la sesion, para luego ser tomados desde distintas partes	
		request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
		request.getSession().setAttribute("logueado", usuarioBuscado.getId().toString());
		request.getSession().setAttribute("nombre", usuarioBuscado.getNombre());
		
	
		    if(usuarioBuscado.getRol().equals("1")){
				return new ModelAndView("redirect:/homeAdmin",model);
			}
				return new ModelAndView("redirect:/home", model);

		}
		else {
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/cerrarsesion ", method = RequestMethod.POST)
	public ModelAndView cerrarsesion(HttpServletRequest request) {
		ModelMap model = new ModelMap();

			request.getSession().removeAttribute("ROL");
			request.getSession().removeAttribute("logueado");
			request.getSession().removeAttribute("nombre");

		return new ModelAndView("redirect:login", model);
	}



	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}

	
// ------------------------------------------------------------------------------------------------------------------------	
// ------------------------------------------------------------------------------------------------------------------------		
	
	
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView ingresarPuntaje(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);
		
			ArrayList<Salon> salones=ServicioRecomendaciones.ObtenerRecomendacionesSalon();
			model.put("salones",salones);
			model.put("tope",salones.size());
			return new ModelAndView("home", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}

	
// ------------------------------------------------------------------------------------------------------------------------	
// ------------------------------------------------------------------------------------------------------------------------		
	
	
	@RequestMapping(path = "/homeAdmin", method = RequestMethod.GET)
	public ModelAndView irAhomeAdmin(HttpServletRequest request) {
		ModelMap model = new ModelMap();

		if(request.getSession().getAttribute("ROL").equals("1")) {
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			
			model.put("usuario", nombreUsuario);
			return new ModelAndView("homeAdmin", model);
		}
		
		// Obtengo datos del usuario logueado
		String nombreUsuario = (request.getSession().getAttribute("nombre").toString());			
		model.put("usuario", nombreUsuario);
		return new ModelAndView("redirect:/home", model);

	}
}		
