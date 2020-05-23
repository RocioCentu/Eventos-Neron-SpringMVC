package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

public interface IngresoDeMenuDao {
	
	void guardarMenuSeleccionado (Reserva menuSeleccionado);
	Reserva traerReserva(Long id);
	void guardarReserva(Reserva reserva);
}
