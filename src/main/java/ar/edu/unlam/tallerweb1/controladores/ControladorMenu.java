package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.servicios.*;
import ar.edu.unlam.tallerweb1.validadores.MenuSeleccionValidar;
import ar.edu.unlam.tallerweb1.validadores.MenuValidar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.viewmodel.RegistroIngresoMenuViewModel;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;


@Controller
public class ControladorMenu {

	@Inject
	private ServicioRecomendaciones ServicioRecomendaciones;
	@Inject
	private ServicioListarTiposMenu servicioListarTiposMenu;
	@Inject
	private ServicioRegistroPlatoMenu servicioRegistroPlatoMenu;
	@Inject
	private ServicioListadoOpcionesMenu servicioListadoOpcionesMenu;
	@Inject
	private ServicioRegistroMenu servicioRegistroMenu;
	@Inject
	private ServicioValidacionSeleccionMenu servicioValidacionSeleccionMenu;
	@Inject
	private ServicioEliminoMenu servicioEliminoMenu;
	
	private MenuValidar menuValidar = new MenuValidar();

	private MenuSeleccionValidar menuSeleccionValidar = new MenuSeleccionValidar();

	
	
	public void setServicioListarTiposMenu(ServicioListarTiposMenu servicioListarTiposMenu) {
		this.servicioListarTiposMenu = servicioListarTiposMenu;
	}

