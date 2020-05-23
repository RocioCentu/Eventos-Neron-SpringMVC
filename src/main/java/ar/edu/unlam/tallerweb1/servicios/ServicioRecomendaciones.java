package ar.edu.unlam.tallerweb1.servicios;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Salon;

import java.util.ArrayList;
import java.util.List;

public interface ServicioRecomendaciones {

    ArrayList<Menu> ObtenerRecomendacionesMenu();


    ArrayList<Salon> ObtenerRecomendacionesSalon();
    List<Salon>ObtenerTodosLosSalones();
}
