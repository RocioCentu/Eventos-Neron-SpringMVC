package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Repository("listadoExtrasDao")
public class ListadoExtrasDaoImp implements ListadoExtrasDao {
	
	@Inject
    private SessionFactory sessionFactory;
	List <Extra> listadoDeExtras;

	@Override
	public List<Extra> listaExtras() {
		final Session session = sessionFactory.getCurrentSession();
		listadoDeExtras = session.createCriteria(Extra.class).list();
		return (listadoDeExtras);
	}



}
