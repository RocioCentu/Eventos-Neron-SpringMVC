package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.CategoriaPersonal;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

// Interface que define los metodos del DAO de Usuarios.
public interface PersonalDao {
	
	Personal buscarPersonalPorId (Long listado);
	
	//Preguntar si no es mas conveniente separar esto en otro DAO (al igual que el servicio)
	void ingresarReserva (Reserva reserva);
	
	List <Reserva> traerReservas();
	
	List <Reserva> traerReservas2();
	
	List <Personal> listadoDelPersonal();
	
	List <CategoriaPersonal> listadoDeCargosDelPersonal();
	
//	List <Personal> traerReservasDesdePersonal();
	
}