	public void setServicioListadoOpcionesMenu(ServicioListadoOpcionesMenu servicioListadoOpcionesMenu) {
		this.servicioListadoOpcionesMenu = servicioListadoOpcionesMenu;
	}



	
	
	
	@RequestMapping(path = "/elimino-menu", method = RequestMethod.POST)
	public ModelAndView registroExtras (@ModelAttribute ("Menu") Menu menu,	HttpServletRequest request) {
		servicioEliminoMenu.eliminarMenu(menu);
		return new ModelAndView("redirect:/abmMenu"); 
	}	
	
	
	//********************************************************	
	// Lado ADMININSTRADOR  **********************************
	//********************************************************

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Ingreso de los diferentes platos que integran el Menu o Carta  //////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Ingreso de los datos correspondientes a cada palto del menu
	@RequestMapping(path = "/ingresar-menu", method = RequestMethod.GET)
	public ModelAndView ingresarMenu(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("1")) {
			ModelMap model = new ModelMap();
			
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		
			// Llamo al metodo "listarTiposDeMenus()" de la instancia "servicioListarTiposMenu", que esta en el area de Servicios.
		    // El valor obtenido es agregado como "value" en el model(KEY/VALUE) a traves del .put
			// Para luego pasarlo a la vista a traves del return ModelAndView
			model.put("usuario", nombreUsuario);
			model.put("listatiposmenu", servicioListarTiposMenu.listarTipoDeMenus()); //Se pasa un listado de los tipos de menu
			return new ModelAndView("ingreso-menu", model);
		}
			return new ModelAndView("redirect:/home");

	}
	
	// Validacion y posterior persistencia de los datos ingresados (platos/bebidas/postres que compondran las opciones a elegir por parte del cliente)
	@RequestMapping(path = "/registro-plato-menu", method = RequestMethod.POST)
	public ModelAndView registroPlatosMenu2 (@ModelAttribute ("vmIngresoMenu") RegistroIngresoMenuViewModel vmIngresoMenu, BindingResult result, HttpServletRequest request) { //, SessionStatus status
		ModelMap model = new ModelMap();
		// Obtengo datos del usuario logueado
		String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
		
		if(request.getSession().getAttribute("ROL").equals("1")) {
			model.put("usuario", nombreUsuario);		
			model.put("listatiposmenu", servicioListarTiposMenu.listarTipoDeMenus());
		
			this.menuValidar.validate(vmIngresoMenu, result);
			if(result.hasErrors()){
				//Volvemos al formulario porque los datos ingresados por el usuario no son correctos
				model.put("descripcion", vmIngresoMenu.getDescripcion());
				model.put("precio", vmIngresoMenu.getPrecio());
				return new ModelAndView("ingreso-menu", model); // Retorna a la vista del formulario (ingreso menu)
			}
			else{
				servicioRegistroPlatoMenu.ingresarPlatosAlMenu(vmIngresoMenu);   // Paso los parametros que recibo desde el formulario a traves del ModelAttribute, al area de Servicios desde donde se maneja la logica
				return new ModelAndView("ingreso-menu", model); // Retorna a la vista del formulario (ingreso menu)
			}
		}
			
		model.put("usuario", nombreUsuario);
		return new ModelAndView("redirect:/home", model);
	}
	
	
	//HAY QUE HACERLO
    /*
	@RequestMapping(path = "/listado-final-salon", method = RequestMethod.GET)
	public ModelAndView listadoExtras (HttpServletRequest request) {
	//	if(request.getSession().getAttribute("ROL").equals("1")) {
			ModelMap modelo = new ModelMap();
			// modelo.put("listadoFinal", servicioListarExtras.listarExtras());
			return new ModelAndView("listado-final-salon", modelo);
	//	}
	//		return new ModelAndView("redirect:/home");

	}*/
	
	
	
	
	//********************************************************	
	// Lado CLIENTE	  ****************************************
	//********************************************************	

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Seleccion del Menu  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// FORMA PARTE DE LA SELECCION DE LA RESERVA
	// Muestra el listado de los diferentes platos/bebidas/postres que componen el menu, para que el cliente seleccione entre ellos
	@RequestMapping(path = "/listado-menu", method = RequestMethod.GET)
	public ModelAndView listadoDeOpcionesDeMenu (HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("2")) {
			ModelMap model = new ModelMap();
			// Obtengo datos del usuario logueado
			String nombreUsuario = (request.getSession().getAttribute("nombre").toString());
			model.put("usuario", nombreUsuario);
			
			// Llamo al metodo "listarOpcionesMenu()" de la instancia "servicioListarPersonas", que esta en el area de Servicios.
			// El valor obtenido es agregado como "value" en el model(KEY/VALUE) a traves del .put
			// Para luego pasarlo a la vista a traves del return ModelAndView
			model.put("listaopciones", servicioListadoOpcionesMenu.listarOpcionesMenu());
			model.put("secciones", servicioListarTiposMenu.listarTipoDeMenus());

			// parte de las recomendaciones de menu
			// obtengo una lista con los menus recomendados , estos menus estan agrupados segun la reserva
			// por eso tengo una lista de menu dentro de otra lista
			ArrayList<Menu> menus=ServicioRecomendaciones.ObtenerRecomendacionesMenu();
			model.put("menus",menus);
			model.put("tope",menus.size());

			return new ModelAndView("listado-opciones-menu", model);
		}
		
		return new ModelAndView("redirect:/homeAdmin");
	}


	
