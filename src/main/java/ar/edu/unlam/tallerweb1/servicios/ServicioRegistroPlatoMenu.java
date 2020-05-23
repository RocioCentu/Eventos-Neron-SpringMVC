package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroIngresoMenuViewModel;

// Interface que define los metodos del Servicio de Regisatros de platos del Menu.
public interface ServicioRegistroPlatoMenu {

	void ingresarPlatosAlMenu(RegistroIngresoMenuViewModel vmIngresoMenu);

}
