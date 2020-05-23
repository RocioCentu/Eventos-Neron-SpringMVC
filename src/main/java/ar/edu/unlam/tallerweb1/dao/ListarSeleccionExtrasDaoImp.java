package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("listarSeleccionExtrasDao")
public class ListarSeleccionExtrasDaoImp implements ListarSeleccionExtrasDao {

	@Inject
    private SessionFactory sessionFactory;
	List <Extra> listarSeleccionExtras;
	
	

	@Override
	public List<Extra> listarSeleccionExtras() {
		final Session session = sessionFactory.getCurrentSession();
		listarSeleccionExtras = session.createCriteria(Extra.class).list();
		return (listarSeleccionExtras);
	}

}
