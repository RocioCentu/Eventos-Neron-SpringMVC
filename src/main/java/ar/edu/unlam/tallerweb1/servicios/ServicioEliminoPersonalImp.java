package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EliminoMenuDao;
import ar.edu.unlam.tallerweb1.dao.EliminoPersonalDao;
import ar.edu.unlam.tallerweb1.dao.IngresoExtraDao;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Personal;



@Service("servicioEliminoPersonal")
@Transactional
public class ServicioEliminoPersonalImp implements ServicioEliminoPersonal {

	@Inject //hace el new de la implementacion. cuando llamo a servicio ingreso usuario dao
	private EliminoPersonalDao servicioEliminoPersonalDao; //objeto


	@Override
	public void eliminarPersonal(Personal personal) {
		servicioEliminoPersonalDao.eliminarPersonal(personal);
		
	}

}