// -------------------------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------------------------	
	
	
	// Recibo los datos del formulario - Realiza la validacion y persistencia de los datos
	@RequestMapping(path = "/registra-reserva-menu", method = RequestMethod.POST)

	public ModelAndView registraReservaMenu (@ModelAttribute("vm") RegistroMenuViewModel vm, HttpServletRequest request, BindingResult result) {
		String id=request.getSession().getAttribute("idReserva").toString();
		Long reserva= Long.parseLong(id);
		ModelMap model = new ModelMap();


		this.menuSeleccionValidar.validate(vm, result);
		
        if(result.hasErrors()){
        	//////////////////////////////////////////////////////////////////////////////////////////////////
            // Volvemos al formulario porque NO se realizo ninguna seleccion
        	//////////////////////////////////////////////////////////////////////////////////////////////////
        	
        	// Llamo al metodo "listarOPcionesMenu()" de la instancia "servicioListarPersonas", que esta en el area de Servicios.
    		// El valor obtenido es agregado como "value" en el model(KEY/VALUE) a traves del .put
    		// Para luego pasarlo a la vista a traves del return ModelAndView
    		model.put("listaopciones", servicioListadoOpcionesMenu.listarOpcionesMenu());
    		model.put("secciones", servicioListarTiposMenu.listarTipoDeMenus());

    		//parte de las recomendaciones de menu
    		//obtengo una lista con los menus recomendados , estos menus estan agrupados segun la reserva
    		//por eso tengo una lista de menu dentro de otra lista

    			ArrayList<Menu> menus=ServicioRecomendaciones.ObtenerRecomendacionesMenu();
    			model.put("menus",menus);
    			model.put("tope",menus.size());

    		return new ModelAndView("listado-opciones-menu", model);
        }
        else{
		
        	String mensajeFinal = servicioValidacionSeleccionMenu.validacionSeleccionMenu(vm);
		
        	if(mensajeFinal == "") {
        		//////////////////////////////////////////////////////////////////////////////////////////////////
        		// Se persisten los datos y se pasa a la siguiente vista
        		//////////////////////////////////////////////////////////////////////////////////////////////////
        		servicioRegistroMenu.ingresarMenuSeleccionado(reserva,vm.getIdmenu());
        		return new ModelAndView("redirect:/listado-extra");			
        	}
        		else {
        			//////////////////////////////////////////////////////////////////////////////////////////////////
        			// Volvemos al formulario porque los datos ingresados por el usuario no son correctos
        			//////////////////////////////////////////////////////////////////////////////////////////////////
        	
        			// Llamo al metodo "listarOPcionesMenu()" de la instancia "servicioListarPersonas", que esta en el area de Servicios.
        			// El valor obtenido es agregado como "value" en el model(KEY/VALUE) a traves del .put
        			// Para luego pasarlo a la vista a traves del return ModelAndView
        			model.put("listaopciones", servicioListadoOpcionesMenu.listarOpcionesMenu());
        			model.put("secciones", servicioListarTiposMenu.listarTipoDeMenus());
        			model.put("mensajeerror", mensajeFinal);
        			model.put("datos", vm);

        			//parte de las recomendaciones de menu
        			//obtengo una lista con los menus recomendados , estos menus estan agrupados segun la reserva
        			//por eso tengo una lista de menu dentro de otra lista

        			ArrayList<Menu> menus=ServicioRecomendaciones.ObtenerRecomendacionesMenu();
        			model.put("menus",menus);
        			model.put("tope",menus.size());

        			return new ModelAndView("listado-opciones-menu", model);			
        	}
        }
	}

	public void setServicioRecomendaciones(ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones servicioRecomendaciones) {
		ServicioRecomendaciones = servicioRecomendaciones;
	}

	public void setServicioRegistroPlatoMenu(ServicioRegistroPlatoMenu servicioRegistroPlatoMenu) {
		this.servicioRegistroPlatoMenu = servicioRegistroPlatoMenu;
	}

	public void setServicioRegistroMenu(ServicioRegistroMenu servicioRegistroMenu) {
		this.servicioRegistroMenu = servicioRegistroMenu;
	}

	public void setServicioValidacionSeleccionMenu(ServicioValidacionSeleccionMenu servicioValidacionSeleccionMenu) {
		this.servicioValidacionSeleccionMenu = servicioValidacionSeleccionMenu;
	}

	public void setServicioEliminoMenu(ServicioEliminoMenu servicioEliminoMenu) {
		this.servicioEliminoMenu = servicioEliminoMenu;
	}

	public void setMenuValidar(MenuValidar menuValidar) {
		this.menuValidar = menuValidar;
	}

	public void setMenuSeleccionValidar(MenuSeleccionValidar menuSeleccionValidar) {
		this.menuSeleccionValidar = menuSeleccionValidar;
	}
}