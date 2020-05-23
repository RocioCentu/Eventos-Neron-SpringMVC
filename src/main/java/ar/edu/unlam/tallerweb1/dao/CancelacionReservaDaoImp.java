package ar.edu.unlam.tallerweb1.dao;
import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.Reserva;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

@Repository("cancelacionReservaDao")
public class CancelacionReservaDaoImp implements CancelacionReservaDao {

	@Inject
    private SessionFactory sessionFactory;


	@Override
	public void eliminarReserva(Reserva reserva) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(reserva);		
	}

}