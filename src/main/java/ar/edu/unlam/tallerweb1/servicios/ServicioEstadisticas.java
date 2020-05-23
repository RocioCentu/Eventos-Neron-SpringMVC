package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Salon;

import java.util.List;
import java.util.Map;

public interface ServicioEstadisticas {
    Map<Long,Integer> estadisticasSalones();
}
