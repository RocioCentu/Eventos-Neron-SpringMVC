package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.RecomendacionesDao;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.PuntajeMenu;
import ar.edu.unlam.tallerweb1.modelo.PuntajeSalon;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("servicioRecomendaciones")
@Transactional
public class ServicioRecomendacionesImpl implements ServicioRecomendaciones{

    @Inject
    private RecomendacionesDao RecomendacionesDao;
    @Override
//este servicio obtiene todos lo menu de la base y filtra los mejores puntuados para retornarlo en la vista donde
    //el usuario selecciona su menu para la reserva
    public   ArrayList<Menu> ObtenerRecomendacionesMenu(){
        ArrayList<Menu> recomendaciones=new ArrayList<>();
        List<Menu> menus=RecomendacionesDao.obtenerMenusDeLaBase();
        for(Menu menu:menus){
            if(menu.getPuntaje()==(null)){
                menu.setPuntaje(0.0);
            }
        }
        for(int x=0; x<5 ;x++){
            int p=0;
            Double max=menus.get(0).getPuntaje();
            for(int i = 0; i < menus.size(); i++)
            {

                if(menus.get(i).getPuntaje()==null){
                }else{

                    if(max<menus.get(i).getPuntaje())
                    {
                        max=menus.get(i).getPuntaje();
                        p=i;
                    }
                }
            }
            if(menus.get(p).getPuntaje()!= 0.0) {
            recomendaciones.add(menus.get(p));
            menus.remove(p);
            }

        }



        return recomendaciones;
    }


//PARTE DE SALONES
    @Override
    public ArrayList<Salon> ObtenerRecomendacionesSalon(){
        ArrayList<Salon> recomendaciones=new ArrayList<>();
        List<Salon> salones=RecomendacionesDao.obtenerSalonesDeLaBase();

        for(Salon salon:salones){
            if(salon.getPuntaje()==(null)){
                salon.setPuntaje(0.0);
            }
        }

       for(int x=0; x<3 ;x++){
            int p=0;
            Double max=salones.get(0).getPuntaje();
            for(int i = 0; i < salones.size(); i++)
            {



                if(max<salones.get(i).getPuntaje())
                {
                    max=salones.get(i).getPuntaje();
                    p=i;
                }

            }
            if(salones.get(p).getPuntaje()!= 0.0) {
                recomendaciones.add(salones.get(p));
                salones.remove(p);
            }
       }



        return recomendaciones;
    }

    @Override
    public List<Salon>ObtenerTodosLosSalones(){

        return RecomendacionesDao.obtenerSalonesDeLaBase();
    }


    public ar.edu.unlam.tallerweb1.dao.RecomendacionesDao getRecomendacionesDao() {
        return RecomendacionesDao;
    }

    public void setRecomendacionesDao(ar.edu.unlam.tallerweb1.dao.RecomendacionesDao recomendacionesDao) {
        RecomendacionesDao = recomendacionesDao;
    }

}
