package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unlam.tallerweb1.modelo.CategoriaPersonal;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroReasignacionPersonalViewModel;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioPersonal {

	Map <Long, Integer> obtencionListadoDeAsistencias();

	List <Personal> controlDeServiciosPrestados ();

	Map OrdenaAscendentemente(Map unsortMap);

	Map OrdenaAscendentementePersonal(Map unsortMap);

	List <Integer> calcularPersonal(Integer cantidadDeInvitados);
	
	List <Double> consultarSueldoPersonal();
	
	List <Long> asignarPersonalNecesario(List <Integer> personalNecesario, Map <Long,Integer> conteoOrdenadoAscendentementePorAsistencia);

	List <Personal> listadoPersonalAsignado(List <Long> listado);

	void persisteElListadoDePersonalAsignado (Reserva reserva);
	
	List<CategoriaPersonal> consultaCargosDelPersonal ();
	
	List <Personal> listadoPersonalAsignado (Long idReserva);
	
	void asignaPersonalAlEvento(Long idReserva);
	
	List <Integer> determinaCategoriaYCantidadDelPersonalDadoDeBajaAUnEvento (List<Long> personalDadoDeBajaAlEvento);
	
	List <Personal> reasingacionDelPersonalAUnEvento (List<Long> personalDadoDeBajaAlEvento, Long idReserva);

	Map <Personal, Integer> obtencionListadoDeAsistenciasPersonal();
	
	
	// nuevo
	List<Reserva> listadoDeReservas();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	List<Personal> sinPersonal();

}
