package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IngresoExtraDao;

import ar.edu.unlam.tallerweb1.modelo.Extra;



@Service("servicioIngresoExtras")
@Transactional
public class ServicioIngresoExtrasImp implements ServicioIngresoExtras {

	@Inject //hace el new de la implementacion. cuando llamo a servicio ingreso usuario dao
	private IngresoExtraDao servicioIngresoExtraDao; //objeto

	@Override
	public void ingresarExtras(Extra extra) {
		servicioIngresoExtraDao.ingresarExtras(extra);
		
	}

}
