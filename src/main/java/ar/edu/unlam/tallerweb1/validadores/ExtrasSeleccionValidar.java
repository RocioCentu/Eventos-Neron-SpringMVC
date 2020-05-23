package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.modelo.Extra;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;


public class ExtrasSeleccionValidar implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Extra.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre", "El campo Descripcion es Obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "required.precio", "El campo Precio es Obligatorio.");
   
	}
}    