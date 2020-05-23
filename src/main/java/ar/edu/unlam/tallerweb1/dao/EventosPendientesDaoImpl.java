package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Repository("eventosPendientesDao")
public class EventosPendientesDaoImpl implements EventosPendientesDao {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<Reserva> traerListaDeFechas(LocalDate fechaActual){
        final Session session = sessionFactory.getCurrentSession();

        List <Reserva> reservas = session.createCriteria(Reserva.class)
        		.add(Restrictions.gt("fecha", fechaActual))
                .list();

        return reservas;
    }

	@Override
	public List<Reserva> traerListaDeEventosPendientesPorCliente(LocalDate fechaActual, Long idUsuario) {
        final Session session = sessionFactory.getCurrentSession();

        List <Reserva> reservasCliente = session.createCriteria(Reserva.class)
        		.add(Restrictions.gt("fecha", fechaActual))
        		.createAlias("usuario", "buscaUsuario")
        		.add(Restrictions.eq("buscaUsuario.id", idUsuario))
                .list();

        return reservasCliente;
	}

}