
package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

// Interface que define los metodos del Servicio de Regisatros de platos del Menu.
public interface ServicioRegistroMenu {

	void ingresarMenuSeleccionado(Long id,Long[] idmenu);
    Double calcularPuntaje(Long id, Double puntaje);
}
