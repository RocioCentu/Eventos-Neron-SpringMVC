package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistroPlatosMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroIngresoMenuViewModel;


@Service("servicioRegistroPlatoMenu")
@Transactional
public class ServicioRegistroPlatoMenuImpl implements ServicioRegistroPlatoMenu {

	@Inject
	private RegistroPlatosMenuDao registroPlatosMenuDao;

	@Override
	public void ingresarPlatosAlMenu(RegistroIngresoMenuViewModel vmIngresoMenu) {
		Menu menu = new Menu ();
		
		menu.setDescripcion(vmIngresoMenu.getDescripcion());
		menu.setPrecio(vmIngresoMenu.getPrecio());
		menu.setTipoDeMenu(registroPlatosMenuDao.traerTipoDeMenuPorId(vmIngresoMenu.getTipoDeMenu()));
		
		registroPlatosMenuDao.registraPlatosAlMenu(menu);
	}

}
