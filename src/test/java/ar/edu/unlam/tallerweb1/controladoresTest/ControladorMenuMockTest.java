package ar.edu.unlam.tallerweb1.controladoresTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorMenu;
import ar.edu.unlam.tallerweb1.dao.ListadoOpcionesMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoOpcionesMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioListarTiposMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones;



public class ControladorMenuMockTest extends SpringTest {

	@Test
	public void testQuePruebaListadoDeOpcionesDeMenu() {

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  PREPARACION   //////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////		
		ControladorMenu miControladorMenu = new ControladorMenu();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession misession= mock(HttpSession.class);
		when(request.getSession()).thenReturn(misession);
		when(request.getSession().getAttribute("ROL")).thenReturn("2");
		when(request.getSession().getAttribute("nombre")).thenReturn("Gallo");
		
	
		ServicioListadoOpcionesMenu servicioListadoOpcionesMenu = mock(ServicioListadoOpcionesMenu.class);
		miControladorMenu.setServicioListadoOpcionesMenu(servicioListadoOpcionesMenu);	
		List<Menu> menu = mock(List.class);
		when(servicioListadoOpcionesMenu.listarOpcionesMenu()).thenReturn(menu);

		ServicioListarTiposMenu servicioListarTiposMenu = mock(ServicioListarTiposMenu.class);
		miControladorMenu.setServicioListarTiposMenu(servicioListarTiposMenu);
		List<TipoDeMenu> tipoMenu = mock(List.class);
		when(servicioListarTiposMenu.listarTipoDeMenus()).thenReturn(tipoMenu);
		
		ServicioRecomendaciones servicioRecomendaciones = mock(ServicioRecomendaciones.class);
		miControladorMenu.setServicioRecomendaciones(servicioRecomendaciones);
		ArrayList<Menu> menu2 = mock(ArrayList.class);
		when(servicioRecomendaciones.ObtenerRecomendacionesMenu()).thenReturn(menu2);
		

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  EJECUCION   ////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////				
		ModelAndView modelAndView = miControladorMenu.listadoDeOpcionesDeMenu(request);


		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  CONSTATACION   /////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		assertThat(modelAndView.getViewName() ).isEqualTo("listado-opciones-menu");
	}

}