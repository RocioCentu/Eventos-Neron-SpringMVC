package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;


public interface RegistroMenuDao {

	void registraMenuEnReserva (Reserva reservaMenu);
	Reserva traerReserva(Long id);
}
