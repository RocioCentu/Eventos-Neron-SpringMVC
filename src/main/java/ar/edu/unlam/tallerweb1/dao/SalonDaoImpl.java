package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.criterion.Restrictions.*;

@Repository("SalonDao")
public class SalonDaoImpl implements SalonDao {

    @Inject
    private SessionFactory sessionFactory;
    @Override
    public Usuario traerCliente(Long id){
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public Salon verificarSalon(Salon salon){
        final Session session = sessionFactory.getCurrentSession();
        return (Salon) session.createCriteria(Salon.class)
                .add(Restrictions.eq("id", salon.getId()))
                .uniqueResult();
    }


    @Override
    public  List<Salon> buscar(String nombre){
        final Session session = sessionFactory.getCurrentSession();


        List salones= session.createCriteria(Salon.class)
                .add(like("nombre", "%"+nombre+"%"))
                .list();

        return salones;

    }
    @Override
    public Salon galeria(Integer id){
        final Session session = sessionFactory.getCurrentSession();
        return (Salon) session.createCriteria(Salon.class)
                .add(eq("id", id))
                .uniqueResult();
    }
    @Override
    public void guardarReserva(Reserva reserva){
        final Session session = sessionFactory.getCurrentSession();
         session.save(reserva);
    }
    @Override
    public Salon traerSalonPorId(Long id){
        final Session session = sessionFactory.getCurrentSession();


        return (Salon) session.createCriteria(Salon.class)
                .add(eq("id", id))
                .uniqueResult();
    }
    @Override
    public List<Reserva> traerListaDeFechas(Salon salon){
        final Session session = sessionFactory.getCurrentSession();

        List reservas= session.createCriteria(Reserva.class)
                .add(eq("salon", salon))
                .list();

        return reservas;
    }
 //buscar salones por zona
    @Override
    public List<Salon> buscarSalones(Integer cantidad , LocalDate fecha){
        final Session session = sessionFactory.getCurrentSession();


        List salones= session.createCriteria(Salon.class)
                .add(ge("capacidadMaxima", cantidad))
                .createAlias("reserva","reservaBuscada")
               .add(ne(  "reservaBuscada.fecha", fecha))

                .list();

       return salones;
}



@Override
    public  List<Zona> traerZonas(){
    final Session session = sessionFactory.getCurrentSession();
    List zonas= session.createCriteria(Zona.class)
           .list();

    return zonas;

}

// Para el calculo del personal necesario para el evento
@Override
public Reserva cantidadDeInvitadosPorIdReserva(Long idReserva){
    final Session session = sessionFactory.getCurrentSession();

    return (Reserva) session.createCriteria(Reserva.class)
            .add(eq("id", idReserva))
            .uniqueResult();
}

}
