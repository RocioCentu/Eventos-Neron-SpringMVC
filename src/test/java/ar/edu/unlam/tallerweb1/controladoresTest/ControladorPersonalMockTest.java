package ar.edu.unlam.tallerweb1.controladoresTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorMenu;
import ar.edu.unlam.tallerweb1.controladores.ControladorPersonal;
import ar.edu.unlam.tallerweb1.dao.ListadoOpcionesMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosPendientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoOpcionesMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioListarTiposMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;



public class ControladorPersonalMockTest extends SpringTest {

	@Test
	public void testQuePruebaListarEventosPendientesDeRealizarse() {

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  PREPARACION   //////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		ControladorPersonal miControladorPersonal = new ControladorPersonal();	
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession misession= mock(HttpSession.class);
		when(request.getSession()).thenReturn(misession);
		when(request.getSession().getAttribute("ROL")).thenReturn("1");
		when(request.getSession().getAttribute("nombre")).thenReturn("Gallo");
		
		ServicioEventosPendientes servicioEventosPendientes = mock(ServicioEventosPendientes.class);
		miControladorPersonal.setServicioEventosPendientes(servicioEventosPendientes);
		LocalDate fechaActual = LocalDate.now();
		Set <Reserva> listadoDeEventosPendientes = mock(Set.class);
		when(servicioEventosPendientes.listadoDeEventosPendientes(fechaActual)).thenReturn(listadoDeEventosPendientes);

		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  EJECUCION   ////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////		
		ModelAndView modelAndView = miControladorPersonal.listarEventosPendientesDeRealizarse(request);

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  CONSTATACION   /////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////		
		assertThat(modelAndView.getViewName() ).isEqualTo("eventos-pendientes");
	}
}