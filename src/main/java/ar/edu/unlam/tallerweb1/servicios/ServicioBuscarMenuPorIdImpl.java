package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;



@Service("servicioBuscarMenuPorId")
@Transactional
public class ServicioBuscarMenuPorIdImpl implements ServicioBuscarMenuPorId {

	@Inject
	private RegistroPlatosMenuDao registroPlatosMenuDao;


	@Override
	public Menu buscaMenuPorId(Long idMenu) {
		return registroPlatosMenuDao.traerMenuPorId(idMenu);
	}

}
