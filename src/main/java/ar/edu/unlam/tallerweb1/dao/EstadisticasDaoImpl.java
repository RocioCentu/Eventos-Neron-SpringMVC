package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Salon;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hibernate.criterion.Restrictions.eq;

@Repository("estadisticasDao")
public class EstadisticasDaoImpl implements EstadisticasDao {

    @Inject
    private SessionFactory sessionFactory;
    @Override
    public List<Reserva> cantidadDeReservas(Salon salon){
        final Session session = sessionFactory.getCurrentSession();
        List<Reserva> reservas=session.createCriteria(Reserva.class)
                .add(eq("salon", salon))
                .list();
        return reservas;
    }
    public List<Reserva> Reservas(){
        final Session session = sessionFactory.getCurrentSession();
        List<Reserva> reservas=session.createCriteria(Reserva.class)

                .list();
        return reservas;
    }
}
