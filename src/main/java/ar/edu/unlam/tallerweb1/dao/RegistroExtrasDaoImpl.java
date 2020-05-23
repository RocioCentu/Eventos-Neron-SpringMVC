package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("registroExtrasDao")
public class RegistroExtrasDaoImpl implements RegistroExtrasDao {

	@Inject
    private SessionFactory sessionFactory;


	@Override
	public Extra traerExtrasPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Extra) session.createCriteria(Extra.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

}