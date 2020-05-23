package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

@Repository("resumenSeleccionDao")
public class ResumenSeleccionDaoImpl implements ResumenSeleccionDao {

	@Inject
    private SessionFactory sessionFactory;

	
	@Override
	public Reserva obtieneDatosReserva(Long idReserva) {
		final Session session = sessionFactory.getCurrentSession();
		return (Reserva) session.createCriteria(Reserva.class)
				.createAlias("salon", "buscaSalon")
				.createAlias("menu", "buscaMenu")
		//		.createAlias("Extra", "buscaExtra")      <---------- FALTA TERMINAR
				.add(Restrictions.eq("id", idReserva))
				.uniqueResult();
	}
}