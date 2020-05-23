package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("listadoTiposMenuDao")
public class ListadoTiposMenuDaoImpl implements ListadoTiposMenuDao {

	@Inject
    private SessionFactory sessionFactory;
	List <TipoDeMenu> listadoDeTiposDeMenu;
	
	@Override
	public List<TipoDeMenu> listarTiposDeMenus() {
		final Session session = sessionFactory.getCurrentSession(); // Obtengo una sesion
		// A traves de la sesion abierta, consulto en la tabla TipoDeMenu que esta en la BD
		// y guardo el valor obtenido en la coleccion del tipo List llamada "listadoDeTiposDeMenu"
		// para luego retornar el resultado
		listadoDeTiposDeMenu = session.createCriteria(TipoDeMenu.class).list();
		return (listadoDeTiposDeMenu);
	}


}
