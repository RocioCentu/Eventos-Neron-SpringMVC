package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ListadoOpcionesMenuDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;

@Service("servicioListadoOpcionesMenu")
@Transactional
public class ServicioListadoOpcionesMenuImpl implements ServicioListadoOpcionesMenu {

	@Inject
	private ListadoOpcionesMenuDao listadoOpcionesMenuDao;


	@Override
	public List<Menu> listarOpcionesMenu() {
		// Llamo al metodo "listadoOpcionesDeMenu()" de la instancia "listadoOpcionesMenuDao", que esta en el area del DAO
		// El valor obtenido es retornado
		return listadoOpcionesMenuDao.listadoOpcionesDeMenu();
	}
}
