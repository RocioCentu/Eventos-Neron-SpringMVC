package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public interface EventosPendientesDao {

	List <Reserva> traerListaDeFechas (LocalDate fechaActual);
	List <Reserva> traerListaDeEventosPendientesPorCliente (LocalDate fechaActual, Long idUsuario);

}
