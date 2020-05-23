package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.CancelacionReservaDao;
import ar.edu.unlam.tallerweb1.modelo.Reserva;


@Service("servicioCancelacion")
@Transactional
public class ServicioCancelacionImpl implements ServicioCancelacion {

	@Inject
	private CancelacionReservaDao cancelacionReservaDao;


	@Override
	public Double calcularDevolucion(Reserva reservafinal, List<Double> precios) {
		Integer primerLimite = 30, segundoLimite = 15, tercerLimite = 5;
		Integer primerPorcentajeDevolucion = 20, segundoPorcentajeDevolucion = 10, tercerPorcentajeDevolucion = 5;
		Double reintegro;
		
		LocalDate fechaEvento = reservafinal.getFecha();
		LocalDate fechaActual = LocalDate.now();
		
        Integer diasDeDiferencia = Period.between(fechaActual, fechaEvento).getDays();

		if(diasDeDiferencia >= primerLimite) {
			 reintegro = (precios.get(4)*primerPorcentajeDevolucion)/100;
		}
		else if(diasDeDiferencia >= segundoLimite){
			reintegro = (precios.get(4)*segundoPorcentajeDevolucion)/100;
		}
		else if(diasDeDiferencia >= tercerLimite) {
			reintegro = (precios.get(4)*tercerPorcentajeDevolucion)/100;
		}
		else {
			reintegro=0D;
		}

		return reintegro;
	}


	@Override
	public List<Integer> datosDevolucion(Reserva reservafinal) {
		Integer primerLimite = 30, segundoLimite = 15, tercerLimite = 5;
		Integer primerPorcentajeDevolucion = 20, segundoPorcentajeDevolucion = 10, tercerPorcentajeDevolucion = 5;
		List<Integer> datos = new ArrayList();
		LocalDate fechaEvento = reservafinal.getFecha();
		LocalDate fechaActual = LocalDate.now();
		
        Integer diasDeDiferencia = Period.between(fechaActual, fechaEvento).getDays();

		if(diasDeDiferencia >= primerLimite) {
			 datos.add(diasDeDiferencia);
			 datos.add(primerPorcentajeDevolucion);
		}
		else if(diasDeDiferencia >= segundoLimite){
			 datos.add(diasDeDiferencia);
			 datos.add(segundoPorcentajeDevolucion);
		}
		else if(diasDeDiferencia >= tercerLimite) {
			 datos.add(diasDeDiferencia);
			 datos.add(tercerPorcentajeDevolucion);
		}
		else {
			 datos.add(diasDeDiferencia);
			 datos.add(0);
		}

		return datos;
	}


	@Override
	public void eliminarReserva(Reserva reserva) {
		cancelacionReservaDao.eliminarReserva(reserva);
	}
	
}
