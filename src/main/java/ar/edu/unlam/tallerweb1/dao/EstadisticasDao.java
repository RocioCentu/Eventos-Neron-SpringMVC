package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import java.util.ArrayList;
import java.util.List;
public interface EstadisticasDao {
    List<Reserva> cantidadDeReservas(Salon salon);
    List<Reserva> Reservas();
}
