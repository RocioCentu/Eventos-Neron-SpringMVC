package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EliminoMenuDao;
import ar.edu.unlam.tallerweb1.dao.IngresoExtraDao;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;



@Service("servicioEliminoMenu")
@Transactional
public class ServicioEliminoMenuImp implements ServicioEliminoMenu {

	@Inject //hace el new de la implementacion. cuando llamo a servicio ingreso usuario dao
	private EliminoMenuDao servicioEliminoMenuDao; //objeto


	@Override
	public void eliminarMenu(Menu menu) {
		servicioEliminoMenuDao.eliminarMenu(menu);
		
	}

}