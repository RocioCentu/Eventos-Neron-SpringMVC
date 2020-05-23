package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.CategoriaPersonal;
import ar.edu.unlam.tallerweb1.modelo.Personal;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


@Repository("personalDao")
public class PersonalDaoImpl implements PersonalDao {


	@Inject
    private SessionFactory sessionFactory;
	List <Reserva> listaDeReservas;
	List <Personal> personal;
	List <CategoriaPersonal> categoriapersonal;
	
	@Override
	public Personal buscarPersonalPorId(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Personal) session.createCriteria(Personal.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@Override
	public void ingresarReserva(Reserva reserva) {
		
		final Session session = sessionFactory.getCurrentSession();
		session.save(reserva);
	}

	@Override
	public List <Reserva> traerReservas() {
		final Session session = sessionFactory.getCurrentSession();
	
		//	listaDeReservas = session.createCriteria(Evento.class).list();
		return (listaDeReservas) = session.createCriteria(Reserva.class).list();
	}
	
	
	// EN DESARROLLO ///////////////////////////////////////////////////////
	@Override
	public List <Reserva> traerReservas2() {
		final Session session = sessionFactory.getCurrentSession();
	
		//	listaDeReservas = session.createCriteria(Evento.class).list();
		return (listaDeReservas) = session.createCriteria(Reserva.class)
				.createAlias("personal", "buscaPersonal")
				.add(Restrictions.eq("buscaPersonal.nombre", null)).list();
	}

	@Override
	public List <Personal> listadoDelPersonal() {
		
		final Session session = sessionFactory.getCurrentSession();
		return (personal) = session.createCriteria(Personal.class).list();
	}
	
	
	// EN DESARROLLO ///////////////////////////////////////////////////////
	@Override
	public List <CategoriaPersonal> listadoDeCargosDelPersonal() {
		
		final Session session = sessionFactory.getCurrentSession();
		return (categoriapersonal) = session.createCriteria(CategoriaPersonal.class).list();
	}
	

	

//	@Override
//	public List <Personal> traerReservasDesdePersonal() {
//		final Session session = sessionFactory.getCurrentSession();
//	
//		return (personal) = session.createCriteria(Personal.class)
//							.createAlias("reserva", "buscaReserva")
//							.add(Restrictions.eq("id","Cordoba 1345"))
//							.list();
//	}
}
