package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public interface SalonDao {

    List<Salon> buscarSalones(Integer cantidad ,LocalDate fecha);
   List<Zona> traerZonas();
    Salon traerSalonPorId(Long id);
    List<Reserva> traerListaDeFechas(Salon salon);
    void guardarReserva(Reserva reserva);
   Salon galeria(Integer id);

    List<Salon> buscar(String nombre);

    Salon verificarSalon(Salon salon);
    Usuario traerCliente(Long id);

    Reserva cantidadDeInvitadosPorIdReserva(Long idReserva);
}
