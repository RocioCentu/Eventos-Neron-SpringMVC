package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.viewmodel.RegistroIngresoMenuViewModel;

public class IngresoExtrasValidar implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistroIngresoMenuViewModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "required.descripcion", "El campo Descripcion es Obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precio", "required.precio", "El campo Precio es Obligatorio.");
        
//        RegistroIngresoMenuViewModel menu=(RegistroIngresoMenuViewModel)target;
//       if (menu.getPrecio().isNaN()) {
//        }
//        else {
//        	 errors.rejectValue("precio", "invalid.precio","El valor ingresado debe ser numerico");
//        }

	}
}