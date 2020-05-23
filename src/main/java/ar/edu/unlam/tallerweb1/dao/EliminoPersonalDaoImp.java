package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Personal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("eliminoPersonalDao")
public class EliminoPersonalDaoImp implements EliminoPersonalDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void eliminarPersonal(Personal personal) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(personal);
		
	}

}