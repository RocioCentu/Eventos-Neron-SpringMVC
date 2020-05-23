package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

import ar.edu.unlam.tallerweb1.modelo.Salon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("ingresoDeMenuDao")
public class IngresoDeMenuDaoImpl implements IngresoDeMenuDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarMenuSeleccionado(Reserva menuSeleccionado) {
		final Session session = sessionFactory.getCurrentSession(); 
		session.save(menuSeleccionado);
	}
	@Override
	public Reserva traerReserva(Long id){

		final Session session = sessionFactory.getCurrentSession();
		return (Reserva) session.createCriteria(Reserva.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Override
	public void guardarReserva(Reserva reserva){
		final Session session = sessionFactory.getCurrentSession();
		session.save(reserva);
	}

}