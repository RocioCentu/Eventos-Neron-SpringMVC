package ar.edu.unlam.tallerweb1.controladoresTest;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorMenu;
import ar.edu.unlam.tallerweb1.controladores.ControladorSalon;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.servicios.*;
import ar.edu.unlam.tallerweb1.validadores.MenuSeleccionValidar;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroSalonViewModel;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
public class TestControllerSalon extends SpringTest{
    //METODO : TOMARDATOS()
    @Test
    @Transactional
    @Rollback(true)
    public void TomarDatos(){
        //El test comprueba la busqueda de salones por cantidad de invitados y por fecha
        //caso : la fecha se ingresa bien y muestra los salones
        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioSalon servicioSalon =mock( ServicioSalon.class);
        ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon =mock( ServicioValidacionSeleccionSalon.class);
        //se los seteo al controlador
        controlador.setServicioSalon(servicioSalon);
        controlador.setServicioValidacionSeleccionSalon(servicioValidacionSeleccionSalon);
        //valores a ingresar
        Integer cantidad=200;
        String fechaString="2019-12-15";
        LocalDate fecha=LocalDate.parse("2019-12-15");
        Set<Salon> salones=new TreeSet<>();
        List<Zona> zonas=new ArrayList<>();
        //hago los when para los servicios truchos
        when(servicioSalon.buscarSalones(cantidad,fecha)).thenReturn(salones);
        when(servicioSalon.traerZonas()).thenReturn( zonas );
        when(servicioValidacionSeleccionSalon.validacionSeleccionSalon(cantidad,fechaString)).thenReturn("");

        //ModelAndView
        ModelAndView modelAndView=controlador.tomarDatos(cantidad, fechaString);
        ModelMap modelo=controlador.tomarDatos(cantidad, fechaString).getModelMap();
        //pruebo el metodo del controllador
        controlador.tomarDatos(cantidad, fechaString);
        assertThat(modelo.get("salones")).isNotNull();
        assertThat(modelo.get("zonas")).isNotNull();
        assertThat(modelo.get("cantidad")).isEqualTo(cantidad);
        assertThat(modelo.get("fecha")).isEqualTo(fechaString);
        assertThat(modelo.get("isset")).isEqualTo("capital");
        assertThat( modelAndView.getViewName()).isEqualTo("/salon");

    }

    @Test
    @Transactional
    @Rollback(true)
    public void TomarDatosFechaYcantidadInvalida(){
        //El test comprueba la busqueda de salones por cantidad de invitados y por fecha
        //caso : la fecha se ingresa mal regrea a la vista salon con un mensaje de error
        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon =mock( ServicioValidacionSeleccionSalon.class);
        //se los seteo al controlador
         controlador.setServicioValidacionSeleccionSalon(servicioValidacionSeleccionSalon);
        //valores a ingresar
        Integer cantidad=-200;
        String fechaString="2019-06-11";
       //hago los when para los servicios truchos

        when(servicioValidacionSeleccionSalon.validacionSeleccionSalon(cantidad,fechaString)).thenReturn("La cantidad ingresada de invitados es incorrecta" + "<br/>"+"Fecha invalida - La fecha seleccionada no puede ser anterior al d�a de hoy");

        //ModelAndView
        ModelAndView modelAndView=controlador.tomarDatos(cantidad, fechaString);
        ModelMap modelo=controlador.tomarDatos(cantidad, fechaString).getModelMap();
        //pruebo el metodo del controllador
        controlador.tomarDatos(cantidad, fechaString);

        assertThat(modelo.get("cantidad")).isEqualTo(cantidad);
        assertThat(modelo.get("fecha")).isEqualTo(fechaString);
        assertThat(modelo.get("mensajefecha")).isEqualTo("La cantidad ingresada de invitados es incorrecta" + "<br/>"+"Fecha invalida - La fecha seleccionada no puede ser anterior al d�a de hoy");
        assertThat( modelAndView.getViewName()).isEqualTo("salon");

    }
    @Test
    @Transactional
    @Rollback(true)
    public void TomarDatosFechaYcantidadVacia(){
        //El test comprueba la busqueda de salones por cantidad de invitados y por fecha
        //caso : la fecha esta vacia regrea a la vista salon con un mensaje de error
        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon =mock( ServicioValidacionSeleccionSalon.class);
        //se los seteo al controlador
        controlador.setServicioValidacionSeleccionSalon(servicioValidacionSeleccionSalon);
        //valores a ingresar
        Integer cantidad=null;
        String fechaString=null;
        //hago los when para los servicios truchos

        when(servicioValidacionSeleccionSalon.validacionSeleccionSalon(cantidad,fechaString)).thenReturn("Debe ingresar la cantidad de invitados" + "<br/>"+"Debe ingresar una fecha" + "<br/>");

        //ModelAndView
        ModelAndView modelAndView=controlador.tomarDatos(cantidad, fechaString);
        ModelMap modelo=controlador.tomarDatos(cantidad, fechaString).getModelMap();
        //pruebo el metodo del controllador
        controlador.tomarDatos(cantidad, fechaString);

        assertThat(modelo.get("cantidad")).isNull();
        assertThat(modelo.get("fecha")).isNull();
        assertThat(modelo.get("mensajefecha")).isEqualTo("Debe ingresar la cantidad de invitados" + "<br/>"+"Debe ingresar una fecha" + "<br/>");
        assertThat( modelAndView.getViewName()).isEqualTo("salon");

    }

