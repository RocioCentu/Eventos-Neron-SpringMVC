package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Salon;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("eliminoSalonDao")
public class EliminoSalonDaoImp implements EliminoSalonDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void eliminarSalon(Salon salon) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(salon);
		
	}

}