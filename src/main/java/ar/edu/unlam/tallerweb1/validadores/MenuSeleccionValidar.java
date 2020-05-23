package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;

public class MenuSeleccionValidar implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroMenuViewModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmpty(errors, "idmenu", "required.idmenu", "Debe realizar una selección");
   
	}
}    