package ar.edu.unlam.tallerweb1.validadores;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.unlam.tallerweb1.viewmodel.RegistroIngresoMenuViewModel;

public class PuntajeValidar implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistroIngresoMenuViewModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "puntaje", "required.puntaje", "Complete el campo puntaje por favor.");




    }
}