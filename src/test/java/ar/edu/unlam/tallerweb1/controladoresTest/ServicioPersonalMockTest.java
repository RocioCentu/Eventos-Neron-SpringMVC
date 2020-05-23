package ar.edu.unlam.tallerweb1.controladoresTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import ar.edu.unlam.tallerweb1.dao.PersonalDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosPendientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoOpcionesMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioListarTiposMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPersonalImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioResumenImpl;



public class ServicioPersonalMockTest extends SpringTest {

	@Test
	public void testQueVerificaControlDeServiciosPrestados() {

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  PREPARACION   //////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		ServicioPersonalImpl miServicioPersonal = new ServicioPersonalImpl();
		List<Reserva> miReserva = new ArrayList();
		PersonalDao miPersonalDao = mock(PersonalDao.class);
		when(miPersonalDao.traerReservas()).thenReturn(miReserva);
		miServicioPersonal.setPersonalDao(miPersonalDao);

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  EJECUCION   ////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////		
		List<Personal> miPersonalMock = miServicioPersonal.controlDeServiciosPrestados();

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//  CONSTATACION   /////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////		
		assertThat(miPersonalMock).isNotNull();
	}
}