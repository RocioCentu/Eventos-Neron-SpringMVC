package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Menu;
import ar.edu.unlam.tallerweb1.modelo.TipoDeMenu;

public interface RegistroPlatosMenuDao {
	
	void registraPlatosAlMenu (Menu menu);
	Menu traerMenuPorId(Long id);
	
	TipoDeMenu traerTipoDeMenuPorId(Long id);
}