    @Test
    @Transactional
    @Rollback(true)
    public void TomarDatosCantidadSuperaElMax(){


        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioValidacionSeleccionSalon servicioValidacionSeleccionSalon =mock( ServicioValidacionSeleccionSalon.class);
        //se los seteo al controlador
        controlador.setServicioValidacionSeleccionSalon(servicioValidacionSeleccionSalon);
        //valores a ingresar
        Integer cantidad=500;
        String fechaString=null;
        //hago los when para los servicios truchos

        when(servicioValidacionSeleccionSalon.validacionSeleccionSalon(cantidad,fechaString)).thenReturn("No disponemos salones para esa capacidad de invitados" + "<br/>");

        //ModelAndView
        ModelAndView modelAndView=controlador.tomarDatos(cantidad, fechaString);
        ModelMap modelo=controlador.tomarDatos(cantidad, fechaString).getModelMap();
        //pruebo el metodo del controllador
        controlador.tomarDatos(cantidad, fechaString);

        assertThat(modelo.get("cantidad")).isEqualTo(cantidad);
        assertThat(modelo.get("fecha")).isEqualTo(fechaString);
        assertThat(modelo.get("mensajefecha")).isEqualTo("No disponemos salones para esa capacidad de invitados" + "<br/>");
        assertThat( modelAndView.getViewName()).isEqualTo("salon");

    }






    // METODO VALIDAR()
    @Test
    @Transactional
    @Rollback(true)
    public void validarBien(){

        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioSalon servicioSalon =mock( ServicioSalon.class);

        //se los seteo al controlador
        controlador.setServicioSalon(servicioSalon);

        //valores a ingresar
        RegistroSalonViewModel salonmv=new RegistroSalonViewModel();
        salonmv.setId(5l);
        String horario="de 9 a ";
        Integer cantidad=200;
        String fechaString="2019-12-15";
        LocalDate fecha=LocalDate.parse("2019-12-15");

        //sesion mock
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(requestMock.getSession().getAttribute("logueado")).thenReturn(1l);
        //when(requestMock.getSession().getAttribute("idReserva")).thenReturn(2l);
        //hago los when para los servicios truchos
        when(servicioSalon.hacerReserva(1l,salonmv.getId(),fecha,horario,cantidad)).thenReturn(2l);
         //ModelAndView
        ModelAndView modelAndView=controlador.validar(salonmv,horario,fechaString,cantidad,requestMock);
       // ModelMap modelo=controlador.validar(salon,horario,fechaString,cantidad,requestMock).getModelMap();
        //pruebo el metodo del controllador
       controlador.validar(salonmv,horario,fechaString,cantidad,requestMock).getModelMap();

        assertThat(requestMock.getSession().getAttribute("logueado")).isEqualTo(1l);
        //assertThat(requestMock.getSession().getAttribute("idReserva")).isEqualTo(2l);
        assertThat( modelAndView.getViewName()).isEqualTo("redirect:/listado-menu");

    }


    @Test
    @Transactional
    @Rollback(true)
    public void ValidarNoSeIngreaNingunSalon(){


        //creo un controller
        ControladorSalon controlador=new ControladorSalon();
        //creo los servicios truchos
        ServicioSalon servicioSalon =mock( ServicioSalon.class);
        //se los seteo al controlador
        controlador.setServicioSalon(servicioSalon);
        //valores a ingresar
        Integer cantidad=500;
        String fechaString="2019-12-15";
        LocalDate fecha=LocalDate.parse(fechaString);
        String horario="de 9 a 15hs";
        Set<Salon> salones=new TreeSet<>();
        List<Zona> zonas=new ArrayList<>();
        RegistroSalonViewModel salonmv=new RegistroSalonViewModel();
        salonmv.setId(0l);
       //sesion mock
        HttpServletRequest requestMock=mock(HttpServletRequest.class);
        HttpSession sessionMock=mock(HttpSession.class);
        when(requestMock.getSession()).thenReturn(sessionMock);
        //hago los when para los servicios truchos
        when(servicioSalon.buscarSalones(cantidad,fecha)).thenReturn(salones);
        when(servicioSalon.traerZonas()).thenReturn( zonas );
        //ModelAndView
        ModelAndView modelAndView=controlador.validar(salonmv,horario,fechaString,cantidad,requestMock);
        ModelMap modelo=controlador.validar(salonmv,horario,fechaString,cantidad,requestMock).getModelMap();
        //pruebo el metodo del controllador
        controlador.validar(salonmv,horario,fechaString,cantidad,requestMock);

        assertThat(modelo.get("isset")).isEqualTo("capital");
        assertThat(modelo.get("salones")).isEqualTo(salones);
        assertThat(modelo.get("zonas")).isEqualTo(zonas);
        assertThat(modelo.get("cantidad")).isEqualTo(cantidad);
        assertThat(modelo.get("fecha")).isEqualTo(fecha);
        assertThat(modelo.get("mensaje")).isEqualTo("Seleccione un sal�n");
        assertThat( modelAndView.getViewName()).isEqualTo("salon");

    }

}
