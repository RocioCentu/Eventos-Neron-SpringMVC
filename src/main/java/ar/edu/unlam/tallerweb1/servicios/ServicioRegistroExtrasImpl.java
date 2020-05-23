package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroMenuDao;
import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.dao.RegistroExtrasDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Reserva;


@Service("servicioRegistroExtras")
@Transactional
public class ServicioRegistroExtrasImpl implements ServicioRegistroExtras {

	@Inject
	private RegistroExtrasDao registroExtrasDao;
	@Inject
	private RegistroMenuDao registroMenuDao;



	@Override
	public void ingresarExtrasSeleccionados(Long id,Long[] idmenu) {
		Reserva reserva=registroMenuDao.traerReserva(id);
		List<Extra> menuElegido = new ArrayList<>();

		  int arrayLength = idmenu.length;

		    for (int i=0; i<arrayLength; i++) {
		    	Long idm = idmenu[i];
		    	menuElegido.add(registroExtrasDao.traerExtrasPorId(idm));
		    }

		    reserva.setExtra(menuElegido);


		registroMenuDao.registraMenuEnReserva(reserva);
	}

}