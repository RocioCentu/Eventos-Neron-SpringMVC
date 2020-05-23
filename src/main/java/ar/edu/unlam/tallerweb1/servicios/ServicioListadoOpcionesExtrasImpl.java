package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ListadoOpcionesMenuDao;
import ar.edu.unlam.tallerweb1.dao.ListadoOpcionesExtrasDao;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Extra;

@Service("servicioListadoOpcionesExtras")
@Transactional
public class ServicioListadoOpcionesExtrasImpl implements ServicioListadoOpcionesExtras {

	@Inject
	private ListadoOpcionesExtrasDao listadoOpcionesExtrasDao;


	@Override
	public List<Extra> listarOpcionesDeExtras() {
		// Llamo al metodo "listadoOpcionesDeMenu()" de la instancia "listadoOpcionesMenuDao", que esta en el area del DAO
		// El valor obtenido es retornado
		return listadoOpcionesExtrasDao.listadoOpcionesDeExtras();
	}
}
