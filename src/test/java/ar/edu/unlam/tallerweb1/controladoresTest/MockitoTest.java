package ar.edu.unlam.tallerweb1.controladoresTest;
import static org.assertj.core.api.Assertions.assertThat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.controladores.ControladorPersonal;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioEventosPendientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioPersonal;

public class MockitoTest {
	

@SuppressWarnings(value = { })          /*@ SuppressWarnings ("sin marcar") 
le dice al compilador que el programador cree que el código es seguro 
y que no causará excepciones inesperadas.*/
@Test
@Transactional 
@Rollback(true)
public void ObtenerEventosPendientes() {
	
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpSession sessionMock = mock(HttpSession.class);
	
	LocalDate fechaActual = null ;
	
	ServicioEventosPendientes servicioEventosPendientesmock = mock(ServicioEventosPendientes.class);
	Set <Reserva> listadoDeEventosPendientes=mock(Set.class);
	ControladorPersonal controladorPersonal = new ControladorPersonal();
	
	controladorPersonal.setServicioEventosPendientes(servicioEventosPendientesmock);
	
	
	when(request.getSession()).thenReturn(sessionMock);
	when(request.getSession().getAttribute("ROL")).thenReturn("1");
	when (servicioEventosPendientesmock.listadoDeEventosPendientes(fechaActual)).thenReturn(listadoDeEventosPendientes);
	when(request.getSession().getAttribute("nombre")).thenReturn("Gallo");
	
	
	ModelAndView modelandview = controladorPersonal.listarEventosPendientesDeRealizarse(request);
	assertThat(modelandview.getModelMap().get("fechaActual")).isEqualTo(fechaActual);
	assertThat(modelandview.getViewName()).isEqualTo("eventos-pendientes");
	
}
@SuppressWarnings(value = { })          /*@ SuppressWarnings ("sin marcar") 
le dice al compilador que el programador cree que el código es seguro 
y que no causará excepciones inesperadas.*/
@Test
@Transactional 
@Rollback(true)

public void ObtenerPersonalAsignadoAUnEvento() {
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpSession sessionMock = mock(HttpSession.class);
	
	Long idreserva = null;
	
	ServicioPersonal servicioPersonalmock = mock(ServicioPersonal.class);
	@SuppressWarnings("unchecked")
	List<Personal> listadoPersonalAsignado=mock(List.class);
	ControladorPersonal controladorPersonal = new ControladorPersonal();
	
	controladorPersonal.setServicioPersonal(servicioPersonalmock);
	
//	Reserva reservaMock = mock(Reserva.class);
//	
	
	
	when(request.getSession()).thenReturn(sessionMock);
	when(request.getSession().getAttribute("ROL")).thenReturn("1");
	when(servicioPersonalmock.listadoPersonalAsignado(idreserva)).thenReturn(listadoPersonalAsignado);
	when(request.getSession().getAttribute("nombre")).thenReturn("Gallo");
	
	
	ModelAndView modelandview = controladorPersonal.listarPersonalAsignadoAlEvento();
	assertThat(modelandview.getModelMap().get("idreserva")).isEqualTo(idreserva);
	assertThat(modelandview.getViewName()).isEqualTo("listar-personal");
}
}