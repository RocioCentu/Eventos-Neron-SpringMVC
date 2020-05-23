package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.ListadoTiposMenuDao;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;


@Service("servicioListarTiposMenu")
@Transactional
public class ServicioListarTiposMenuImpl implements ServicioListarTiposMenu {

	@Inject
	private ListadoTiposMenuDao servicioListadoTiposMenuDao;

	@Override
	public List<TipoDeMenu> listarTipoDeMenus() {
		// Llamo al metodo "listarTiposDeMenus()" de la instancia "servicioListadoTiposMenuDao", que esta en el area del DAO
		// El valor obtenido es retornado
		return servicioListadoTiposMenuDao.listarTiposDeMenus();
	}

}
