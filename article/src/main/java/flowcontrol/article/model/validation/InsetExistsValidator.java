package flowcontrol.article.model.validation;

import flowcontrol.article.model.validation.annotation.InsetExists;
import flowcontrol.article.service.InsetService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InsetExistsValidator implements ConstraintValidator<InsetExists, String> {

    @Autowired
    private InsetService insetService;

    @SneakyThrows
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null || value.equals("") || value.isBlank() || value.isEmpty()){
            return true;
        }

        if(value.equals("0")) return true;

        return insetService.isAlreadyPresentById(value);
    }
}
