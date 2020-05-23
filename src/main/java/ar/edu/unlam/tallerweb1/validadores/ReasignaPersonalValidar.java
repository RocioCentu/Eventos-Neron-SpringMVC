package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.viewmodel.RegistroMenuViewModel;
import ar.edu.unlam.tallerweb1.viewmodel.RegistroReasignacionPersonalViewModel;

public class ReasignaPersonalValidar implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroReasignacionPersonalViewModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		ValidationUtils.rejectIfEmpty(errors, "idpersonal", "required.idpersonal", "Debe realizar una selección");
   
	}
}    