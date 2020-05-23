package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.modelo.Salon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroMenuDao;
import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;


@Service("servicioRegistroMenu")
@Transactional
public class ServicioRegistroMenuImpl implements ServicioRegistroMenu {

	@Inject
	private RegistroPlatosMenuDao registroPlatosMenuDao;
	@Inject
	private RegistroMenuDao registroMenuDao;

	@Override
	public  Double calcularPuntaje(Long id,Double puntaje){
		Menu menu=registroPlatosMenuDao.traerMenuPorId(id);
		Double nuevoPuntaje=0.0;
		Double puntajeActual=menu.getPuntaje();
		if(puntajeActual==null){
			nuevoPuntaje=puntaje;
		}else{
			nuevoPuntaje=(puntajeActual+puntaje)/2;
		}

		menu.setPuntaje(nuevoPuntaje);
		return nuevoPuntaje;
	}

	@Override
	public void ingresarMenuSeleccionado(Long id,Long[] idmenu) {
		Reserva reserva=registroMenuDao.traerReserva(id);
		List<Menu> menuElegido = new ArrayList<>();

		  int arrayLength = idmenu.length;

		    for (int i=0; i<arrayLength; i++) {
		    	Long idm = idmenu[i];
		    	menuElegido.add(registroPlatosMenuDao.traerMenuPorId(idm));
		    }

		reserva.setMenu(menuElegido);

		registroMenuDao.registraMenuEnReserva(reserva);
	}

}
