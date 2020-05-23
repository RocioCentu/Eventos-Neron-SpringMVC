
package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

// Interface que define los metodos del Servicio de Regisatros de platos del Menu.
public interface ServicioCancelacion {

	Double calcularDevolucion(Reserva reservafinal, List<Double> precios);
	List<Integer> datosDevolucion(Reserva reservafinal);
	void eliminarReserva(Reserva reserva);

}
