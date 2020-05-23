package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorMenu;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioEliminoMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioListadoOpcionesMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioListarTiposMenu;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecomendaciones;
import ar.edu.unlam.tallerweb1.validadores.MenuSeleccionValidar;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.controladores.ControladorPuntuarMenu;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestControllerMenu extends SpringTest {
  /*  @Test
    @Transactional
    @Rollback(true)
    public void PonerPuntajeAlMenu(){
        //creo un controller
        ControladorPuntuarMenu controlador=new ControladorPuntuarMenu();
        //creo los servicios truchos
        ServicioListadoOpcionesMenu servicioListadoOpcionesMenu=mock(ServicioListadoOpcionesMenu.class);
        ServicioListarTiposMenu servicioListarTiposMenu=mock(ServicioListarTiposMenu.class);
        //le digo que me va a devolver cada vez q un servicio ejecute un metodo
        List<Menu> listaMenus=new ArrayList<>();
        when( servicioListadoOpcionesMenu.listarOpcionesMenu()).thenReturn(listaMenus);
        List<TipoDeMenu> listaTiposDeMenus=new ArrayList<>();
        when( servicioListarTiposMenu.listarTipoDeMenus()).thenReturn(listaTiposDeMenus);

        controlador.setServicioListarTiposMenu(servicioListarTiposMenu);
        controlador.setServicioListadoOpcionesMenu(servicioListadoOpcionesMenu);
        //ModelAndView
        ModelAndView modelAndView=controlador.puntajesMenu();
        ModelMap modelo=controlador.puntajesMenu().getModelMap();
        //pruebo el metodo del controllador
         assertThat(modelo.size()).isEqualTo(2);
        assertThat( modelAndView.getViewName()).isEqualTo("puntaje-menu");

    }
*/
    @Test
    @Transactional
    @Rollback(true)
    public void IngresarMenu(){
        //FINALIDAD DEL TEST: Comprobar q si el usuario logueado es admin es decir la sesion tiene como
        //atributo rol "1" lo deje ingresar a la vista
        //creo un controller
        ControladorMenu controlador=new ControladorMenu();
        //creo los servicios truchos
        ServicioListarTiposMenu servicioListarTiposMenu=mock(ServicioListarTiposMenu.class);
        //setteo el servicio al controlador
        controlador.setServicioListarTiposMenu(servicioListarTiposMenu);
        List<TipoDeMenu> listaTiposDeMenus=new ArrayList<>();
        when( servicioListarTiposMenu.listarTipoDeMenus()).thenReturn(listaTiposDeMenus);
        //trucheo el request
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getSession().getAttribute("ROL")).thenReturn("1");
        when(requestMock.getSession().getAttribute("nombre")).thenReturn("Martin");
        //llamo al metodo del controlador
        controlador.ingresarMenu(requestMock);
        //ModelAndView
        ModelAndView modelAndView=controlador.ingresarMenu(requestMock);
        ModelMap modelo=controlador.ingresarMenu(requestMock).getModelMap();
        //pruebo el metodo del controllador
        assertThat(requestMock.getSession().getAttribute("nombre")).isEqualTo("Martin");
        assertThat(modelo.get("listatiposmenu")).isEqualTo(listaTiposDeMenus);
        assertThat(modelo.get("usuario")).isEqualTo("Martin");
        assertThat( modelAndView.getViewName()).isEqualTo("ingreso-menu");

    }
    @Test
    @Transactional
    @Rollback(true)
    public void IngresarMenuFallida(){
        //FINALIDAD DEL TEST: Comprobar q si el usuario logueado no es admin es decir la sesion tiene como
        //atributo rol "2" por lo q no lo deja ingresar a la vista para insertar un nuevo menu en la base
        ControladorMenu controlador=new ControladorMenu();

        //trucheo el request
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getSession().getAttribute("ROL")).thenReturn("2");
        //llamo al metodo del controlador
        controlador.ingresarMenu(requestMock);
        //ModelAndView
        ModelAndView modelAndView=controlador.ingresarMenu(requestMock);

        //pruebo el metodo del controllador

        assertThat( modelAndView.getViewName()).isEqualTo("redirect:/home");

    }
    @Test
    @Transactional
    @Rollback(true)
    public void ListarMenu(){
        //FINALIDAD DEL TEST: Comprobar q si el usuario esla logueado para poder elegit un menu
        ControladorMenu controlador=new ControladorMenu();
        //mokeo servicios necesarios
        ServicioListadoOpcionesMenu servicioListadoOpcionesMenu=mock(ServicioListadoOpcionesMenu.class);
        ServicioListarTiposMenu servicioListarTiposMenu=mock(ServicioListarTiposMenu.class);
        ServicioRecomendaciones servicioRecomendaciones=mock(ServicioRecomendaciones.class);
        //setarlos al controllador
        controlador.setServicioListarTiposMenu(servicioListarTiposMenu);
        controlador.setServicioListadoOpcionesMenu(servicioListadoOpcionesMenu);
        controlador.setServicioRecomendaciones(servicioRecomendaciones);
        //los when
        List<Menu> listaMenu=new ArrayList<>();
        when(servicioListadoOpcionesMenu.listarOpcionesMenu()).thenReturn(listaMenu);
        List<TipoDeMenu> listaTipoDeMenu=new ArrayList<>();
        when(servicioListarTiposMenu.listarTipoDeMenus()).thenReturn(listaTipoDeMenu);
        ArrayList<Menu> MenuRecomendados=new ArrayList<>();
        when(servicioRecomendaciones.ObtenerRecomendacionesMenu()).thenReturn(MenuRecomendados);
        //trucheo el request
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getSession().getAttribute("ROL")).thenReturn("2");
        when(requestMock.getSession().getAttribute("nombre")).thenReturn("Ezequiel");
        //llamo al metodo del controlador
        controlador.listadoDeOpcionesDeMenu(requestMock);
        //ModelAndView
        ModelAndView modelAndView=controlador.listadoDeOpcionesDeMenu(requestMock);
        ModelMap modelo=controlador.listadoDeOpcionesDeMenu(requestMock).getModelMap();
        //pruebo el metodo del controllador
        assertThat(modelo.get("usuario")).isEqualTo("Ezequiel");
        assertThat(modelo.get("listaopciones")).isEqualTo(listaMenu);
        assertThat(modelo.get("secciones")).isEqualTo(listaTipoDeMenu);
        assertThat(modelo.get("menus")).isEqualTo(MenuRecomendados);
        assertThat(modelo.get("tope")).isEqualTo(MenuRecomendados.size());
        assertThat( modelAndView.getViewName()).isEqualTo("listado-opciones-menu");

    }
    @Test
    @Transactional
    @Rollback(true)
    public void ListarMenuFallido(){
        //FINALIDAD DEL TEST: Comprobar q si el usuario esla logueado para poder elegit un menu
        ControladorMenu controlador=new ControladorMenu();

        //trucheo el request
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getSession().getAttribute("ROL")).thenReturn("1");


        //ModelAndView
        ModelAndView modelAndView=controlador.listadoDeOpcionesDeMenu(requestMock);
        ModelMap modelo=controlador.listadoDeOpcionesDeMenu(requestMock).getModelMap();
        //pruebo el metodo del controllador

        assertThat( modelAndView.getViewName()).isEqualTo("redirect:/homeAdmin");

    }



}
