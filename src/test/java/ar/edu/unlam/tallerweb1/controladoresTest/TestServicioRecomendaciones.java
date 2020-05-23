package ar.edu.unlam.tallerweb1.controladoresTest;

import ar.edu.unlam.tallerweb1.dao.RecomendacionesDao;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
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
import javax.inject.Inject;

public class TestServicioRecomendaciones extends SpringTest{


  /*  @Test
    @Transactional
    @Rollback(true)
    public void RecomendacionesSalon() {
        ServicioRecomendacionesImpl ServicioRecomendaciones=new ServicioRecomendacionesImpl();
        RecomendacionesDao RecomendacionesDao=mock(ar.edu.unlam.tallerweb1.dao.RecomendacionesDao.class);
        ServicioRecomendaciones.setRecomendacionesDao(RecomendacionesDao);

        List<Salon> salones= new ArrayList<>();
        Salon salon1= new Salon(); salon1.setPuntaje(8.0); salones.add(salon1);//salones obtenidos get(2)
        Salon salon2= new Salon(); salon1.setPuntaje(7.0); salones.add(salon2);
        Salon salon3= new Salon(); salon1.setPuntaje(6.0); salones.add(salon3);
        Salon salon4= new Salon(); salon1.setPuntaje(10.0); salones.add(salon4);//salones obtenidos get(0)
        Salon salon5= new Salon(); salon1.setPuntaje(6.0); salones.add(salon5);
        Salon salon6= new Salon(); salon1.setPuntaje(9.0); salones.add(salon6);//salones obtenidos get(1)

        when(RecomendacionesDao.obtenerSalonesDeLaBase()).thenReturn(salones);
        List<Salon> salonesObtenidos=ServicioRecomendaciones.ObtenerRecomendacionesSalon();

        assertThat(salonesObtenidos.get(0).getPuntaje()).isBetween(8.0,10.0);
        assertThat(salonesObtenidos.get(1).getPuntaje()).isBetween(8.0,10.0);
        assertThat(salonesObtenidos.get(2).getPuntaje()).isBetween(8.0,10.0);

        assertThat(salonesObtenidos.size()).isEqualTo(2);

    }*/

    @Test
    @Transactional
    @Rollback(true)
    public void RecomendacionesSalonSalonesConSinPuntaje() {
        ServicioRecomendacionesImpl ServicioRecomendaciones=new ServicioRecomendacionesImpl();
        RecomendacionesDao RecomendacionesDao=mock(ar.edu.unlam.tallerweb1.dao.RecomendacionesDao.class);
        ServicioRecomendaciones.setRecomendacionesDao(RecomendacionesDao);

        List<Salon> salones= new ArrayList<>();
        Salon salon1= new Salon(); salon1.setPuntaje(8.0); salones.add(salon1);
        Salon salon2= new Salon(); salon2.setPuntaje(6.0); salones.add(salon2);
        Salon salon3= new Salon(); salon3.setPuntaje(null); salones.add(salon3);

        when(RecomendacionesDao.obtenerSalonesDeLaBase()).thenReturn(salones);
        List<Salon> salonesObtenidos=ServicioRecomendaciones.ObtenerRecomendacionesSalon();

        assertThat(salonesObtenidos.get(0).getPuntaje()).isEqualTo(salon1.getPuntaje());
        assertThat(salonesObtenidos.get(1).getPuntaje()).isEqualTo(salon2.getPuntaje());

        assertThat(salonesObtenidos.size()).isEqualTo(2);

    }
    @Test
    @Transactional
    @Rollback(true)
    public void RecomendacionesMenuSinPuntaje() {
        ServicioRecomendacionesImpl ServicioRecomendaciones=new ServicioRecomendacionesImpl();
        RecomendacionesDao RecomendacionesDao=mock(ar.edu.unlam.tallerweb1.dao.RecomendacionesDao.class);
        ServicioRecomendaciones.setRecomendacionesDao(RecomendacionesDao);

        List<Menu> menus= new ArrayList<>();
        Menu menu1= new Menu(); menu1.setPuntaje(8.0); menus.add(menu1);
        Menu menu2= new Menu(); menu2.setPuntaje(null); menus.add(menu2);
        Menu menu3= new Menu(); menu3.setPuntaje(null); menus.add(menu3);

        when(RecomendacionesDao.obtenerMenusDeLaBase()).thenReturn(menus);
        List<Menu> menusObtenidos=ServicioRecomendaciones.ObtenerRecomendacionesMenu();

        assertThat(menusObtenidos.get(0).getPuntaje()).isEqualTo(menu1.getPuntaje());
        assertThat(menusObtenidos.size()).isEqualTo(1);

    }

}
