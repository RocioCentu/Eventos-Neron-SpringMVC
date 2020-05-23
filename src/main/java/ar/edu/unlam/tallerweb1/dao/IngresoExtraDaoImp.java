package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("ingresoExtraDao")
public class IngresoExtraDaoImp implements IngresoExtraDao {

	@Inject
    private SessionFactory sessionFactory;


	@Override
	public void ingresarExtras(Extra extra) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(extra);
	}

}
