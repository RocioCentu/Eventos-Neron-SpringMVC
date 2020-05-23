package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroMenuDao;
import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.dao.ResumenSeleccionDao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;


@Service("servicioResumenSeleccion")
@Transactional
public class ServicioResumenImpl implements ServicioResumen {

	@Inject
	private ResumenSeleccionDao resumenSeleccionDao;

	@Inject
	private ServicioPersonal servicioPersonal;
	

	@Override
	public List<Double> calculaCostoTotal(Reserva reservafinal) {
		List<Double> precios = new ArrayList();
		
		// Obtengo el costo del salon
		Double precioSalon = reservafinal.getSalon().getPrecio();

		
		// Obtengo el costo del menu
		Double precioMenu=0D;
		for (Menu m: reservafinal.getMenu()){
			precioMenu = m.getPrecio() + precioMenu;
		}
		Double precioMenuTotal = precioMenu * reservafinal.getCantidadDeInvitados();
		precios.add(precioMenu);
		precios.add(precioMenuTotal);
		
		// Obtengo el costo de los extras
		Double precioExtra=0D;
		for (Extra e: reservafinal.getExtra()){
			precioExtra = e.getPrecio() + precioExtra;
		}
		precios.add(precioExtra);
		
		
		// Obtengo el costo del personal
		List<Integer> personal = servicioPersonal.calcularPersonal(reservafinal.getCantidadDeInvitados());
		List<Double> sueldoPersonal = servicioPersonal.consultarSueldoPersonal();
		Double precioPersonal=0D;
		
		Iterator<Integer> p = personal.iterator();
		Iterator<Double> sp = sueldoPersonal.iterator();
		
		Integer pers;
		Double sueldoPers;
		
		while (p.hasNext()) {
			pers=p.next();
			sueldoPers=sp.next();
			
			precioPersonal = (pers*sueldoPers) + precioPersonal;
		}
		precios.add(precioPersonal);
		
		
		// Calcula el costo final de la reserva
		Double costoFinal = precioSalon + precioMenuTotal + precioExtra + precioPersonal;
		precios.add(costoFinal);
		
		
		return precios;
	}

	
	@Override
	public Reserva buscarDatos(Long idReserva) {
		return resumenSeleccionDao.obtieneDatosReserva(idReserva);
	}
	
}
