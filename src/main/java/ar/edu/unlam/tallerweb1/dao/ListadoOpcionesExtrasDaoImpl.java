package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Extra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("listadoOpcionesExtrasDao")
public class ListadoOpcionesExtrasDaoImpl implements ListadoOpcionesExtrasDao {
	
	@Inject
    private SessionFactory sessionFactory;
	List <Extra> listadoDeOpcionesDeExtras;
	
	
	@Override
	public List<Extra> listadoOpcionesDeExtras () {
		final Session session = sessionFactory.getCurrentSession(); // Obtengo una sesion
		// A traves de la sesion abierta, consulto en la tabla Menu que esta en la BD
		// y guardo el valor obtenido en la coleccion del tipo List llamada "listadoDeOpcionesDeMenu"
		// para luego retornar el resultado
		listadoDeOpcionesDeExtras = session.createCriteria(Extra.class).list();
		return (listadoDeOpcionesDeExtras);
	}

}
