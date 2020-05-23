package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("eliminoMenuDao")
public class EliminoMenuDaoImp implements EliminoMenuDao {

	@Inject
    private SessionFactory sessionFactory;


	@Override
	public void eliminarMenu(Menu menu) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(menu);
		
	}

}