package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.dao.EstadisticasDao;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.modelo.Salon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("servicioEstadisticas")
@Transactional
public class ServicioEstadisticasImpl implements ServicioEstadisticas {
@Inject
    private EstadisticasDao estadisticasDao;

@Override
    public Map<Long,Integer> estadisticasSalones(){
    List<Reserva>  reservas= estadisticasDao.Reservas();
    Map<Long,Integer> cantidadDeVeces= new HashMap<>();
    for(int i=0; i< reservas.size();i++){
          Salon salon=reservas.get(i).getSalon();
        List<Reserva> cantidad=estadisticasDao.cantidadDeReservas(salon);
        cantidad.size();
        cantidadDeVeces.put(salon.getId(),cantidad.size());
    }


return cantidadDeVeces;

}
}
