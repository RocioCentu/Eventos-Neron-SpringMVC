package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EventosPendientesDao;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;


@Service("servicioEventosPendientes")
@Transactional
public class ServicioEventosPendientesImpl implements ServicioEventosPendientes {

	@Inject
	private EventosPendientesDao eventosPendientesDao;
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// LADO ADMINISTRADOR
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado de eventos pendientes de todos los clientes - Lado Administrador   ////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set <Reserva> listadoDeEventosPendientes(LocalDate fechaActual) {
		Set <Reserva> listadoDeEventosSinDuplicados = new TreeSet();
		
		List <Reserva> listadoEventos = eventosPendientesDao.traerListaDeFechas(fechaActual);

		Iterator <Reserva> r = listadoEventos.iterator();
		Reserva reserva;
		while (r.hasNext()) {
			reserva=r.next();
			listadoDeEventosSinDuplicados.add(reserva);
		}
		
		return listadoDeEventosSinDuplicados;
	}


// ----------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// LADO CLIENTE
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Listado de eventos pendientes del CLIENTE    //////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Set<Reserva> listadoDeEventosPendientesDelCliente(LocalDate fechaActual, Long idUsuario) {
		Set <Reserva> listadoDeEventosClienteDuplicados = new TreeSet();
		
		List <Reserva> listadoEventosDelCliente = eventosPendientesDao.traerListaDeEventosPendientesPorCliente(fechaActual, idUsuario);
		
		Iterator <Reserva> r = listadoEventosDelCliente.iterator();
		Reserva reserva;
		while (r.hasNext()) {
			reserva=r.next();
			listadoDeEventosClienteDuplicados.add(reserva);
		}
		return listadoDeEventosClienteDuplicados;
	}
	
}
