package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("registroPlatosMenuDao")
public class RegistroPlatosMenuDaoImpl implements RegistroPlatosMenuDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void registraPlatosAlMenu (Menu menu) {  
		final Session session = sessionFactory.getCurrentSession();  // Obtengo una sesion
		session.save(menu);    // Persisto en la BD, el objeto recibido desde el area de Servicios

	}

	@Override
	public Menu traerMenuPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (Menu) session.createCriteria(Menu.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }
	
	
	
	@Override
	public TipoDeMenu traerTipoDeMenuPorId(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        return (TipoDeMenu) session.createCriteria(TipoDeMenu.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

}
