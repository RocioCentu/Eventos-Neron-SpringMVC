
package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

// Interface que define los metodos del Servicio de Regisatros de platos del Menu.
public interface ServicioRegistroExtras {

	void ingresarExtrasSeleccionados(Long id,Long[] idmenu);

}
