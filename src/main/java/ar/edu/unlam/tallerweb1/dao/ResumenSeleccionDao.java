package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

public interface ResumenSeleccionDao {
	
	Reserva obtieneDatosReserva (Long idReserva);

}
