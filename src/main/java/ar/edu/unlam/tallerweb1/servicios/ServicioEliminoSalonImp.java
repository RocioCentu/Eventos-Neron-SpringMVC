package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EliminoSalonDao;
import ar.edu.unlam.tallerweb1.dao.IngresoExtraDao;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Salon;



@Service("servicioEliminoSalon")
@Transactional
public class ServicioEliminoSalonImp implements ServicioEliminoSalon {

	@Inject //hace el new de la implementacion. cuando llamo a servicio ingreso usuario dao
	private EliminoSalonDao servicioEliminoSalon; //objeto


	@Override
	public void eliminarSalon(Salon salon) {
		servicioEliminoSalon.eliminarSalon(salon);
		
	}

}