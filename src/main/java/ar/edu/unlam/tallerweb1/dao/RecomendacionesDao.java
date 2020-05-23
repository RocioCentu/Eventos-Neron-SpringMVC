package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Menu;

public interface RecomendacionesDao {
    List<Reserva> obtenerReservasDeLaBase();

    List<Salon> obtenerSalonesDeLaBase();
    List<Menu> obtenerMenusDeLaBase();
}
